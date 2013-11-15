Ext.onReady(function(){
    var bd = Ext.getBody();
    // 业务操作表单
    Ext.widget({
        xtype: 'form',
        layout: 'form',
        id: 'simpleForm',
        standardSubmit: true,
        frame: true,
        bodyPadding: '5 5 0',
        margin: '10 10 10 10',
        width: 600,
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 150
        },
        plugins: {
            ptype: 'datatip'
        },
        defaultType: 'textfield',
        url: getFormAction(),
        method: 'POST',
        title: operationTypeDesc() + '流贷',
        items: [getBizSideCombobox(), getBizSideTypeText(), {
            // 隐藏id列，用于修改
            name: 'id',
            hidden: true,
            value: (item != null ? item.id : 0)
        }, {
            xtype: 'numberfield',
            fieldLabel: '贷款余额',
            afterLabelTextTpl: required,
            name: 'amount',
            readOnly: !canModify("amount"),
            allowBlank: false,
            value: (item != null ? item.amount.amount : null),
            minValue: 0
        }, {
            fieldLabel: '发放日',
            name: 'releaseDate',
            readOnly: !canModify("releaseDate"),
            afterLabelTextTpl: required,
            xtype: 'datefield',
            format: 'Y-m-d',
            allowBlank: false,
            value: Ext.Date.format((item != null ? new Date(item.releaseDate.time) : null), 'Y-m-d')
        }, {
            fieldLabel: '到期日',
            name: 'expireDate',
            readOnly: !canModify("expireDate"),
            afterLabelTextTpl: required,
            xtype: 'datefield',
            format: 'Y-m-d',
            allowBlank: false,
            value: Ext.Date.format((item != null ? new Date(item.expireDate.time) : null), 'Y-m-d')
        }, getRepayedCombobox(), {
            fieldLabel: '当前可用额度(元)',
            id: 'showAvailableLimit',
            readOnly: true
        }],
        buttons: [{
            text: operationTypeDesc(),
            handler: function(){
                this.up('form').getForm().submit();
            },
            hidden: isReadOnly() == true ? true : false
        }, {
            text: '返回',
            handler: function(){
                window.history.back();
            }
        }]
    }).render(document.body);
    
});

function setSpeicalFields(type){
    if (type == "OPEN") {
        Ext.getCmp("openMoney").show();
        Ext.getCmp("closeMoney").show();
        Ext.getCmp("openMoney").allowBlank = false;
        Ext.getCmp("closeMoney").allowBlank = false;
    }
    else {
        Ext.getCmp("openMoney").hide();
        Ext.getCmp("closeMoney").hide();
        Ext.getCmp("openMoney").allowBlank = true;
        Ext.getCmp("closeMoney").allowBlank = true;
    }
}

/**
 * 区分不同业务方的下拉框，如企业和个人
 */
function getBizSideCombobox(){
    var bizSideType = getLoanBizSideType();
    if (bizSideType == "CORPORATE") {
        return {
            xtype: "combobox",
            fieldLabel: '贷款企业',
            afterLabelTextTpl: required,
            allowBlank: false,
            forceSelection: true,
            queryMode: 'remote',
            triggerAction: 'query',
            minChars: 0,
            listConfig: {
                getInnerTpl: function(df){
                    return '{[values.name.replace(this.field.getRawValue(), "<b>" + this.field.getRawValue() + "</b>")]}';
                }
            },
            name: 'loanerId',
            store: new Ext.data.Store({
                proxy: {
                    type: 'ajax',
                    url: getContextPath() + '/query/enterprise/fuzzyQuery.json',
                    reader: {
                        type: 'json',
                        totalProperty: 'totalItems',
                        root: 'items'
                    }
                },
                autoLoad: true,
                fields: [{
                    name: 'name'
                }, {
                    name: 'id'
                }, {
                    name: 'identifier'
                }]
            }),
            readOnly: !canModify(null),
            displayField: 'name',
            valueField: 'identifier',
            queryParam: 'fuzzyName',
            listeners: {
                render: function(c){
                    var bizSideId = (item != null ? item.loaner.identifier : 0);
                    this.getStore().on('load', function(){
                        if (c.getValue() == null) {
                            c.setValue(bizSideId);
                            // 显示企业可用额度
                            getAndDisplayAvailableLimit("Enterprise", bizSideId, "showAvailableLimit");
                        }
                    }, this); //初始化显示
                },
                select: function(){
                    getAndDisplayAvailableLimit("Enterprise", this.getValue(), "showAvailableLimit");
                }
            },
        };
    }
    else 
        if (bizSideType == "PRIVATE") {
            return {
                xtype: "combobox",
                fieldLabel: '贷款人',
                afterLabelTextTpl: required,
                allowBlank: false,
                forceSelection: true,
                queryMode: 'remote',
                triggerAction: 'query',
                minChars: 0,
                listConfig: {
                    getInnerTpl: function(df){
                        return '{[values.name.replace(this.field.getRawValue(), "<b>" + this.field.getRawValue() + "</b>")]}';
                    }
                },
                name: 'loanerId',
                store: new Ext.data.Store({
                    proxy: {
                        type: 'ajax',
                        url: getContextPath() + '/query/person/fuzzyQuery.json',
                        reader: {
                            type: 'json',
                            totalProperty: 'totalItems',
                            root: 'items'
                        }
                    },
                    autoLoad: true,
                    fields: [{
                        name: 'name'
                    }, {
                        name: 'id'
                    }, {
                        name: 'identifier'
                    }]
                }),
                readOnly: !canModify(null),
                displayField: 'name',
                valueField: 'identifier',
                queryParam: 'fuzzyName',
                listeners: {
                    render: function(c){
                        var bizSideId = (item != null ? item.loaner.identifier : 0);
                        this.getStore().on('load', function(){
                            if (c.getValue() == null) {
                                c.setValue(bizSideId);
                                // 显示可用额度
                                getAndDisplayAvailableLimit("Person", bizSideId, "showAvailableLimit");
                            }
                        }, this); //初始化显示
                    },
                    select: function(){
                        getAndDisplayAvailableLimit("Person", this.getValue(), "showAvailableLimit");
                    }
                },
            };
        }
}

function getBizSideTypeText(){
    return {
        // 隐藏id列，用于修改
        name: 'bizSideType',
        hidden: true,
        value: getLoanBizSideType()
    };
}

/**
 *
 */
function getRepayedCombobox(){
    var type = operationType();
    if (type == "UPDATE" || type == "QUERY") {
        return {
            xtype: "combobox",
            fieldLabel: '还款完毕',
            afterLabelTextTpl: required,
            allowBlank: false,
            forceSelection: true,
            width: 500,
            labelWidth: 130,
            id: "hasRepayedCombo",
            name: 'hasRepayed',
            readOnly: !canModify("hasRepayed"),
            displayField: 'desc',
            valueField: 'code',
            store: Ext.create('Ext.data.Store', {
                mode: 'local',
                data: [{
                    'code': 'true',
                    'desc': '是'
                }, {
                    'code': 'false',
                    'desc': '否'
                }],
                fields: [{
                    name: 'code'
                }, {
                    name: 'desc'
                }]
            }),
            queryMode: 'local',
            listeners: {
                render: function(c){
                    if (c.getValue() == null) {
                        c.setValue((item != null ? item.hasRepayed + "" : null));
                    }
                }
            }
        }
    }
    return null;
}

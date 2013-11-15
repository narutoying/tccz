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
        title: operationTypeDesc() + '银票',
        items: [{
            // 隐藏id列，用于修改
            name: 'id',
            hidden: true,
            value: (item != null ? item.id : 0)
        }, {
            fieldLabel: '银票号',
            name: 'bandarNoteNumber',
            afterLabelTextTpl: required,
            allowBlank: false,
            readOnly: !canModify("bandarNoteNumber"),
            value: (item != null ? item.number : null)
        }, {
            xtype: "combobox",
            fieldLabel: '银票类型',
            afterLabelTextTpl: required,
            allowBlank: false,
            forceSelection: true,
            width: 500,
            labelWidth: 130,
            id: "typeCombo",
            name: 'type',
            readOnly: !canModify("type"),
            displayField: 'desc',
            valueField: 'code',
            store: Ext.create('Ext.data.Store', {
                mode: 'local',
                data: getTypes(),
                fields: [{
                    name: 'code'
                }, {
                    name: 'desc'
                }],
                autoLoad: true
            }),
            queryMode: 'local',
            listeners: {
                render: function(c){
                    if (c.getValue() == null) {
                        c.setValue((item != null ? item.type : null));
                    }
                    setSpeicalFields(c.getValue());
                },
                select: function(){
                    setSpeicalFields(this.getValue());
                }
            }
        }, {
            xtype: "combobox",
            fieldLabel: '出票企业',
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
            name: 'drawerId',
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
                    var bizSideId = (item != null ? item.drawer.identifier : 0);
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
        }, {
            fieldLabel: '出票日',
            name: 'drawDate',
            readOnly: !canModify("drawDate"),
            afterLabelTextTpl: required,
            xtype: 'datefield',
            format: 'Y-m-d',
            allowBlank: false,
            value: Ext.Date.format((item != null ? new Date(item.drawDate.time) : null), 'Y-m-d')
        }, {
            fieldLabel: '到期日',
            name: 'expireDate',
            readOnly: !canModify("expireDate"),
            afterLabelTextTpl: required,
            xtype: 'datefield',
            format: 'Y-m-d',
            allowBlank: false,
            value: Ext.Date.format((item != null ? new Date(item.expireDate.time) : null), 'Y-m-d')
        }, {
            xtype: 'numberfield',
            fieldLabel: '金额',
            afterLabelTextTpl: required,
            name: 'amount',
            readOnly: !canModify("amount"),
            allowBlank: false,
            value: (item != null ? item.amount.amount : null),
            minValue: 0
        }, {
            xtype: 'numberfield',
            fieldLabel: '保证金',
            afterLabelTextTpl: required,
            name: 'marginMoney',
            readOnly: !canModify("marginMoney"),
            allowBlank: false,
            value: (item != null ? item.margin.amount : null),
            minValue: 0
        }, {
            xtype: 'numberfield',
            fieldLabel: '敞口金额',// 敞口类型银票专有 
            afterLabelTextTpl: required,
            id: 'openMoney',
            name: 'openMoney',
            readOnly: !canModify("openMoney"),
            value: (item != null && item.type == "OPEN" ? item.openMoney.amount : null),
            minValue: 0,
            hidden: true
        }, {
            xtype: 'numberfield',
            fieldLabel: '封敞口金额',// 敞口类型银票专有
            afterLabelTextTpl: required,
            id: 'closeMoney',
            name: 'closeMoney',
            readOnly: !canModify("closeMoney"),
            value: (item != null && item.type == "OPEN" ? item.closeMoney.amount : null),
            minValue: 0,
            hidden: true
        }, {
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

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
        title: operationTypeDesc() + '贴现',
        items: [{
            // 隐藏id列，用于修改
            name: 'id',
            hidden: true,
            value: (item != null ? item.id : 0)
        }, {
            xtype: "combobox",
            fieldLabel: '申请企业',
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
            name: 'proposerId',
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
                }]
            }),
            readOnly: isReadOnly(),
            displayField: 'name',
            valueField: 'id',
            queryParam: 'fuzzyName',
            listeners: {
                render: function(c){
                    var bizSideId = (item != null ? item.proposer.id : 0);
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
            fieldLabel: '到期日',
            name: 'expireDate',
            readOnly: isReadOnly(),
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
            readOnly: !isAdd(),
            allowBlank: false,
            value: (item != null ? item.amount.amount : null),
            minValue: 0
        }, {
            xtype: "combobox",
            fieldLabel: '状态',
            afterLabelTextTpl: required,
            allowBlank: false,
            forceSelection: true,
            width: 500,
            labelWidth: 130,
            id: "stateCombo",
            name: 'state',
            readOnly: isReadOnly(),
            displayField: 'stateDesc',
            valueField: 'stateCode',
            store: Ext.create('Ext.data.Store', {
                mode: 'local',
                data: getStates(),
                fields: [{
                    name: 'stateCode'
                }, {
                    name: 'stateDesc'
                }],
                autoLoad: true
            }),
            queryMode: 'local',
            listeners: {
                render: function(c){
                    if (c.getValue() == null) {
                        c.setValue((item != null ? item.state : null));
                    }
                }
            }
        }, {
            fieldLabel: '银票号',
            name: 'bandarNoteNumber',
            readOnly: isReadOnly(),
            value: (item != null ? item.bandarNoteNumber : null)
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
    
    // 贴现变更历史表格
    if (getDiscountChange().length > 0) {
        var grid = Ext.create('Ext.grid.Panel', {
            width: 600,
            margin: '10 10 10 10',
            title: '贴现变更历史',
            store: Ext.create('Ext.data.Store', {
                mode: 'local',
                data: getDiscountChange(),
                fields: [{
                    name: 'createTimeStr'
                }, {
                    name: 'stateDesc'
                }]
            }),
            disableSelection: true,
            loadMask: true,
            viewConfig: {
                trackOver: false,
                stripeRows: false,
                plugins: [{
                    ptype: 'preview',
                    bodyField: 'excerpt',
                    expanded: true,
                    pluginId: 'preview'
                }]
            },
            // grid columns
            columns: [{
                text: "变更状态",
                dataIndex: 'stateDesc',
                sortable: false,
                flex: 1
            }, {
                text: "变更时间",
                dataIndex: 'createTimeStr',
                sortable: false,
                flex: 2
            }],
            renderTo: document.body
        });
    }
    
});

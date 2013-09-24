Ext.onReady(function(){
    Ext.QuickTips.init();
    var bd = Ext.getBody();
    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
    /*
     * 企业相关
     */
    var enterpriseStore = new Ext.data.Store({
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
    });
    var states = [{
        'stateCode': 'IN_STORE',
        'stateDesc': '在库'
    }];
    /*
     * 状态相关
     */
    var stateStore = Ext.create('Ext.data.Store', {
        mode: 'local',
        data: getStates(),
        fields: [{
            name: 'stateCode'
        }, {
            name: 'stateDesc'
        }],
        autoLoad: true
    });
    // 贴现操作表单
    Ext.widget({
        xtype: 'form',
        layout: 'form',
        id: 'simpleForm',
        url: getFormAction(),
        method: 'POST',
        standardSubmit: true,
        frame: true,
        title: operationTypeDesc() + '贴现',
        bodyPadding: '5 5 0',
        margin: '10 10 10 10',
        width: 350,
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 75
        },
        plugins: {
            ptype: 'datatip'
        },
        defaultType: 'textfield',
        items: [{
            name: 'id',
            hidden: true,
            value: getItemId()
        }, {
            xtype: "combobox",
            name: 'proposerId',
            fieldLabel: '申请企业',
            store: enterpriseStore,
            afterLabelTextTpl: required,
            allowBlank: false,
            readOnly: isReadOnly(),
            forceSelection: true,
            displayField: 'name',
            valueField: 'id',
            queryParam: 'fuzzyName',
            queryMode: 'remote',
            triggerAction: 'query',
            minChars: 0,
            listConfig: {
                getInnerTpl: function(df){
                    return '{[values.name.replace(this.field.getRawValue(), "<b>" + this.field.getRawValue() + "</b>")]}';
                }
            },
            listeners: {
                render: function(c){
                    this.getStore().on('load', function(){
                        if (c.getValue() == null) {
                            c.setValue(discount_proposerId);
                        }
                    }, this); //初始化显示
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
            value: Ext.Date.parse(discount_expireDate, 'Y-m-d')
        }, {
            xtype: 'numberfield',
            fieldLabel: '金额',
            afterLabelTextTpl: required,
            name: 'amount',
            readOnly: !isAdd(),
            allowBlank: false,
            value: discount_amount,
            minValue: 0
        }, {
            xtype: "combobox",
            id: "stateCombo",
            fieldLabel: '状态',
            afterLabelTextTpl: required,
            name: 'state',
            readOnly: isReadOnly(),
            allowBlank: false,
            forceSelection: true,
            displayField: 'stateDesc',
            valueField: 'stateCode',
            width: 500,
            labelWidth: 130,
            store: stateStore,
            queryMode: 'local',
            listeners: {
                render: function(c){
                    if (c.getValue() == null) {
                        c.setValue(discount_state);
                    }
                }
            }
        }, {
            fieldLabel: '银票号',
            name: 'bandarNoteNumber',
            readOnly: isReadOnly(),
            value: discount_bandarNoteNumber
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
    
    var discountChangeStore = Ext.create('Ext.data.Store', {
        mode: 'local',
        data: getDiscountChange(),
        fields: [{
            name: 'createTimeStr'
        }, {
            name: 'stateDesc'
        }]
    });
    
    if (getDiscountChange().length > 0) {
        var grid = Ext.create('Ext.grid.Panel', {
            width: 400,
            margin: '10 10 10 10',
            title: '贴现变更历史',
            store: discountChangeStore,
            disableSelection: true,
            loadMask: true,
            viewConfig: {
                id: 'gv',
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

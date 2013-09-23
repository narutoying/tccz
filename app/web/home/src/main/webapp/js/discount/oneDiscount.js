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
        data: getStates(),
        fields: [{
            name: 'stateCode'
        }, {
            name: 'stateDesc'
        }]
    });
    
    Ext.widget({
        xtype: 'form',
        layout: 'form',
        id: 'simpleForm',
        url: 'add.htm',
        method: 'POST',
        standardSubmit: true,
        frame: true,
        title: operationTypeDesc() + '贴现',
        bodyPadding: '5 5 0',
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
                select: function(){
                }
            }
        }, {
            fieldLabel: '到期日',
            name: 'expireDate',
            afterLabelTextTpl: required,
            xtype: 'datefield',
            format: 'Y-m-d',
            allowBlank: false
        }, {
            fieldLabel: '金额',
            afterLabelTextTpl: required,
            name: 'amount',
            allowBlank: false
        }, {
            xtype: "combobox",
            fieldLabel: '状态',
            afterLabelTextTpl: required,
            name: 'state',
            allowBlank: false,
            forceSelection: true,
            displayField: 'stateDesc',
            valueField: 'stateCode',
            width: 500,
            labelWidth: 130,
            store: stateStore,
            queryMode: 'local',
            typeAhead: true
        }, {
            fieldLabel: '银票号',
            name: 'bandarNoteNumber'
        }],
        buttons: [{
            text: operationTypeDesc(),
            handler: function(){
                this.up('form').getForm().submit();
            }
        }, {
            text: '返回',
            handler: function(){
                window.history.back();
            }
        }]
    }).render(document.body);
});

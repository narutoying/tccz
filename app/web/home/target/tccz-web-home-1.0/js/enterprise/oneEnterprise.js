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
        title: operationTypeDesc() + '企业',
        items: [{
            fieldLabel: '企业名',
            afterLabelTextTpl: required,
            name: 'name',
            readOnly: !canModify("name"),
            allowBlank: false,
            value: (item != null ? item.name : null),
            minValue: 0
        }, {
            fieldLabel: '机构代码',
            afterLabelTextTpl: required,
            name: 'institutionCode',
            readOnly: !canModify("institutionCode"),
            allowBlank: false,
            value: (item != null ? item.institutionCode : null),
            minValue: 0
        }, {
            xtype: "combobox",
            fieldLabel: '企业法人',
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
            name: 'legalPersonId',
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
            readOnly: !canModify("legalPersonId"),
            displayField: 'name',
            valueField: 'identifier',
            queryParam: 'fuzzyName',
            listeners: {
                render: function(c){
                    var bizSideId = (item != null ? item.legalPerson.identifier : 0);
                    this.getStore().on('load', function(){
                        if (c.getValue() == null) {
                            c.setValue(bizSideId);
                        }
                    }, this); //初始化显示
                }
            }
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
                window.location.href = getContextPath() + "/query/enterprise/index.htm";
            }
        }]
    }).render(document.body);
    
});

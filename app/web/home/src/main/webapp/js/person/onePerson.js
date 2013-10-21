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
        title: operationTypeDesc() + '个人',
        items: [{
            fieldLabel: '个人名',
            afterLabelTextTpl: required,
            name: 'name',
            readOnly: !canModify("name"),
            allowBlank: false,
            value: (item != null ? item.name : null),
            minValue: 0
        }, {
            fieldLabel: '身份证号',
            afterLabelTextTpl: required,
            name: 'idCardNumber',
            readOnly: !canModify("idCardNumber"),
            allowBlank: false,
            value: (item != null ? item.idCardNumber : null),
            minValue: 0
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
                window.location.href = getContextPath() + "/query/person/index.htm";
            }
        }]
    }).render(document.body);
    
});

Ext.onReady(function(){
    // 业务模型定义
    Ext.define('BandarNote', {
        extend: 'Ext.data.Model',
        fields: ['bandarNoteNumber', 'drawer', 'amount', {
            name: 'expireDate',
            mapping: 'expireDate',
            type: 'date',
            dateFormat: 'Ymd'
        }, {
            name: 'drawDate',
            mapping: 'drawDate',
            type: 'date',
            dateFormat: 'Ymd'
        }, 'type', {
            name: 'id',
            mapping: 'id',
            type: 'int'
        }]
    });
    
    // 创建业务数据Store
    var store = Ext.create('Ext.data.Store', {
        model: 'BandarNote',
        proxy: {
            type: 'ajax',
            url: getContextPath() + '/query/bandarnote/index.json',
            reader: {
                type: 'json',
                root: 'items',
                totalProperty: 'totalCount'
            }
        },
        pageSize: 10 // 每页显示数据条目
    });
    // 渲染查询表单
    var bd = Ext.getBody();
    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
    var simple = Ext.widget({
        xtype: 'form',
        layout: 'form',
        bodyPadding: '5 5 0',
        width: 350,
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 75
        },
        plugins: {
            ptype: 'datatip'
        },
        frame: true,
        defaultType: 'textfield',
        title: '银票查询',
        items: [{
            fieldLabel: '企业名',
            id: 'enterpriseName',
            name: 'enterpriseName',
            allowBlank: true,
        }, {
            fieldLabel: '银票号',
            id: 'number',
            name: 'number',
            allowBlank: true,
        }, {
            xtype: "combobox",
            fieldLabel: '银票类型',
            allowBlank: true,
            forceSelection: true,
            width: 500,
            labelWidth: 130,
            id: "type",
            name: 'type',
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
            queryMode: 'local'
        }, {
            xtype: 'checkbox',
            boxLabel: '显示已到期',
            id: 'showExpire',
            name: 'showExpire'
        }],
        buttons: [{
            xtype: 'component',
            autoEl: {
                tag: 'a',
                href: getContextPath() + '/update/bandarnote/add.htm',
                html: '新增银票'
            }
        }, {
            text: '查询',
            handler: function(){
                store.getProxy().setExtraParam("number", Ext.getCmp("number").getValue());
                store.getProxy().setExtraParam("enterpriseName", Ext.getCmp("enterpriseName").getValue());
                store.getProxy().setExtraParam("type", Ext.getCmp("type").getValue());
                store.getProxy().setExtraParam("showExpire", Ext.getCmp("showExpire").getValue());
                store.loadPage(1);
            }
        }],
        margin: '10 10 10 10'
    });
    simple.render(Ext.get("queryForm"));
    
    // 渲染分页表格
    var grid = Ext.create('Ext.grid.Panel', {
        width: 900,
        margin: '10 10 10 10',
        store: store,
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
        renderTo: 'queryResult',
        title: '银票总览',
        // 表格列
        columns: [{
            dataIndex: 'bandarNoteNumber',
            text: "银票号",
            flex: 2,
            sortable: false
        }, {
            dataIndex: 'drawer',
            text: "出票企业",
            flex: 2,
            sortable: false
        }, {
            dataIndex: 'type',
            text: "银票类型",
            flex: 2,
            sortable: false
        }, {
            dataIndex: 'amount',
            text: "金额",
            flex: 2,
            sortable: false
        }, {
            dataIndex: 'drawDate',
            text: "出票日",
            flex: 2,
            sortable: false,
            renderer: function(value, p, r){
                return Ext.Date.format(value, 'Y-m-d');
            }
        }, {
            dataIndex: 'expireDate',
            text: "到期日",
            flex: 2,
            sortable: false,
            renderer: function(value, p, r){
                p.tdAttr = 'data-qtip="1. <font style=\'color:red\'>标红：</font>过期日期<当前日期<br/>2. <font style=\'color:blue\'>标蓝：</font>过期日期=当前日期<br/>3. <font style=\'color:green\'>标绿：</font>过期日期>当前日期"';
                /*
                 * 1. 标红：过期日期<当前日期
                 * 2. 标蓝：过期日期=当前日期
                 * 3. 标绿：过期日期>当前日期
                 */
                var now = new Date();
                var valueStr = Ext.Date.format(value, 'Y-m-d');
                var nowStr = Ext.Date.format(now, 'Y-m-d');
                var color;
                if (valueStr == nowStr) {
                    color = "blue";
                }
                else {
                    if (value.getTime() > now.getTime()) {
                        color = "green";
                    }
                    else {
                        color = "red";
                    }
                }
                return "<font style='color:" + color + "'>" + valueStr + "</font>";
            }
        }, {
            dataIndex: 'id',
            flex: 3,
            text: "操作",
            renderer: function(value, p, r){
                return buildButton(value, "查看", "/query/bandarnote/view.htm") +
                //                buildButton(value, "修改", "/update/bandarnote/modify.htm") +
                buildButton(value, "删除", "/update/bandarnote/delete.htm", {
                    text: "确认删除此条记录吗？"
                });
            },
            sortable: false
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            emptyMsg: "无数据显示",
        })
    });
    // 默认显示第一页
    store.loadPage(1);
});

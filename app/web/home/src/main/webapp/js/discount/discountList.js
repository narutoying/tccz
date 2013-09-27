Ext.onReady(function(){
    Ext.tip.QuickTipManager.init();
    Ext.QuickTips.init();
    
    Ext.define('Discount', {
        extend: 'Ext.data.Model',
        fields: ['bandarNoteNumber', 'proposer', 'amount', {
            name: 'expireDate',
            mapping: 'expireDate',
            type: 'date',
            dateFormat: 'Ymd'
        }, 'state', {
            name: 'id',
            mapping: 'id',
            type: 'int'
        }],
        idProperty: 'threadid'
    });
    // create the Data Store
    var store = Ext.create('Ext.data.Store', {
        model: 'Discount',
        remoteSort: true,
        proxy: {
            type: 'ajax',
            url: getContextPath() + '/query/discount/index.json',
            reader: {
                type: 'json',
                root: 'items',
                totalProperty: 'totalCount'
            }
        },
        pageSize: 6
    });
    // 渲染查询表单
    var bd = Ext.getBody();
    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
    var simple = Ext.widget({
        xtype: 'form',
        layout: 'form',
        frame: true,
        title: '贴现查询',
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
            fieldLabel: '企业名',
            id: 'enterpriseName',
            name: 'enterpriseName',
            allowBlank: true,
            tooltip: '请输入企业名'
        }, {
            fieldLabel: '银票号',
            id: 'bandarNoteNumber',
            name: 'bandarNoteNumber',
            allowBlank: true,
            tooltip: '请输入银票号'
        }, {
            xtype: 'checkbox',
            boxLabel: '显示已到期',
            id: 'showExpire',
            name: 'showExpire',
            inputValue: 'showExpire'
        }],
        buttons: [{
            xtype: 'component',
            autoEl: {
                tag: 'a',
                href: getContextPath() + '/update/discount/add.htm',
                html: '新增贴现'
            }
        }, {
            text: '查询',
            handler: function(){
                store.getProxy().setExtraParam("bandarNoteNumber", Ext.getCmp("bandarNoteNumber").getValue());
                store.getProxy().setExtraParam("enterpriseName", Ext.getCmp("enterpriseName").getValue());
                var showExpire = Ext.getCmp("showExpire").getValue();
                store.getProxy().setExtraParam("showExpire", showExpire);
                store.loadPage(1);
            }
        }],
        margin: '10 10 10 10'
    });
    simple.render(Ext.get("queryForm"));
    
    var pluginExpanded = true;
    var grid = Ext.create('Ext.grid.Panel', {
        width: 700,
        margin: '10 10 10 10',
        title: '贴现总览',
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
        // grid columns
        columns: [{
            id: 'proposer',
            text: "申请人",
            dataIndex: 'proposer',
            flex: 1,
            sortable: false
        }, {
            id: 'amount',
            text: "金额",
            dataIndex: 'amount',
            flex: 1.5,
            sortable: false
        }, {
            id: 'expireDate',
            text: "到期日期",
            dataIndex: 'expireDate',
            flex: 2,
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
            },
            sortable: false
        }, {
            id: 'state',
            text: "当前状态",
            dataIndex: 'state',
            flex: 2,
            sortable: false
        }, {
            id: 'bandarNoteNumberCol',
            text: "银票号",
            dataIndex: 'bandarNoteNumber',
            flex: 2,
            sortable: false
        }, {
            dataIndex: 'id',
            flex: 3,
            text: "操作",
            renderer: function(value, p, r){
                return buildButton(value, "查看", "/query/discount/view.htm") +
                buildButton(value, "修改", "/update/discount/modify.htm") +
                buildButton(value, "删除", "/update/discount/delete.htm", {
                    text: "确认删除此条记录吗？"
                });
            },
            sortable: false
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            emptyMsg: "无数据显示",
            items: ['-']
        }),
        renderTo: 'queryResult'
    });
    
    store.loadPage(1);
    
});

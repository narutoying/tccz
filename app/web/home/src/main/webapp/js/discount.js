Ext.Loader.setConfig({
    enabled: true
});

Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.toolbar.Paging', 'Ext.ux.PreviewPlugin', 'Ext.ModelManager', 'Ext.tip.QuickTipManager']);

Ext.onReady(function(){
    Ext.tip.QuickTipManager.init();
    
    Ext.define('Discount', {
        extend: 'Ext.data.Model',
        fields: ['bandarNoteNumber', 'proposer', 'amount', {
            name: 'expireDate',
            mapping: 'expireDate',
            type: 'date',
            dateFormat: 'Ymd'
        }, 'state'],
        idProperty: 'threadid'
    });
    
    // create the Data Store
    var store = Ext.create('Ext.data.Store', {
        pageSize: 20,
        model: 'Discount',
        remoteSort: true,
        proxy: {
            type: 'ajax',
            url: getContextPath() + '/query/discount/index.json',
            reader: {
                type: 'json',
                root: 'items'
            }
        },
    });
    
    var pluginExpanded = true;
    var grid = Ext.create('Ext.grid.Panel', {
        width: 700,
        height: 500,
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
            id: 'bandarNoteNumber',
            text: "银票号",
            dataIndex: 'bandarNoteNumber',
            flex: 1
        }, {
            id: 'proposer',
            text: "申请人",
            dataIndex: 'proposer'
        }, {
            id: 'amount',
            text: "金额",
            dataIndex: 'amount'
        }, {
            id: 'expireDate',
            text: "到期日期",
            dataIndex: 'expireDate',
            renderer: function(value, p, r){
                return Ext.Date.format(value, 'Y-m-d');
            }
        }, {
            id: 'state',
            text: "当前状态",
            dataIndex: 'state'
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            emptyMsg: "无数据显示",
            items: ['-']
        }),
        renderTo: 'topic-grid'
    });
    
    store.loadPage(1);
    
});

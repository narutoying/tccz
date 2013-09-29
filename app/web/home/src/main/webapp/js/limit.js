function getAndDisplayAvailableLimit(bizSideType, bizSideId, renderTo){
    var result = 0;
    var url = null;
    if (bizSideType == "Person") {
    
    }
    else 
        if (bizSideType == "Enterprise") {
            url = getContextPath() + "/query/limit/queryForEnterprise.json";
        }
    Ext.Ajax.request({
        url: url,
        method: 'GET',
        params: {
            id: bizSideId
        },
        success: function(response){
            var resObj = JSON.parse(response.responseText);
            var amount = resObj.money.amount;
            var renderToCmp = Ext.getCmp(renderTo);
            renderToCmp.setValue(amount);
            var color = "green";
            if (amount >= 2000000) {
            }
            else {
                color = "red";
            }
            renderToCmp.setFieldStyle({
                color: color
            });
        }
    });
}

function getAndDisplayAvailableLimit(bizSideType, bizSideId, renderTo){
    var result = 0;
    var url = null;
    if (bizSideType == "Person") {
        url = getContextPath() + "/query/limit/queryForPerson.json";
    }
    else 
        if (bizSideType == "Enterprise") {
            url = getContextPath() + "/query/limit/queryForEnterprise.json";
        }
    if (bizSideId <= 0) {
        //        console.log("查询业务方额度id[" + bizSideId + "]不大于0");
    }
    else {
        Ext.Ajax.request({
            url: url,
            method: 'GET',
            params: {
                identifier: bizSideId
            },
            success: function(response){
                var resObj = JSON.parse(response.responseText);
                var amount = resObj.amount;
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
}

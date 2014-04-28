
BloodBank =
{
		issueBlood : function(bloodStockId,patientId){
			if(SESSION.checkSession()){
				url = "issueBlood.form?bloodStockId="+bloodStockId+"&patientId="+patientId+"&keepThis=false&TB_iframe=true&height=200&width=450";
				tb_show("Issue Blood...",url,false);
			}
		
		},
		getBloodStocks : function(thiz,patientId)
		{
			var bloodGroup = jQuery(thiz).val();
			var patientId = patientId;
			if(bloodGroup != null && bloodGroup != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "getBloodStocksByBloodGroup.form"
								,data: ({bloodGroupId :bloodGroup, patientId : patientId})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#availableBloodStocks").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},		
		clearBloodReceipt : function(receiptId){
			ACT.go("clearBloodStockReceipt.form?receiptId="+receiptId);
		
		}
}
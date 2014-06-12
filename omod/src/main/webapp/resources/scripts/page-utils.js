

RECEIPT={
			printDiv : function ()
		{
		  	jQuery("div#printDiv").printArea({mode:"popup",popClose:true,popTitle: "Support by HISP india(hispindia.org)"});
		},
		receiptSlip : function(receiptId){
					var total = jQuery("#totalStocks").val();
					var data="";
					for(var i=1;i<=	total;i++){
					
					var bloodGroup = 	jQuery('#bloodGroup_'+i).val();
					var product = 	jQuery('#product_'+i).val();
					var dateOfReceipt = 	jQuery('#dateOfReceipt_'+i).val();
					var dateOfExpiry = 	jQuery('#dateOfExpiry_'+i).val();
					var donorName = 	jQuery('#donorName_'+i).val();
					var packNo = 	jQuery('#packNo_'+i).val();
					data=data+"&bloodGroup_"+i+"="+bloodGroup+"&product_"+i+"="+product+"&dateOfReceipt_"+i+"="+dateOfReceipt+"&dateOfExpiry_"+i+"="+dateOfExpiry+"&donorName_"+i+"="+donorName+"&packNo_"+i+"="+packNo;
					
					}
					if(SESSION.checkSession()){
						url = "addBloodStockReceiptDescription.form?total="+total+"&keepThis=false"+data+"&TB_iframe=true&height=200&width=450";
						tb_show("Add description for this Slip",url,false);
					}
				
		},
		receiptSlipItem : function(action){
			if(action == 0){
				if(SESSION.checkSession()){
					url = "itemAddDescriptionReceiptSlip.form?action="+action+"&keepThis=false&TB_iframe=true&height=200&width=450";
					tb_show("Add description for this slip....",url,false);
				}
			}else{
				if( confirm("Are you sure you want to clear this slip?")){
					ACT.go("itemClearSlip.form?action="+action);
				}
			}
	}
		
};
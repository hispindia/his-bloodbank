

RECEIPT={
			printDiv : function ()
		{
		  	jQuery("div#printDiv").printArea({mode:"popup",popClose:true,popTitle: "Support by HISP india(hispindia.org)"});
		},
		receiptSlip : function(receiptId){
					if(SESSION.checkSession()){
						url = "addBloodStockReceiptDescription.form?receiptId="+receiptId+"&keepThis=false&TB_iframe=true&height=200&width=450";
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

INVENTORY={
		checkValue : function()
		{
			var form = jQuery("#form");
			if( jQuery("input[type='checkbox']:checked",form).length > 0 )
			{ 
				if(confirm("Are you sure?"))
				{
					form.submit();
				}
			}
			else
			{
				alert("Please choose objects for deleting");
				return false;
			}
		},
		search : function(url, value){
			ACT.go(url+"?"+value+"="+jQuery("#"+value).val());
		},
		checkValueExt : function(thiz, value)
		{
			if(parseInt(jQuery(thiz).val()) > parseInt(value)){
				alert('Issue quantity is greater that available quantity!');
				jQuery(thiz).val("");
				jQuery(thiz).focus();
			}
		},
		removeObject : function(position, check)
		{
			if(confirm("Are you want to remove this?")){
				ACT.go("removeObjectFromList.form?position="+position+"&check="+check);
			}
		},
		initTableHover : function()
		{
			jQuery("tr").each(function(){
				if( jQuery(this).hasClass("evenRow") || jQuery(this).hasClass("oddRow") )
				{
					jQuery(this).hover(
							function(){jQuery(this).addClass("hover");},
							function(){jQuery(this).removeClass("hover");}
							);
				}
			});
		}
};
ITEM={
		listSubCatByCat : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "subCatByCat.form"
								,data: ({categoryId :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divSubCat").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onChangeAttribute : function(thiz)
		{
			if(jQuery(thiz).val() == 1){
				if(jQuery("#reorderQty").val()==0){
					jQuery("#reorderQty").val("");
				}
				jQuery(".depentOnAttribute").show();
			}else{
				jQuery("#reorderQty").val(0);
				jQuery(".depentOnAttribute").hide();
			}
		},
		searchItem : function(thiz)
		{
			var itemName = jQuery("#searchName").val();
			var categoryId = jQuery("#categoryId").val();
			ACT.go("itemList.form?categoryId="+categoryId+"&searchName="+itemName);
		}
	};
DRUG = {
		onChangeAttribute : function(thiz)
		{
			if(jQuery(thiz).val() == 1){
				if(jQuery("#reorderQty").val()==0){
					jQuery("#reorderQty").val("");
				}
				jQuery(".depentOnAttribute").show();
			}else{
				jQuery("#reorderQty").val(0);
				jQuery(".depentOnAttribute").hide();
			}
		},
		searchDrug : function(thiz)
		{
			var drugName = jQuery("#searchName").val();
			var categoryId = jQuery("#categoryId").val();
			ACT.go("drugList.form?categoryId="+categoryId+"&searchName="+drugName);
		}
};
STORE={
		
		goToAdd : function()
		{
			ACT.go('store.form');	
		},
		edit : function(id)
		{
			ACT.go('store.form?storeId='+id);
		}	
};
RECEIPT={
		onChangeCategory : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "drugByCategory.form"
								,data: ({categoryId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divDrug").html(data);
						jQuery("#drugName").hide();
						jQuery("#drugName").val("");
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		checkVAT : function(thiz)
		{
			var VAT = jQuery(thiz).val();
			if(VAT != undefined && VAT != '' && VAT != null){
				if(VAT.indexOf('-') != -1){
					alert('VAT cant be negative number');
					jQuery(thiz).val("");
					jQuery(thiz).focus();
				}
			}
		},
		detailReceiptDrug : function(receiptId)
		{
			if(SESSION.checkSession()){
				url = "drugReceiptDetail.form?receiptId="+receiptId+"&keepThis=false&TB_iframe=true&height=500&width=1000";
				tb_show("Detail receipt drug....",url,false);
			}
		},
		detailReceiptItem : function(receiptId)
		{
			if(SESSION.checkSession()){
				url = "itemReceiptDetail.form?receiptId="+receiptId+"&keepThis=false&TB_iframe=true&height=500&width=1000";
				tb_show("Detail receipt item....",url,false);
			}
		},
		onChangeCategoryItem : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "itemBySubCategory.form"
								,data: ({categoryId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divItem").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlurItem : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "specificationByItem.form"
								,data: ({itemId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divSpecification").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlur : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "formulationByDrugName.form"
								,data: ({drugName: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divFormulation").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlurDrug : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "formulationByDrug.form"
								,data: ({drugId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divFormulation").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		printDiv : function ()
		{
		  	jQuery("div#printDiv").printArea({mode:"popup",popClose:true,popTitle: "Support by HISP india(hispindia.org)"});
		},
		receiptSlip : function(receiptId){
					if(SESSION.checkSession()){
						url = "addBloodStockReceiptDescription.form?receiptId="+receiptId+"&keepThis=false&TB_iframe=true&height=200&width=450";
						tb_show("Add description for this slip....",url,false);
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
PURCHASE={
		onChangeCategory : function(thiz)
		{
			ACT.go('purchaseOrderForGeneralStore.form?categoryId='+jQuery(thiz).val());
		},
		onChangeCategoryItem : function(thiz)
		{
			ACT.go('itemPurchaseOrderForGeneralStore.form?categoryId='+jQuery(thiz).val());
		},
		onBlur : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "formulationByDrug.form"
								,data: ({drugId :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divFormulation").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlurItem : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "specificationByItem.form"
								,data: ({itemId :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divSpecification").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		printDiv : function ()
		{
		  	jQuery("div#printDiv").printArea({mode:"popup",popClose:true,popTitle: "Support by HISP india(hispindia.org)"});
		},
		processSlip : function(action){
				if(action == 0){
					if(SESSION.checkSession()){
						url = "addNameForPurchaseOrderSlip.form?action="+action+"&keepThis=false&TB_iframe=true&height=200&width=450";
						tb_show("Add name for purchase order slip....",url,false);
					}
				}else{
					if( confirm("Are you sure you want to clear this?")){
						ACT.go("clearPurchaseOrder.form?action="+action);
					}
				}
		},
		processSlipItem : function(action){
			if(action == 0){
				if(SESSION.checkSession()){
					url = "itemAddNameForPurchaseOrderSlip.form?action="+action+"&keepThis=false&TB_iframe=true&height=200&width=450";
					tb_show("Add name for purchase order slip....",url,false);
				}
			}else{
				if( confirm("Are you sure you want to clear this?")){
					ACT.go("itemClearPurchaseOrder.form?action="+action);
				}
			}
	}
		
};

INDENT={
		onChangeCategory : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "drugByCategory.form"
								,data: ({categoryId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divDrug").html(data);
						jQuery("#drugName").hide();
						jQuery("#drugName").val("");
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onChangeCategoryItem : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "itemBySubCategory.form"
								,data: ({categoryId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divItem").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlur : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "formulationByDrugName.form"
								,data: ({drugName :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divFormulation").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlurDrug : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "formulationByDrug.form"
								,data: ({drugId :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divFormulation").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlurInput : function(thiz,value,mainStoreValue)
		{
			var x = jQuery(thiz).val();
			if(x != null && x !=''){
				if(parseInt(x) > parseInt(value)){
					alert('Transfer quantity more than quantity indent');
					jQuery(thiz).val('');
					jQuery(thiz).focus();
				}else if(parseInt(x) > parseInt(mainStoreValue)){
					alert('Transfer quantity less than quantity on hand');
					jQuery(thiz).val('');
					jQuery(thiz).focus();
				}
			}
		},
		onBlurItem : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "specificationByItem.form"
								,data: ({itemId :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divSpecification").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		printDiv : function ()
		{
		  	jQuery("div#printDiv").printArea({mode:"popup",popClose:true,popTitle: "Support by HISP india(hispindia.org)"});
		},
		processSlip : function(action){
				if(action == 0){
					if(SESSION.checkSession()){
						url = "addNameIndentSlip.form?action="+action+"&keepThis=false&TB_iframe=true&height=200&width=450";
						tb_show("Add name for indent slip....",url,false);
					}
				}else if(action == 2){
					if(SESSION.checkSession()){
						url = "addNameIndentSlip.form?send=1&action="+action+"&keepThis=false&TB_iframe=true&height=200&width=450";
						tb_show("Add name for indent slip....",url,false);
					}
				}else{
					if( confirm("Are you sure you want to clear this?")){
						ACT.go("clearSubStoreIndent.form?action="+action);
					}
				}
		},
		processSlipItem : function(action){
			if(action == 0){
				if(SESSION.checkSession()){
					url = "itemAddNameIndentSlip.form?action="+action+"&keepThis=false&TB_iframe=true&height=200&width=450";
					tb_show("Add name for indent slip....",url,false);
				}
			}else if(action == 2){
				if(SESSION.checkSession()){
					url = "itemAddNameIndentSlip.form?send=1&action="+action+"&keepThis=false&TB_iframe=true&height=200&width=450";
					tb_show("Add name for indent slip....",url,false);
				}
			}else{
				if( confirm("Are you sure you want to clear this?")){
					ACT.go("itemClearSubStoreIndent.form?action="+action);
				}
			}
		},
		detailDrugIndent : function(indentId)
		{
			if(SESSION.checkSession()){
				url = "indentDrugDetail.form?indentId="+indentId+"&keepThis=false&TB_iframe=true&height=500&width=1000";
				tb_show("Detail drug indent....",url,false);
			}
		},
		detailItemIndent : function(indentId)
		{
			if(SESSION.checkSession()){
				url = "indentItemDetail.form?indentId="+indentId+"&keepThis=false&TB_iframe=true&height=500&width=1000";
				tb_show("Detail item indent....",url,false);
			}
		},
		sendToMainStore : function(indentId)
		{
			ACT.go("sentDrugIndentToMainStore.form?indentId="+indentId);
		},
		sendToMainStoreItem : function(indentId)
		{
			ACT.go("sentItemIndentToMainStore.form?indentId="+indentId);
		},
		refuseIndentFromMainStore : function(thiz)
		{
			if(confirm("Are you sure this?")) {
				jQuery('#tableIndent').remove();
				jQuery("#refuse").val("1");
				jQuery('#formMainStoreProcessIndent').submit()
				}else{
					return false;
				}
		},
		
		refuseIndentFromSubStore : function(thiz)
		{
			if(confirm("Are you sure this?")) {
				jQuery('#tableIndent').remove();
				jQuery("#refuse").val("1");
				jQuery('#formSubStoreDrugProcessIndent').submit()
				}else{
					return false;
				}
		},
		refuseIndentFromSubStoreItem : function(thiz)
		{
			if(confirm("Are you sure this?")) {
				jQuery('#tableIndent').remove();
				jQuery("#refuse").val("1");
				jQuery('#formSubStoreItemProcessIndent').submit()
				}else{
					return false;
				}
		}
		
};

ISSUE={
		onChangeCategory : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "drugByCategoryForIssue.form"
								,data: ({categoryId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divDrug").html(data);
						jQuery("#drugName").hide();
						jQuery("#drugName").val("");
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onChangeCategoryAccount : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "drugByCategoryForIssue.form"
								,data: ({categoryId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divDrug").html(data);
						jQuery("#drugName").hide();
						jQuery("#drugName").val("");
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onChangeCategoryItem : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "itemBySubCategoryForIssue.form"
								,data: ({categoryId: x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divItem").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlur : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "formulationByDrugNameForIssue.form"
								,data: ({drugName :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divFormulation").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlurDrug : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "formulationByDrugForIssue.form"
								,data: ({drugId :x})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divFormulation").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		onBlurItem : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != undefined  && x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "specificationByItemForIssue.form"
								,data: ({itemId :x})	
								,async: false
								, cache : false
							}).responseText;
					
					if(data != undefined  && data != null && data != ''){
						if(jQuery(data).hasClass('box')){
							jQuery("#divSpecification").html('');
							jQuery("#divItemAvailable").html(data);
						}else{
							jQuery("#divItemAvailable").html('');
							jQuery("#divSpecification").html(data);
						}
						
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		checkQtyBeforeIssue : function(thiz){

			if(parseInt(jQuery("#issueItemQuantity").val()) > parseInt(jQuery("#currentQuantity").val())){
				alert('Issue quantity must less than current quantity');
				return false;
			}
			jQuery('#formIssueItem').submit();
		},
		onBlurPatient : function(thiz)
		{
			var x = jQuery(thiz).val();
			if(x != undefined  && x != null && x != '' ){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "autoCompletePatientList.form"
								,data: ({searchPatient :x})	
								,async: false
								, cache : false
							}).responseText;
					
					if(data != undefined  && data != null && data != ''){
						jQuery("#divShowPatients").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
			
		},
		formulationOnChange : function(thiz){
			var formulationId = jQuery(thiz).val();
			var drugId = jQuery("#drugId").val();
			if(formulationId != '' && drugId != ''){
				if(SESSION.checkSession()){
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "listReceiptDrug.form"
								,data: ({drugId: drugId,formulationId: formulationId})	
								,async: false
								, cache : false
							}).responseText;
					if(data != undefined  && data != null && data != ''){
						jQuery("#divDrugAvailable").html(data);
					}else{
						alert('Please refresh page!');
					}
				}
			}
		},
		specificationOnChange : function(thiz){
			
				var specificationId = jQuery(thiz).val();
				var itemId = jQuery("#itemId").val();
				if(specificationId != '' && itemId != ''){
					if(SESSION.checkSession()){
						var data = jQuery.ajax(
								{
									type:"GET"
									,url: "listReceiptItem.form"
									,data: ({itemId: itemId,specificationId: specificationId})	
									,async: false
									, cache : false
								}).responseText;
						if(data != undefined  && data != null && data != ''){
							jQuery("#divItemAvailable").html(data);
						}else{
							alert('Please refresh page!');
						}
					}
			}
		},
		createPatient : function()
		{
			if(SESSION.checkSession()){
				url = "createPatientIssueDrug.form?keepThis=false&TB_iframe=true&height=500&width=800";
				tb_show("...",url,false);
			}
		},
		addPatient : function(url)
		{
			if(SESSION.checkSession()){
				self.parent.tb_remove();
				self.parent.ACT.go(url);
			}
		},
		createAccount : function()
		{
			if(SESSION.checkSession()){
				url = "createAccountIssueItem.form?keepThis=false&TB_iframe=true&height=300&width=450";
				tb_show("Create account issue item....",url,false);
			}
		},
		createAccountIssueDrug : function()
		{
			if(SESSION.checkSession()){
				url = "createAccountIssueDrug.form?keepThis=false&TB_iframe=true&height=300&width=500";
				tb_show("Create account issue drug....",url,false);
			}
		},
		processSlip : function(data){
			if(data == 1){
				if( confirm("Are you sure you want to clear this?")){
					ACT.go("processIssueDrug.form?action="+data);
				}
			}else{
				if( confirm("Are you sure ?")){
					jQuery("#bttprocess").val("Wait a moment!");
					jQuery("#bttprocess").attr("disabled","disabled");
					jQuery("#bttclear").attr("disabled","disabled");
					jQuery("#bttprint").attr("disabled","disabled");
					ACT.go("processIssueDrug.form?action="+data);
				}
			}
			
		},
		processIssueDrugToAccount : function(data){
			if(data == 1){
				if( confirm("Are you sure you want to clear this?")){
					ACT.go("processIssueDrugAccount.form?action="+data);
				}
			}else{
				if( confirm("Are you sure ?")){
					jQuery("#bttprocess").val("Wait a moment!");
					jQuery("#bttprocess").attr("disabled","disabled");
					jQuery("#bttclear").attr("disabled","disabled");
					jQuery("#bttprint").attr("disabled","disabled");
					ACT.go("processIssueDrugAccount.form?action="+data);
				}
			}
			
		},
		processSlipItem : function(data){
			if(data == 1){
				if( confirm("Are you sure you want to clear this?")){
					ACT.go("processIssueItem.form?action="+data);
				}
			}else{
				if( confirm("Are you sure ?")){
					jQuery("#bttprocess").val("Wait a moment!");
					jQuery("#bttprocess").attr("disabled","disabled");
					jQuery("#bttclear").attr("disabled","disabled");
					jQuery("#bttprint").attr("disabled","disabled");
					ACT.go("processIssueItem.form?action="+data);
				}
			}
			
		},
		detailIssueDrug : function(id){
			if(SESSION.checkSession()){
				url = "subStoreIssueDrugDettail.form?issueId="+id+"&keepThis=false&TB_iframe=true&height=500&width=800";
				tb_show("Detail issue....",url,false);
			}
		},
		detailIssueDrugAccount : function(id){
			if(SESSION.checkSession()){
				url = "subStoreIssueDrugAccountDettail.form?issueId="+id+"&keepThis=false&TB_iframe=true&height=500&width=800";
				tb_show("Detail issue....",url,false);
			}
		},
		detailIssueItem : function(id){
			if(SESSION.checkSession()){
				url = "subStoreIssueItemDettail.form?issueId="+id+"&keepThis=false&TB_iframe=true&height=500&width=800";
				tb_show("Detail issue....",url,false);
			}
		}
		
};
STOCKBALLANCE = {
		back : function()
		{
			history.back(-1);
		},
		detail : function(drugId, formulationId)
		{
		
			ACT.go("viewStockBalanceDetail.form?drugId="+drugId+"&formulationId="+formulationId);
		},
		detailExpiry : function(drugId, formulationId)
		{
			ACT.go("viewStockBalanceDetail.form?drugId="+drugId+"&formulationId="+formulationId+"&expiry=1");
		},
		goToTranser : function(url)
		{
			ACT.go(url);
		},
		detailItem : function(itemId, specificationId)
		{
			ACT.go("itemViewStockBalanceDetail.form?itemId="+itemId+"&specificationId="+specificationId);
		},
		detailSubStoreDrug : function(drugId, formulationId)
		{
			ACT.go("viewStockBalanceSubStoreDetail.form?drugId="+drugId+"&formulationId="+formulationId);
		},
		detailSubStoreDrugExpiry : function(drugId, formulationId)
		{
			ACT.go("viewStockBalanceSubStoreDetail.form?drugId="+drugId+"&formulationId="+formulationId+"&expiry=1");
		},
		detailSubStoreItem : function(itemId, specificationId)
		{
			ACT.go("itemViewStockBalanceSubStoreDetail.form?itemId="+itemId+"&specificationId="+specificationId);
		}
		
};

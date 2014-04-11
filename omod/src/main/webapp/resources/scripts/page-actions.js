
var EVT =
{
	ready : function()
	{
		/**
		 * Page Actions
		 */
		var enableCheck = true;
		var pageId = jQuery("form").attr("id");
		if(enableCheck && jQuery("form").attr("id") != undefined && pageId != null && pageId != undefined && eval("CHECK." + pageId))
		{
			eval("CHECK." + jQuery("form").attr("id") + "()");
		}
		
		jQuery('.date-pick').datepicker({yearRange:'c-30:c+30', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});

		/**
		 * Ajax Indicator when send and receive data
		 */
		if(jQuery.browser.msie)
		{
			jQuery.ajaxSetup({cache: false});
		}
	
	}
};

var CHECK = 
{
	
	inventoryStore : function()
	{
		var validator = jQuery("#inventoryStore").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true},
				"code" : { required : true},
				"role" : { required : true},
				"isDrug" : { required : true}
			
			}
		});
	},
	drugCategory : function()
	{
		var validator = jQuery("#drugCategory").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true}
			
			}
		});
	},
	drugUnit : function()
	{
		var validator = jQuery("#drugUnit").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true}
			
			}
		});
	},
	drugFormulation : function()
	{
		var validator = jQuery("#drugFormulation").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true},
				"dozage" : { required : true}
			
			}
		});
	},
	drugForm : function()
	{
		//jQuery("#drugCore").autocomplete({source: 'autoCompleteDrugCoreList.form', minLength: 3 } );
		
		jQuery("#drugCore").autocomplete('autoCompleteDrugCoreList.form', {
			 minChars: 3 ,
			 delay:1000,
			 scroll: true});
		
		var validator = jQuery("#drugForm").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true},
				"category" : { required : true},
				"unit" : { required : true},
				"drugCore" : { required : true},
				"formulations" : { required : true},
				"attribute" : { required : true},
				"reorderQty" : { required : true, number: true}
			}
		});
	},
	item : function()
	{
		var validator = jQuery("#item").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true},
				"unit" : { required : true},
				"subCategory" : { required : true},
				"attribute" : { required : true},
				"reorderQty" : { required : true, number: true}
			
			}
		});
	},
	itemCategory : function()
	{
		var validator = jQuery("#itemCategory").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true}
			
			}
		});
	},
	itemSubCategory : function()
	{
		var validator = jQuery("#itemSubCategory").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true},
				"code" : { required : true},
				"categoryId" : { required : true}
			
			}
		});
	},
	itemUnit : function()
	{
		var validator = jQuery("#itemUnit").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true}
			
			}
		});
	},
	itemSpecification : function()
	{
		var validator = jQuery("#itemSpecification").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true}
			
			}
		});
	},formFinishReceipSlip : function()
	{
		var validator = jQuery("#formFinishReceipSlip").validate(
		{
			event : "blur",
			rules : 
			{
				"description" : { required : true}
			
			}
		});
	},
	createAccountIssueDrug : function()
	{
		var validator = jQuery("#createAccountIssueDrug").validate(
				{
					event : "blur",
					rules : 
					{
						"accountName" : { required : true}
					
					}
				});
	},
	receiptDrug : function()
	{
		//jQuery("#drugName").autocomplete({source: 'autoCompleteDrugList.form?categoryId='+jQuery('#category').val(), minLength: 3 });
		jQuery("#drugName").autocomplete('autoCompleteDrugList.form', {
			 minChars: 3 ,
			 delay:1000,
			 scroll: true});
		
		var validator = jQuery("#receiptDrug").validate(
				{
					event : "blur",
					rules : 
					{
						"category" : { required : true},
						"drugId" : { required : true},
						"formulation" : { required : true},
						"batchNo" : { required : true},
						"companyName" : { required : true},
						"dateManufacture" : { required : true},
						"quantity" : { required : true,digits : true,min : 1},
						"unitPrice" : { required : true,number : true,min : 0},
						"VAT" : { required : true,number : true,min : 0},
						"dateExpiry" : { required : true},
						"receiptDate" : { required : true}
					
					},
					submitHandler: function(form) {
						var check =(jQuery("#dateManufacture").datepicker("getDate") < jQuery("#dateExpiry").datepicker("getDate"));
						var check1 =(jQuery("#dateManufacture").datepicker("getDate") < jQuery("#receiptDate").datepicker("getDate"));
						if(check && check1){
							form.submit();
						}else{
							alert('Please make sure manufacture date < expiry date,manufacture date < receipt date');
						}
						
					}
				});
	},
	receiptItem : function()
	{
		//jQuery("#itemName").autocomplete({source: 'autoCompleteItemList.form?categoryId='+jQuery('#category').val(), minLength: 3 });
		var validator = jQuery("#receiptItem").validate(
				{
					event : "blur",
					rules : 
					{
						"category" : { required : true},
						"itemId" : { required : true},
						"specification" : { required : true},
						"quantity" : { required : true,digits : true,min : 1},
						"unitPrice" : { required : true,number : true,min : 0},
						"VAT" : { required : true,number : true,min : 0},
						"receiptDate" : { required : true}
					
					}
				});
	},
	subStoreIndentDrug : function()
	{
		//jQuery("#drugName").autocomplete({source: 'autoCompleteDrugList.form?categoryId='+jQuery('#category').val(), minLength: 3 });
		jQuery("#drugName").autocomplete('autoCompleteDrugList.form', {
			 minChars: 3 ,
			 delay:1000,
			 scroll: true});
		
		var validator = jQuery("#subStoreIndentDrug").validate(
				{
					event : "blur",
					rules : 
					{
						
						"drugId" : { required : true},
						"formulation" : { required : true},
						"quantity" : { required : true,digits : true}
					}
				});
	},
	subStoreIndentItem : function()
	{
		//jQuery("#itemName").autocomplete({source: 'autoCompleteItemList.form?categoryId='+jQuery('#category').val(), minLength: 3 });
		var validator = jQuery("#subStoreIndentItem").validate(
				{
					event : "blur",
					rules : 
					{
						"category" : { required : true},
						"itemId" : { required : true},
						"specification" : { required : true},
						"quantity" : { required : true,digits : true}
					}
				});
	},
	formMainStoreProcessIndent : function()
	{
		jQuery("#formMainStoreProcessIndent").validate();
	},
	formAddNameForPurchase : function()
	{
		var validator = jQuery("#formAddNameForPurchase").validate(
				{
					event : "blur",
					rules : 
					{
						"indentName" : { required : true}
					}
				});
	},
	formIssueDrug : function()
	{
		//jQuery("#drugName").autocomplete({source: 'autoCompleteDrugList.form?categoryId='+jQuery('#category').val(), minLength: 3 });
		jQuery("#drugName").autocomplete('autoCompleteDrugList.form', {
			 minChars: 3 ,
			 delay:1000,
			 scroll: true});
		
		var validator = jQuery("#formIssueDrug").validate(
				{
					event : "blur",
					rules : 
					{
						
						"drugId" : { required : true},
						"formulation" : { required : true}
					}
				});
	},
	formIssueDrugAccount : function()
	{
		//jQuery("#drugName").autocomplete({source: 'autoCompleteDrugList.form?categoryId='+jQuery('#category').val(), minLength: 3 });
		var validator = jQuery("#formIssueDrugAccount").validate(
				{
					event : "blur",
					rules : 
					{
						"category" : { required : true},
						"drugId" : { required : true},
						"formulation" : { required : true}
					}
				});
	},
	formIssueItem : function()
	{
		//jQuery("#itemName").autocomplete({source: 'autoCompleteItemList.form?categoryId='+jQuery('#category').val(), minLength: 3 });
		var validator = jQuery("#formIssueItem").validate(
				{
					event : "blur",
					rules : 
					{
						"category" : { required : true},
						"itemId" : { required : true},
						"specification" : { required : true}
					}
				});
	},
	formCreateAccount : function()
	{
		var validator = jQuery("#formCreateAccount").validate(
				{
					event : "blur",
					rules : 
					{
						"name" : { required : true}
					}
				});
	},
	findPatient : function()
	{
		jQuery('input#searchPatient').keypress(function(e) {
			  if (e.keyCode == '13') 
			  {
			     e.preventDefault();
			     ISSUE.onBlurPatient(jQuery('input#searchPatient'));
			   }
		});
	}
	
	
	
};

/**
 * Pageload actions trigger
 */

jQuery(document).ready(
	function() 
	{
		EVT.ready();
	}
);




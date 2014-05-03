
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

	receiptBloodStockReceipt : function()
	{
		var validator = jQuery("#receiptBloodStockReceipt").validate(
				{
					event : "blur",
					rules : 
					{
						"product" : { required : true},
						"packNo" : { required : true},
						"donorName" : { required : true},
						"bloodGroup" : { required : true},
						"dateOfReceipt" : { required : true},
						"dateOfExpiry" : 	{ required : true}
					}
				});
	},
	issueBloodForm :function()
	{
		var validator = jQuery("#issueBloodForm").validate(
				{
					event : "blur",
					rules : 
					{
						"result" : { required : true},
						
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





jQuery(document).ready(function(){
	jQuery(".udiv").hover(
		function(){
			jQuery(this).addClass("boxHover");
			jQuery(this).removeClass("boxNormal");
		}
		,function(){
			jQuery(this).removeClass("boxHover");
			jQuery(this).addClass("boxNormal");
		}
	);
	
	var finishDrugOrderDiv = jQuery("div#finishDrugOrderDiv");
	if( finishDrugOrderDiv.length > 0 )
	{
		var left = f_clientWidth() - finishDrugOrderDiv.outerWidth();
		var top = f_clientHeight() - finishDrugOrderDiv.outerHeight();
		finishDrugOrderDiv.css({"position":"fixed","top":top+"px","left":left+"px"});
		jQuery("div#extra",finishDrugOrderDiv).css({"max-height":"300px","overflow":"auto"});
		
	    /**
	    * June 5th 2012: Thai Chuong supported issue #246
	    * [PUNJAB] Text box when bill is voided & Print out of a bill that is voided
	    * Cancel draggale to allow typing. 
	    */
		finishDrugOrderDiv.draggable({cancel:".cancelDraggable",containment:"window"});
		
		jQuery("#toogleFinishDrugOrderBtn").click();
	}
	jQuery("#tabs").tabs();

});

function f_clientWidth() {
	return f_filterResults (
		window.innerWidth ? window.innerWidth : 0,
		document.documentElement ? document.documentElement.clientWidth : 0,
		document.body ? document.body.clientWidth : 0
	);
}
function f_clientHeight() {
	return f_filterResults (
		window.innerHeight ? window.innerHeight : 0,
		document.documentElement ? document.documentElement.clientHeight : 0,
		document.body ? document.body.clientHeight : 0
	);
}
function f_filterResults(n_win, n_docel, n_body) {
	var n_result = n_win ? n_win : 0;
	if (n_docel && (!n_result || (n_result > n_docel)))
		n_result = n_docel;
	return n_body && (!n_result || (n_result > n_body)) ? n_body : n_result;
}
function toogleFinishDrugOrder(this_)
{
	if( jQuery(this_).val() == "-" ) 
	{
		var finishDrugOrderDiv = jQuery("div#finishDrugOrderDiv");
		jQuery("#extra",finishDrugOrderDiv).hide();
		var left = f_clientWidth() - finishDrugOrderDiv.outerWidth()  ;
		var top = f_clientHeight() - finishDrugOrderDiv.outerHeight();
		finishDrugOrderDiv.css({"position":"fixed","top":top+"px","left":left+"px"});
		jQuery(this_).val("+");
	}else{
		var finishDrugOrderDiv = jQuery("div#finishDrugOrderDiv");
		var left = f_clientWidth() - finishDrugOrderDiv.outerWidth();
		finishDrugOrderDiv.css({"position":"fixed","top":"50px","left":left+"px"});
		jQuery("#extra",finishDrugOrderDiv).show();
		jQuery(this_).val("-");
	}
}



//Convert numbers to words
//copyright 25th July 2006, by Stephen Chapman http://javascript.about.com
//permission to use this Javascript on your web page is granted
//provided that all of the code (including this copyright notice) is
//used exactly as shown (you can change the numbering system if you wish)

//American Numbering System
//var th = ['','thousand','million', 'billion','trillion'];
//uncomment this line for English Number System
var th = ['','thousand','million', 'milliard','billion'];

var dg = ['zero','one','two','three','four', 'five','six','seven','eight','nine']; 
var tn = ['ten','eleven','twelve','thirteen', 'fourteen','fifteen','sixteen', 'seventeen','eighteen','nineteen']; 
var tw = ['twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety']; 

function toWords(s){
	//ghanshyam 12-dec-2012 Bug #458 [BILLING 3.2.8-SNAPSHOT] Edit in patient category, the amount in figures and words in the print out of the previous bill is not same
	if (s == 0.00){
		return "zero";
	}
if( s.indexOf('.') != -1 ){
	var arr = s.split(".");
	if( arr[1] == "00"){
		s = arr[0];
	}
}
s = s.replace(/[\, ]/g,''); 
/*if (s != String(parseFloat(s))) 
	return 'not a number';*/ 
var x = s.indexOf('.');

	if (x == -1) x = s.length;
	
	if (x > 15) 
		return 'too big'; 
	var n = s.split(''); 
	var str = ''; 
	var sk = 0; 
	for (var i=0; i < x; i++) {
		if ((x-i)%3==2) {
			if (n[i] == '1') {str += tn[Number(n[i+1])] + ' '; i++; sk=1;} 
			else if (n[i]!=0) {str += tw[n[i]-2] + ' ';sk=1;}} 
		else if (n[i]!=0) {str += dg[n[i]] +' '; 
		if ((x-i)%3==0) str += 'hundred ';sk=1;} 
		if ((x-i)%3==1) {if (sk) str += th[(x-i-1)/3] + ' ';sk=0;}} 
	if (x != s.length) {
		var y = s.length; str += 'point '; 
		for (var i=x+1; i<y; i++) str += dg[n[i]] +' ';} 
	return str.replace(/\s+/g,' ');
	}

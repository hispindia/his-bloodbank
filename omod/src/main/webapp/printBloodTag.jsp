<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="includes/js_css.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <title>Blood Bag Tag</title>
  <style type="text/css">
    p.header {word-break: normal; font-weight: bold; margin: 0.0px 0.0px 0.0px 0.0px; font: 16px sans-serif}
    #wrapper{
    	padding: 10px;
    	width: 10cm;
    	height: 9cm;
    	border-color: black;
    	border-style: solid;
    	border-width: 1px;
    	text-align: center;
    	widows: 1;
    }
    body{
    	background-color: white;
    	font-family: sans-serif;
    }
    #info{
    	width: 100%;
    	text-align: left;
    }
    #bloodgroup{
    
    	margin-top: 15px;
		
		margin-right: 10px;
    
    	padding: 10px;
    
    	min-width: 40px;
    
    	font-size: 20px;
    
    	font-weight: bold;
    
    	background-color: white;
    
    	float: right;
    
    	text-shadow: 0px 0px;
    
    	border-color: black;
    
    	border-style: solid;
    
    	border-width: 1px;
    
    	text-align: center;
    
    }
    #bloodgroup p{
    
    	word-break: normal;
    
    	margin: 0px;
    
    	font-weight: bold;
    
    	font-size: 4em;
    }
    #type{
    	text-align: right;
    	width: 100%;
    }
  </style>
</head>
<body>
<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<form>
<input id="butt" type="button" value="Print" onClick="window.printList()">
</form>
<div id="wrapper">
	<div class="header">
	DEPARTMENT OF BLOD TRANSFUSION<br/>
	STATE BLOOD BANK<br/>
	DDU HOSPITAL COMPLEX, SHIMLA
	</p>
	
	<div id="info">
		<table border="0" cellspacing="5" cellpadding="5">
			<tr><td>Patient I.D. No:</td><td><strong>${patientId}</strong></td></tr>
			<tr><td>Date of Collection:</td><td><strong><openmrs:formatDate date="${bb.storageDate}"/></strong></td></tr>
			<tr><td>Date of Expiry:</td> <td><strong><openmrs:formatDate date="${bb.expiryDate}"/></strong></td></tr>
		</table>
		<div id="type">
		<div id="bloodgroup">
			BLOOD GROUP:
			<p>${bloodType}</p>
		</div>
	</div>
	<table border="0" cellspacing="5">
		<tr><td><b>Free from:</b></td></tr>
		<c:forEach items="${br}" var="name">
		<tr><td>${name}</td></tr>
	</c:forEach></table>
	</div>
</div>

</body>
</html>

<script type="text/javascript" charset="utf-8">
	function printList(){
		document.getElementById("butt").style.display="none";
		window.print();
		window.close();
	}
</script>

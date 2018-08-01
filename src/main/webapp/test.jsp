<html>
<head>
<!-- <link rel="icon" href="img/avatar.ico" type="image/x-icon">  
<link rel="shortcut icon" href="img/avatar.ico" type="image/x-icon">  -->

<title>Idhayangal : Patients Details</title>
<meta charset="UTF-8">
<meta name="google" content="notranslate">
<meta http-equiv="Content-Language" content="en">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script src="js/main.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>

    <script type="text/javascript" src="js/underscore.js"></script>
    <script type="text/javascript" src="js/jsv.js"></script>
    <script type="text/javascript" src="js/jsonform.js"></script>
    
<style type="text/css">
.list {
  font-family:sans-serif;
}


input {
  border:solid 1px #ccc;
  border-radius: 5px;
  padding:7px 14px;
  margin-bottom:10px
}
input:focus {
  outline:none;
  border-color:#aaa;
}
.sort {
  padding:8px 30px;
  border-radius: 6px;
  border:none;
  display:inline-block;
  color:#fff;
  text-decoration: none;
  background-color: #28a8e0;
  height:30px;
}
.sort:hover {
  text-decoration: none;
  background-color:#1b8aba;
}
.sort:focus {
  outline:none;
}
.sort:after {
  display:inline-block;
  width: 0;
  height: 0;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-bottom: 5px solid transparent;
  content:"";
  position: relative;
  top:-10px;
  right:-5px;
}
.sort.asc:after {
  width: 0;
  height: 0;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-top: 5px solid #fff;
  content:"";
  position: relative;
  top:4px;
  right:-5px;
}
.sort.desc:after {
  width: 0;
  height: 0;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-bottom: 5px solid #fff;
  content:"";
  position: relative;
  top:-4px;
  right:-5px;
}

.item {
	border: solid 1px gray;
  padding:10px; 
}

#personal_information {
   background-color: aliceblue; 
   padding: 10px;
}

#initial_diagnosis_details {
   background-color: antiquewhite; 
   padding: 10px;
}

#diabetes_influencing_factors {
   background-color: gainsboro; 
   padding: 10px;
}

#insulin_details {
   background-color: lightyellow; 
   padding: 10px;
}

#hospitalization_details {
   background-color: seashell; 
   padding: 10px;
}

#pregnancy_details {
   background-color: palegoldenrod; 
   padding: 10px;
}

#visits {
   background-color: beige; 
   padding: 10px;
}

legend {
	background-color: darkgreen;
	color: white;
	padding: 5px;
}

.input-group {
	width: 100%;
}

.input-group-addon {
	width: 150px;
}
</style>

</head>
<body>
	<div class="header" style="zoom: 70%">

		

		<div id=header
			style="padding-left: 20px; padding-right: 20px; padding-top: 7px">

			<table width="100%">
				<tr>
					<td width=200>
					<div class="logo" align=center ><img style="zoom:75%" src="img/logo-free.png"></div>
					</td>
					<td>
						<div id="testcasename" >
							<span><h2>
									<span id="testcase">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Patient Details</span> &nbsp;&nbsp;&nbsp
								</h2></span>
						</div>


					</td>
					<td align=right>
					<a
						href="javascript:$('form').submit();" target="_blank"
						class="btn btn-lg btn-primary"><span
							class="glyphicon glyphicon-floppy-disk"></span>&nbsp;&nbsp;&nbsp;&nbsp;Save Patient Details</a>
					</td>
					
				</tr>
			</table>

		</div>


	</div>

	<div id=flowcontainer
		style="width: 100%; border: 5px; padding: 5px; background-color: whitesmoke"
		class="flow">



		<div class="w3-sidebar w3-bar-block w3-light-grey w3-card"
			style="width: 260px; position: absolute !important; height: 68%; padding: 5px; overflow: scroll">
			<br><br>
<a href="#personal_information" style="width:95%; text-align: left; margin: 2px;" class="btn btn-med btn-success"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;&nbsp;&nbsp;Personal Information</a>
<a href="#initial_diagnosis_details" style="width:95%; text-align: left; margin: 2px;"  class="btn btn-med btn-success"><span class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;&nbsp;&nbsp;Initial Diagnosis Details</a>
<a href="#diabetes_influencing_factors" style="width:95%; text-align: left; margin: 2px;"  class="btn btn-med btn-success"><span class="glyphicon glyphicon-globe"></span>&nbsp;&nbsp;&nbsp;&nbsp;Diabetes Influencing Factors</a>
<a href="#insulin_details" style="width:95%; text-align: left; margin: 2px;"  class="btn btn-med btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;&nbsp;Insulin details</a>
<a href="#hospitalization_details" style="width:95%; text-align: left; margin: 2px;"  class="btn btn-med btn-success"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;&nbsp;&nbsp;Hospitalization details</a>
<a href="#pregnancy_details" style="width:95%; text-align: left; margin: 2px;"  class="btn btn-med btn-success"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;&nbsp;Pregnancies Details</a>
<hr style="margin-top:5px; margin-bottom: 5px">
<big><b>&nbsp</b></big>
<br>
<a href="#visits" style="width:95%; text-align: left; margin: 2px;"  class="btn btn-med btn-warning"><span class="glyphicon glyphicon-calendar"></span>&nbsp;&nbsp;&nbsp;&nbsp;Visits</a>

			</div>
		
	
		<div id=sortable class="w3-container"
			style="margin: 20px; margin-left: 260px; background-color: white; overflow: scroll; height: -webkit-fill-available; width: -webkit-fill-available">
			<center>
			<table width=100% style="float:center; align:center"><tr><td width=10%>&nbsp;</td><td>
			<div id="users" style="width:100%">
<br>
 <form></form>


</div>
			</td>
			<td width=10%>&nbsp;</td></tr></table>
</center>


		</div>

		

	</div>


	</div>



	<!-- Modal Alert-->
	<button style="display: none" id="alert" type="button"
		class="btn btn-info btn-lg" data-toggle="modal" data-target="#message">Open
		Message</button>
	<div id="message" class="modal fade" role="dialog">
		<div class="modal-dialog modal-small">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="msg_header"></h4>
				</div>
				<div class="modal-body">
					<p id="msg_body"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal Loading -->
	<button style="display: none" id="processing" type="button"
		class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="static" data-keyboard="false" data-target="#spinner">Open
		Processing</button>

	<div id="spinner" class="modal fade" role="dialog">
		<div class="modal-dialog modal-small">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Processing...</h4>
				</div>
				<div class="modal-body">
					<p align="center">
					<div class="loading style-2"></div>
					</p>
				</div>
				<div class="modal-footer">
					<button style="display: none" type="button" class="btn btn-default"
						id="doneprocessing" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>





	<script type="text/javascript">
	var patient_id;
	patient_id = getParameterByName("id");
	load_patientrecord(patient_id);
	

				</script>
  </script>			

</body>
</html>
<html>
<head>
<!-- <link rel="icon" href="img/avatar.ico" type="image/x-icon">  
<link rel="shortcut icon" href="img/avatar.ico" type="image/x-icon"> -->
<link rel="shortcut icon" href="img/TNT1DR.ico" type="image/x-icon">
<link rel="icon" href="img/TNT1DR.ico" type="image/x-icon">

<title>Tamil Nadu Type 1 Diabetes : Patients registry</title>
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
</style>

</head>
<body>
	<div class="header" style="zoom: 70%">

		

		<div id=header
			style="padding-left: 20px; padding-right: 20px; padding-top: 7px">

			<table width="100%">
				<tr>
					<td width=200>
					<div class="logo" align=center ><img style="zoom:80%" src="img/TNT1DR.png"></div>
					</td>
					<td>
						<div id="testcasename" >
							<span><h2>
									<span id="testcase">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type 1 Diabetes Registry</span> &nbsp;&nbsp;&nbsp
								</h2></span>
						</div>


					</td>
					<td align=right>
					<a
						href="patient.jsp" target="_blank"
						class="btn btn-lg btn-primary"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;&nbsp;&nbspNew Patient Record</a>
					</td>
					
				</tr>
			</table>

		</div>


	</div>

	<div id=flowcontainer
		style="width: 100%; border: 5px; padding: 5px; background-color: whitesmoke"
		class="flow">




		
	
		<div class="w3-sidebar w3-bar-block w3-light-grey w3-card"
			style="width: -webkit-fill-available; padding: 50px; position: relative; overflow: scroll">
			
<div id="users" >
  <input class="search" size=100 placeholder="Search" />

  <table>
    <!-- IMPORTANT, class="list" have to be at tbody -->
    <tbody class="list1">
    <tr>
        <th class="item" style="width: 50px"><b>S.No</b></th>
        <th class="item" style="width: 200px"><b>Patient ID</b></th>
        <th class="item" style="width: 250px"><b>Name</b></th>
        <th class="item" style="width: 300px"><b>DOB</b></th>
        <th class="item" style="width: 200px"><b>City</b></th>
        <th class="item" style="width: 200px"><b>Phone</b></th>
        <th class="item" style="width: 200px"><b>Year of Diagnosis</b></th>
        <th class="item" style="width: 200px"><b>Consultant Name</b></th>
        <th class="item" style="width: 60px"><b></b></th>
      </tr>
      </tbody>
      
     <tbody class="list">

<!--       <tr> -->
<!--         <td class="item sno">1.</td> -->
<!--         <td class="item tcid">TC90c13f5680dc4dc18a648baf12cb6329</td> -->
<!--         <td class="item tcname">Demo Test Case</td> -->
<!--         <td class="item tcdesc">This is a test case created for demo This is a test case created for demo This is a test case created for demo This is a test case created for demo</td> -->
<!--         <td class="item tctags">demo,sanity</td> -->
<!--         <td class="item tcedit"><a href="#" class="btn btn-sm btn-primary" id="newtestcase">Edit -->
<!-- 								&nbsp;&nbsp;&nbsp;<span -->
<!-- 								class="glyphicon glyphicon-pencil"></span> -->
<!-- 							</a></td> -->
<!--       </tr> -->
      
    </tbody>
  </table>

</div>



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
					<button type="button" class="btn btn-default" data-dismiss="modal" id="modal_close">Close</button>
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
					<h4 class="modal-title">Loading...</h4>
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




	<script>

			load_patientrecords();
			

						</script>
</body>
</html>
var consultants = [];
var hospitals = [];

function load_patientrecords() {
	$("#processing").click();
	$
			.ajax({

				url : 'API/patients',
				type : 'GET',
				success : function(data) {
					$("#doneprocessing").click();
					tc_counter = 1
					Object.keys(data.patientrecords).forEach(function(key) {
						tc_data = data.patientrecords[key];

						str = "<tr>";
						str += "<td class='item sno'>" + tc_counter + ".</td>";
						str += '<td class="item tcid">' + tc_data.patient_id + '</td>';
						str += '<td class="item tcname">' + tc_data.patient_name + '</td>';
						str += '<td class="item tcdob">' + tc_data.patient_dob + '</td>';	
						str += '<td class="item tccity">' + tc_data.patient_city + '</td>';	
						str += '<td class="item tcphone">' + tc_data.patient_phone + '</td>';	
						str += '<td class="item tcyod">' + tc_data.patient_year_of_diagnosis + '</td>';	
						str += '<td class="item tctags">' + tc_data.consultant_name + '</td>';	
						str += '<td class="item tcedit"><a target=_blank href="./patient.jsp?id=' + tc_data.patient_id + '" class="btn btn-sm btn-primary" id="newtestcase">Edit&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-pencil"></span></a></td>';
						str += '</tr>';
						$(".list").append(
								str)
						tc_counter = tc_counter + 1;
					});

					var options = {
							  valueNames: [ 'tcid', 'tcname', 'tcdob', 'tctags', 'tccity', 'tcphone', 'tcyod' ]
							};

							var userList = new List('users', options);

				},
				error : function(request, error) {
					$("#doneprocessing").click();
					$("#msg_header").html("Failure");
					$("#msg_body").html(
							request.status + ":" + request.responseText);
					$("button#modal_close").off("click");
					$("#alert").click();
				}
			});
}


function load_patientrecorddata(record) {
	$("#processing").click();
	$
			.ajax({

				url : 'API/patient/' + patient_id,
				type : 'GET',
				success : function(data) {
					$("#doneprocessing").click();
					record["value"] = data["patient_record"]["patient_record"];
					$('form').jsonForm(record);

		    			$("#doneprocessing").click();
				},
				error : function(request, error) {
					$("#doneprocessing").click();
					$("#msg_header").html("Failure");
					$("#msg_body").html(
							request.status + ":" + request.responseText);
					$("button#modal_close").off("click");
					$("#alert").click();
				}
			});
}



function load_patientrecord(patient_id) {
	$("#processing").click();
	$
			.ajax({

				url : 'API/schema',
				type : 'GET',
				success : function(data) {
					
					data["displayErrors"] = function (errors, formElt) {
								    for (var i=0; i<errors.length; i++) {
								      errors[i].message = "<font color='red'>This is a required field. Please enter the value.</font>";
								    }
								    $('form').jsonFormErrors(errors, data);
								  }
					data["onSubmit"] = function (errors, values) {
				        if (errors) {
				           alert("Some error in the form. Please fix the record and try again");
				          } else {
				            save_patientrecord(values);
				          }
				        }; 
				    if (patient_id)
				    	{
				    		load_patientrecorddata(data);
				    	}
				    else
				    	{
				    		$('form').jsonForm(data);

				    		$("#doneprocessing").click();
				    	}

				},
				error : function(request, error) {
					$("#doneprocessing").click();
					$("#msg_header").html("Failure");
					$("#msg_body").html(
							"Error" + ":" + "Failed to load the schema");
					$("button#modal_close").off("click");
					$("#alert").click();
				}
			});
}

function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function save_patientrecord(record)
{
	$("#processing").click();

	patient_data = {"patient_record": record};
	if (patient_id)
		patient_data["patient_id"] = patient_id;
	patient_data["patient_name"] = record["name"];
	patient_data["patient_city"] = "N/A";
	if (record["address"])
		patient_data["patient_city"] = record["address"]["city"];
	patient_data["patient_phone"] = "N/A";
	if (record["phone"])
		patient_data["patient_phone"] = record["phone"];
	patient_data["patient_dob"] = "N/A";
	if (record["date_of_birth"])
		patient_data["patient_dob"] = record["date_of_birth"];
	patient_data["patient_year_of_diagnosis"]  = "N/A";
	if (record["year_of_diagnosis"])
		patient_data["patient_year_of_diagnosis"] = record["year_of_diagnosis"];
	patient_data["consultant_name"] = record["consultant_name"];
	patient_data["patient_tags"] = "";

	$.ajax({

		url : 'API/patient',
		type : 'POST',
		dataType : 'json',
		headers : {
			'content-type' : 'application/json'
		},
		data : JSON.stringify(patient_data),
		success : function(data) {
			$("#doneprocessing").click();

				$("#msg_header").html("Success");
				$("#msg_body").html("Success : Patient record " + data["patient_id"] + " saved successfully.");
				patient_id = data["patient_id"];
				$("button#modal_close").on("click", function(){window.location = 'patient.jsp?id=' + patient_id + ';'});
				$("#alert").click();

				
				
		},
		error : function(request, error) {
			$("#doneprocessing").click();
			$("#msg_header").html("Failure");
			$("#msg_body").html(
					request.status + ":" + request.responseText);
			$("button#modal_close").off("click");
			$("#alert").click();
		}
	});
}

function load_lookup_values(field) {
	ret_data = [];
	$
			.ajax({

				url : 'API/lookup/' + field,
				type : 'GET',
				async: false,
				success : function(data) {
					ret_data = data;
				},
				error : function(request, error) {
				}
			});
	return ret_data;
}



function set_lookups()
{
	consultants = load_lookup_values('consultants');
	hospitals = load_lookup_values('hospitals');
	
	$("#jsonform-1-elt-consultant_name").autocomplete({
	    source: consultants,
	    delay: 0
	});
	
	$("#jsonform-1-elt-hospital_name").autocomplete({
	    source: hospitals,
	    delay: 0
	});
}

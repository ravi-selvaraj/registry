package com.idhayangal.registry.rest;
 
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.util.logging.*;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhayangal.registry.common.Helper;
import com.idhayangal.registry.objects.PatientRecord;
import com.idhayangal.registry.worker.PatientRecordsWorker;


@Path("/schema")
public class SchemaHandler {
	static final Logger LOG = Logger.getLogger(SchemaHandler.class.getName());

	static final String schema = "{\n" + 
			"   \"schema\":{\n" + 
			"      \"name\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Name\",\n" + 
			"         \"required\":true\n" + 
			"      },\n" + 
			"      \"address\":{\n" + 
			"         \"type\":\"object\",\n" + 
			"         \"properties\":{\n" + 
			"            \"address_line_1\":{\n" + 
			"               \"type\":\"string\",\n" + 
			"               \"title\":\"Address\",\n" + 
			"               \"required\":true\n" + 
			"            },\n" + 
			"            \"address_line_2\":{\n" + 
			"               \"type\":\"string\",\n" + 
			"               \"required\":false\n" + 
			"            },\n" + 
			"            \"locality\":{\n" + 
			"               \"type\":\"string\",\n" + 
			"               \"required\":false\n" + 
			"            },\n" + 
			"            \"city\":{\n" + 
			"               \"type\":\"string\",\n" + 
			"               \"required\":true\n" + 
			"            },\n" + 
			"            \"state\":{\n" + 
			"               \"type\":\"string\",\n" + 
			"               \"required\":true\n" + 
			"            },\n" + 
			"            \"pincode\":{\n" + 
			"               \"type\":\"string\",\n" + 
			"               \"required\":true\n" + 
			"            }\n" + 
			"         }\n" + 
			"      },\n" + 
			"      \"phone\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Phone\"\n" + 
			"      },\n" + 
			"      \"email\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"E-Mail Id\"\n" + 
			"      },\n" + 
			"      \"date_of_birth\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Date of Birth\"\n" + 
			"      },\n" + 
			"      \"gender\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Gender\",\n" + 
			"         \"enum\":[\n" + 
			"            \"male\",\n" + 
			"            \"female\",\n" + 
			"            \"other\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"father_occupation\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Father Occupation\"\n" + 
			"      },\n" + 
			"      \"mother_occupation\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Mother Occupation\"\n" + 
			"      },\n" + 
			"      \"annual_income\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Annual Income in Rupees\"\n" + 
			"      },\n" + 
			"      \"home_refridgerator\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Refridgerator at Home\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"year_of_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Year of Diagnosis\"\n" + 
			"      },\n" + 
			"      \"age_of_patient_at_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Age of Patient at Diagnosis\"\n" + 
			"      },\n" + 
			"      \"height_at_diagnois\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Height of Patient at Diagnosis\"\n" + 
			"      },\n" + 
			"      \"weight_at_diagosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Weight of Patient at Diagnosis\"\n" + 
			"      },\n" + 
			"      \"bmi_at_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"BMI of Patient at Diagnosis\"\n" + 
			"      },\n" + 
			"      \"education_details_at_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Education of Patient at Diagnosis\",\n" + 
			"         \"enum\":[\n" + 
			"            \"Preschool Toddler\",\n" + 
			"            \"Primary School\",\n" + 
			"            \"Secondary School\",\n" + 
			"            \"College\",\n" + 
			"            \"Work\",\n" + 
			"            \"Unemployed\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"diabetic_ketoacidosis_admission_at_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Diabetic Ketoacidosis admission at Diagnosis\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"GAD_antibody_test_done\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"GAD Antibody test done\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"level_of_GAD_antibody_at_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Level of GAD antibody at Diagnosis\"\n" + 
			"      },\n" + 
			"      \"HbA1c_at_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"HbA1c of Patient at Diagnosis\"\n" + 
			"      },\n" + 
			"      \"family_history_of_diabetes\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Family History of Type 1 Diabetes\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"family_history_details\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"If Yes, Family History Details\"\n" + 
			"      },\n" + 
			"      \"factories_or_industries_nearby\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Factories or Industries Nearby\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"list_of_factories_or_industries\":{\n" + 
			"         \"type\":\"array\",\n" + 
			"         \"items\":{\n" + 
			"            \"type\":\"string\",\n" + 
			"            \"title\":\"List of factories or Industries nearby\"\n" + 
			"         }\n" + 
			"      },\n" + 
			"      \"favourite_food_or_snaks_at_diagnosis\":{\n" + 
			"         \"type\":\"array\",\n" + 
			"         \"items\":{\n" + 
			"            \"type\":\"string\",\n" + 
			"            \"title\":\"Favourite Food or Snacks at Diagnosis\"\n" + 
			"         }\n" + 
			"      },\n" + 
			"      \"time_spent_with_electronics\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Total time spent with Electronics per day\"\n" + 
			"      },\n" + 
			"      \"types_of_electronics\":{\n" + 
			"         \"type\":\"array\",\n" + 
			"         \"title\":\"Types of Electronics\",\n" + 
			"		  \"items\":{\n" +	
			"		       \"type\":\"string\",\n" +	
			"				\"enum\":[\"TV\", \"Computer/Laptops\", \"Tablets\", \"Smartphones\", \"Video Games\"]}" +
			"      },\n" + 
						
			
			"      \"total_daily_dose_of_insulin\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Total daily dose of Insulin\"\n" + 
			"      },\n" + 
			"      \"type_of_insulin\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Type of Insulin\",\n" + 
			"         \"enum\":[\n" + 
			"            \"Basal bolus with human insulins\",\n" + 
			"            \"Basal bolus with analogue insulins\",\n" + 
			"            \"Premix human insulins\",\n" + 
			"            \"Premix analogue insulins\",\n" + 
			"            \"Insulin pump\",\n" + 
			"            \"Others\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"type_of_insulin_if_others\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Type of Insulin (if others)\"\n" + 
			"      },\n" + 
			"      \"insulin_details\":{\n" + 
			"         \"type\":\"array\",\n" + 
			"         \"title\":\"Insulin Details\",\n" + 
			"         \"items\":{\n" + 
			"            \"type\":\"object\",\n" + 
			"            \"properties\":{\n" + 
			"               \"drug_name\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Drug Name\"\n" + 
			"               },\n" + 
			"               \"dose\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Dose\"\n" + 
			"               }\n" + 
			"            }\n" + 
			"         }\n" + 
			"      },\n" + 
			"      \"insulin_device\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Insulin Device\",\n" + 
			"         \"enum\":[\n" + 
			"            \"Syringe needle\",\n" + 
			"            \"Pen Device\",\n" + 
			"            \"Insulin Pump\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"year_of_insulin_pump_start\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Year of Insulin Pump Start\"\n" + 
			"      },\n" + 
			"      \"Number_of_severe_hypoglycemias_hospitalization_since_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Number of Severe Hypoglycemias Hospitalizations since Diagnosis\"\n" + 
			"      },\n" + 
			"      \"Number_of_diabetic_ketoacidosis_hospitalization_since_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Number of Diabetic Ketoacidosis Hospitalizations since Diagnosis\"\n" + 
			"      },\n" + 
			"      \"Number_of_any_hospitalization_since_diagnosis\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Number of Hospitalizations since Diagnosis\"\n" + 
			"      },\n" + 
			"      \"any_pregnancies_with_type_1_diabetes\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Any Pregnancies with Type 1 Diabetes\",\n" + 
			"         \"enum\":[\n" + 
			"            \"N/A\",\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"pregnancy_delivery_method\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Pregnancy Delivery Method\",\n" + 
			"         \"enum\":[\n" + 
			"            \"Normal Delivery\",\n" + 
			"            \"Elective\",\n" + 
			"            \"C-Section\",\n" + 
			"            \"Emergency C-Section\",\n" + 
			"            \"Forceps\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"any_miscarriages_in_pregnancy\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Any Miscarriages in Pregnancy\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"number_of_miscarriages_in_pregnancy\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Number of Miscarriages in Pregnancy\"\n" + 
			"      },\n" + 
			"      \"any_birth_defects_to_baby\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Any Birth Defects to Baby\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"baby_birth_defect_details\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Baby Birth Defect Details\"\n" + 
			"      },\n" + 
			"      \"birth_weight_of_baby\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Birth Weight of Baby\"\n" + 
			"      },\n" + 
			"      \"is_the_child_or_parent_supported_by_idhayangal_charitable_trust\":{\n" + 
			"         \"type\":\"string\",\n" + 
			"         \"title\":\"Were you supported during pregnancy with Idhayangal Charitable Trust\",\n" + 
			"         \"enum\":[\n" + 
			"            \"yes\",\n" + 
			"            \"no\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      \"visits\":{\n" + 
			"         \"type\":\"array\",\n" + 
			"         \"items\":{\n" + 
			"            \"type\":\"object\",\n" + 
			"            \"title\":\"Visits\",\n" + 
			"            \"properties\":{\n" + 
			"               \"date_of_visit\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Date of Visit\",\n" + 
			"                  \"required\":true\n" + 
			"               },\n" + 
			"               \"months_since_diagnosis\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Months since Diagnosis\"\n" + 
			"               },\n" + 
			"               \"nunber_of_severe_hypos_since_last_visit\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Number of Severe Hypos since last visit\"\n" + 
			"               },\n" + 
			"               \"number_of_DKA_admissions_since_last_visit\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Number of DKA admissions since last visit\"\n" + 
			"               },\n" + 
			"               \"HbA1c\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"HbA1c\"\n" + 
			"               },\n" + 
			"               \"creatinine\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Creatinine\"\n" + 
			"               },\n" + 
			"               \"fasting_glucose\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Fasting Glucose\"\n" + 
			"               },\n" + 
			"               \"post_prandial_glucose\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Post Prandial Glucose\"\n" + 
			"               },\n" + 
			"               \"random_glucose\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Random Glucose\"\n" + 
			"               },\n" + 
			"               \"TSH\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"TSH\"\n" + 
			"               },\n" + 
			"               \"hemoglobin\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Hemoglobin\"\n" + 
			"               },\n" + 
			"               \"urine_protein\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Urine Protein\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"Not Done\",\n" + 
			"                     \"Albuminuria\",\n" + 
			"                     \"No Albuminuria\"\n" + 
			"                  ]\n" + 
			"               },\n" + 
			"               \"is_patient_undergoing_dialysis\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Is Patient undergoing Dialysis\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"yes\",\n" + 
			"                     \"no\"\n" + 
			"                  ]\n" + 
			"               },\n" + 
			"               \"retinal_examination\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Retinal Examination\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"Not Done\",\n" + 
			"                     \"Normal\",\n" + 
			"                     \"NPDR\",\n" + 
			"                     \"PDR\",\n" + 
			"                     \"Maculopathy\"\n" + 
			"                  ]\n" + 
			"               },\n" + 
			"               \"laser_treatment_for_retinopathy\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Laser treatment_for Retinopathy\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"yes\",\n" + 
			"                     \"no\"\n" + 
			"                  ]\n" + 
			"               },\n" + 
			"               \"foot_ulcer\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Foot Ulcer\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"yes\",\n" + 
			"                     \"no\"\n" + 
			"                  ]\n" + 
			"               },\n" + 
			"               \"foot_amputation\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Foot Amputation\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"yes\",\n" + 
			"                     \"no\"\n" + 
			"                  ]\n" + 
			"               },\n" + 
			"               \"cardiovascular_problems\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Cardiovascular Problems\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"yes\",\n" + 
			"                     \"no\"\n" + 
			"                  ]\n" + 
			"               },\n" + 
			"               \"details_of_cardiovascular_problems\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Details of Cardiovascular Problems\"\n" + 
			"               },\n" + 
			"               \"quality_of_life\":{\n" + 
			"                  \"type\":\"string\",\n" + 
			"                  \"title\":\"Quality of Life\",\n" + 
			"                  \"enum\":[\n" + 
			"                     \"Extremely Poor\",\n" + 
			"                     \"Poor\",\n" + 
			"                     \"Good\",\n" + 
			"                     \"Very Good\",\n" + 
			"                     \"Excellent\"\n" + 
			"                  ]\n" + 
			"               }\n" + 
			"            }\n" + 
			"         }\n" + 
			"      }\n" + 
			"   },\n" + 
			"   \"form\":[\n" + 
			"      {\n" + 
			"         \"type\":\"fieldset\",\n" + 
			"         \"id\":\"personal_information\",\n" + 
			"         \"title\":\"Personal Information\",\n" + 
			"         \"expandable\":false,\n" + 
			"         \"items\":[\n" + 
			"            {\n" + 
			"               \"key\":\"name\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"address.address_line_1\",\n" + 
			"               \"prepend\":\"Address Line 1\",\n" + 
			"               \"notitle\":false,\n" + 
			"               \"htmlClass\":\"usermood\",\n" + 
			"               \"fieldHtmlClass\":\"input-xxlarge\",\n" + 
			"               \"placeholder\":\"\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"address.address_line_2\",\n" + 
			"               \"prepend\":\"Address Line 2\",\n" + 
			"               \"notitle\":true,\n" + 
			"               \"htmlClass\":\"usermood\",\n" + 
			"               \"fieldHtmlClass\":\"input-xxlarge\",\n" + 
			"               \"placeholder\":\"\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"address.locality\",\n" + 
			"               \"prepend\":\"Locality&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\",\n" + 
			"               \"notitle\":true,\n" + 
			"               \"htmlClass\":\"usermood\",\n" + 
			"               \"fieldHtmlClass\":\"input-xxlarge\",\n" + 
			"               \"placeholder\":\"\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"address.city\",\n" + 
			"               \"prepend\":\"City&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\",\n" + 
			"               \"notitle\":true,\n" + 
			"               \"htmlClass\":\"usermood\",\n" + 
			"               \"fieldHtmlClass\":\"input-xxlarge\",\n" + 
			"               \"placeholder\":\"\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"address.state\",\n" + 
			"               \"prepend\":\"State&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\",\n" + 
			"               \"notitle\":true,\n" + 
			"               \"htmlClass\":\"usermood\",\n" + 
			"               \"fieldHtmlClass\":\"input-xxlarge\",\n" + 
			"               \"placeholder\":\"\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"address.pincode\",\n" + 
			"               \"prepend\":\"Pincode&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\",\n" + 
			"               \"notitle\":true,\n" + 
			"               \"htmlClass\":\"usermood\",\n" + 
			"               \"fieldHtmlClass\":\"input-xxlarge\",\n" + 
			"               \"placeholder\":\"\"\n" + 
			"            },\n" + 
			"            \"phone\",\n" + 
			"            \"email\",\n" + 
			"            \"date_of_birth\",\n" + 
			"            {\n" + 
			"               \"key\":\"gender\",\n" + 
			"               \"titleMap\":{\n" + 
			"                  \"male\":\"Male\",\n" + 
			"                  \"female\":\"Female\",\n" + 
			"                  \"other\":\"Other\"\n" + 
			"               }\n" + 
			"            },\n" + 
			"            \"father_occupation\",\n" + 
			"            \"mother_occupation\",\n" + 
			"            \"annual_income\",\n" + 
			"            {\n" + 
			"               \"key\":\"home_refridgerator\",\n" + 
			"               \"titleMap\":{\n" + 
			"                  \"yes\":\"Yes\",\n" + 
			"                  \"no\":\"No\"\n" + 
			"               }\n" + 
			"            }\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"type\":\"fieldset\",\n" + 
			"         \"id\":\"initial_diagnosis_details\",\n" + 
			"         \"title\":\"Initial Diagnosis Details\",\n" + 
			"         \"expandable\":false,\n" + 
			"         \"items\":[\n" + 
			"            \"year_of_diagnosis\",\n" + 
			"            \"age_of_patient_at_diagnosis\",\n" + 
			"            \"height_at_diagnois\",\n" + 
			"            \"weight_at_diagosis\",\n" + 
			"            \"bmi_at_diagnosis\",\n" + 
			"            {\n" + 
			"               \"key\":\"education_details_at_diagnosis\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"diabetic_ketoacidosis_admission_at_diagnosis\",\n" + 
			"               \"titleMap\":{\n" + 
			"                  \"yes\":\"Yes\",\n" + 
			"                  \"no\":\"No\"\n" + 
			"               }\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"key\":\"GAD_antibody_test_done\",\n" + 
			"               \"titleMap\":{\n" + 
			"                  \"yes\":\"Yes\",\n" + 
			"                  \"no\":\"No\"\n" + 
			"               }\n" + 
			"            },\n" + 
			"            \"level_of_GAD_antibody_at_diagnosis\",\n" + 
			"            \"HbA1c_at_diagnosis\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"type\":\"fieldset\",\n" + 
			"         \"id\":\"diabetes_influencing_factors\",\n" + 
			"         \"title\":\"Diabetes Influencing Factors\",\n" + 
			"         \"expandable\":false,\n" + 
			"         \"items\":[\n" + 
			"            {\n" + 
			"               \"key\":\"family_history_of_diabetes\",\n" + 
			"               \"titleMap\":{\n" + 
			"                  \"yes\":\"Yes\",\n" + 
			"                  \"no\":\"No\"\n" + 
			"               }\n" + 
			"            },\n" + 
			"            \"family_history_details\",\n" + 
			"            {\n" + 
			"               \"key\":\"factories_or_industries_nearby\",\n" + 
			"               \"titleMap\":{\n" + 
			"                  \"yes\":\"Yes\",\n" + 
			"                  \"no\":\"No\"\n" + 
			"               }\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"type\":\"array\",\n" + 
			"               \"items\":[\n" + 
			"                  \"list_of_factories_or_industries[]\"\n" + 
			"               ]\n" + 
			"            },\n" + 
			"            {\n" + 
			"               \"type\":\"array\",\n" + 
			"               \"items\":[\n" + 
			"                  \"favourite_food_or_snaks_at_diagnosis[]\"\n" + 
			"               ]\n" + 
			"            },\n" + 
			"            \"time_spent_with_electronics\",\n" + 
			"            {\n" + 
			"               \"key\":\"types_of_electronics\",\n" + 
			"               \"type\":\"checkboxes\"\n" + 
			"            }\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"type\":\"fieldset\",\n" + 
			"         \"id\":\"insulin_details\",\n" + 
			"         \"title\":\"Insulin Details\",\n" + 
			"         \"expandable\":false,\n" + 
			"         \"items\":[\n" + 
			"            \"total_daily_dose_of_insulin\",\n" + 
			"            \"type_of_insulin\",\n" + 
			"            \"type_of_insulin_if_others\",\n" + 
			"            {\n" + 
			"               \"type\":\"array\",\n" + 
			"               \"title\":\"Insulin Details\",\n" + 
			"               \"items\":{\n" + 
			"                  \"type\":\"section\",\n" + 
			"                  \"items\":[\n" + 
			"                     \"insulin_details[].drug_name\",\n" + 
			"                     \"insulin_details[].dose\"\n" + 
			"                  ]\n" + 
			"               }\n" + 
			"            },\n" + 
			"            \"insulin_device\",\n" + 
			"            \"year_of_insulin_pump_start\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"type\":\"fieldset\",\n" + 
			"         \"id\":\"hospitalization_details\",\n" + 
			"         \"title\":\"Hospitalization Details\",\n" + 
			"         \"expandable\":false,\n" + 
			"         \"items\":[\n" + 
			"            \"Number_of_severe_hypoglycemias_hospitalization_since_diagnosis\",\n" + 
			"            \"Number_of_diabetic_ketoacidosis_hospitalization_since_diagnosis\",\n" + 
			"            \"Number_of_any_hospitalization_since_diagnosis\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"type\":\"fieldset\",\n" + 
			"         \"id\":\"pregnancy_details\",\n" + 
			"         \"title\":\"Pregnancy Details\",\n" + 
			"         \"expandable\":false,\n" + 
			"         \"items\":[\n" + 
			"            \"any_pregnancies_with_type_1_diabetes\",\n" + 
			"            \"pregnancy_delivery_method\",\n" + 
			"            \"any_miscarriages_in_pregnancy\",\n" + 
			"            \"number_of_miscarriages_in_pregnancy\",\n" + 
			"            \"any_birth_defects_to_baby\",\n" + 
			"            \"baby_birth_defect_details\",\n" + 
			"            \"birth_weight_of_baby\",\n" + 
			"            \"is_the_child_or_parent_supported_by_idhayangal_charitable_trust\"\n" + 
			"         ]\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"type\":\"fieldset\",\n" + 
			"         \"id\":\"visits\",\n" + 
			"         \"title\":\"Visit Details\",\n" + 
			"         \"expandable\":false,\n" + 
			"         \"items\":[\n" + 
			"            {\n" + 
			"               \"type\":\"tabarray\",\n" + 
			"               \"minItems\":0,\n" + 
			"               \"items\":{\n" + 
			"                  \"type\":\"section\",\n" + 
			"                  \"legend\":\"{{value}}\",\n" + 
			"                  \"items\":[\n" + 
			"                     {\n" + 
			"                        \"key\":\"visits[].date_of_visit\",\n" + 
			"                        \"valueInLegend\":true\n" + 
			"                     },\n" + 
			"                     \"visits[].months_since_diagnosis\",\n" + 
			"                     \"visits[].nunber_of_severe_hypos_since_last_visit\",\n" + 
			"                     \"visits[].number_of_DKA_admissions_since_last_visit\",\n" + 
			"                     \"visits[].HbA1c\",\n" + 
			"                     \"visits[].creatinine\",\n" + 
			"                     \"visits[].fasting_glucose\",\n" + 
			"                     \"visits[].post_prandial_glucose\",\n" + 
			"                     \"visits[].random_glucose\",\n" + 
			"                     \"visits[].TSH\",\n" + 
			"                     \"visits[].hemoglobin\",\n" + 
			"                     \"visits[].urine_protein\",\n" + 
			"                     \"visits[].is_patient_undergoing_dialysis\",\n" + 
			"                     \"visits[].retinal_examination\",\n" + 
			"                     \"visits[].laser_treatment_for_retinopathy\",\n" + 
			"                     \"visits[].foot_ulcer\",\n" + 
			"                     \"visits[].foot_amputation\",\n" + 
			"                     \"visits[].cardiovascular_problems\",\n" + 
			"                     \"visits[].details_of_cardiovascular_problems\",\n" + 
			"                     \"visits[].quality_of_life\"\n" + 
			"                  ]\n" + 
			"               }\n" + 
			"            }\n" + 
			"         ]\n" + 
			"      }\n" + 
			"   ]\n" + 
			"}";
 
	@GET
	@Produces("application/json")
	public Response getSchema() {
			return Response.status(200).entity(schema).build();
		
	}
}
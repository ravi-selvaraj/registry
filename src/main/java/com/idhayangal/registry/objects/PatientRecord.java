package com.idhayangal.registry.objects;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class PatientRecord {
	public String patient_id;
	public String patient_name;
	public String patient_dob;
	public String patient_city;
	public String patient_year_of_diagnosis;
	public String patient_phone;
	public String patient_tags;

	public Map patient_record = new HashMap();
	
	public PatientRecord()
	{
		
	}
	public PatientRecord(PatientRecord source)
	{
		this.patient_id = source.patient_id;
		this.patient_name  = source.patient_name;
		this.patient_dob  = source.patient_dob;
		this.patient_city = source.patient_city;
		this.patient_year_of_diagnosis = source.patient_year_of_diagnosis;
		this.patient_phone = source.patient_phone;
		this.patient_tags = source.patient_tags;
		
		this.patient_record = new HashMap(source.patient_record);
	}

}

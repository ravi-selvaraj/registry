package com.idhayangal.registry.worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhayangal.registry.objects.PatientRecord;
import com.idhayangal.registry.objects.PatientRecords;


public class PatientRecordsWorker {
	static final Logger LOG = Logger.getLogger(PatientRecordsWorker.class.getName());

	public static boolean initializePatientRecords()
	{
		return true;
	}
	
	public static boolean loadPatientRecords()
	{

		return true;
				
	}
	
	public static PatientRecords getPatientRecords()
	{
		return getPatientRecords(null);
	}
	
	public static PatientRecords getPatientRecords(List<String> tags)
	{
		PatientRecords PatientRecords = new PatientRecords();
		Connection connection = DBConnectionPool.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		if (connection != null)
		{
			try
			{
				stmt = connection.createStatement();
				String sql = "SELECT patient_id, patient_record, patient_name, patient_dob, patient_city, patient_year_of_diagnosis, patient_phone, patient_record->'patient_record'->'consultant_name' as \"consultant_name\", patient_tags from patient_records";
				if (tags != null)
				{
					sql += " where patient_record->\'tags\' @> ALL (ARRAY [";
					for (String attr: tags)
					{
						sql += "\'\"" + attr + "\"\',";
					}
					sql = sql.substring(0, sql.length() - 1);
					sql += "]::jsonb[])";
				}
				rs = stmt.executeQuery(sql);
				JSONParser parser = new JSONParser(); 

				while (rs.next()) {
					PatientRecord tc = new PatientRecord();
					tc.patient_id = rs.getString("patient_id").trim();
					tc.patient_name = rs.getString("patient_name").trim();
					tc.patient_city = rs.getString("patient_city").trim();
					tc.patient_dob = rs.getString("patient_dob").trim();
					tc.patient_phone = rs.getString("patient_phone").trim();
					tc.consultant_name = rs.getString("consultant_name").trim();
					tc.patient_tags = rs.getString("patient_tags").trim();
					tc.patient_year_of_diagnosis = rs.getString("patient_year_of_diagnosis").trim();
					tc.patient_record = (JSONObject) parser.parse(rs.getString("patient_record").trim());
					
				    PatientRecords.patientrecords.put(rs.getString("patient_id").trim(), tc);
				}
			}
			catch (Exception e)
			{
				LOG.log(Level.SEVERE, e.getMessage(), e);
				PatientRecords = new PatientRecords();
			}
			finally
			{
				try
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (connection != null)
						connection.close();
				}
				catch (Exception e)
				{
					
				}
			}
		}
		return PatientRecords;
	}
	
	public static PatientRecord getPatientRecord(String id)
	{
		PatientRecord tc = new PatientRecord();
		Connection connection = DBConnectionPool.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		if (connection != null)
		{
			try
			{
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT patient_id, patient_record, patient_name, patient_dob, patient_city, patient_year_of_diagnosis, patient_phone, patient_tags from patient_records where patient_id = \'" + id + "\'");
				ObjectMapper mapper = new ObjectMapper();
				JSONParser parser = new JSONParser(); 
				if (rs.next()) {
					tc.patient_id = rs.getString("patient_id").trim();
					tc.patient_name = rs.getString("patient_name").trim();
					tc.patient_city = rs.getString("patient_city").trim();
					tc.patient_dob = rs.getString("patient_dob").trim();
					tc.patient_phone = rs.getString("patient_phone").trim();
					tc.patient_tags = rs.getString("patient_tags").trim();
					tc.patient_year_of_diagnosis = rs.getString("patient_year_of_diagnosis").trim();
					tc.patient_record = (JSONObject) parser.parse(rs.getString("patient_record").trim());
				}
				else
					tc = null;
					
			}
			catch (Exception e)
			{
				LOG.log(Level.SEVERE, e.getMessage(), e);
				tc = null;
			}
			finally
			{
				try
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (connection != null)
						connection.close();
				}
				catch (Exception e)
				{
					
				}
			}
		}
		return tc;
	}
	
	public static boolean savePatientRecord(PatientRecord patient_record)
	{		
		Connection connection = DBConnectionPool.getConnection();
		PreparedStatement stmt = null;
		if (connection != null)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				
				String sql = "";
				if (patient_record.patient_id == null)
				{
					sql = "INSERT INTO patient_records (patient_name, patient_dob, patient_city, patient_year_of_diagnosis, patient_phone, patient_tags, patient_record) VALUES (" + 
						"\'" + patient_record.patient_name + "\'" +
						",\'" + patient_record.patient_dob + "\'" +
						",\'" + patient_record.patient_city + "\'" +
						",\'" + patient_record.patient_year_of_diagnosis + "\'" +
						",\'" + patient_record.patient_phone + "\'" +
						",\'" + patient_record.patient_tags + "\'" +
									",\'" + mapper.writeValueAsString(patient_record) + "\'"
									+ ")";
				}
				else
				{
					sql = "UPDATE patient_records SET patient_record = \'"
							+ mapper.writeValueAsString(patient_record) + "\'"
							+ ", patient_name = \'" + patient_record.patient_name + "\'" 
							+ ", patient_dob = \'" + patient_record.patient_dob + "\'" 
							+ ", patient_city = \'" + patient_record.patient_city + "\'" 
							+ ", patient_year_of_diagnosis = \'" + patient_record.patient_year_of_diagnosis + "\'" 
							+ ", patient_phone = \'" + patient_record.patient_phone + "\'" 
							+ ", patient_tags = \'" + patient_record.patient_tags + "\' WHERE patient_id = " + patient_record.patient_id;
				}
				LOG.log(Level.INFO, sql);
				stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				int nRet = stmt.executeUpdate();

				if (nRet == 0)
					return false;
				else
				{
					ResultSet generatedKeys = stmt.getGeneratedKeys();
		            if (generatedKeys.next()) {
		            		patient_record.patient_id = String.valueOf(generatedKeys.getInt(1));
		            }
				}
			}
			catch (Exception e)
			{
				LOG.log(Level.SEVERE, e.getMessage(), e);
				return false;
			}
			finally
			{
				try
				{
					if (stmt != null)
						stmt.close();
					if (connection != null)
						connection.close();
				}
				catch (Exception e)
				{
					
				}
			}
			
		}		
		return true;
	}	
	
	public static boolean deletePatientRecord(String patientId)
	{		
		Connection connection = DBConnectionPool.getConnection();
		Statement stmt = null;
		if (connection != null)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				stmt = connection.createStatement();
				int nRet = stmt.executeUpdate("DELETE from patient_records where patient_id = \'" +  patientId + "\'");
				if (nRet == 0)
					return false;
			}
			catch (Exception e)
			{
				LOG.log(Level.SEVERE, e.getMessage(), e);
				return false;
			}
			finally
			{
				try
				{
					if (stmt != null)
						stmt.close();
					if (connection != null)
						connection.close();
				}
				catch (Exception e)
				{
					
				}
			}
			
		}		
		return true;
	}	
	
}

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


public class LookupWorker {
	static final Logger LOG = Logger.getLogger(LookupWorker.class.getName());
	
	public static ArrayList<String> getLookupValues(String szField)
	{
		ArrayList<String> szValues = new ArrayList<String>();
		Connection connection = DBConnectionPool.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		if (connection != null)
		{
			try
			{
				stmt = connection.createStatement();
				String sql = "SELECT name from lookup_" + szField;
				
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					szValues.add(rs.getString("name").trim());
				}
			}
			catch (Exception e)
			{
				LOG.log(Level.SEVERE, e.getMessage(), e);
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
		return szValues;
	}
	
	public static boolean saveLookupValue(String szField, String szValue)
	{		
		Connection connection = DBConnectionPool.getConnection();
		Statement stmt = null;
		if (connection != null)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				stmt = connection.createStatement();
				int nRet = stmt.executeUpdate("INSERT INTO lookup_" + szField + " (name) VALUES (\'" + szValue.replaceAll("\'", "\'\'") + "\')");
			}
			catch (Exception e)
			{
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

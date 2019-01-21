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


public class SchemaWorker {
	static final Logger LOG = Logger.getLogger(SchemaWorker.class.getName());
	
	public static String getSchema()
	{
		String szSchema = "";
		Connection connection = DBConnectionPool.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		if (connection != null)
		{
			try
			{
				stmt = connection.createStatement();
				String sql = "SELECT schema from schema";
				
				rs = stmt.executeQuery(sql);
				JSONParser parser = new JSONParser(); 

				if (rs.next()) {
					szSchema = rs.getString("schema").trim();
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
		return szSchema;
	}
	
}

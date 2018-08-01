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


@Path("/patient")
public class PatientRecordHandler {
	static final Logger LOG = Logger.getLogger(PatientRecordHandler.class.getName());

	@POST
	@Path("/")
	@Produces("application/json")
	@Consumes("application/json")
	public Response createPatient(String entity) {
 		ObjectMapper mapper = new ObjectMapper();
		try {
			PatientRecord record = mapper.readValue(entity, PatientRecord.class);
		
			String id = record.patient_id;
		    LOG.fine(entity);
		    if (true == PatientRecordsWorker.savePatientRecord(record))
		    {
			    String szReturn = String.format("{\"message\": \"Patient Record saved\", \"patient_id\": \"%s\"}", record.patient_id);
				return Response.status(200).entity(szReturn).build();
		    }
		    else
		    {
				return Response.status(500).entity(Helper.response("Internal Error while saving this test case. Please report this.", 500)).build();
		    }

		} catch (JsonParseException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(400).entity(Helper.response("Error parsing data : " + e.getMessage(), 400)).build();
		} catch (JsonMappingException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(400).entity(Helper.response("Error parsing data : " + e.getMessage(), 400)).build();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(500).entity(Helper.response("Internal Error : " + e.getMessage(), 500)).build();
		}
 
	}
	
 
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getRecord(@PathParam("id") String id) {
		ObjectMapper mapper = new ObjectMapper();
		PatientRecord record = null;

		record = PatientRecordsWorker.getPatientRecord(id);

		if (record == null)
		{
			return Response.status(404).entity(Helper.response("Patient Record not found", 404)).build();
		}
 
		try {
			return Response.status(200).entity(mapper.writeValueAsString(record)).build();
		} catch (JsonProcessingException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(500).entity(Helper.response("Internal Error : " + e.getMessage(), 500)).build();

		}
			
	}
	
	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response delTestCase(@PathParam("id") String id) {
		ObjectMapper mapper = new ObjectMapper();

		if (PatientRecordsWorker.deletePatientRecord(id) == true)
		{
			return Response.status(200).entity(Helper.response("Patient Record deleted", 200)).build();

		}
		return Response.status(404).entity(Helper.response("Patient Record not found", 404)).build();
	}
}
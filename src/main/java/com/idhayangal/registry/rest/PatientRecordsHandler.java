package com.idhayangal.registry.rest;
 
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.logging.*;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhayangal.registry.common.Helper;
import com.idhayangal.registry.worker.PatientRecordsWorker;
 
@Path("/patients")
public class PatientRecordsHandler {
	static final Logger LOG = Logger.getLogger(PatientRecordsHandler.class.getName());

	@GET
	@Path("/")
	@Produces("application/json")
	public Response getTestCases(@Context UriInfo info) {
 
		ObjectMapper mapper = new ObjectMapper();
		List<String> tags = info.getQueryParameters().get("tags");
		Object testcases = PatientRecordsWorker.getPatientRecords(tags);
 
		try {
			return Response.status(200).entity(mapper.writeValueAsString(testcases)).build();
		} catch (JsonProcessingException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(500).entity(Helper.response("Internal Error : " + e.getMessage(), 500)).build();

		}
 
	}
 
}
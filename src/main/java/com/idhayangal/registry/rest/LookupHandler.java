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
import com.idhayangal.registry.worker.LookupWorker;
import com.idhayangal.registry.worker.PatientRecordsWorker;
import com.idhayangal.registry.worker.SchemaWorker;


@Path("/lookup")
public class LookupHandler {
	static final Logger LOG = Logger.getLogger(LookupHandler.class.getName());

	@GET
	@Path("/{field}")
	@Produces("application/json")
	public Response getValues(@PathParam("field") String szField) {
		return Response.status(200).entity(LookupWorker.getLookupValues(szField)).build();
		
	}
}
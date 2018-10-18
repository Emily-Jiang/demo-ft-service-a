
package org.eclipse.microprofile.ft.servicea;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
public class ServiceAEndpoint {

	@Inject @RestClient
	
	ServiceBClient client;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Timeout
	@Retry(maxRetries=4)
	@Fallback(fallbackMethod="fallback")
	public String callService() throws Exception {
		//return client.callMe();
		return client.callMe();
	}
	
	public String fallback() {
		return "I am ok.";
	}
	
	
	
}
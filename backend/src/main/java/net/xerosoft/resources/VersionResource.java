package net.xerosoft.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/version")
@Produces(MediaType.TEXT_HTML)
public class VersionResource {
    
    @ConfigProperty(name = "version")
    String version;

    @GET
    public Response version() {
        return Response.ok(version).build();
    }

}

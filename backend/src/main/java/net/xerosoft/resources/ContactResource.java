package net.xerosoft.resources;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import lombok.RequiredArgsConstructor;
import net.xerosoft.dtos.ContactRequest;
import net.xerosoft.models.Contact;
import net.xerosoft.models.User;
import net.xerosoft.services.AuthService;
import net.xerosoft.services.ContactService;

@Path("/contacts")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class ContactResource {
    private final AuthService authService;
    private final ContactService contactService;

    @GET
    @RolesAllowed({"user"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response list(@QueryParam("name") @DefaultValue("") String name) {
        User user = authService.user();
        System.out.println(name);
        return Response.ok(Contact.findByName(user, name)).build();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({"user"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response retrieve(@PathParam("id") Long id) {
        User user = authService.user();
        return Response.ok(contactService.findById(user, id)).build();
    }

    @POST
    @Transactional
    @RolesAllowed({"user"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response create(@Valid ContactRequest req) {
        User user = authService.user();
        Contact contact = contactService
                    .create(user, req.getName(), req.getPhone(), req.getEmail());
        return Response.status(Status.CREATED).entity(contact).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @RolesAllowed({"user"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response update(@PathParam("id") Long id, @Valid ContactRequest req) {
        User user = authService.user();
        contactService.update(user, id, req.getName(), req.getPhone(), req.getEmail());
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @RolesAllowed({"user"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response delete(@PathParam("id") Long id) {
        User user = authService.user();
        contactService.delete(user, id);
        return Response.noContent().build();
    }
}
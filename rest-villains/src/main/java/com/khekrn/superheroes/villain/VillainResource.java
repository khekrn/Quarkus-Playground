package com.khekrn.superheroes.villain;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/villains")
public class VillainResource {

    Logger logger;
    VillainService villainService;

    public VillainResource(Logger logger, VillainService villainService) {
        this.logger = logger;
        this.villainService = villainService;
    }

    @GET
    @Path("/random")
    public Response getRandomVillain() {
        Villain villain = villainService.findRandomVillain();
        logger.debug("Found random villain = " + villain);
        return Response.ok(villain).build();
    }

    @GET
    public Response getAllVillain() {
        List<Villain> villainList = villainService.findAllVillains();
        logger.debug("Total no of villains = " + villainList);
        return Response.ok(villainList).build();
    }

    @GET
    @Path("/{id}")
    public Response getVillain(@RestPath Long id) {
        Villain villain = villainService.findById(id);
        if (villain != null) {
            logger.debug("Found villain " + villain);
            return Response.ok(villain).build();
        } else {
            logger.debug("No villain found with id " + id);
            return Response.status(NOT_FOUND).build();
        }
    }

    @POST
    public Response createVillain(@Valid Villain villain, @Context UriInfo uriInfo) {
        villain = villainService.persistVillain(villain);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(villain.id));
        logger.debug("New villain created with URI " + builder.build().toString());
        return Response.ok(builder.build()).build();
    }

    @PUT
    public Response updateVillain(@Valid Villain villain) {
        villain = villainService.updateVillain(villain);
        logger.debug("Villain updated with new valued " + villain);
        return Response.ok(villain).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVillain(@RestPath Long id) {
        villainService.deleteVillain(id);
        logger.debug("Villain deleted with " + id);
        return Response.noContent().build();
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus !!";
    }
}

package pl.openkp.business.uzytkownicy.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import pl.openkp.business.uzytkownicy.entity.Uzytkownik;

@Stateless
@Path("/profil")
public class UzytkownikResource {

    @PersistenceContext(unitName = "openkp-persistence-unit")
    private EntityManager em;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response zapisz(Uzytkownik entity) {
        if (entity.getId() != null) {
            entity = em.merge(entity);
        } else {
            em.persist(entity);
        }
        return Response.created(UriBuilder.fromResource(UzytkownikResource.class).path(String.valueOf(entity.getId())).build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response usun(@PathParam("id") Long id) {
        Uzytkownik entity = em.find(Uzytkownik.class, id);
        if (entity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        em.remove(entity);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response uzytkownik(@PathParam("id") Long id) {
        Uzytkownik uzytkownik = em.find(Uzytkownik.class, id);
        if (uzytkownik == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(uzytkownik).build();
    }

}

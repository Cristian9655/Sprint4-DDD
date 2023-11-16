package br.com.fiap.demo.sprint4.resource;

import java.util.List;

import br.com.fiap.demo.sprint4.model.Vistorias;
import br.com.fiap.demo.sprint4.service.VistoriasService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/vistorias")
public class VistoriasResource {
	private final VistoriasService vistoriasService = new VistoriasService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vistorias> listarVistorias() {
		return vistoriasService.listarVistorias();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vistorias consultarVistorias(@PathParam("id") Long id) {
		return vistoriasService.consultarVistorias(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastraVistorias(Vistorias vistorias) {
		vistoriasService.cadastraVistorias(vistorias);
		return Response.status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletaVistorias(@PathParam("id") Long id) {
		boolean removido = vistoriasService.deletaVistorias(id);

		if (removido) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}

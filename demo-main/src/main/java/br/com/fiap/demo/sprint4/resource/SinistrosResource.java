package br.com.fiap.demo.sprint4.resource;

import java.util.List;

import br.com.fiap.demo.sprint4.model.Sinistros;
import br.com.fiap.demo.sprint4.service.SinistrosService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sinistros")
public class SinistrosResource {

	private final SinistrosService sinistrosService = new SinistrosService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sinistros> listarBicicletas() {
		return sinistrosService.listarSinistros();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sinistros consultarSinistros(@PathParam("id") Long id) {
		return sinistrosService.consultarSinistros(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarSinistros(Sinistros sinistros) {
		sinistrosService.cadastraSinistros(sinistros);
		return Response.status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletaSinistros(@PathParam("id") Long id) {
		boolean removido = sinistrosService.deletaSinistros(id);

		if (removido) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}

package br.com.fiap.demo.sprint4.resource;

import java.util.List;

import br.com.fiap.demo.sprint4.model.Bicicletas;
import br.com.fiap.demo.sprint4.service.BicicletasService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bicicletas")
public class BicicletasResource {

	private final BicicletasService bicicletasService = new BicicletasService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bicicletas> listarBicicletas() {
		return bicicletasService.listarBicicletas();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bicicletas consultarBicicletas(@PathParam("id") Long id) {
		return bicicletasService.consultarBicicletas(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastraBicicletas(Bicicletas bicicleta) {
		bicicletasService.cadastraBicicletas(bicicleta);
		return Response.status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletaBicicletas(@PathParam("id") Long id) {
		boolean removido = bicicletasService.deletaBicicletas(id);

		if (removido) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaBicicletas(@PathParam("id") Long id, Bicicletas bicicletaAtualizado) {
        boolean atualizado = bicicletasService.atualizaBicicletas(id, bicicletaAtualizado);

        if (atualizado) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

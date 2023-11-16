package br.com.fiap.demo.sprint4.resource;

import java.util.List;

import br.com.fiap.demo.sprint4.model.Clientes;
import br.com.fiap.demo.sprint4.service.ClientesService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
public class ClientesResource {

	private final ClientesService clientesService = new ClientesService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Clientes> listarClientes() {
		return clientesService.listarClientes();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Clientes consultarClientes(@PathParam("id") Long id) {
		return clientesService.consultarClientes(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastraClientes(Clientes cliente) {
		clientesService.cadastraClientes(cliente);
		return Response.status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletaClientes(@PathParam("id") Long id) {
		boolean removido = clientesService.deletaClientes(id);

		if (removido) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaClientes(@PathParam("id") Long id, Clientes clienteAtualizado) {
        boolean atualizado = clientesService.atualizaClientes(id, clienteAtualizado);

        if (atualizado) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
package br.com.fiap.demo.sprint4.service;

import java.util.List;

import br.com.fiap.demo.sprint4.data.BicicletasDao;
import br.com.fiap.demo.sprint4.model.Bicicletas;

public class BicicletasService {
	
	static BicicletasDao dao = new BicicletasDao();
	
	public List<Bicicletas> listarBicicletas() {
		return dao.listarBicicletas();
	}
	
	public Bicicletas consultarBicicletas(Long id) {
		return dao.consultarBicicletas(id);
	}
	
	public boolean cadastraBicicletas(Bicicletas bicicleta) {
		if (!validar(bicicleta)) return false;
		dao.cadastraBicicletas(bicicleta);
		return true;
	}

	private boolean validar(Bicicletas bicicleta) {
	    if (bicicleta.marca().isEmpty()) return false;
	    if (bicicleta.modelo().isEmpty()) return false;
	    if (bicicleta.ano_fabricacao().isEmpty()) return false;
	    if (bicicleta.valor_mercado().isEmpty()) return false;
	    if (bicicleta.num_serie().isEmpty()) return false;
	    
	    return true;
	}

	public boolean deletaBicicletas(Long id) {
		Bicicletas bicicleta = dao.consultarBicicletas(id);

		if (bicicleta != null) {
			dao.deletaBicicletas(id);
			return true;
		} else {
			return false;
		}
	}

	public boolean atualizaBicicletas(Long id, Bicicletas bicicletaAtualizado) {
		Bicicletas bicicletaExistente = dao.consultarBicicletas(id);

		if (bicicletaExistente != null) {
			Bicicletas bicicletaAtualizadoComId = new Bicicletas(
					bicicletaExistente.id(),
					bicicletaAtualizado.marca(),
					bicicletaAtualizado.modelo(),
					bicicletaAtualizado.ano_fabricacao(),
					bicicletaAtualizado.valor_mercado(),
					bicicletaAtualizado.num_serie()
			);

			dao.atualizaBicicletas(bicicletaAtualizadoComId);
			
			return true;
		} else {
			return false;
		}
	}

	

}

package br.com.fiap.demo.sprint4.service;

import java.util.List;

import br.com.fiap.demo.sprint4.data.SinistrosDao;
import br.com.fiap.demo.sprint4.model.Sinistros;

public class SinistrosService {
	
	static SinistrosDao dao = new SinistrosDao();
	
	public List<Sinistros> listarSinistros() {
		return dao.listarSinistros();
	}

	public Sinistros consultarSinistros(Long id) {
		return dao.consultarSinistros(id);
	}

	public boolean cadastraSinistros(Sinistros sinistros) {
		if (!validar(sinistros)) return false;
		dao.cadastraSinistros(sinistros);
		return true;
	}

	private boolean validar(Sinistros sinistros) {
	    if (sinistros.data_sinistro().isEmpty()) return false;
	    if (sinistros.descricao().isEmpty()) return false;
	    if (sinistros.valor_prejuizo().toString().isEmpty()) return false;
	    if (sinistros.id_cliente().toString().isEmpty()) return false;
	    if (sinistros.id_bike().toString().isEmpty()) return false;
	    
	    return true;
	}
	
	public boolean deletaSinistros(Long id) {
		Sinistros sinistros = dao.consultarSinistros(id);

		if (sinistros != null) {
			dao.deletaSinistros(id);
			return true;
		} else {
			return false;
		}
	}

}

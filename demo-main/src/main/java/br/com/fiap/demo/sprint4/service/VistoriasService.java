package br.com.fiap.demo.sprint4.service;

import java.util.List;

import br.com.fiap.demo.sprint4.data.VistoriasDao;
import br.com.fiap.demo.sprint4.model.Vistorias;

public class VistoriasService {

	static VistoriasDao dao = new VistoriasDao();
	
	public List<Vistorias> listarVistorias() {
		return dao.listarVistorias();
	}

	public Vistorias consultarVistorias(Long id) {
		return dao.consultarVistorias(id);
	}

	public boolean cadastraVistorias(Vistorias vistorias) {
		if (!validar(vistorias)) return false;
		dao.cadastraVistorias(vistorias);
		return true;
	}
	
	private boolean validar(Vistorias vistorias) {
		if (vistorias.fotos_videos().isEmpty()) return false;
		if (vistorias.id_cliente().toString().isEmpty()) return false;
		if (vistorias.id_bike().toString().isEmpty()) return false;
		return true;
	}
	public boolean deletaVistorias(Long id) {
		Vistorias vistoria = dao.consultarVistorias(id);

		if (vistoria != null) {
			dao.deletaVistorias(id);
			return true;
		} else {
			return false;
		}
	}

}

package telefonia.model.bo;

import java.util.ArrayList;

import telefonia.model.dao.TelefoneDAO;
import telefonia.model.vo.Telefone;

public class TelefoneBO {
	
	TelefoneDAO dao = new TelefoneDAO();
	

	public ArrayList<Telefone> listarTodos() {
		
		return  dao.consultarTodos();
	}

}


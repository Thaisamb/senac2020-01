package telefonia.controller;

import java.util.ArrayList;

import telefonia.model.bo.TelefoneBO;
import telefonia.model.vo.Telefone;

public class TelefoneController {
	
	TelefoneBO bo = new TelefoneBO();

	public ArrayList<Telefone> listarTodos() {
		
		ArrayList<Telefone> lista = new ArrayList<Telefone>();
		
		return  lista = bo.listarTodos();
		
	}

}

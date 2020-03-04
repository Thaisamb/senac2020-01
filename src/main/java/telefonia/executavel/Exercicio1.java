package telefonia.executavel;

import telefonia.model.Endereco;
import telefonia.model.dao.EnderecoDAO;

public class Exercicio1 {
	
	public static void main(String[] args) {
	
	Endereco e = new Endereco();
	EnderecoDAO enderecoDAO = new EnderecoDAO();
	e = enderecoDAO.consultarPorId(3);
	
	
	System.out.println(e);
	
	
	
	}
}

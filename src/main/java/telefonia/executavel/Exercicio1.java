package telefonia.executavel;

import telefonia.model.Endereco;
import telefonia.model.dao.EnderecoDAO;

public class Exercicio1 {
	public static void main(String[] args) {

		// procurar um ednereco pela DAO =/
		EnderecoDAO dao = new EnderecoDAO();
		Endereco endereco3 = dao.consultarPorId(3);
		Endereco endereco4 = dao.consultarPorId(4);
		Endereco endereco5 = dao.consultarPorId(5);

		// cadastrar 5 clientes
		Telefone t1 = new Telefone(1, null, "051", "048", "999950296", true, true);
		Telefone t2 = new Telefone(2, null, "051", "048", "999950297", true, true);
		Telefone t3 = new Telefone(3, null, "051", "048", "999950298", true, true);
		Telefone t4 = new Telefone(4, null, "051", "048", "999950299", true, true);
		Telefone t5 = new Telefone(5, null, "051", "048", "999950260", true, true);

		ArrayList<Telefone> telefones1 = new ArrayList();
		telefones1.add(t1);
		telefones1.add(t2);

		ArrayList<Telefone> telefones2 = new ArrayList();
		telefones2.add(t3);

		ArrayList<Telefone> telefones3 = new ArrayList();
		telefones3.add(t4);
		telefones3.add(t5);

		Cliente c1 = new Cliente("Maria", "Cardoso", "506.535.870-23", telefones1, endereco3);
		Cliente c2 = new Cliente("Mario", "Cardoso", "884.489.250-25", telefones1, endereco3);
		Cliente c3 = new Cliente("Joao", "Cardoso", "441.635.520-30", telefones2, endereco4);
		Cliente c4 = new Cliente("Mauro", "Cardoso", "689.972.320-45", telefones3, endereco5);
		Cliente c5 = new Cliente("Maria", "Cardoso", "165.871.010-03", telefones3, endereco5);

		ClienteBO clienteBO = new ClienteBO();

		String msg1 = clienteBO.salvar(c1);
		String msg2 = clienteBO.salvar(c2);
		String msg3 = clienteBO.salvar(c3);
		String msg4 = clienteBO.salvar(c4);
		String msg5 = clienteBO.salvar(c5);

	}
}

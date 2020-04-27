package telefonia.model.bo;

import java.util.ArrayList;

import telefonia.model.dao.ClienteDAO;
import telefonia.model.dao.TelefoneDAO;
import telefonia.model.vo.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();
	String msg = "";

	public String salvar(Cliente cliente) {

		if (dao.cpfJaUtilizado(cliente.getCpf())) {
			msg = "CPF informado (" + cliente.getCpf() + ") já foi utilizado";
		} else {
			cliente = dao.salvar(cliente);

			if (cliente.getId() > 0) {
				msg = "Cliente salvo com sucesso";
			} else {
				msg = "Erro ao salvar cliente";
			}
		}

		return msg;
	}

	public String excluir(int id) {

		if (dao.excluir(id)) {
			msg = "Exclusão do cliente (id = " + id + ") realizada com sucesso!";
		} else {
			msg = "Erro ao tentar excluir cliente (id =" + id + ").";
		}
		return msg;

	}

	public String alterar(Cliente cliente) {
		if (dao.alterar(cliente)) {
			msg = "Alteração do cliente realizada com sucesso!";
		} else {
			msg = "Erro ao tentar alterar cliente.";
		}
		return msg;
	}

	public Cliente consultarPorId(int id) {

		return dao.consultarPorId(id);

	}

	public ArrayList<Cliente> consultarTodos() {
		return dao.consultarTodos();
	}

	public String excluirPorCpf(String cpf) {

		Cliente cliente = dao.consultarPorCpf(cpf);

		if (cliente != null) {

			TelefoneDAO telDao = new TelefoneDAO();

			if (telDao.verificarSeClienteTemTelefone(cliente.getId())) {
				msg = "Cliente possui telefone(s) cadastrados, favor primeiro desativar os telefones e assim excluir o cliente.";
			} else {

				if (dao.excluir(cliente.getId())) {
					msg = "Cliente excluído com sucesso!";
				} else {
					msg = "Não foi possível excluir o Cliente.";

				}
			}

		} else {
			msg = "Cliente não cadastrado.";

		}

		return msg;

	}
}
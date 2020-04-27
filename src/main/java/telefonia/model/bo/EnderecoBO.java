package telefonia.model.bo;

import java.util.ArrayList;

import telefonia.model.dao.ClienteDAO;
import telefonia.model.dao.EnderecoDAO;
import telefonia.model.vo.Endereco;

public class EnderecoBO {
	EnderecoDAO endDao = new EnderecoDAO();

	public String excluir(int idSelecionado) {
		String msg = "";

		ClienteDAO clienteDAO = new ClienteDAO();
		if (clienteDAO.temClienteMorandoNoEndereco(idSelecionado)) {
			msg = "Endere�o informado n�o pode ser exclu�do, pois existe cliente relacionado a ele.";
		} else {
			if (endDao.excluir(idSelecionado)) {
				msg = "Exclu�do com sucesso";
			} else {
				msg = "Erro ao excluir";
			}
		}
		return msg;
	}

	public String salvar(Endereco novoEndereco) {
		String msg = "";

		endDao.salvar(novoEndereco);

		if (novoEndereco.getId() > 0) {
			msg = "Salvo com sucesso!";
		} else {
			msg = "Erro ao salvar";
		}
		return msg;
	}

	public String consultarPorId(int idInformado) {
		String msg = "";

		if (!(idInformado > 0)) {
			msg = "Informar um ID > 0 ";
		} else {
			endDao.consultarPorId(idInformado);

		}
		return msg;
	}

	public String editar(Endereco endereco) {

		String msg = "";

		if (!(endereco.getId() > 0)) {
			msg = "Informar um ID > 0 ";
		} else {
			endDao.alterar(endereco);
		}

		return msg;

	}

	public ArrayList<Endereco> consultarTodos() {
		
		return endDao.consultarTodos();
	}

}

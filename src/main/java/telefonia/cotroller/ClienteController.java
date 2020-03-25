package telefonia.cotroller;

import java.util.ArrayList;

import telefonia.model.dao.ClienteDAO;
import telefonia.model.vo.Cliente;


public class ClienteController {
	private ClienteDAO dao = new ClienteDAO();

	public ArrayList<Cliente> listarTodosOsClientes() {
		return dao.consultarTodos();
	}

}

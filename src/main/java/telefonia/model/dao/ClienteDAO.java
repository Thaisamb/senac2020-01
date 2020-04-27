
package telefonia.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import telefonia.model.dao.Banco;
import telefonia.model.vo.Cliente;
import telefonia.model.vo.Endereco;
import telefonia.model.vo.Telefone;

public class ClienteDAO implements BaseDAO<Cliente> {

	public Cliente salvar(Cliente novoCliente) {
		
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, SOBRENOME, CPF, IDENDERECO) " + " VALUES (?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getSobrenome());
			stmt.setString(3, novoCliente.getCpf());
			stmt.setInt(4, novoCliente.getEndereco().getId());
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				novoCliente.setId(idGerado);
				
				if(!novoCliente.getTelefones().isEmpty()) {
					TelefoneDAO telDao = new TelefoneDAO();
					telDao.ativarTelefones(novoCliente, novoCliente.getTelefones());
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo endereço.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novoCliente;
	}

	public boolean excluir(int id) {

		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM CLIENTE WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conn);

		int qteLinhasAfetadas = 0;

		try {
			qteLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente. Erro: " + e.getMessage());
		}

		boolean excluiu = qteLinhasAfetadas > 0;

		if (excluiu) {
			TelefoneDAO telDao = new TelefoneDAO();
			telDao.desativarTelefones(id);
		}

		return excluiu;

	}

	public boolean alterar(Cliente cliente) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE CLIENTE SET NOME=?, SOBRENOME=?, CPF=?, IDENDERECO=? " + " WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSobrenome());
			stmt.setString(3, cliente.getCpf());
			stmt.setInt(4, cliente.getEndereco().getId());
			stmt.setInt(5, cliente.getId());
			registrosAlterados = stmt.executeUpdate();

			TelefoneDAO telDao = new TelefoneDAO();
			telDao.ativarTelefones(cliente, cliente.getTelefones());

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public Cliente consultarPorId(int id) {

		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM Cliente WHERE id = " + id;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		Cliente c = null;

		try {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c = construirClienteDoResultSet(rs);
			}
		} catch (SQLException e) {

			System.out.println("Erro ao consultar cliente com id = " + id);
			System.out.println("Erro: " + e.getMessage());
		}

		return c;

	}

	public ArrayList<Cliente> consultarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente c = construirClienteDoResultSet(rs);
				clientes.add(c);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes.");
			System.out.println("Erro: " + e.getMessage());
		}

		return clientes;
	}

	private Cliente construirClienteDoResultSet(ResultSet rs) {
		
		Cliente c = new Cliente();
		
		try {
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setSobrenome(rs.getString("sobrenome"));
			c.setCpf(rs.getString("cpf"));

			EnderecoDAO enderecoDAO = new EnderecoDAO();
			Endereco end = enderecoDAO.consultarPorId(rs.getInt("idendereco"));
			c.setEndereco(end);

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			ArrayList<Telefone> telefones = telefoneDAO.consultarTodosPorIdCliente(rs.getInt("id"));
			c.setTelefones(telefones);
		} catch (SQLException e) {
			System.out.println("Erro ao construir cliente a partir do ResultSet. Causa: " + e.getMessage());
		}

		return c;
	}

	public boolean cpfJaUtilizado(String cpf) {
		Connection conn = Banco.getConnection();
		String sql = "Select id from cliente c where c.cpf = '" + cpf + "'";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		boolean cpfExistente = false;

		try {
			ResultSet rs = stmt.executeQuery();
			cpfExistente = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao procurar cpf. Causa:" + e.getMessage());
		}

		return cpfExistente;

	}

	public boolean excluirPorCpf(String cpf) {

		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM CLIENTE WHERE CPF= '" + cpf+"'";
		Statement stmt = Banco.getStatement(conn);
		int quantidadeLinhasAfetadas = 0;

		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente pelo CPF.");
			System.out.println("Erro: " + e.getMessage());
		}

		return quantidadeLinhasAfetadas > 0;

	}

	public Cliente consultarPorCpf(String cpf) {
		
		Connection conn = Banco.getConnection();
		String sql = "SELECT FROM CLIENTE WHERE CPF= '" + cpf+"'";
		PreparedStatement stmt = Banco.getPreparedStatement(conn,sql);
		Cliente c = null;
		
		try {
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				c =construirClienteDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar cliente com cpf = " + cpf);
			System.out.println("Erro: " + e.getMessage());
		}
		return c;
		
	}

	public boolean temClienteMorandoNoEndereco(int idEndereco) {
		
		Connection conn = Banco.getConnection();
		String sql = "SELECT id FROM Cliente WHERE idEndereco= " + idEndereco;
		Statement stmt = Banco.getStatement(conn);
		boolean temCliente = false;
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			temCliente = rs.next();
			
		}catch (SQLException e) {
			System.out.println("Erro ao verificar se tem cliente relacionado ao endereço (idEndereco = "+idEndereco+")");
			System.out.println("Erro:"+e.getMessage());
		}
		
		return temCliente;
	}
	
	
}

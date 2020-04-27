package telefonia.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import telefonia.model.vo.Endereco;

public class EnderecoDAO implements BaseDAO<Endereco> {

	public Endereco salvar(Endereco novaEntidade) {

		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO ENDERECO (CEP, ESTADO, CIDADE, RUA, BAIRRO, NUMERO) " + " VALUES ( "
				+ novaEntidade.getCep() + ", " + novaEntidade.getEstado() + "," + novaEntidade.getCidade() + ", "
				+ novaEntidade.getRua() + "," + novaEntidade.getBairro() + "," + novaEntidade.getNumero() + ")";

		PreparedStatement statement = Banco.getPreparedStatement(conexao, sql);
		try {
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultado = statement.getGeneratedKeys();

			if (resultado.next()) {
				novaEntidade.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(" Erro ao salvar novo endereço. Causa: " + e.getMessage());
		}

		return novaEntidade;
	}

	public boolean excluir(int id) {

		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM endereco WHERE id=" + id;
		PreparedStatement pstm = Banco.getPreparedStatement(conn, sql);
		int qdeLinhasAfetadas = 0;

		try {
			qdeLinhasAfetadas = pstm.executeUpdate(sql);
		} catch (SQLException ex) {
			System.out.println(" Erro ao tentar excluir endereço. Id: " + id + " .Causa: " + ex.getMessage());
		}

		return qdeLinhasAfetadas > 0;
	}

	public boolean alterar(Endereco endereco) {

		Connection conn = Banco.getConnection();
		String sql = "UPDATE ENDERECO SET CEP=?, ESTADO=?, CIDADE=?, RUA=?, BAIRRO=?, NUMERO=? WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int qdeItensAlterados = 0;

		try {
			stmt.setString(1, endereco.getCep());
			stmt.setString(2, endereco.getEstado());
			stmt.setString(3, endereco.getCidade());
			stmt.setString(4, endereco.getRua());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getNumero());
			stmt.setInt(7, endereco.getId());

			qdeItensAlterados = stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(
					"Erro ao Editar dados do Endereço com id =" + endereco.getId() + ". Causa = " + e.getMessage());
		}

		return qdeItensAlterados > 0;
	}

	public Endereco consultarPorId(int id) {
		Connection conexao = Banco.getConnection();

		String sql = " SELECT id, cep, bairro, cidade, estado " + " FROM endereco WHERE id = ?";
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		Endereco enderecoConsultado = null;
		try {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				enderecoConsultado = construirEnderecoDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar endereço. Id: " + id + " .Causa: " + ex.getMessage());
		}
		return enderecoConsultado;
	}

	private Endereco construirEnderecoDoResultSet(ResultSet conjuntoResultante) {
		Endereco e = new Endereco();
		try {
			e.setId(conjuntoResultante.getInt("id"));
			e.setCep(conjuntoResultante.getString("cep"));
			e.setBairro(conjuntoResultante.getString("bairro"));
			e.setCidade(conjuntoResultante.getString("cidade"));
			e.setEstado(conjuntoResultante.getString("estado"));
		} catch (SQLException ex) {
			System.out.println(" Erro ao construir endereço a partir do ResultSet. Causa: " + ex.getMessage());
		}
		return e;
	}

	public ArrayList<Endereco> consultarTodos() {
		Connection conn = Banco.getConnection();
		String sql = "SELECT * FROM ENDERECO ";
		PreparedStatement stm = Banco.getPreparedStatement(conn, sql);

		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

		try {
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Endereco endereco = construirEnderecoDoResultSet(rs);
				enderecos.add(endereco);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os endereços. Erro: " + e.getMessage());
		}
		return enderecos;
	}

}

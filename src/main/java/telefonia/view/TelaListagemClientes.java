package telefonia.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import telefonia.controller.ClienteController;
import telefonia.model.vo.Cliente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListagemClientes {

	private JFrame frmListagemDeClientes;
	private String[] nomeColunas = { "Nome Completo", "CPF", "Qde Telefones" };
	private ArrayList<Cliente> clientes;
	private JTable tblClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemClientes window = new TelaListagemClientes();
					window.frmListagemDeClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemClientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemDeClientes = new JFrame();
		frmListagemDeClientes.setTitle("Listagem de Clientes");
		frmListagemDeClientes.setBounds(100, 100, 700, 520);
		frmListagemDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemDeClientes.getContentPane().setLayout(null);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController controle = new ClienteController();
				controle.listarClientes();
				limparTabela();
				atualizarTabela();
			}
		});
		btnConsultar.setBounds(25, 23, 89, 23);
		frmListagemDeClientes.getContentPane().add(btnConsultar);

		JTable tblClientes = new JTable();
		tblClientes.setBounds(25, 70, 650, 400);
		frmListagemDeClientes.getContentPane().add(tblClientes);

	}

	private void atualizarTabela() {
		DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();

		for (Cliente c : clientes) {

			Object[] linhaTabela = new Object[3];
			linhaTabela[0] = c.getNomeCompleto();
			linhaTabela[1] = c.getCpf();
			linhaTabela[2] = c.getTelefones().size();
			model.addRow(linhaTabela);
		}

	}

	private void limparTabela() {
		tblClientes.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
	}

}

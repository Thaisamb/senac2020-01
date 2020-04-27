package telefonia.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import telefonia.controller.TelefoneController;
import telefonia.model.vo.Telefone;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListagemTelefones {

	private JFrame frmListagemDeTelefones;
	private String[] nomeColunas = { "Cod.País", "DDD", "Número", "Móvel", "Ativo" };
	private ArrayList<Telefone> telefones;
	private JTable tblTelefones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemTelefones window = new TelaListagemTelefones();
					window.frmListagemDeTelefones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemTelefones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemDeTelefones = new JFrame();
		frmListagemDeTelefones.setTitle("Listagem de Telefones");
		frmListagemDeTelefones.setBounds(100, 100, 700, 520);
		frmListagemDeTelefones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemDeTelefones.getContentPane().setLayout(null);

		JButton btnConsultarTel = new JButton("Consultar");
		btnConsultarTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelefoneController controle = new TelefoneController();
				telefones = controle.listarTodos();
				limparTela();
				atualizarTela();
			}
		});
		btnConsultarTel.setBounds(25, 23, 89, 23);
		frmListagemDeTelefones.getContentPane().add(btnConsultarTel);

		tblTelefones = new JTable();
		tblTelefones.setBounds(25, 70, 650, 400);
		frmListagemDeTelefones.getContentPane().add(tblTelefones);
	}

	private void limparTela() {
		tblTelefones.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));

	}

	private void atualizarTela() {
		DefaultTableModel model = (DefaultTableModel) tblTelefones.getModel();

		for (Telefone t : telefones) {

			Object[] linhaTabela = new Object[5];
			linhaTabela[0] = t.getCodigoPais();
			linhaTabela[1] = t.getDdd();
			linhaTabela[2] = t.getNumero();
			linhaTabela[3] = t.isMovel();
			linhaTabela[4] = t.isAtivo();

			model.addRow(linhaTabela);

		}

	}

}

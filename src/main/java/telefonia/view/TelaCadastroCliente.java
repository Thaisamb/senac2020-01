package telefonia.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import telefonia.controller.ClienteController;
import telefonia.controller.EnderecoController;
import telefonia.model.vo.Endereco;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaCadastroCliente {

	private JFrame frmCadastroDeCliente;
	private JTextField txtNomeCliente;
	private JTextField txtSobrenome;
	private JTextField txtCpf;
	private JComboBox<Endereco> cboxEnderecos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente window = new TelaCadastroCliente();
					window.frmCadastroDeCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroCliente() {
		initialize();
	}

	private void initialize() {
		frmCadastroDeCliente = new JFrame();
		frmCadastroDeCliente.setTitle("Cadastro de Cliente ");
		frmCadastroDeCliente.setBounds(100, 100, 450, 200);
		frmCadastroDeCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeCliente.getContentPane().setLayout(null);

		JLabel lblNomeCliente = new JLabel("Nome* :");
		lblNomeCliente.setBounds(10, 11, 46, 14);
		frmCadastroDeCliente.getContentPane().add(lblNomeCliente);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(56, 8, 136, 20);
		frmCadastroDeCliente.getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JLabel lblSobreNome = new JLabel("Sobrenome *:");
		lblSobreNome.setBounds(211, 11, 67, 14);
		frmCadastroDeCliente.getContentPane().add(lblSobreNome);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(288, 8, 136, 20);
		frmCadastroDeCliente.getContentPane().add(txtSobrenome);
		txtSobrenome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF*:");
		lblCpf.setBounds(10, 48, 46, 14);
		frmCadastroDeCliente.getContentPane().add(lblCpf);

		MaskFormatter maskCpf;
		try {
			maskCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(maskCpf);
			txtCpf.setBounds(56, 45, 104, 20);
			frmCadastroDeCliente.getContentPane().add(txtCpf);
			txtCpf.setColumns(10);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		JLabel lblEndereco = new JLabel("Endereço*:");
		lblEndereco.setBounds(10, 88, 67, 14);
		frmCadastroDeCliente.getContentPane().add(lblEndereco);

		EnderecoController controleEnd = new EnderecoController();
		ArrayList<Endereco> listaEnderecos = controleEnd.consultarTodos();
		final JComboBox<Endereco> cboxEnderecos = new JComboBox<Endereco>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(listaEnderecos.toArray());
		cboxEnderecos.setModel(model);
		cboxEnderecos.setBounds(78, 88, 346, 22);
		frmCadastroDeCliente.getContentPane().add(cboxEnderecos);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController controle = new ClienteController();
				controle.salvar(txtNomeCliente.getText(), txtSobrenome.getText(), txtCpf.getText(),
						(Endereco) cboxEnderecos.getSelectedItem());
			}
		});
		btnSalvar.setBounds(78, 127, 89, 23);
		frmCadastroDeCliente.getContentPane().add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}

		});
		btnLimpar.setBounds(256, 127, 89, 23);
		frmCadastroDeCliente.getContentPane().add(btnLimpar);
	}

	private void limparCampos() {

		this.txtNomeCliente.setText("");
		this.txtSobrenome.setText("");
		this.txtCpf.setText("");

		this.cboxEnderecos.setSelectedIndex(0);

	}

}

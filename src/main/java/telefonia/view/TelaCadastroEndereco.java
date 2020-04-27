package telefonia.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import telefonia.controller.EnderecoController;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;

public class TelaCadastroEndereco extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCep;
	private JComboBox comboBoxEstado;
	private JTextField txtCidade;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEndereco frame = new TelaCadastroEndereco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroEndereco() {
		setTitle("Cadastro de Endereço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(87, 11, 211, 20);
		contentPane.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		JLabel lblLougradouro = new JLabel("Lougradouro:");
		lblLougradouro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLougradouro.setBounds(17, 14, 71, 14);
		contentPane.add(lblLougradouro);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumero.setBounds(306, 14, 46, 14);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(350, 11, 58, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBairro.setBounds(17, 49, 46, 14);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setText("");
		txtBairro.setBounds(87, 46, 152, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCep.setBounds(271, 49, 23, 14);
		contentPane.add(lblCep);
		
		try {
			
			MaskFormatter mascCep = new MaskFormatter("#####-###");
			
			txtCep = new JFormattedTextField(mascCep);
			txtCep.setBounds(322, 46, 86, 20);
			contentPane.add(txtCep);
			txtCep.setColumns(10);
			
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEstado.setBounds(252, 87, 46, 14);
		contentPane.add(lblEstado);
		
		ArrayList<String> siglaEstados = listarEstados();
		comboBoxEstado = new JComboBox(siglaEstados.toArray());
		comboBoxEstado.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxEstado.setBounds(312, 84, 96, 20);
		contentPane.add(comboBoxEstado);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCidade.setBounds(17, 87, 46, 14);
		contentPane.add(lblCidade);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(252, 128, 110, 23);
		contentPane.add(btnLimpar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController endController = new EnderecoController();
				endController.salvar(txtLogradouro.getText(), txtBairro.getText(), txtNumero.getText(), txtCep.getText()
						, txtCidade.getText(), (String) comboBoxEstado.getSelectedItem());
			}
		});
		btnSalvar.setBounds(87, 128, 110, 23);
		contentPane.add(btnSalvar);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(87, 84, 152, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
	}

	private ArrayList<String> listarEstados() {
		
		ArrayList<String> lista = new ArrayList<String>();
		lista.add(">>Selecione<<");
		lista.add("AC");
		lista.add("AL");
		lista.add("AP");
		lista.add("AM");
		lista.add("BA");
		lista.add("CE");
		lista.add("DF");
		lista.add("ES");
		lista.add("GO");
		lista.add("MA");
		lista.add("MT");
		lista.add("MS");
		lista.add("MG");
		lista.add("PA");
		lista.add("PB");
		lista.add("PR");
		lista.add("PE");
		lista.add("PI");
		lista.add("RJ");
		lista.add("RN");
		lista.add("RS");
		lista.add("RO");
		lista.add("RR");
		lista.add("SC");
		lista.add("SP");
		lista.add("SE");
		lista.add("TO");

		return lista;
	}
	
	private void limparCampos() {
		
		this.txtLogradouro.setText("") ;
		this.txtNumero.setText("");
		this.txtBairro.setText("");
		this.txtCep.setText("");
		this.txtCidade.setText("");
		
		this.comboBoxEstado.setSelectedIndex(0);
		
	}
	
}



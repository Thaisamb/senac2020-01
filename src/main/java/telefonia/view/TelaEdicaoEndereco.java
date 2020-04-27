package telefonia.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import telefonia.controller.EnderecoController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class TelaEdicaoEndereco extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdEndEdit;
	private JTextField txtRuaEdit;
	private JTextField txtNumEdit;
	private JTextField txtBairroEdit;
	private JTextField txtCidadeEdit;
	private JTextField txtCepEdit;
	private final JComboBox cbxEstadoEdit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdicaoEndereco frame = new TelaEdicaoEndereco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEdicaoEndereco() {
		this.cbxEstadoEdit = new JComboBox();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIDEndEdit = new JLabel("ID Endere\u00E7o: ");
		lblIDEndEdit.setBounds(10, 21, 97, 14);
		contentPane.add(lblIDEndEdit);

		txtIdEndEdit = new JTextField();
		txtIdEndEdit.setBounds(117, 18, 96, 20);
		contentPane.add(txtIdEndEdit);
		txtIdEndEdit.setColumns(10);

		JLabel lblRuaEdit = new JLabel("Logradouro:");
		lblRuaEdit.setBounds(10, 52, 97, 14);
		contentPane.add(lblRuaEdit);

		txtRuaEdit = new JTextField();
		txtRuaEdit.setBounds(117, 49, 240, 20);
		contentPane.add(txtRuaEdit);
		txtRuaEdit.setColumns(10);

		JLabel lblNumeroEdit = new JLabel("N\u00FAmero:");
		lblNumeroEdit.setBounds(10, 83, 97, 14);
		contentPane.add(lblNumeroEdit);

		txtNumEdit = new JTextField();
		txtNumEdit.setBounds(117, 80, 99, 20);
		contentPane.add(txtNumEdit);
		txtNumEdit.setColumns(10);

		JLabel lblBairroEdit = new JLabel("Bairro:");
		lblBairroEdit.setBounds(226, 86, 46, 14);
		contentPane.add(lblBairroEdit);

		txtBairroEdit = new JTextField();
		txtBairroEdit.setBounds(271, 80, 86, 20);
		contentPane.add(txtBairroEdit);
		txtBairroEdit.setColumns(10);

		JLabel lblCidadeEdit = new JLabel("Cidade:");
		lblCidadeEdit.setBounds(10, 114, 86, 14);
		contentPane.add(lblCidadeEdit);

		txtCidadeEdit = new JTextField();
		txtCidadeEdit.setBounds(117, 111, 97, 20);
		contentPane.add(txtCidadeEdit);
		txtCidadeEdit.setColumns(10);

		JLabel lblEstadoEdit = new JLabel("Estado:");
		lblEstadoEdit.setBounds(226, 111, 46, 14);
		contentPane.add(lblEstadoEdit);

		ArrayList<String> estados = listarEstados();
		final JComboBox cbxEstadoEdit = new JComboBox(estados.toArray());
		cbxEstadoEdit.setBounds(296, 107, 40, 20);
		cbxEstadoEdit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(cbxEstadoEdit);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController controller = new EnderecoController();
				controller.editar(txtIdEndEdit.getText(), txtRuaEdit.getText(), txtBairroEdit.getText(),
						txtNumEdit.getText(), txtCepEdit.getText(), txtCidadeEdit.getText(),
						(String) cbxEstadoEdit.getSelectedItem());
			}
		});

		btnEditar.setBounds(133, 157, 89, 23);
		contentPane.add(btnEditar);

		JLabel lblCepEdit = new JLabel("CEP:");
		lblCepEdit.setBounds(223, 21, 46, 14);
		contentPane.add(lblCepEdit);

		try {

			MaskFormatter mascCep = new MaskFormatter("#####-###");

			txtCepEdit = new JFormattedTextField(mascCep);
			txtCepEdit.setBounds(271, 18, 86, 20);
			contentPane.add(txtCepEdit);
			txtCepEdit.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

		this.txtIdEndEdit.setText("");
		this.txtRuaEdit.setText("");
		this.txtNumEdit.setText("");
		this.txtBairroEdit.setText("");
		this.txtCepEdit.setText("");
		this.txtCidadeEdit.setText("");
		this.cbxEstadoEdit.setSelectedIndex(0);

	}

}

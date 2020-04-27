package telefonia.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import telefonia.controller.EnderecoController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaExclusaoEndereco extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExclusaoEndereco frame = new TelaExclusaoEndereco();
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
	public TelaExclusaoEndereco() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdEndereco = new JLabel("Digite o ID do Endere\u00E7o:");
		lblIdEndereco.setBounds(10, 28, 124, 14);
		contentPane.add(lblIdEndereco);
		
		txtId = new JTextField();
		txtId.setBounds(133, 25, 41, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController endController = new EnderecoController();
				endController.excluir(txtId.getText());
			}
		});
		btnExcluir.setBounds(45, 74, 89, 23);
		contentPane.add(btnExcluir);
	}

}

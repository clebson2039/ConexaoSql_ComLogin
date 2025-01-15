package classes_de_conexao;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_de_login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_login frame = new Tela_de_login();
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
	public Tela_de_login() {
		setResizable(false);
		setTitle("Tela de login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 244, 265);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNewLabel.setBounds(32, 58, 76, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(32, 101, 76, 34);
		contentPane.add(lblNewLabel_1);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		tfUsuario.setBounds(90, 66, 86, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(90, 109, 86, 20);
		contentPane.add(tfSenha);
		
		JButton btnNewButton = new JButton("Acessar");
		btnNewButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				try {
					
		            // establish a connection
		            Connection conn = DriverManager.getConnection("jdbc:sqlserver://;serverName=localhost;databaseName=master;trustServerCertificate=true", "sa", "Ng4048fg");

				    if (conn != null) {
				        String sql = "select * from db_login.dbo.Persons where usuario=? and senha=?";
				        PreparedStatement stmt = conn.prepareStatement(sql);
				        stmt.setString(1, tfUsuario.getText());
				        stmt.setString(2, new String(tfSenha.getPassword()));
				        java.sql.ResultSet rs = stmt.executeQuery();

				        if (rs.next()) {
				            JOptionPane.showMessageDialog(null, "Esse usuário existe");
				        } else {
				            JOptionPane.showMessageDialog(null, "Este usuário não existe");
				        }

				        stmt.close();
				        conn.close(); // Close the connection after use
				    } else {
				        JOptionPane.showMessageDialog(null, "Error: Connection is null!");
				    }
				} catch (SQLException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, "Error connecting to database: " + e1.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton.setBounds(90, 166, 86, 23);
		contentPane.add(btnNewButton);
	}
}

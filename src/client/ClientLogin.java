package client;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;
import main.ChooseLogin;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Font;

public class ClientLogin extends JFrame{
	
	static String username, password;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	public ClientLogin() {
		
		setTitle("Login Account");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		UsernameField = new JTextField();
		getContentPane().add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		springLayout.putConstraint(SpringLayout.NORTH, UsernameField, -3, SpringLayout.NORTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, UsernameField, 6, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -253, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 124, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, -31, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblUsername);
		getContentPane().add(lblPassword);
		
		JButton LoginButton = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, LoginButton, 160, SpringLayout.NORTH, getContentPane());
		getContentPane().add(LoginButton);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		springLayout.putConstraint(SpringLayout.WEST, btnForgotPassword, 199, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, LoginButton, -5, SpringLayout.WEST, btnForgotPassword);
		springLayout.putConstraint(SpringLayout.SOUTH, btnForgotPassword, 0, SpringLayout.SOUTH, LoginButton);
		getContentPane().add(btnForgotPassword);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, lblPassword);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, -19, SpringLayout.NORTH, LoginButton);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, UsernameField);
		getContentPane().add(passwordField);
		
		JLabel lblWelcomeToStudent = new JLabel("Welcome to Student Information Center");
		springLayout.putConstraint(SpringLayout.NORTH, lblWelcomeToStudent, 29, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblWelcomeToStudent, 25, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblWelcomeToStudent, -6, SpringLayout.NORTH, UsernameField);
		springLayout.putConstraint(SpringLayout.EAST, lblWelcomeToStudent, 24, SpringLayout.EAST, getContentPane());
		lblWelcomeToStudent.setFont(new Font("Cambria", Font.BOLD, 18));
		getContentPane().add(lblWelcomeToStudent);
		
		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnBack);
		
		
		//Login button
		LoginButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				if(e.getSource()==LoginButton) {
					username= UsernameField.getText();
					password= passwordField.getText();
					
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					
					if(!username.equals("")&& !password.equals("")) {
						
						String sql ="Select * from clientlogin Where username='" + username + "' and password='" + password + "'";
						PreparedStatement statement = conn.prepareStatement(sql);
						ResultSet rs = (ResultSet) statement.executeQuery(sql);
						 
				            if (rs.next()) {
						ClientDashboard dashboard= new ClientDashboard();
						
						dispose();
				            }
				            else {
								JOptionPane.showMessageDialog(null, "Incorrect Username/Password Combination");
							}
					}
					else if(username.equals("")&& password.equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter Username and Password");
					}
					
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
				
			}
		});
		
		//back button
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource()==btnBack) {
						ChooseLogin login = new ChooseLogin();
						dispose();
					}
				}
				catch(Exception ex) {
					
				}
				// TODO Auto-generated method stub
				
			}
		});
	}
}

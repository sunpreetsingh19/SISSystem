package Admin;

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
import java.awt.Font;

public class AdminLogin extends JFrame{
	
	static String username, password;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	public AdminLogin() {
		
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
		getContentPane().add(LoginButton);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		springLayout.putConstraint(SpringLayout.WEST, btnForgotPassword, 199, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, LoginButton, -5, SpringLayout.WEST, btnForgotPassword);
		springLayout.putConstraint(SpringLayout.SOUTH, btnForgotPassword, 0, SpringLayout.SOUTH, LoginButton);
		getContentPane().add(btnForgotPassword);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, LoginButton, 19, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, UsernameField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -3, SpringLayout.NORTH, lblPassword);
		getContentPane().add(passwordField);
		
		JLabel lblWelcomeAdministrator = new JLabel("Welcome Administrator");
		lblWelcomeAdministrator.setFont(new Font("Cambria", Font.BOLD, 20));
		springLayout.putConstraint(SpringLayout.NORTH, lblWelcomeAdministrator, -50, SpringLayout.NORTH, UsernameField);
		springLayout.putConstraint(SpringLayout.WEST, lblWelcomeAdministrator, 41, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblWelcomeAdministrator, -18, SpringLayout.NORTH, UsernameField);
		springLayout.putConstraint(SpringLayout.EAST, lblWelcomeAdministrator, -5, SpringLayout.EAST, btnForgotPassword);
		getContentPane().add(lblWelcomeAdministrator);
		
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
						
						String sql ="Select * from adminlogin Where username='" + username + "' and password='" + password + "'";
						PreparedStatement statement = conn.prepareStatement(sql);
						ResultSet rs = (ResultSet) statement.executeQuery(sql);
						 
				            if (rs.next()) {
						AdminDashboard dashboard= new AdminDashboard();
						
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
					
					if(e.getSource()==btnBack) {
						ChooseLogin login= new ChooseLogin();
						dispose();
					}
				}
				catch(Exception ex) {
					
					ex.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
	}
}

package client;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class ClientLogin extends JFrame{
	
	static String username;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	public ClientLogin() {
		
		setTitle("Login Account");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		UsernameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, UsernameField, 73, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, UsernameField, -161, SpringLayout.EAST, getContentPane());
		getContentPane().add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, 0, SpringLayout.SOUTH, UsernameField);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -6, SpringLayout.WEST, UsernameField);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 31, SpringLayout.SOUTH, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblUsername);
		getContentPane().add(lblPassword);
		
		JButton ButtonSubmit = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, ButtonSubmit, 67, SpringLayout.SOUTH, UsernameField);
		springLayout.putConstraint(SpringLayout.WEST, ButtonSubmit, 0, SpringLayout.WEST, UsernameField);
		getContentPane().add(ButtonSubmit);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		springLayout.putConstraint(SpringLayout.WEST, btnForgotPassword, 5, SpringLayout.EAST, ButtonSubmit);
		springLayout.putConstraint(SpringLayout.SOUTH, btnForgotPassword, 0, SpringLayout.SOUTH, ButtonSubmit);
		getContentPane().add(btnForgotPassword);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -3, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, UsernameField);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, UsernameField);
		getContentPane().add(passwordField);
		
		
		//Login button
		ButtonSubmit.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				if(e.getSource()==ButtonSubmit) {
					username= UsernameField.getText();
					String password= passwordField.getText();
					
					if(username.equals("bunny")&& password.equals("bunny")) {
						ClientDashboard dashboard= new ClientDashboard();
						
						dispose();
					}
					else if(username.equals("")&& password.equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter Username and Password");
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect Username/Password Combination");
					}
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
				
			}
		});
	}
}

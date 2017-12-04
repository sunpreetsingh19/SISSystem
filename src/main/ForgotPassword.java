package main;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ForgotPassword extends JFrame{
	private JTextField studentIdField;
	private JTextField textFieldAnswer;
	private JTextField textFieldNewPassword;
	String studentId;
	public ForgotPassword() {
		
		
		setTitle("Reset your password");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblStudentId = new JLabel("StudentId");
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentId, 45, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStudentId, 53, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStudentId);
		
		studentIdField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, studentIdField, 0, SpringLayout.NORTH, lblStudentId);
		springLayout.putConstraint(SpringLayout.WEST, studentIdField, 74, SpringLayout.EAST, lblStudentId);
		getContentPane().add(studentIdField);
		studentIdField.setColumns(10);
		
		JLabel lblSecurityQuestion = new JLabel("Your Security Question");
		springLayout.putConstraint(SpringLayout.NORTH, lblSecurityQuestion, 24, SpringLayout.SOUTH, lblStudentId);
		springLayout.putConstraint(SpringLayout.WEST, lblSecurityQuestion, 51, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblSecurityQuestion);
		
		JLabel lblAnswer = new JLabel("Answer");
		springLayout.putConstraint(SpringLayout.NORTH, lblAnswer, 27, SpringLayout.SOUTH, lblSecurityQuestion);
		springLayout.putConstraint(SpringLayout.WEST, lblAnswer, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblAnswer);
		
		JLabel lblNewPassword = new JLabel("New Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewPassword, 26, SpringLayout.SOUTH, lblAnswer);
		springLayout.putConstraint(SpringLayout.WEST, lblNewPassword, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblNewPassword);
		
		textFieldAnswer = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldAnswer, -3, SpringLayout.NORTH, lblAnswer);
		springLayout.putConstraint(SpringLayout.WEST, textFieldAnswer, 0, SpringLayout.WEST, studentIdField);
		textFieldAnswer.setText("");
		getContentPane().add(textFieldAnswer);
		textFieldAnswer.setColumns(10);
		
		textFieldNewPassword = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldNewPassword, -3, SpringLayout.NORTH, lblNewPassword);
		springLayout.putConstraint(SpringLayout.WEST, textFieldNewPassword, 0, SpringLayout.WEST, studentIdField);
		textFieldNewPassword.setEditable(false);
		getContentPane().add(textFieldNewPassword);
		textFieldNewPassword.setColumns(10);
		
		JComboBox comboBoxSecurityQuestion = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxSecurityQuestion, -3, SpringLayout.NORTH, lblSecurityQuestion);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxSecurityQuestion, 0, SpringLayout.WEST, studentIdField);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxSecurityQuestion, 217, SpringLayout.EAST, lblSecurityQuestion);
		comboBoxSecurityQuestion.setModel(new DefaultComboBoxModel(new String[] {"What is your father's name?", "What is your Mother Maiden Name?", "Which is your birth place?", "Who is your childhood's friend?", ""}));
		getContentPane().add(comboBoxSecurityQuestion);
		
		JButton btnResetPassword = new JButton("Reset Password");
		springLayout.putConstraint(SpringLayout.SOUTH, btnResetPassword, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnResetPassword, -99, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnResetPassword);
		
		btnResetPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					String newPassword= textFieldNewPassword.getText();
					
					
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();

					String sql = "Update student_accessible set password= '"+newPassword+"' Where studentid='"+studentId+"'";
					PreparedStatement statement = conn.prepareStatement(sql);
					 statement.executeUpdate(sql);

					
					JOptionPane.showMessageDialog(null, "Password successfully changed. \n Proceed to Login");
					dispose();
					new LoginChoose();

					statement.close();
					conn.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnResetPassword);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 6, SpringLayout.EAST, btnResetPassword);
		getContentPane().add(btnCancel);
		
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginChoose();
				dispose();
				
			}
		});
		
		JButton btnCheckProfile = new JButton("Check Profile");
		springLayout.putConstraint(SpringLayout.WEST, btnCheckProfile, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnCheckProfile, 0, SpringLayout.SOUTH, btnResetPassword);
		getContentPane().add(btnCheckProfile);
		
		
		btnCheckProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					studentId= studentIdField.getText();
					
					
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();

					String sql = "Select * from student_accessible Where studentid='"+studentId+"'";
					PreparedStatement statement = conn.prepareStatement(sql);
					ResultSet rs = (ResultSet) statement.executeQuery(sql);

					if (rs.next()) {

						studentIdField.setEditable(false);
						comboBoxSecurityQuestion.setEditable(false);
						textFieldAnswer.setEditable(false);
						textFieldNewPassword.setEditable(true);
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Student ID, Security Question and Security answer combination not found");
					}

					statement.close();
					conn.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}				
			}
		});
		
		
		
		
	}
}

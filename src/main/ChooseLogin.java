package main;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Admin.AdminLogin;
import client.ClientLogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ChooseLogin extends JFrame{
	public ChooseLogin() {
		
		setTitle("Choose your Login Option");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		//Student login button
		JButton btnLoginAsStudent = new JButton("Login as Student");
		btnLoginAsStudent.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnLoginAsStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					if(e1.getSource()==btnLoginAsStudent) {
						ClientLogin login= new ClientLogin();
						dispose();
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLoginAsStudent, 62, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnLoginAsStudent, 125, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnLoginAsStudent, 105, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLoginAsStudent, 293, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnLoginAsStudent);
		
		
		// Admin login button
		JButton btnLoginAsAdmin = new JButton("Login as Administrator");
		springLayout.putConstraint(SpringLayout.NORTH, btnLoginAsAdmin, 41, SpringLayout.SOUTH, btnLoginAsStudent);
		springLayout.putConstraint(SpringLayout.WEST, btnLoginAsAdmin, 0, SpringLayout.WEST, btnLoginAsStudent);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLoginAsAdmin, 84, SpringLayout.SOUTH, btnLoginAsStudent);
		springLayout.putConstraint(SpringLayout.EAST, btnLoginAsAdmin, 293, SpringLayout.WEST, getContentPane());
		btnLoginAsAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				
				try {
					if(e2.getSource()==btnLoginAsAdmin) {
						AdminLogin login= new AdminLogin();
						dispose();
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				
				
			}
		}});
		btnLoginAsAdmin.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(btnLoginAsAdmin);
	}
}

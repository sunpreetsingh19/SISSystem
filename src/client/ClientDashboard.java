package client;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JScrollBar;

public class ClientDashboard extends JFrame {

	public ClientDashboard() {
		getContentPane().setBackground(SystemColor.activeCaption);

		setTitle("Welcome " + ClientLogin.username);
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JButton btnLogout = new JButton("Logout");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLogout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 49, SpringLayout.SOUTH, btnLogout);
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, -16, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 0, SpringLayout.EAST, btnLogout);
		getContentPane().add(tabbedPane);

	
		
		JPanel dashboard = new JPanel();
		tabbedPane.addTab("Dashboard", null, dashboard, null);

		JPanel courseRegister = new JPanel();
		tabbedPane.addTab("Register Course", null, courseRegister, null);

		JPanel accEnquiry = new JPanel();
		tabbedPane.addTab("Account Enquiry", null, accEnquiry, null);

		JPanel acedamics = new JPanel();
		tabbedPane.addTab("Acedamics", null, acedamics, null);

		// logout button code

		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnLogout) {
						ClientLogin login = new ClientLogin();
						dispose();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		/*// add button
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnAdd) {
						String textfield = textField.getText();
						model.addElement(textfield);
					}

				}

				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});*/
	}
}

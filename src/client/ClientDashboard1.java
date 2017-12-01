package client;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import main.LoginChoose;

public class ClientDashboard1 extends JFrame{
	public ClientDashboard1() {
		getContentPane().setBackground(SystemColor.activeCaption);

		setTitle("Welcome " + LoginChoose.username);
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
		dashboard.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Dashboard", null, dashboard, null);
		SpringLayout sl_dashboard = new SpringLayout();
		dashboard.setLayout(sl_dashboard);
		
		JPanel accEnquiry = new JPanel();
		tabbedPane.addTab("Account Enquiry", null, accEnquiry, null);
		SpringLayout sl_accEnquiry = new SpringLayout();
		accEnquiry.setLayout(sl_accEnquiry);
		
		JPanel acedamics = new JPanel();
		tabbedPane.addTab("Acedamics", null, acedamics, null);
		SpringLayout sl_acedamics = new SpringLayout();
		acedamics.setLayout(sl_acedamics);
		
		JPanel register = new JPanel();
		tabbedPane.addTab("RegisterCourses", null, register, null);
		register.setLayout(null);
	}

}

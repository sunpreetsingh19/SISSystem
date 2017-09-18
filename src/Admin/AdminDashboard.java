package Admin;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.SystemColor;
public class AdminDashboard extends JFrame {

	public AdminDashboard() {
		getContentPane().setBackground(SystemColor.activeCaption);
		
		setTitle("Welcome " +AdminLogin.username);
		setVisible(true);
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JButton btnLogout = new JButton("Logout");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLogout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 34, SpringLayout.SOUTH, btnLogout);
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, -7, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 774, SpringLayout.WEST, getContentPane());
		getContentPane().add(tabbedPane);
		
		JPanel Dashboard = new JPanel();
		tabbedPane.addTab("Dashboard", null, Dashboard, null);
		
		JPanel courseList = new JPanel();
		tabbedPane.addTab("Course Listing", null, courseList, null);
		
		JPanel enrolments = new JPanel();
		tabbedPane.addTab("Enrolled Students", null, enrolments, null);
		
		
		//logout button code
		
		btnLogout.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(e.getSource()==btnLogout) {
					AdminLogin login= new AdminLogin();
					dispose();
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
	}
}

package client;

import javax.swing.JFrame;
public class ClientDashboard extends JFrame {

	public ClientDashboard() {
		
		setTitle("Welcome: " +ClientLogin.username);
		setVisible(true);
		setSize(800,600);
	}
}

package main;
import java.awt.EventQueue;

import client.ClientLogin;



public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin frame = new ClientLogin();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


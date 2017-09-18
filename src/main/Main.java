package main;
import java.awt.EventQueue;

import client.ClientLogin;



public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseLogin frame = new ChooseLogin();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


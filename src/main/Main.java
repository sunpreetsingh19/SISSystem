package main;
import java.awt.EventQueue;

import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;





public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginChoose frame = new LoginChoose();
					 WebLookAndFeel.install();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


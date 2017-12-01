package client;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;
import validation.CvvValidation;
import validation.PaymentValidation;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Payment extends JFrame implements ActionListener {
	private JTextField NametextField;
	private JTextField CardNumbertextField;
	private JTextField CVVtextField;
	private JTextField ExpiryDatetextField;
	JButton btnPayNow, btnCancel;
	String studentId;

	public Payment(String studentId) {
		
		this.studentId= studentId;
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setTitle("Pay Tuition Fees");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 650);
		setVisible(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		NametextField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, NametextField, -506, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(NametextField);
		NametextField.setColumns(10);

		JLabel lblCardholderName = new JLabel("Card-Holder Name");
		springLayout.putConstraint(SpringLayout.WEST, NametextField, 30, SpringLayout.EAST, lblCardholderName);
		springLayout.putConstraint(SpringLayout.NORTH, lblCardholderName, 3, SpringLayout.NORTH, NametextField);
		getContentPane().add(lblCardholderName);

		CardNumbertextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, CardNumbertextField, 43, SpringLayout.SOUTH, NametextField);
		springLayout.putConstraint(SpringLayout.WEST, CardNumbertextField, 0, SpringLayout.WEST, NametextField);
		getContentPane().add(CardNumbertextField);
		CardNumbertextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Card Number");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, CardNumbertextField);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(lblNewLabel);

		CVVtextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, CVVtextField, 45, SpringLayout.SOUTH, CardNumbertextField);
		springLayout.putConstraint(SpringLayout.WEST, CVVtextField, 0, SpringLayout.WEST, NametextField);
		getContentPane().add(CVVtextField);
		CVVtextField.setColumns(10);

		JLabel lblCvv = new JLabel("CVV");
		springLayout.putConstraint(SpringLayout.NORTH, lblCvv, 3, SpringLayout.NORTH, CVVtextField);
		springLayout.putConstraint(SpringLayout.WEST, lblCvv, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(lblCvv);

		btnPayNow = new JButton("Pay Now");
		springLayout.putConstraint(SpringLayout.WEST, lblCardholderName, 0, SpringLayout.WEST, btnPayNow);
		getContentPane().add(btnPayNow);
		btnPayNow.addActionListener(this);

		btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 269, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnPayNow, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnPayNow, -64, SpringLayout.WEST, btnCancel);
		getContentPane().add(btnCancel);

		ExpiryDatetextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 65, SpringLayout.SOUTH, ExpiryDatetextField);
		springLayout.putConstraint(SpringLayout.NORTH, ExpiryDatetextField, 45, SpringLayout.SOUTH, CVVtextField);
		springLayout.putConstraint(SpringLayout.EAST, ExpiryDatetextField, 0, SpringLayout.EAST, NametextField);
		getContentPane().add(ExpiryDatetextField);
		ExpiryDatetextField.setColumns(10);

		JLabel lblExpiryDate = new JLabel("Expiry Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblExpiryDate, 3, SpringLayout.NORTH, ExpiryDatetextField);
		springLayout.putConstraint(SpringLayout.WEST, lblExpiryDate, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(lblExpiryDate);

		btnPayNow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					PaymentValidation validation = new PaymentValidation();
					String cardNumber = CardNumbertextField.getText();
					boolean validCardNum = validation.cardValidate(cardNumber);
					CvvValidation cvv = new CvvValidation();
					String cvvnumber = CVVtextField.getText();
					boolean validCvvNum = cvv.cardValidateCvv(cvvnumber);

					if (!validCardNum && !validCvvNum) {
						JOptionPane.showMessageDialog(null, "Invalid card Number and Cvv Number");
					} else if (!validCvvNum && validCardNum) {
						JOptionPane.showMessageDialog(null, "Invalid cvv Number");
					}

					else if (validCvvNum && !validCardNum) {
						JOptionPane.showMessageDialog(null, "Invalid Card Number");
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Payment Recieved Succesfully");
						dispose();
						new ClientDashboard(studentId);
						

					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new ClientDashboard(studentId);
				
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}

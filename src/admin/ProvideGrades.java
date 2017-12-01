package admin;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProvideGrades extends JFrame{
	
	String studentId, courseName, studentName;
	public ProvideGrades(String studentId, String name, String courseName) {
		
		this.studentId= studentId;
		this.courseName=courseName;
		studentName= name;
		
		setVisible(true);
		setSize(400,300);
		setLocationRelativeTo(null);
		setTitle("Grade Student");
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentId, 77, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStudentId, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentName, 21, SpringLayout.SOUTH, lblStudentId);
		springLayout.putConstraint(SpringLayout.WEST, lblStudentName, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblStudentName);
		
		JLabel lblEnterGrade = new JLabel("Enter Grade:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterGrade, 54, SpringLayout.SOUTH, lblStudentName);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterGrade, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblEnterGrade);
		
		JLabel studentIdLabel = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.WEST, studentIdLabel, 63, SpringLayout.EAST, lblStudentId);
		springLayout.putConstraint(SpringLayout.SOUTH, studentIdLabel, 0, SpringLayout.SOUTH, lblStudentId);
		getContentPane().add(studentIdLabel);
		studentIdLabel.setText(studentId);
		
		JLabel studentNameLabel = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, studentNameLabel, 0, SpringLayout.NORTH, lblStudentName);
		springLayout.putConstraint(SpringLayout.WEST, studentNameLabel, 0, SpringLayout.WEST, studentIdLabel);
		getContentPane().add(studentNameLabel);
		studentNameLabel.setText(name);
		
		JComboBox gradeBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, gradeBox, -3, SpringLayout.NORTH, lblEnterGrade);
		springLayout.putConstraint(SpringLayout.WEST, gradeBox, 0, SpringLayout.WEST, studentIdLabel);
		getContentPane().add(gradeBox);
		
		//gradeBox items
		gradeBox.addItem("A+");
		gradeBox.addItem("A");
		gradeBox.addItem("A-");
		gradeBox.addItem("B+");
		gradeBox.addItem("B");
		gradeBox.addItem("B-");
		gradeBox.addItem("C");
		gradeBox.addItem("F");
		
		JButton btnEnterGrade = new JButton("Enter Grade and Exit");
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnterGrade, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnEnterGrade, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnEnterGrade);
		
	//	btnEnterGrade
		btnEnterGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String grade= (String) gradeBox.getSelectedItem();
				try {
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();

					String sql = "UPDATE course_enrollment set grades= '"+grade+"' Where student_id='"+studentId+"' and course_description='"+courseName+"'";
					PreparedStatement statement = conn.prepareStatement(sql);
					 statement.executeUpdate(sql);
					
						

						statement.close();
						conn.close();
					JOptionPane.showMessageDialog(null, "Grade Entered");
					dispose();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		JLabel lblPleaseEnterThe = new JLabel("Please enter the grade");
		lblPleaseEnterThe.setFont(new Font("Tahoma", Font.BOLD, 13));
		springLayout.putConstraint(SpringLayout.NORTH, lblPleaseEnterThe, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblPleaseEnterThe, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblPleaseEnterThe);
		
		JLabel lblCourseName = new JLabel("Course Name:");
		springLayout.putConstraint(SpringLayout.WEST, lblCourseName, 0, SpringLayout.WEST, lblStudentId);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCourseName, -18, SpringLayout.NORTH, lblEnterGrade);
		getContentPane().add(lblCourseName);
		
		JLabel courseNameLabel = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.WEST, courseNameLabel, 0, SpringLayout.WEST, studentIdLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, courseNameLabel, 0, SpringLayout.SOUTH, lblCourseName);
		getContentPane().add(courseNameLabel);
		courseNameLabel.setText(courseName);
	}
}

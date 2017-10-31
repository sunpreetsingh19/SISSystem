package admin;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;
import validation.CourseValidation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JComboBox;

public class AddCourse extends JFrame {
	private JTextField courseNumberField;
	private JTextField courseNameField;
	private JTextField profNameField;
	private JTextField startDateField;
	private JTextField endDateField;
	private JTextField startTimeField;
	private JTextField endTimeField;
	private JTextField vacancyField;

	
	public AddCourse() {
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setTitle("Add a Course");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600,650);
		setVisible(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCourseName = new JLabel("Course ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseName, 87, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCourseName, 83, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCourseName);
		
		JLabel lblCourseNumber = new JLabel("Course Number(Digits only)");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseNumber, 54, SpringLayout.SOUTH, lblCourseName);
		springLayout.putConstraint(SpringLayout.WEST, lblCourseNumber, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblCourseNumber);
		
		courseNumberField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, courseNumberField, -3, SpringLayout.NORTH, lblCourseNumber);
		springLayout.putConstraint(SpringLayout.WEST, courseNumberField, 41, SpringLayout.EAST, lblCourseNumber);
		springLayout.putConstraint(SpringLayout.EAST, courseNumberField, -203, SpringLayout.EAST, getContentPane());
		getContentPane().add(courseNumberField);
		courseNumberField.setColumns(10);
		
		JLabel lblCourseName_1 = new JLabel("Course Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseName_1, 47, SpringLayout.SOUTH, lblCourseNumber);
		springLayout.putConstraint(SpringLayout.WEST, lblCourseName_1, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblCourseName_1);
		
		courseNameField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, courseNameField, 108, SpringLayout.EAST, lblCourseName_1);
		springLayout.putConstraint(SpringLayout.SOUTH, courseNameField, 0, SpringLayout.SOUTH, lblCourseName_1);
		getContentPane().add(courseNameField);
		courseNameField.setColumns(15);
		
		JLabel lblProfessorsName = new JLabel("Professor's Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblProfessorsName, 51, SpringLayout.SOUTH, lblCourseName_1);
		springLayout.putConstraint(SpringLayout.WEST, lblProfessorsName, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblProfessorsName);
		
		profNameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, profNameField, 51, SpringLayout.SOUTH, courseNameField);
		springLayout.putConstraint(SpringLayout.WEST, profNameField, 89, SpringLayout.EAST, lblProfessorsName);
		springLayout.putConstraint(SpringLayout.EAST, profNameField, -203, SpringLayout.EAST, getContentPane());
		getContentPane().add(profNameField);
		profNameField.setColumns(10);
		
		
		JLabel lblStartDate = new JLabel("Start Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartDate, 40, SpringLayout.SOUTH, lblProfessorsName);
		springLayout.putConstraint(SpringLayout.WEST, lblStartDate, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblStartDate);
		
		startDateField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, startDateField, 122, SpringLayout.EAST, lblStartDate);
		springLayout.putConstraint(SpringLayout.SOUTH, startDateField, 0, SpringLayout.SOUTH, lblStartDate);
		getContentPane().add(startDateField);
		startDateField.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndDate, 28, SpringLayout.SOUTH, lblStartDate);
		springLayout.putConstraint(SpringLayout.WEST, lblEndDate, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblEndDate);
		
		endDateField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, endDateField, 0, SpringLayout.NORTH, lblEndDate);
		springLayout.putConstraint(SpringLayout.WEST, endDateField, 128, SpringLayout.EAST, lblEndDate);
		getContentPane().add(endDateField);
		endDateField.setColumns(10);
		
		JLabel lblStartTime = new JLabel("Start Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartTime, 29, SpringLayout.SOUTH, lblEndDate);
		springLayout.putConstraint(SpringLayout.WEST, lblStartTime, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblStartTime);
		
		startTimeField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, startTimeField, -3, SpringLayout.NORTH, lblStartTime);
		springLayout.putConstraint(SpringLayout.WEST, startTimeField, 123, SpringLayout.EAST, lblStartTime);
		getContentPane().add(startTimeField);
		startTimeField.setColumns(10);
		
		endTimeField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, endTimeField, 23, SpringLayout.SOUTH, startTimeField);
		getContentPane().add(endTimeField);
		endTimeField.setColumns(10);
		
		JLabel lblEndTime = new JLabel("End Time");
		springLayout.putConstraint(SpringLayout.WEST, endTimeField, 129, SpringLayout.EAST, lblEndTime);
		springLayout.putConstraint(SpringLayout.WEST, lblEndTime, 0, SpringLayout.WEST, lblCourseName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEndTime, 0, SpringLayout.SOUTH, endTimeField);
		getContentPane().add(lblEndTime);
		
		JLabel lblVacancy = new JLabel("Vacancy");
		springLayout.putConstraint(SpringLayout.NORTH, lblVacancy, 32, SpringLayout.SOUTH, lblEndTime);
		springLayout.putConstraint(SpringLayout.WEST, lblVacancy, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblVacancy);
		
		vacancyField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, vacancyField, 26, SpringLayout.SOUTH, endTimeField);
		springLayout.putConstraint(SpringLayout.WEST, vacancyField, 132, SpringLayout.EAST, lblVacancy);
		springLayout.putConstraint(SpringLayout.EAST, vacancyField, -203, SpringLayout.EAST, getContentPane());
		getContentPane().add(vacancyField);
		vacancyField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSubmit, -98, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnSubmit);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 6, SpringLayout.EAST, btnSubmit);
		getContentPane().add(btnCancel);
		
		JLabel lblAddCourseDetails = new JLabel("Add course details");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddCourseDetails, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAddCourseDetails, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblAddCourseDetails);
		
		JComboBox courseIdBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, courseIdBox, -3, SpringLayout.NORTH, lblCourseName);
		springLayout.putConstraint(SpringLayout.WEST, courseIdBox, 0, SpringLayout.WEST, courseNumberField);
		getContentPane().add(courseIdBox);
	
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql ="Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			
			while(rs.next()) {
				String id= rs.getString("id");
				//String program= rs.getString("name");
				
			courseIdBox.addItem(id);
			}
			conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		//cancel button
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AdminDashboard admin= new AdminDashboard();
					dispose();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		//button submit
		btnSubmit.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String courseId= (String) courseIdBox.getSelectedItem();
				String courseNum= courseNumberField.getText();
				String courseName= courseNameField.getText();
				String profName= profNameField.getText();
				String startDate= startDateField.getText();
				String endDate= endDateField.getText();
				String startTime= startTimeField.getText();
				String endTime= endTimeField.getText();
				String Vacancy= vacancyField.getText();
				
				CourseValidation validateCourse= new CourseValidation();
				boolean courseNumb= validateCourse.courseNumber(courseNum);
				boolean courseNaming= validateCourse.courseName(courseName);
				boolean vacancy= validateCourse.courseVacancy(Vacancy);
				if(!courseNumb) {
					JOptionPane.showMessageDialog(null, "Please enter valid course Number");
				}
				else if(!courseNaming) {
					JOptionPane.showMessageDialog(null, "Please enter valid course name");
				}
				else if(!vacancy) {
					JOptionPane.showMessageDialog(null, "Accommodation should be between 0 and 200");
				}
				else {
				try {
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					String sql=  "INSERT INTO courses (course_id,course_name,professor_name,start_date,end_date, start_time, end_time, vacancy) VALUES ('"+courseId+" "+courseNum+"','"+courseName+"','"+profName+"','"+startDate+"','"+endDate+"','"+startTime+"','"+endTime+"','"+Vacancy+"')";
							PreparedStatement statement = conn.prepareStatement(sql);
							 statement.executeUpdate(sql);
							conn.close();
							JOptionPane.showMessageDialog(null, "Course successfully created");
							AdminDashboard admin= new AdminDashboard();
							dispose();
				}catch(Exception ex) {
					ex.printStackTrace();
					
				}
				}
			}
		});
	}
}

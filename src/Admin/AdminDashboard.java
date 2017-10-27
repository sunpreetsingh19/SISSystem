package admin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import org.omg.PortableServer.THREAD_POLICY_ID;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;
import main.LoginChoose;
import main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;

public class AdminDashboard extends JFrame {
	private JTextField searchField;
	private JTextField nameField;
	public static Object[][] data;
	String[] columnCourseData= {"Course ID", "Course name","Date", "Time","Teacher", "Vacancy"};
	String[] columnDepartmentData = {"Department ID", "Department Name", "Number of Students Enrolled"};
	private JTable tableCourseData ;
	private JTable tableDepartmentData;
	public AdminDashboard() {
		getContentPane().setBackground(SystemColor.activeCaption);

		setTitle("Welcome " + LoginChoose.username);
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);

		setResizable(false);

		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JButton btnLogout = new JButton();
		btnLogout.setIcon(new ImageIcon(getClass().getResource("/image/ImageIcons.jpg")));
		//btnLogout.setPreferredSize(new Dimension(40, 40));
		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLogout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 84, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, -7, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 774, SpringLayout.WEST, getContentPane());
		getContentPane().add(tabbedPane);

		JPanel Dashboard = new JPanel();
		tabbedPane.addTab("Dashboard", null, Dashboard, null);
		SpringLayout sl_Dashboard = new SpringLayout();
		Dashboard.setLayout(sl_Dashboard);

		JPanel courseList = new JPanel();
		courseList.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Course Listing", null, courseList, null);
		SpringLayout sl_courseList = new SpringLayout();
		courseList.setLayout(sl_courseList);
		
		JButton btnAddCourse = new JButton("Add Course");
		sl_courseList.putConstraint(SpringLayout.NORTH, btnAddCourse, 10, SpringLayout.NORTH, courseList);
		sl_courseList.putConstraint(SpringLayout.WEST, btnAddCourse, 10, SpringLayout.WEST, courseList);
		courseList.add(btnAddCourse);
		
		//add course
		btnAddCourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AddCourse addCourse= new AddCourse();
					dispose();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		JComboBox programBox = new JComboBox();
		sl_courseList.putConstraint(SpringLayout.NORTH, programBox, 10, SpringLayout.NORTH, courseList);
		sl_courseList.putConstraint(SpringLayout.WEST, programBox, 73, SpringLayout.EAST, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.EAST, programBox, 303, SpringLayout.EAST, btnAddCourse);
		courseList.add(programBox);
		
		//search programbox list data
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql ="Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			
			while(rs.next()) {
				String id= rs.getString("id");
				String program= rs.getString("name");
				
			programBox.addItem(id+" "+program);
			}
			conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		
		
		JButton btnSearchCourseList = new JButton("Search Course List");
		sl_courseList.putConstraint(SpringLayout.NORTH, btnSearchCourseList, 0, SpringLayout.NORTH, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.WEST, btnSearchCourseList, 6, SpringLayout.EAST, programBox);
		courseList.add(btnSearchCourseList);
		
		JScrollPane scrollPaneCourse = new JScrollPane();
		sl_courseList.putConstraint(SpringLayout.NORTH, scrollPaneCourse, 22, SpringLayout.SOUTH, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.WEST, scrollPaneCourse, 0, SpringLayout.WEST, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.SOUTH, scrollPaneCourse, -10, SpringLayout.SOUTH, courseList);
		sl_courseList.putConstraint(SpringLayout.EAST, scrollPaneCourse, 749, SpringLayout.WEST, courseList);
		courseList.add(scrollPaneCourse);
		
		
		
		
		DefaultTableModel tableModelCourse= new DefaultTableModel(columnCourseData, 0);
		tableCourseData = new JTable(tableModelCourse);
		scrollPaneCourse.setViewportView(tableCourseData);
		tableCourseData.setRowHeight(30);
		
		
		JButton btnViewCourseDetails = new JButton("View Course Details");
		sl_courseList.putConstraint(SpringLayout.SOUTH, btnViewCourseDetails, 0, SpringLayout.SOUTH, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.EAST, btnViewCourseDetails, -10, SpringLayout.EAST, courseList);
		courseList.add(btnViewCourseDetails);
	//	JScrollPane scroll= new JScrollPane(table);

		// course data panel code
		
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select course_id, course_name, professor_name, start_date, end_date, start_time, end_time, vacancy from courses";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while(rs.next()) {
				
				String courseId= rs.getString("course_id");
				String courseName= rs.getString("course_name");
				String profName= rs.getString("professor_name");
				String startDate= rs.getString("start_date");
				String endDate= rs.getString("end_date");
				String startTime= rs.getString("start_time");
				String endTime= rs.getString("end_time");
				String vacancy= rs.getString("vacancy");
				
				String date= startDate+"-"+endDate;
				String time= startTime+"-"+endTime;
				//action= new JCheckBox("Details");
				tableModelCourse.addRow(new Object[] {courseId, courseName,  date, time,profName, vacancy});
			//	TableColumn selectColumn= studentData.getColumnModel().getColumn(4);
			//	selectColumn.setCellEditor(new DefaultCellEditor(action));
			}
			
		
			statement.close();
			conn.close();

			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		
		
		JPanel enrolments = new JPanel();
		enrolments.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Enrolled Students", null, enrolments, null);
		SpringLayout sl_enrolments = new SpringLayout();
		enrolments.setLayout(sl_enrolments);

		JButton requestOfStudent = new JButton("Student Requests");
		sl_enrolments.putConstraint(SpringLayout.NORTH, requestOfStudent, 10, SpringLayout.NORTH, enrolments);
		sl_enrolments.putConstraint(SpringLayout.WEST, requestOfStudent, 10, SpringLayout.WEST, enrolments);
		enrolments.add(requestOfStudent);

		// enrolments
		// request of student button
		requestOfStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					 StudentRequestData studentData = new StudentRequestData();
				//setVisible(false);
					

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		searchField = new JTextField();
		enrolments.add(searchField);
		searchField.setColumns(15);

		JButton btnSearch = new JButton("Search");
		sl_enrolments.putConstraint(SpringLayout.NORTH, btnSearch, 123, SpringLayout.NORTH, enrolments);
		sl_enrolments.putConstraint(SpringLayout.WEST, btnSearch, 388, SpringLayout.WEST, enrolments);
		sl_enrolments.putConstraint(SpringLayout.NORTH, searchField, 1, SpringLayout.NORTH, btnSearch);
		sl_enrolments.putConstraint(SpringLayout.EAST, searchField, -6, SpringLayout.WEST, btnSearch);
		enrolments.add(btnSearch);

		JLabel lblSearchByStudent = new JLabel("Search by Student ID");
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchByStudent, 0, SpringLayout.WEST, searchField);
		sl_enrolments.putConstraint(SpringLayout.SOUTH, lblSearchByStudent, -7, SpringLayout.NORTH, searchField);
		enrolments.add(lblSearchByStudent);

		JLabel lblOr = new JLabel("or");
		sl_enrolments.putConstraint(SpringLayout.NORTH, lblOr, 19, SpringLayout.SOUTH, searchField);
		sl_enrolments.putConstraint(SpringLayout.WEST, lblOr, 0, SpringLayout.WEST, searchField);
		enrolments.add(lblOr);

		JComboBox selectProgram = new JComboBox();
		sl_enrolments.putConstraint(SpringLayout.NORTH, selectProgram, 40, SpringLayout.SOUTH, lblOr);
		sl_enrolments.putConstraint(SpringLayout.WEST, selectProgram, 0, SpringLayout.WEST, searchField);
		enrolments.add(selectProgram);

		// combobox
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql = "Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String program = rs.getString("name");

				selectProgram.addItem(id + " " + program);
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JLabel lblSearchByProgram = new JLabel("Search by Program");
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchByProgram, 0, SpringLayout.WEST, searchField);
		sl_enrolments.putConstraint(SpringLayout.SOUTH, lblSearchByProgram, -5, SpringLayout.NORTH, selectProgram);
		enrolments.add(lblSearchByProgram);

		nameField = new JTextField();
		sl_enrolments.putConstraint(SpringLayout.EAST, nameField, 0, SpringLayout.EAST, btnSearch);
		enrolments.add(nameField);
		nameField.setColumns(10);

		JLabel studentName = new JLabel("Name of Student");
		sl_enrolments.putConstraint(SpringLayout.NORTH, nameField, -3, SpringLayout.NORTH, studentName);
		sl_enrolments.putConstraint(SpringLayout.NORTH, studentName, 15, SpringLayout.SOUTH, selectProgram);
		sl_enrolments.putConstraint(SpringLayout.WEST, studentName, 0, SpringLayout.WEST, searchField);
		enrolments.add(studentName);

		JButton btnSearchByName = new JButton("Search");
		sl_enrolments.putConstraint(SpringLayout.NORTH, btnSearchByName, 16, SpringLayout.SOUTH, studentName);
		sl_enrolments.putConstraint(SpringLayout.WEST, btnSearchByName, 0, SpringLayout.WEST, searchField);
		btnSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		enrolments.add(btnSearchByName);

		JLabel lblSearchAStudent = new JLabel("Search a Student:");
		sl_enrolments.putConstraint(SpringLayout.NORTH, lblSearchAStudent, 28, SpringLayout.SOUTH, requestOfStudent);
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchAStudent, 0, SpringLayout.WEST, requestOfStudent);
		enrolments.add(lblSearchAStudent);
		
		
		
		//department model
		JPanel addDepartment = new JPanel();
		addDepartment.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Department Information", null, addDepartment, null);
		SpringLayout sl_addDepartment = new SpringLayout();
		addDepartment.setLayout(sl_addDepartment);
		
		JButton btnAddDepartment = new JButton("Add Department");
		sl_addDepartment.putConstraint(SpringLayout.NORTH, btnAddDepartment, 10, SpringLayout.NORTH, addDepartment);
		sl_addDepartment.putConstraint(SpringLayout.WEST, btnAddDepartment, 10, SpringLayout.WEST, addDepartment);
		addDepartment.add(btnAddDepartment);
		
		
		
		JPanel departmentDetails = new JPanel();
		sl_addDepartment.putConstraint(SpringLayout.NORTH, departmentDetails, 18, SpringLayout.SOUTH, btnAddDepartment);
		sl_addDepartment.putConstraint(SpringLayout.WEST, departmentDetails, 10, SpringLayout.WEST, addDepartment);
		sl_addDepartment.putConstraint(SpringLayout.SOUTH, departmentDetails, -21, SpringLayout.SOUTH, addDepartment);
		sl_addDepartment.putConstraint(SpringLayout.EAST, departmentDetails, 749, SpringLayout.WEST, addDepartment);
		addDepartment.add(departmentDetails);
		
		JButton btnViewDepartmentDetails = new JButton("View Department Details");
		sl_addDepartment.putConstraint(SpringLayout.NORTH, btnViewDepartmentDetails, 0, SpringLayout.NORTH, btnAddDepartment);
		sl_addDepartment.putConstraint(SpringLayout.EAST, btnViewDepartmentDetails, 0, SpringLayout.EAST, departmentDetails);
		
		
		
		DefaultTableModel tableModelDepartment= new DefaultTableModel(columnDepartmentData, 0);
		SpringLayout sl_departmentDetails = new SpringLayout();
		departmentDetails.setLayout(sl_departmentDetails);
	//	departmentDetails.add(tableDepartmentData);
		
		tableDepartmentData= new JTable(tableModelDepartment);
		JScrollPane scrollPaneDepartment = new JScrollPane();
		sl_departmentDetails.putConstraint(SpringLayout.NORTH, scrollPaneDepartment, 0, SpringLayout.NORTH, departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.WEST, scrollPaneDepartment, 0, SpringLayout.WEST, departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.SOUTH, scrollPaneDepartment, 0, SpringLayout.SOUTH, departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.EAST, scrollPaneDepartment, 0, SpringLayout.EAST, departmentDetails);
		departmentDetails.add(scrollPaneDepartment);
		addDepartment.add(btnViewDepartmentDetails);
		scrollPaneDepartment.setViewportView(tableDepartmentData);

		// logout button code

		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnLogout) {
						LoginChoose login = new LoginChoose();
						
						
						
						dispose();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		// styling
		ImageIcon imageDash = new ImageIcon(getClass().getResource("/image/AdminDash.jpg"));
		JLabel image = new JLabel(imageDash);
		// image.setBounds(0, 0, 100, 80);
		image.setVisible(true);
		getContentPane().add(image);
	}
}

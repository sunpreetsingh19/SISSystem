package admin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
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
import java.sql.SQLException;

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
import javax.swing.JToggleButton;

public class AdminDashboard extends JFrame implements ActionListener {
	private JTextField searchStudentIdField;
	private JTextField studentNameField;
	//public static Object[][] data;
	String[] columnCourseData = { "Course ID", "Course Number", "Course Description" };
	String[] columnDepartmentData = { "Department ID", "Department Name" };
	private JTable tableCourseData;
	private JTable tableDepartmentData;
	public String departmentID;
	public static DefaultTableModel tableModelDepartment;
	private JButton btnLogout, btnAddCourse, requestOfStudent, btnViewDepartmentDetails, btnSearchById, btnSearchByName,btnResult,btnViewCourseDetails;
	String departmentId, departmentName;
	public static String studentSearchIdByName;
	 String studentSearchId, studentSearchName, studentSearchProgram;
	JComboBox selectProgram;
	public String studentProgramSearch, studentNameSearch;
	public static int flag = 0;

	public AdminDashboard() throws Exception {
		IconPackage icons = new IconPackage();
		getContentPane().setBackground(SystemColor.activeCaption);

		setTitle("Welcome " + LoginChoose.username);
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);

		setResizable(false);

		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		btnLogout = new JButton();
		btnLogout.setIcon(icons.IconsLogout());
		// btnLogout.setPreferredSize(new Dimension(40, 40));
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
		tabbedPane.addTab("Dashboard", icons.IconsDash(), Dashboard);
		SpringLayout sl_Dashboard = new SpringLayout();
		Dashboard.setLayout(sl_Dashboard);

		JPanel courseList = new JPanel();
		courseList.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Course List", icons.IconsCourseList(), courseList);
		SpringLayout sl_courseList = new SpringLayout();
		courseList.setLayout(sl_courseList);

		btnAddCourse = new JButton("Add Course");
		btnAddCourse.setIcon(icons.IconsAdd());
		sl_courseList.putConstraint(SpringLayout.NORTH, btnAddCourse, 10, SpringLayout.NORTH, courseList);
		sl_courseList.putConstraint(SpringLayout.WEST, btnAddCourse, 10, SpringLayout.WEST, courseList);
		courseList.add(btnAddCourse);

		// add course
		btnAddCourse.addActionListener(this);

		

		JScrollPane scrollPaneCourse = new JScrollPane();
		sl_courseList.putConstraint(SpringLayout.NORTH, scrollPaneCourse, 22, SpringLayout.SOUTH, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.WEST, scrollPaneCourse, 0, SpringLayout.WEST, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.SOUTH, scrollPaneCourse, -10, SpringLayout.SOUTH, courseList);
		sl_courseList.putConstraint(SpringLayout.EAST, scrollPaneCourse, 749, SpringLayout.WEST, courseList);
		courseList.add(scrollPaneCourse);

		DefaultTableModel tableModelCourse = new DefaultTableModel(columnCourseData, 0);
		tableCourseData = new JTable(tableModelCourse);
		scrollPaneCourse.setViewportView(tableCourseData);
		tableCourseData.setRowHeight(30);

		btnViewCourseDetails = new JButton("View Course Details");
		btnViewCourseDetails.setIcon(icons.IconsSearch());
		sl_courseList.putConstraint(SpringLayout.SOUTH, btnViewCourseDetails, 0, SpringLayout.SOUTH, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.EAST, btnViewCourseDetails, -10, SpringLayout.EAST, courseList);
		courseList.add(btnViewCourseDetails);
		btnViewCourseDetails.addActionListener(this);
		// JScrollPane scroll= new JScrollPane(table);

		// course data panel code

		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from courses";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				String courseId = rs.getString("course_id");
				String courseNum = rs.getString("course_num");
				String courseDescription = rs.getString("course_description");
				/*
				 * String profName = rs.getString("professor_id"); String startDate =
				 * rs.getString("start_date"); String endDate = rs.getString("end_date"); String
				 * startTime = rs.getString("start_time"); String endTime =
				 * rs.getString("end_time"); String vacancy = rs.getString("vacancy");
				 * 
				 * 
				 * String date = startDate + "-" + endDate; String time = startTime + "-" +
				 * endTime;
				 */
				// action= new JCheckBox("Details");
				tableModelCourse.addRow(new Object[] { courseId, courseNum, courseDescription });
				// TableColumn selectColumn= studentData.getColumnModel().getColumn(4);
				// selectColumn.setCellEditor(new DefaultCellEditor(action));
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JPanel enrolments = new JPanel();
		enrolments.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Students", icons.IconsEnrollment(), enrolments);
		SpringLayout sl_enrolments = new SpringLayout();
		enrolments.setLayout(sl_enrolments);

		requestOfStudent = new JButton("Student Requests");
		sl_enrolments.putConstraint(SpringLayout.NORTH, requestOfStudent, 10, SpringLayout.NORTH, enrolments);
		sl_enrolments.putConstraint(SpringLayout.WEST, requestOfStudent, 10, SpringLayout.WEST, enrolments);
		enrolments.add(requestOfStudent);

		// enrolments
		// request of student button
		requestOfStudent.addActionListener(this);

		searchStudentIdField = new JTextField();
		enrolments.add(searchStudentIdField);
		searchStudentIdField.setColumns(15);
		searchStudentIdField.addActionListener(this);

		btnSearchById = new JButton("Search Student");
		btnSearchById.setIcon(icons.IconsSearch());
		sl_enrolments.putConstraint(SpringLayout.NORTH, btnSearchById, 123, SpringLayout.NORTH, enrolments);
		sl_enrolments.putConstraint(SpringLayout.WEST, btnSearchById, 388, SpringLayout.WEST, enrolments);
		sl_enrolments.putConstraint(SpringLayout.NORTH, searchStudentIdField, 1, SpringLayout.NORTH, btnSearchById);
		sl_enrolments.putConstraint(SpringLayout.EAST, searchStudentIdField, -6, SpringLayout.WEST, btnSearchById);
		enrolments.add(btnSearchById);
		btnSearchById.addActionListener(this);

		JLabel lblSearchByStudent = new JLabel("Search by Student ID");
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchByStudent, 0, SpringLayout.WEST, searchStudentIdField);
		sl_enrolments.putConstraint(SpringLayout.SOUTH, lblSearchByStudent, -7, SpringLayout.NORTH,
				searchStudentIdField);
		enrolments.add(lblSearchByStudent);

		JLabel lblOr = new JLabel("or");
		sl_enrolments.putConstraint(SpringLayout.NORTH, lblOr, 19, SpringLayout.SOUTH, searchStudentIdField);
		sl_enrolments.putConstraint(SpringLayout.WEST, lblOr, 0, SpringLayout.WEST, searchStudentIdField);
		enrolments.add(lblOr);

		selectProgram = new JComboBox();
		sl_enrolments.putConstraint(SpringLayout.NORTH, selectProgram, 40, SpringLayout.SOUTH, lblOr);
		sl_enrolments.putConstraint(SpringLayout.WEST, selectProgram, 0, SpringLayout.WEST, searchStudentIdField);
		enrolments.add(selectProgram);

		// combobox to search program courses
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql = "Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String program = rs.getString("name");

				selectProgram.addItem(id);
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JLabel lblSearchByProgram = new JLabel("Search by Program");
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchByProgram, 0, SpringLayout.WEST, searchStudentIdField);
		sl_enrolments.putConstraint(SpringLayout.SOUTH, lblSearchByProgram, -5, SpringLayout.NORTH, selectProgram);
		enrolments.add(lblSearchByProgram);

		studentNameField = new JTextField();
		sl_enrolments.putConstraint(SpringLayout.WEST, studentNameField, -166, SpringLayout.EAST, btnSearchById);
		sl_enrolments.putConstraint(SpringLayout.EAST, studentNameField, 0, SpringLayout.EAST, btnSearchById);
		enrolments.add(studentNameField);
		studentNameField.setColumns(10);

		JLabel studentName = new JLabel("Name of Student");
		sl_enrolments.putConstraint(SpringLayout.NORTH, studentNameField, -3, SpringLayout.NORTH, studentName);
		sl_enrolments.putConstraint(SpringLayout.NORTH, studentName, 15, SpringLayout.SOUTH, selectProgram);
		sl_enrolments.putConstraint(SpringLayout.WEST, studentName, 0, SpringLayout.WEST, searchStudentIdField);
		enrolments.add(studentName);

		btnSearchByName = new JButton("Search");
		btnSearchByName.setIcon(icons.IconsSearch());
		sl_enrolments.putConstraint(SpringLayout.NORTH, btnSearchByName, 16, SpringLayout.SOUTH, studentName);
		sl_enrolments.putConstraint(SpringLayout.WEST, btnSearchByName, 0, SpringLayout.WEST, searchStudentIdField);
		btnSearchByName.addActionListener(this);
		enrolments.add(btnSearchByName);

		JLabel lblSearchAStudent = new JLabel("Search a Student:");
		sl_enrolments.putConstraint(SpringLayout.NORTH, lblSearchAStudent, 28, SpringLayout.SOUTH, requestOfStudent);
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchAStudent, 0, SpringLayout.WEST, requestOfStudent);
		enrolments.add(lblSearchAStudent);
		
		btnResult = new JButton("Submit Results");
		sl_enrolments.putConstraint(SpringLayout.SOUTH, btnResult, 0, SpringLayout.SOUTH, requestOfStudent);
		sl_enrolments.putConstraint(SpringLayout.EAST, btnResult, -10, SpringLayout.EAST, enrolments);
		enrolments.add(btnResult);
		btnResult.addActionListener(this);

		// department model
		JPanel addDepartment = new JPanel();
		addDepartment.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Department Information", icons.IconsDepartment(), addDepartment);
		SpringLayout sl_addDepartment = new SpringLayout();
		addDepartment.setLayout(sl_addDepartment);

		JPanel departmentDetails = new JPanel();
		sl_addDepartment.putConstraint(SpringLayout.WEST, departmentDetails, 10, SpringLayout.WEST, addDepartment);
		sl_addDepartment.putConstraint(SpringLayout.SOUTH, departmentDetails, -21, SpringLayout.SOUTH, addDepartment);
		sl_addDepartment.putConstraint(SpringLayout.EAST, departmentDetails, 749, SpringLayout.WEST, addDepartment);
		addDepartment.add(departmentDetails);

		btnViewDepartmentDetails = new JButton("View Department Details");
		sl_addDepartment.putConstraint(SpringLayout.NORTH, departmentDetails, 18, SpringLayout.SOUTH,
				btnViewDepartmentDetails);
		sl_addDepartment.putConstraint(SpringLayout.EAST, btnViewDepartmentDetails, -10, SpringLayout.EAST,
				addDepartment);
		btnViewDepartmentDetails.setIcon(icons.IconsSearch());
		// view department details button
		btnViewDepartmentDetails.addActionListener(this);

		tableModelDepartment = new DefaultTableModel(columnDepartmentData, 0);

		SpringLayout sl_departmentDetails = new SpringLayout();
		departmentDetails.setLayout(sl_departmentDetails);
		// departmentDetails.add(tableDepartmentData);

		tableDepartmentData = new JTable();
		tableDepartmentData.setModel(tableModelDepartment);
		JScrollPane scrollPaneDepartment = new JScrollPane();
		sl_departmentDetails.putConstraint(SpringLayout.NORTH, scrollPaneDepartment, 0, SpringLayout.NORTH,
				departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.WEST, scrollPaneDepartment, 0, SpringLayout.WEST,
				departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.SOUTH, scrollPaneDepartment, 0, SpringLayout.SOUTH,
				departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.EAST, scrollPaneDepartment, 0, SpringLayout.EAST,
				departmentDetails);
		departmentDetails.add(scrollPaneDepartment);
		addDepartment.add(btnViewDepartmentDetails);
		scrollPaneDepartment.setViewportView(tableDepartmentData);
		tableDepartmentData.setRowHeight(30);
		tableDepartmentData.setRowSelectionAllowed(true);
		tableDepartmentData.addNotify();

		departmentData();
		JButton btnAddDepartment = new JButton("Add Department");
		sl_addDepartment.putConstraint(SpringLayout.NORTH, btnViewDepartmentDetails, 0, SpringLayout.NORTH,
				btnAddDepartment);
		sl_addDepartment.putConstraint(SpringLayout.NORTH, btnAddDepartment, 10, SpringLayout.NORTH, addDepartment);
		btnAddDepartment.setIcon(icons.IconsAdd());
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDepartment department = new AddDepartment();
				dispose();
			}
		});
		sl_addDepartment.putConstraint(SpringLayout.WEST, btnAddDepartment, 0, SpringLayout.WEST, departmentDetails);
		addDepartment.add(btnAddDepartment);

		// logout button code
		btnLogout.addActionListener(this);

		// styling
		ImageIcon imageDash = new ImageIcon(getClass().getResource("/image/AdminDash.jpg"));
		JLabel image = new JLabel(imageDash);
		// image.setBounds(0, 0, 100, 80);
		image.setVisible(true);
		getContentPane().add(image);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddCourse) {
			AddCourse addCourse = new AddCourse();
			dispose();
		} else if (e.getSource() == requestOfStudent) {
			StudentRequestData studentData = new StudentRequestData();
		} else if (e.getSource() == btnLogout) {
			LoginChoose login = new LoginChoose();
			dispose();
		} else if (e.getSource() == btnViewDepartmentDetails) {
			try {
				int rowNum = tableDepartmentData.getSelectedRow();
				departmentID = (String) tableDepartmentData.getValueAt(rowNum, 0);
				viewDepartment departmentDetails = new viewDepartment(departmentID);
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Please select department");

			}
		} else if (e.getSource() == btnSearchById) {

			String studentIdSearch = searchStudentIdField.getText();

			try {
				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Select * from student_accessible Where studentid='" + studentIdSearch + "'";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				if (rs.next()) {
					studentSearchId = rs.getString("studentid");
					studentSearchName = rs.getString("name");
					studentSearchProgram = rs.getString("program");
					new StudentSearchResult(studentSearchName, studentSearchId, studentSearchProgram).setVisible(true);
				//	StudentSearchResult result = new StudentSearchResult();
				//	dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Correct Student ID");
				}

				statement.close();
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Please Enter Student Id");
				// ex.printStackTrace();

			}

		} else if (e.getSource() == btnSearchByName) {

			try {
				studentProgramSearch = (String) selectProgram.getSelectedItem();
				studentNameSearch = studentNameField.getText();

				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Select * from student_accessible Where program='" + studentProgramSearch
						+ "' and  name LIKE CONCAT('" + studentNameSearch + "','%')";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				if (rs.next()) {
					

					new StudentSearchByName(studentProgramSearch, studentNameSearch);

				} else {
					JOptionPane.showMessageDialog(null, "No result Found");
				}

				statement.close();
				conn.close();

			} catch (Exception ex) {
				ex.printStackTrace();

			}

		}
		else if (e.getSource()==btnResult) {
			CourseListForResult listResult= new CourseListForResult();
		}
		else if(e.getSource()==btnViewCourseDetails) {
			try {
			int rowNum = tableCourseData.getSelectedRow();
			String courseId = (String) tableCourseData.getValueAt(rowNum, 0);
			String courseNum = (String) tableCourseData.getValueAt(rowNum, 1);
			new CourseDetails(courseId, courseNum);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please select course for details");
			}
		}

	}

	public void departmentData() {
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				departmentId = rs.getString("id");
				departmentName = rs.getString("name");

				// Object[] data= (Object[]) new Object();
				tableModelDepartment.addRow(new Object[] { departmentId, departmentName });
				tableModelDepartment.fireTableChanged(null);
				// action= new JCheckBox("Details");
				//
				// TableColumn selectColumn= studentData.getColumnModel().getColumn(4);
				// selectColumn.setCellEditor(new DefaultCellEditor(action));
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}
}

package client;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;


import admin.AdminDashboard;
import admin.IconPackage;
import admin.StudentRequestData;
import admin.viewDepartment;
import javabeans.DatabaseConnection;
import main.LoginChoose;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.Font;

public class ClientDashboard extends JFrame {
	private JTextField nameField;
	private JTextField departmentField;
	private JTextField addressField;
	private JTextField studentIdField;
	private JTextField userNameField;
	String studentName, studentId, studentAddress, studentContact, studentUserName, studentDepartment, present_due,
			payment_Due, future_due;
	String[] columns = { "Course ID","Course Number", "Course Description","Term"};
	String  courseId, courseNum, term;
	public static DefaultTableModel tableModelDepartment;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	
	String[] columnName = { "Course ID","Course Number", "Course Description", "Term", "Grades" };
	String[] columnNameFees = { "Fees" };
	private JTable GPATable, RegisterCoursesTable;
	public JPanel panelAcademics;
	DefaultTableModel tableModel, tableModelcourses;
	DefaultTableModel tableModelFees;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable tableRegisterCourse;
	// public ClientDashboard clientDashboard;

	// JTable jt;
	// String[] column_headers= {"Class", "Schedule"};
	// String[][]Class_info = {{"INSE6260", "Wed: 5:45-6:45"}};

	public ClientDashboard(String studentId) {

		this.studentId = studentId;

		// ClientDashboard clientDashboard = new ClientDashboard();
		// clientDashboard.studentData();
		// clientDashboard.studentData();

		IconPackage icons = new IconPackage();

		getContentPane().setBackground(SystemColor.activeCaption);

		setTitle("Welcome " + LoginChoose.username);
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		// clientDashboard.studentData();

		JButton btnLogout = new JButton("Logout");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLogout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 49, SpringLayout.SOUTH, btnLogout);
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, -16, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 0, SpringLayout.EAST, btnLogout);
		getContentPane().add(tabbedPane);

		JPanel dashboard = new JPanel();
		dashboard.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Dashboard", null, dashboard, null);
		SpringLayout sl_dashboard = new SpringLayout();
		dashboard.setLayout(sl_dashboard);

		JPanel panel = new JPanel();
		sl_dashboard.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, dashboard);
		sl_dashboard.putConstraint(SpringLayout.SOUTH, panel, 435, SpringLayout.NORTH, dashboard);
		sl_dashboard.putConstraint(SpringLayout.EAST, panel, 341, SpringLayout.WEST, dashboard);
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		dashboard.add(panel);

		JPanel panel_1 = new JPanel();
		sl_dashboard.putConstraint(SpringLayout.WEST, panel_1, 16, SpringLayout.EAST, panel);
		sl_dashboard.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, dashboard);
		sl_dashboard.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, panel_1);
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		sl_dashboard.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.NORTH, dashboard);
		sl_dashboard.putConstraint(SpringLayout.SOUTH, panel_1, 216, SpringLayout.NORTH, dashboard);
		dashboard.add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);

		JScrollPane scrollPane = new JScrollPane();
		sl_panel_1.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, scrollPane, 185, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, scrollPane, 392, SpringLayout.WEST, panel_1);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, },
				new String[] { "Course", "Schedule" }));
		scrollPane.setViewportView(table);
		table.setRowHeight(50);
		panel_1.add(scrollPane);

		JPanel panel_2 = new JPanel();
		sl_dashboard.putConstraint(SpringLayout.WEST, panel_2, 16, SpringLayout.EAST, panel);
		sl_dashboard.putConstraint(SpringLayout.EAST, panel_2, -10, SpringLayout.EAST, dashboard);
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		sl_dashboard.putConstraint(SpringLayout.NORTH, panel_2, -167, SpringLayout.SOUTH, dashboard);
		sl_dashboard.putConstraint(SpringLayout.SOUTH, panel_2, -10, SpringLayout.SOUTH, dashboard);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		nameField = new JTextField();
		sl_panel.putConstraint(SpringLayout.EAST, nameField, -10, SpringLayout.EAST, panel);
		nameField.setEditable(false);
		panel.add(nameField);
		nameField.setColumns(10);

		JLabel lblName = new JLabel("Name");
		sl_panel.putConstraint(SpringLayout.WEST, nameField, 37, SpringLayout.EAST, lblName);
		lblName.setForeground(Color.WHITE);
		panel.add(lblName);

		JButton btnEdit = new JButton("Edit");
		sl_panel.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, btnEdit);
		sl_panel.putConstraint(SpringLayout.WEST, btnEdit, 10, SpringLayout.WEST, panel);
		panel.add(btnEdit);

		departmentField = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, departmentField, 0, SpringLayout.WEST, nameField);
		sl_panel.putConstraint(SpringLayout.EAST, departmentField, 0, SpringLayout.EAST, nameField);
		departmentField.setEditable(false);
		panel.add(departmentField);
		departmentField.setColumns(10);
		// departmentField.setText(LoginChoose.username);
		JLabel Program = new JLabel("Department");
		sl_panel.putConstraint(SpringLayout.NORTH, departmentField, -3, SpringLayout.NORTH, Program);
		sl_panel.putConstraint(SpringLayout.NORTH, Program, 58, SpringLayout.SOUTH, lblName);
		sl_panel.putConstraint(SpringLayout.WEST, Program, 0, SpringLayout.WEST, lblName);
		Program.setForeground(Color.WHITE);
		panel.add(Program);

		addressField = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, addressField, 0, SpringLayout.WEST, nameField);
		sl_panel.putConstraint(SpringLayout.EAST, addressField, 0, SpringLayout.EAST, nameField);
		addressField.setEditable(false);
		panel.add(addressField);

		JLabel lblAddress = new JLabel("Address");
		sl_panel.putConstraint(SpringLayout.NORTH, addressField, -3, SpringLayout.NORTH, lblAddress);
		sl_panel.putConstraint(SpringLayout.NORTH, lblAddress, 55, SpringLayout.SOUTH, Program);
		sl_panel.putConstraint(SpringLayout.WEST, lblAddress, 0, SpringLayout.WEST, lblName);
		lblAddress.setForeground(Color.WHITE);
		panel.add(lblAddress);

		studentIdField = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, nameField, 49, SpringLayout.SOUTH, studentIdField);
		sl_panel.putConstraint(SpringLayout.SOUTH, studentIdField, -367, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, studentIdField, -10, SpringLayout.EAST, panel);
		studentIdField.setEditable(false);
		panel.add(studentIdField);
		studentIdField.setColumns(10);

		JLabel lblStudentId = new JLabel("Student Id");
		sl_panel.putConstraint(SpringLayout.NORTH, lblName, 55, SpringLayout.SOUTH, lblStudentId);
		sl_panel.putConstraint(SpringLayout.WEST, studentIdField, 12, SpringLayout.EAST, lblStudentId);
		sl_panel.putConstraint(SpringLayout.NORTH, lblStudentId, 41, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblStudentId, 0, SpringLayout.WEST, btnEdit);
		lblStudentId.setForeground(Color.WHITE);
		panel.add(lblStudentId);

		userNameField = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, userNameField, 0, SpringLayout.WEST, nameField);
		sl_panel.putConstraint(SpringLayout.EAST, userNameField, 0, SpringLayout.EAST, nameField);
		userNameField.setEditable(false);
		panel.add(userNameField);
		userNameField.setColumns(10);

		JLabel lblUsername = new JLabel("UserName");
		sl_panel.putConstraint(SpringLayout.NORTH, lblUsername, 48, SpringLayout.SOUTH, lblAddress);
		sl_panel.putConstraint(SpringLayout.NORTH, userNameField, -3, SpringLayout.NORTH, lblUsername);
		sl_panel.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblName);
		lblUsername.setForeground(Color.WHITE);
		panel.add(lblUsername);

		JButton btnSave = new JButton("Save");
		sl_panel.putConstraint(SpringLayout.NORTH, btnEdit, 0, SpringLayout.NORTH, btnSave);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnSave, -10, SpringLayout.EAST, panel);
		panel.add(btnSave);

		studentData();

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// nameField.setEditable(true);

				userNameField.setEditable(true);
				addressField.setEditable(true);
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// String name = nameField.getText();
				String address = addressField.getText();

				String userName = userNameField.getText();
				// String department = departmentField.getText();

				// String studentId = studentIdField.getText();

				try {
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					String sql = "Update student_accessible set address='" + address + "', username='" + userName
							+ "' Where studentid='" + studentId + "'";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.executeUpdate(sql);
					conn.close();

				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}
		});

		dashboard.add(panel_2);

		table_1 = new JTable();

		table_2 = new JTable();

		JScrollPane scrollPane_1 = new JScrollPane();

		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(new Object[][] { { "Present Due", null }, { null, null }, },
				new String[] { "Fees", "Value" }));
		scrollPane_1.setViewportView(table_3);
		SpringLayout sl_panel_2 = new SpringLayout();
		sl_panel_2.putConstraint(SpringLayout.NORTH, scrollPane_1, 15, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, scrollPane_1, 10, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, scrollPane_1, -54, SpringLayout.NORTH, table_1);
		sl_panel_2.putConstraint(SpringLayout.EAST, scrollPane_1, 392, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.NORTH, table_2, 140, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, table_2, 85, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, table_2, 141, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, table_2, 86, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.NORTH, table_1, 128, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, table_1, 290, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, table_1, 129, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, table_1, 291, SpringLayout.WEST, panel_2);
		panel_2.setLayout(sl_panel_2);
		panel_2.add(table_1);
		panel_2.add(table_2);
		panel_2.add(scrollPane_1);

		JPanel accEnquiry = new JPanel();
		tabbedPane.addTab("Account Enquiry", null, accEnquiry, null);
		SpringLayout sl_accEnquiry = new SpringLayout();
		accEnquiry.setLayout(sl_accEnquiry);

		JLabel lblDue = new JLabel("Due");
		sl_accEnquiry.putConstraint(SpringLayout.NORTH, lblDue, 47, SpringLayout.NORTH, accEnquiry);
		sl_accEnquiry.putConstraint(SpringLayout.WEST, lblDue, 108, SpringLayout.WEST, accEnquiry);
		accEnquiry.add(lblDue);

		JLabel lblAmount = new JLabel("Amount");
		sl_accEnquiry.putConstraint(SpringLayout.NORTH, lblAmount, 24, SpringLayout.SOUTH, lblDue);
		sl_accEnquiry.putConstraint(SpringLayout.WEST, lblAmount, 0, SpringLayout.WEST, lblDue);
		accEnquiry.add(lblAmount);

		textField_4.setEditable(false);
		sl_accEnquiry.putConstraint(SpringLayout.NORTH, textField_4, 0, SpringLayout.NORTH, lblDue);
		sl_accEnquiry.putConstraint(SpringLayout.WEST, textField_4, 62, SpringLayout.EAST, lblDue);
		accEnquiry.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		sl_accEnquiry.putConstraint(SpringLayout.SOUTH, textField_5, 0, SpringLayout.SOUTH, lblAmount);
		sl_accEnquiry.putConstraint(SpringLayout.EAST, textField_5, 0, SpringLayout.EAST, textField_4);
		accEnquiry.add(textField_5);
		textField_5.setColumns(10);

		JComboBox comboBox = new JComboBox();
		sl_accEnquiry.putConstraint(SpringLayout.NORTH, comboBox, 19, SpringLayout.SOUTH, textField_5);
		sl_accEnquiry.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, textField_4);
		accEnquiry.add(comboBox);
		comboBox.addItem("Select");
		comboBox.addItem("Pay By Credit");
		comboBox.addItem("Pay By Debit");

		JButton btnPay = new JButton("Pay");

		accEnquiry.add(btnPay);

		JButton btnCancel = new JButton("Cancel");
		sl_accEnquiry.putConstraint(SpringLayout.NORTH, btnCancel, 47, SpringLayout.SOUTH, comboBox);
		sl_accEnquiry.putConstraint(SpringLayout.WEST, btnCancel, 242, SpringLayout.WEST, accEnquiry);
		sl_accEnquiry.putConstraint(SpringLayout.NORTH, btnPay, 0, SpringLayout.NORTH, btnCancel);
		sl_accEnquiry.putConstraint(SpringLayout.EAST, btnPay, -59, SpringLayout.WEST, btnCancel);
		accEnquiry.add(btnCancel);

		JLabel lblPayBy = new JLabel("Pay By");
		sl_accEnquiry.putConstraint(SpringLayout.SOUTH, lblPayBy, 0, SpringLayout.SOUTH, comboBox);
		sl_accEnquiry.putConstraint(SpringLayout.EAST, lblPayBy, 0, SpringLayout.EAST, lblAmount);
		accEnquiry.add(lblPayBy);

		JPanel acedamics = new JPanel();
		tabbedPane.addTab("Acedamics", null, acedamics, null);
		SpringLayout sl_acedamics = new SpringLayout();
		acedamics.setLayout(sl_acedamics);

		JPanel register = new JPanel();
		tabbedPane.addTab("RegisterCourses", null, register, null);
		register.setLayout(null);

		JScrollPane courseRegisterPane = new JScrollPane();
		courseRegisterPane.setBounds(10, 37, 749, 363);
		register.add(courseRegisterPane);

		JLabel lblRegisteredCourses = new JLabel("Registered Courses");
		lblRegisteredCourses.setBounds(10, 15, 127, 16);
		lblRegisteredCourses.setFont(new Font("Tahoma", Font.BOLD, 13));

		DefaultTableModel courseRegisterModel = new DefaultTableModel(columns, 0);
		tableRegisterCourse = new JTable(courseRegisterModel);
		courseRegisterPane.setViewportView(tableRegisterCourse);
		register.add(lblRegisteredCourses);

		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.setBounds(533, 411, 108, 23);
		register.add(btnAddCourse);

	btnAddCourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new AddCourseStudent(studentId);
					dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		/*JButton btnDropCourse = new JButton("Drop Course");
		btnDropCourse.setBounds(651, 411, 108, 23);
		register.add(btnDropCourse);

		btnDropCourse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (tableRegisterCourse.getSelectedRow() != -1) {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to drop course?", studentId,
							JOptionPane.YES_NO_OPTION);
					if (option == 0) {
						try {
							int rowNum = tableRegisterCourse.getSelectedRow();
							courseId = (String) tableRegisterCourse.getValueAt(rowNum, 0);
							courseNum = (String) tableRegisterCourse.getValueAt(rowNum, 1);
							term = (String) tableRegisterCourse.getValueAt(rowNum, 3);
							DatabaseConnection connection = new DatabaseConnection();
							Connection conn = connection.openConnection();

							String sql = "delete from course_enrollment Where student_id='" + studentId
									+ "' and course_id='" + courseId + "' and course_num='" + courseNum + "' and term='"
									+ term + "' ";
							PreparedStatement statement = conn.prepareStatement(sql);
							statement.executeUpdate(sql);

							statement.close();
							conn.close();
							JOptionPane.showMessageDialog(null,
									"Course Successfully dropped from the Student Course List");
							dispose();
							new ClientDashboard(studentId);

						} catch (Exception ex) {
							ex.printStackTrace();
							// JOptionPane.showMessageDialog(null, "Error while dropping course");

						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please Select course");
				}

			}
		});*/

		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (e.getSource() == btnPay) {
						Payment payment = new Payment(studentId);

						dispose();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		JScrollPane GPAData = new JScrollPane();
		sl_acedamics.putConstraint(SpringLayout.NORTH, GPAData, 10, SpringLayout.NORTH, acedamics);
		sl_acedamics.putConstraint(SpringLayout.WEST, GPAData, 10, SpringLayout.WEST, acedamics);
		sl_acedamics.putConstraint(SpringLayout.SOUTH, GPAData, -10, SpringLayout.SOUTH, acedamics);
		sl_acedamics.putConstraint(SpringLayout.EAST, GPAData, 759, SpringLayout.WEST, acedamics);
		acedamics.add(GPAData);
		springLayout.putConstraint(SpringLayout.NORTH, GPAData, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, GPAData, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, GPAData, 290, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, GPAData, 440, SpringLayout.WEST, this);

		tableModel = new DefaultTableModel(columnName, 0);
		GPATable = new JTable();
		GPATable.setModel(tableModel);
		GPAData.setViewportView(GPATable);

		tableModelFees = new DefaultTableModel(columnNameFees, 0);
		table_3 = new JTable();
		table_3.setModel(tableModelFees);
		// GPAData.setViewportView(GPATable);

		// SpringLayout acade = new SpringLayout();
		// acedamics.setLayout(acade);
	try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from course_enrollment Where student_id='" + studentId + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				String courseId = rs.getString("course_id");
				String c_description = rs.getString("course_description");
				String courseNum = rs.getString("course_num");
				;
				String term = rs.getString("term");
				String grade = rs.getString("grades");

				// action= new JCheckBox("Details");
				tableModel.addRow(new Object[] { courseId, courseNum, c_description, term, grade });
				// TableColumn selectColumn=
				// studentData.getColumnModel().getColumn(4);
				// selectColumn.setCellEditor(new DefaultCellEditor(action));
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from course_enrollment Where student_id='" + studentId + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				String courseId = rs.getString("course_id");
				String c_description = rs.getString("course_description");
				String courseNum = rs.getString("course_num");
				;
				String term = rs.getString("term");

				// action= new JCheckBox("Details");
				courseRegisterModel.addRow(new Object[] { courseId, courseNum, c_description, term });
				// TableColumn selectColumn=
				// studentData.getColumnModel().getColumn(4);
				// selectColumn.setCellEditor(new DefaultCellEditor(action));

				// new AddCourse(studentId);
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql = "Select * from fees Where student_id='" + studentId + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				// textField_4.setText(rs.getString("present_due"));
				// table_2.

			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from fees";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				String fees = rs.getString("present_due");

				// action= new JCheckBox("Details");
				tableModelFees.addRow(new Object[] { fees });
				// TableColumn selectColumn=
				// studentData.getColumnModel().getColumn(4);
				// selectColumn.setCellEditor(new DefaultCellEditor(action));
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

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

		/*
		 * // add button btnAdd.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { try { if
		 * (e.getSource() == btnAdd) { String textfield = textField.getText();
		 * model.addElement(textfield); }
		 * 
		 * }
		 * 
		 * catch (Exception ex) { ex.printStackTrace(); } } });
		 */
	}

	public void studentData() {
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from student_accessible Where studentid='"+studentId+"'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			if (rs.next()) {

				studentName = rs.getString("name");
				studentId = rs.getString("studentid");
				studentAddress = rs.getString("address");
				
				studentUserName = rs.getString("username");
				studentDepartment = rs.getString("program");
				
				// Object[] data= (Object[]) new Object();
				
				
				nameField.setText(studentName);
				addressField.setText(studentAddress);

				studentIdField.setText(studentId);

				userNameField.setText(studentUserName);

				

				departmentField.setText(studentDepartment);
				// action= new JCheckBox("Details");
				//
				// TableColumn selectColumn=
				// studentData.getColumnModel().getColumn(4);
				// selectColumn.setCellEditor(new DefaultCellEditor(action));
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}
}

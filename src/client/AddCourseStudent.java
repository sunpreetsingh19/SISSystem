package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class AddCourseStudent extends JFrame {
	private JTable courseTable;

	String[] column = { "Course ID", "Course Number", "Course Description", "Term", "Start Date", "End Date",
			"Start Time", "End Time", "Vacancy" };
String studentId, courseId, courseNum, courseDesc,term;
	public AddCourseStudent(String studentId) {

		this.studentId= studentId;
		setTitle("Add Course");
		setVisible(true);
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		DefaultTableModel tableModelCourse = new DefaultTableModel(column, 0);
		JScrollPane addCoursePane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, addCoursePane, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, addCoursePane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, addCoursePane, 424, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, addCoursePane, 684, SpringLayout.WEST, getContentPane());
		getContentPane().add(addCoursePane);

		courseTable = new JTable(tableModelCourse);
		addCoursePane.setViewportView(courseTable);
		
		JButton btnAdd = new JButton("Add");
		getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, -6, SpringLayout.WEST, btnCancel);
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 6, SpringLayout.SOUTH, addCoursePane);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, addCoursePane);
		getContentPane().add(btnCancel);

		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from courses";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while(rs.next()) {
				String courseId = rs.getString("course_id");
				String courseName = rs.getString("course_description");
				String courseNum = rs.getString("course_num");
				term = rs.getString("term");
				String startDate = rs.getString("start_date");
				String endDate = rs.getString("end_date");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String vacancy = rs.getString("vacancy");
				
				tableModelCourse.addRow(new Object[] { courseId, courseNum,courseName, term,startDate, endDate, startTime, endTime, vacancy });

			}

			statement.close();
			conn.close();

		} catch (Exception ex) {

		}
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(courseTable.getSelectedRow()!=-1) {
					int option=	JOptionPane.showConfirmDialog(null, "Are you sure you want to add course to Student List?", studentId, JOptionPane.YES_NO_OPTION);
					if(option==0) {
					try {
						int rowNum= courseTable.getSelectedRow();
						 courseId= (String)courseTable.getValueAt(rowNum, 0) ;
						 courseNum= (String)courseTable.getValueAt(rowNum, 1) ;
						 courseDesc= (String)courseTable.getValueAt(rowNum, 2);
						DatabaseConnection connection = new DatabaseConnection();
						Connection conn = connection.openConnection();
						
						String sql = "Insert INTO course_enrollment (student_id, course_id, course_num, term, course_description) VALUES ('"+ studentId+"', '"+courseId+"' ,'"+courseNum+"','"+term+"','"+courseDesc+"')";
							PreparedStatement statement = conn.prepareStatement(sql);
							 statement.executeUpdate(sql);

							
							statement.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "Course Successfully Added to the Student Course List");
						dispose();
						new ClientDashboard(studentId);

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Course Already Taken");

					}
					}
				}
					else {
						JOptionPane.showMessageDialog(null, "Please Select course");
					}

			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ClientDashboard(studentId);
				
			}
		});
	}

}

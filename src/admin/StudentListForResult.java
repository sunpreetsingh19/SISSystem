package admin;

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

public class StudentListForResult extends JFrame implements ActionListener {

	String term;
	String course;
	DefaultTableModel tableModel;
	String[] columns = { "Student ID", "Student Name", "Course Name", "Grades" };
	private JTable studentResultTable;
	String courseNum, courseId;
	JButton btnSelect, btnCancel;
	String studentId, courseName;
	String studentName;
	public StudentListForResult(String term, String course) {
		
		this.term= term;
		this.course= course;
		
		setVisible(true);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("Student List");
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		tableModel = new DefaultTableModel(columns, 0);

		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 329, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 584, SpringLayout.WEST, getContentPane());
		getContentPane().add(scrollPane);

		studentResultTable = new JTable(tableModel);
		scrollPane.setViewportView(studentResultTable);
		
		btnSelect = new JButton("Select");
		getContentPane().add(btnSelect);
		btnSelect.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 9, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnSelect, -6, SpringLayout.WEST, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, scrollPane);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(this);

		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select ce.student_id, sa.studentid, ce.course_description, sa.name, ce.grades from student_accessible AS sa INNER join course_enrollment AS ce ON ce.student_id = sa.studentid Where ce.term = '"  
								+ this.term + "' and ce.course_description='" + this.course + "' ";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			
				while (rs.next()) {

					String name = rs.getString("sa.name");
					String studentId = rs.getString("ce.student_id");
					String grades = rs.getString("ce.grades");
					String courseName = rs.getString("ce.course_description");

					tableModel.addRow(new Object[] { studentId, name,courseName, grades });

				}

				statement.close();
				conn.close();
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSelect) {
			int rowNum = studentResultTable.getSelectedRow();
			studentId = (String) studentResultTable.getValueAt(rowNum, 0);
			courseName=(String) studentResultTable.getValueAt(rowNum, 2);
			studentName= (String)studentResultTable.getValueAt(rowNum, 1);
			new ProvideGrades(studentId,studentName, courseName);
			dispose();
		}
		else if(e.getSource()==btnCancel) {
			dispose();
		}
		
	}
}

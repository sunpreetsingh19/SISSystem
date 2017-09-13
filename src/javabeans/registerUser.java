package javabeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import model.RegisterPageUser;

public class registerUser {

	private String name, username, password, question1, question2, answer1, answer2;

	public int registeruser() throws Exception {

		RegisterPageUser pageuser = new RegisterPageUser();

		name = pageuser.name;
		username = pageuser.username;
		password = pageuser.password;
		question1 = pageuser.question1;
		question2 = pageuser.question2;
		answer1 = pageuser.answer1;
		answer2 = pageuser.answer2;

		DatabaseConnection connection = new DatabaseConnection();
		Connection conn = connection.openConnection();
		String sql = "INSERT INTO registeruser values (?,?,?,?,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, name);
		statement.setString(2, username);
		statement.setString(3, password);
		statement.setString(4, question1);
		statement.setString(5, answer1);
		statement.setString(6, question2);
		statement.setString(7, answer2);

		int result = statement.executeUpdate();

		conn.close();
		return result;

		// String myDriver= "org.gjt.mm.mysql.Driver";

	}
}

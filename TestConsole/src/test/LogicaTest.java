package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import utils.PropertyReader;

public class LogicaTest {

	public LogicaTest() {

	}

	public void principalLogica() {
		Connection conn = null;

		try {

			String url = "jdbc:mysql://" + PropertyReader.getProp("host") + ":" + PropertyReader.getProp("port") + "/"
					+ PropertyReader.getProp("schema") + "?user=" + PropertyReader.getProp("user") + "&password="
					+ PropertyReader.getProp("password");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();

			System.out.println("conectado a la base de datos");

			String query = "";

			query = "update usuario set usuario = 'admin' where id = 2";
			st.execute(query);

			ArrayList<String> queries = new ArrayList<String>();
			queries.add("insert into usuario (usuario) values ('tester3')");
			queries.add("delete from usuario where usuario = 'tester3'");
			queries.add("update usuario set usuario = 'admin1' where id = 2");

			obtenerUsuarios(conn, st);

			for (String q : queries) {
				query = q;

				st.execute(query);
				int lineasInsertadas = st.getUpdateCount();
				if (lineasInsertadas > 0)
					System.out.println(query + " lineas: " + lineasInsertadas);

				obtenerUsuarios(conn, st);
			}

			obtenerPerfiles(conn, st);

			st.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
				System.out.println("conexion cerrada");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void obtenerPerfiles(Connection conn, Statement st) {
		try {
			String query = "select * from perfil";

			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				int id = rs.getInt("id");
				String perfil = rs.getString("perfil");
				Timestamp time = rs.getTimestamp("fecha_ing");

				System.out.println("Perfil: (" + id + ") " + perfil + " - " + time);
			}
			rs.close();

		} catch (SQLException e) {

		}
	}

	public void obtenerUsuarios(Connection conn, Statement st) {
		try {
			String query = "select * from usuario";

			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				int id = rs.getInt("id");
				String usuario = rs.getString("usuario");
				Timestamp time = rs.getTimestamp("fecha_ing");

				System.out.println("Usuario: (" + id + ") " + usuario + " - " + time);
			}
			rs.close();

		} catch (SQLException e) {

		}
	}

}

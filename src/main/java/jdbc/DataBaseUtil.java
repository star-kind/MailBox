package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库Jdbc工具类
 * 
 * @author gzh
 *
 */
public class DataBaseUtil {
	public static Connection connect;
	public static ResultSet resultSet;
	public static Statement stat;
	private static String dirURI;

	static {
		dirURI = System.getProperty("user.dir");// user.dir指定了当前的路径
		System.out.println(dirURI);
	}

	/**
	 * 连接数据库,且返还Statement对象
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			// 加载数据库驱动
			Class.forName("org.sqlite.JDBC");
			System.out.println("数据库驱动加载成功");

			// 通过访问数据库路径获取数据库连接对象,如无,则于当前工程目录创一个db数据库
			connect = DriverManager.getConnection("jdbc:sqlite:" + dirURI
					+ "/eclipse-workspace/MailBox/postbox.db");
			System.out.println("数据库连接成功");

			// stat = conne.createStatement();
			// System.out.println("开始返还statement对象");
		} catch (SQLException e) {
			try {
				connect = DriverManager.getConnection(
						"jdbc:sqlite:/home/gzh/eclipse-workspace/MailBox/postbox.db");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}

	/**
	 * 关闭连接，释放资源
	 *
	 * @param conne
	 *            连接对象
	 * 
	 * @param rs
	 *            结果集对象
	 */
	public static void close(Connection conne, ResultSet rs) {
		// 关闭数据库连接体连接
		if (!(conne == null))
			try {
				conne.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		// 关闭结果集对象连接
		if (!(rs == null))
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 释放语句对象
	 * 
	 * @param stat
	 *            语句对象
	 */
	public static void closeStatObj(Statement stat) {

		// 关闭语句体对象连接
		if (!(stat == null))
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

	/**
	 * 释放conn/stat/rs对象
	 * 
	 * @param conn
	 * @param stat
	 * @param rs
	 */
	public static void closeThreeObj(Connection conn, Statement stat,
			ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (stat != null)
				stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 注意executeUpdateStatement和queryForStatement这两个方法不要与DAO层直连,不然会重复执行语句;
 * @author gzh
 *
 */
public class DataBaseUtilSon extends DataBaseUtil{
	/**
	 * 执行增删改SQL语句
	 * 
	 * @param statement
	 * @return
	 */
	public static Integer executeUpdateStatement(String statement) {
		Integer effcts = null;
		try {
			connect = getConnection();
			stat = connect.createStatement();

			effcts = stat.executeUpdate(statement);// 涉及表内,才会返回受影响行数
			System.out.println("Operation done successfully");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return effcts;
	}

	/**
	 * 查询,返还结果集
	 * 
	 * @param statement
	 * @return
	 */
	public static ResultSet queryForStatement(String statement) {
		try {
			// 无需简化此2步,因为KitsDao需要返还的是connection
			connect = getConnection();
			stat = connect.createStatement();
			resultSet = stat.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;

	}
	
//	 public static void main(String[] args) throws SQLException {
//	 String sql = "CREATE TABLE IF NOT EXISTS account( \n" +
//	 " acid Integer PRIMARY KEY AUTOINCREMENT, \n" +
//	 " acname varchar(255) NOT NULL UNIQUE, \n" +
//	 " gender int(1) DEFAULT '2' NOT NULL,\n" +
//	 " email text,\n" +
//	 " birth date NOT NULL,\n" +
//	 " reg_time date NOT NULL,\n" +
//	 " password varchar(52) NOT NULL,\n" +
//	 " salt varchar(20) NOT NULL\n" +
//	 " );";
//	 Integer integer = executeUpdateStatement(sql);
//	 System.out.println(integer);

	// 判断指定的表是否存在,可以用如下语句,如果查询结果大于0,表示该表存在于数据库中,否则不存在:
	// String sql = "select count(*) from sqlite_master where type='table' and
	// name = 'account';";
	// ResultSet set = queryForStatement(sql);
	// while (set.next()) {
	// System.out.println("count:" + set.getInt("count(*)"));
	// }

	// 原理:sqlite中有一个内建表sqlite_master，这个表中存储这所有自建表的表名称等信息
	// String sql="select name from sqlite_master where type='table' order by
	// name;";
	// ResultSet set = queryForStatement(sql);
	// while (set.next()) {
	// System.out.println("name:" + set.getString("name"));
	// }

	// 删除表
//	 String sql="DROP TABLE account;";
//	 Integer integer = executeUpdateStatement(sql);
//	 System.out.println(integer);
	     
//	 String sentence="CREATE TABLE IF NOT EXISTS receiveLetter(\n" + 
//	 	"	ID Integer PRIMARY KEY AUTOINCREMENT,\n" + 
//	 	"	transmitter Char(100) not null,\n" + 
//	 	"	receiver Char(100) not null,\n" + 
//	 	"	title VarChar(200) not null,\n" + 
//	 	"	content text not null,\n" + 
//	 	"	receiveTime datetime,\n" + 
//	 	"	moveInRecycleTime datetime,\n" + 
//	 	"	status int(1),\n" + 
//	 	"	attachmentFileName Char(255)\n" + 
//	 	");";
	 
//	String sentence1="CREATE TABLE IF NOT EXISTS transmitLetter(\n" + 
//		"	ID Integer PRIMARY KEY AUTOINCREMENT,\n" + 
//		"	transmitter Char(100) not null,\n" + 
//		"	receiver Char(100) not null,\n" + 
//		"	title VarChar(200) not null,\n" + 
//		"	content text not null,\n" + 
//		"	composeTime datetime,\n" + 
//		"	launchTime datetime,\n" + 
//		"	lastEditTime datetime,\n" + 
//		"	moveInRecycleTime datetime,\n" + 
//		"	status int(1),\n" + 
//		"	attachmentFileName Char(255)\n" + 
//		");";     
     
//	 Integer integer = executeUpdateStatement(sentence1);
	 
//	 System.out.println(integer);
	     
//	 String senten0="ALTER TABLE transmitLetter ADD COLUMN feature int DEFAULT 1;";
//	 
//	 String senten00="ALTER TABLE receiveLetter ADD COLUMN feature int DEFAULT 0;";
//	 
//	 Integer integer = executeUpdateStatement(senten00);
//	 System.out.println(integer);
//	 
//	 close(connect, resultSet);
//}
	
}
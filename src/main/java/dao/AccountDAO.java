package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.support.KitsDAO;
import dao.support.RowMapper;
import entity.Account;

/**
 * DAO类:封装数据访问逻辑
 * 
 * @author gzh
 *
 */
public class AccountDAO extends KitsDAO {
    /**
     * 创建内部类实现RowMapper,作用:将获得的结果集(1行)整顿为account型数据
     * 
     * @author gzh
     *
     */
    protected static class AccountRowMapper implements RowMapper<Account> {
	@Override
	public Account mapRow(ResultSet rs) throws SQLException {
	    Account account = new Account();

	    account.setAcid(rs.getInt("acid"));
	    account.setGender(rs.getInt("gender"));

	    account.setAcname(rs.getString("acname"));
	    account.setPassword(rs.getString("password"));
	    account.setSalt(rs.getString("salt"));
	    account.setEmail(rs.getString("email"));

	    account.setBirth(rs.getDate("birth"));
	    account.setRegTime(rs.getDate("reg_time"));

	    // System.out.println(account.toString());
	    return account;
	}

    }

    /**
     * 无条件查阅全部
     * 
     * @return
     */
    public List<Account> selectAllAccount() {
	String sql = "SELECT acid,acname,gender,salt,password,email,birth,reg_time FROM account";
	AccountRowMapper mapper = new AccountRowMapper();
	List<Account> list = query(sql, mapper);
	return list;

    }

    /**
     * 插入新一行Account
     * 
     * @param acc
     * @return
     */
    public Integer insertNewAccount(Account acc) {
	String sql = " INSERT INTO account ";
	sql += " (acname,email,gender,password,salt,birth,reg_time) ";
	sql += " VALUES ( ";
	sql += " ?,?,?,?,?,?,? ";
	sql += " ); ";

	return update(sql, acc.getAcname(), acc.getEmail(), acc.getGender(), acc.getPassword(), acc.getSalt(),
		acc.getBirth(), acc.getRegTime());

    }

    /**
     * 据名而查
     * 
     * @param acname
     * @return
     */
    public Account selectAccountByAcname(String acname) {
	String sql = " SELECT ";
	sql += " acid,acname,email,gender,password,salt,birth,reg_time ";
	sql += " FROM ";
	sql += " account ";
	sql += " WHERE ";
	sql += " acname=? ";
	sql += " ; ";

	return queryByCondition(sql, new AccountRowMapper(), acname);
    }

    /**
     * 据acid而查
     * 
     * @param acname
     * @return
     */
    public Account selectAccountByAcid(Integer acid) {
	String sql = " SELECT ";
	sql += " acid,acname,email,gender,password,salt,birth,reg_time ";
	sql += " FROM ";
	sql += " account ";
	sql += " WHERE ";
	sql += " acid=? ";
	sql += " ; ";

	return queryByCondition(sql, new AccountRowMapper(), acid);
    }

    /**
     * 
     * @param acid
     * @param password 新密码
     * @return
     */
    public Integer updatePasswordWhereAcid(Integer acid, String password) {
	String sql = "UPDATE account SET password=? WHERE acid=?";
	Integer effects = update(sql, password, acid);
	return effects;

    }

    /**
     * 
     * @param id
     * @return
     */
    public int deleteAccountByAcid(Integer id) {
	String sentence = "DELETE FROM account WHERE acid=?";

	Integer effects = update(sentence, id);

	return effects;
    }

}
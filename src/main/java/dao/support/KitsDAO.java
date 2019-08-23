package dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DataBaseUtil;

/**
 * 封装DAO层用到的常用方法.其他的DAO类应该继承自该DAO基础类,即可调用封装好的访问数据库方法.
 * 
 * @author gzh
 *
 */
public abstract class KitsDAO {
    /**
     * 增删改
     * 
     * @param sql
     * @param args
     * @return
     */
    protected Integer update(String sql, Object... args) {
	Integer effectRows = 0;
	Connection conn = null;
	PreparedStatement ps = null;

	try {
	    conn = DataBaseUtil.getConnection();
	    ps = conn.prepareStatement(sql);

	    // 设置参数
	    if (args != null && args.length > 0) {
		for (int i = 0; i < args.length; i++) {
		    ps.setObject(i + 1, args[i]);
		}
	    }

	    // 执行DML
	    effectRows = ps.executeUpdate();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    DataBaseUtil.closeThreeObj(conn, ps, null);
	}
	return effectRows;

    }

    /**
     * 查询
     * 
     * @param <T>
     * @param sql
     * @param rowMapper
     * @param args
     * @return
     */
    protected <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
	List<T> list = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    conn = DataBaseUtil.getConnection();
	    ps = conn.prepareStatement(sql);
	    // 设置参数
	    if (args != null && args.length > 0) {
		for (int i = 0; i < args.length; i++) {
		    ps.setObject(i + 1, args[i]);
		}
	    }
	    // 执行DQL
	    rs = ps.executeQuery();
	    // 处理结果集
	    while (rs.next()) {
		T entity = rowMapper.mapRow(rs);
		list.add(entity);
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    DataBaseUtil.closeThreeObj(conn, ps, rs);
	}
	return list;

    }

    /**
     * 附带条件根据参数查询<h2>一行数据</h2>
     * 
     * @param <T>
     * @param sql
     * @param rowMapper
     * @param args
     * @return
     */
    protected <T> T queryByCondition(String sql, RowMapper<T> rowMapper, Object... args) {
	List<T> list = this.query(sql, rowMapper, args);

	if (list != null && list.size() > 0) {
	    return list.get(0);
	}
	return null;

    }

}
package dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	/**
	 * 将结果集的一行记录封装为一个实体类对象 
	 * TIPS: 由实现类来实现
	 *
	 * @param rs
	 *            结果集对象
	 * @return {@link T}
	 */
	T mapRow(ResultSet rs) throws SQLException;
}

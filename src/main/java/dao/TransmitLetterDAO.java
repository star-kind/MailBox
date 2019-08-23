package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.support.KitsDAO;
import dao.support.RowMapper;
import entity.TransmitLetter;

/**
 * 
 * @author gzh
 *
 */
public class TransmitLetterDAO extends KitsDAO {
    protected static class TransmitLetterRowMapper implements RowMapper<TransmitLetter> {

	@Override
	public TransmitLetter mapRow(ResultSet rs) throws SQLException {
	    TransmitLetter transmitLetter = new TransmitLetter();
	    transmitLetter.setId(rs.getInt("id"));
	    transmitLetter.setComposeTime(rs.getDate("composeTime"));
	    transmitLetter.setLaunchTime(rs.getDate("launchTime"));
	    transmitLetter.setLastEditTime(rs.getDate("lastEditTime"));

	    transmitLetter.setTransmitter(rs.getString("transmitter"));
	    transmitLetter.setReceiver(rs.getString("receiver"));
	    transmitLetter.setTitle(rs.getString("title"));
	    transmitLetter.setContent(rs.getString("content"));
	    transmitLetter.setMoveInRecycleTime(rs.getDate("moveInRecycleTime"));
	    transmitLetter.setAttachmentFileName(rs.getString("attachmentFileName"));
	    transmitLetter.setStatus(rs.getInt("status"));
	    transmitLetter.setFeature(rs.getInt("feature"));

	    return transmitLetter;
	}

    }

    /** insert into */
    public Integer insertIntoAnTransmitLetter(TransmitLetter tl) {
	String sentence0 = "INSERT INTO ";

	sentence0 += "transmitLetter ( ";

	sentence0 += "transmitter,receiver,";

	sentence0 += "title,";

	sentence0 += "content,composeTime,";

	sentence0 += "launchTime,";

	sentence0 += "lastEditTime,";

	sentence0 += "moveInRecycleTime,";

	sentence0 += "status,";

	sentence0 += "attachmentFileName,";

	sentence0 += "feature ";

	sentence0 += " ) VALUES (?,?,?,?,?,?,?,?,?,?,?);";

	return update(sentence0, tl.getTransmitter(), tl.getReceiver(), tl.getTitle(), tl.getContent(),
		tl.getComposeTime(), tl.getLaunchTime(), tl.getLastEditTime(), tl.getMoveInRecycleTime(),
		tl.getStatus(), tl.getAttachmentFileName(), tl.getFeature());

    }

    /** 查询全部信件 */
    public List<TransmitLetter> selectAllTransmitLetter() {
	String sentence0 = "SELECT ";
	sentence0 += " id, transmitter, ";
	sentence0 += " receiver,title, ";
	sentence0 += " content, composeTime,";
	sentence0 += " launchTime, lastEditTime,";
	sentence0 += " moveInRecycleTime, status,";
	sentence0 += " attachmentFileName,";
	sentence0 += " feature ";
	sentence0 += " FROM transmitLetter;";

	return query(sentence0, new TransmitLetterRowMapper());

    }

    /** 据发件箱信件id而查 */
    public TransmitLetter selectAnTransmitLetterById(Integer id) {
	String sentence0 = "";
	sentence0 += "SELECT ";
	sentence0 += " id,transmitter,";
	sentence0 += " receiver,title,";
	sentence0 += " content,composeTime,";
	sentence0 += " launchTime,lastEditTime,";
	sentence0 += " moveInRecycleTime, ";
	sentence0 += " status,attachmentFileName,";
	sentence0 += " feature ";
	sentence0 += " FROM ";
	sentence0 += " transmitLetter ";
	sentence0 += " WHERE ";
	sentence0 += " id=?; ";

	return queryByCondition(sentence0, new TransmitLetterRowMapper(), id);
    }

    /** 据发信者查询属于他的全部信件 */
    public List<TransmitLetter> selectLetterListByTransmitter(String transmitter) {
	String sentence = null;
	sentence = "SELECT ";
	sentence += " id,transmitter,";
	sentence += " receiver,";
	sentence += " title,content,";
	sentence += " composeTime,";
	sentence += " launchTime,";
	sentence += " lastEditTime,";
	sentence += " moveInRecycleTime,";
	sentence += " status,";
	sentence += " attachmentFileName,";
	sentence += " feature ";
	sentence += " FROM ";
	sentence += " transmitLetter ";
	sentence += " WHERE ";
	sentence += " transmitter=?;";

	return query(sentence, new TransmitLetterRowMapper(), transmitter);

    }

    /** 删除,base on Id Array[1 ~ n] */
    public Integer deleteLetterByIdArray(Integer[] ids) {
	String sentence = "DELETE FROM transmitLetter ";
	
	sentence += " WHERE id IN ( ";
	
	String condition = " ?,";

	for (int i = 0; i < ids.length; i++) {
	    sentence += condition;
	}

	sentence = sentence.substring(0, sentence.length() - 1);
	sentence += " );";
	System.out.println(sentence);

	Integer rows = update(sentence, ids);

	return rows;
	
    }

    /** 据integer数组设置状态 */
    public Integer setStatusByIds(Integer[] ids, Integer status) {
	String sentence = "UPDATE transmitLetter SET status=";

	sentence += status;

	sentence += " WHERE id IN ( ";

	String condition = "?,";

	for (int i = 0; i < ids.length; i++) {
	    sentence += condition;
	}

	sentence = sentence.substring(0, sentence.length() - 1);
	sentence += " );";
	System.out.println(sentence);

	Integer rows = update(sentence, ids);

	return rows;
    }

    /** 据integer数组更新移至回收站时间 */
    public Integer updateMoveInRecycleTimeByIds(Integer[] ids, String moveInRecycleTime) {
	String sentence = "UPDATE transmitLetter ";
	sentence += " SET moveInRecycleTime=" + moveInRecycleTime;
	sentence += " WHERE id IN ( ";
	String condition = "?,";

	for (int i = 0; i < ids.length; i++) {
	    sentence += condition;
	}

	sentence = sentence.substring(0, sentence.length() - 1);
	sentence += " );";
	System.out.println(sentence);

	Integer rows = update(sentence, ids);

	return rows;
    }

    /** 更新一封信件的收件者，标题，正文，发送之时,上次改动时间，状态，附件名,据其id */
    public Integer updateAnTransmitLetterById(TransmitLetter t) {
	String sentence = "UPDATE transmitLetter SET ";

	sentence += " receiver=?, ";

	sentence += " title=?,content=?, ";

	sentence += " launchTime=?,lastEditTime=?, ";

	sentence += " attachmentFileName=?,status=? ";

	sentence += " WHERE id=?;";

	Integer affect = update(sentence, t.getReceiver(), t.getTitle(), t.getContent(), t.getLaunchTime(),
		t.getLastEditTime(), t.getAttachmentFileName(), t.getStatus(), t.getId());

	return affect;

    }

    /** 根据状态查找符合之信件们 */
    public List<TransmitLetter> selectTransmitLettersByStatus(Integer status) {
	String sentence = null;
	sentence = "SELECT ";
	sentence += " id,transmitter,";
	sentence += " receiver,title,";
	sentence += " content,composeTime,";
	sentence += " launchTime,lastEditTime,";
	sentence += " moveInRecycleTime,";
	sentence += " status,";
	sentence += " attachmentFileName,";
	sentence += " feature ";
	sentence += " FROM ";
	sentence += " transmitLetter ";
	sentence += " WHERE ";
	sentence += " status=?;";

	List<TransmitLetter> list = query(sentence, new TransmitLetterRowMapper(), status);

	return list;
    }

}
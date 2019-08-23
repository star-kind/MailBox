package mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.TransmitLetterDAO;
import entity.TransmitLetter;

/**
 * 
 * @author gzh
 *
 */
public class ORMTransmitLetter {

    @Test
    public void insertIntoNewRow() {
	TransmitLetterDAO dao = new TransmitLetterDAO();
	TransmitLetter letter = new TransmitLetter();
	Date now = new Date();

	letter.setTransmitter("machiacl");
	letter.setReceiver("iop@domain.com");
	letter.setTitle("topiCode");
	letter.setContent("omg");
	letter.setComposeTime(now);
	letter.setLaunchTime(now);
	letter.setLastEditTime(now);
	letter.setMoveInRecycleTime(null);
	letter.setStatus(2);
	letter.setAttachmentFileName("row-html.jpg");
	// letter.setFeature(1);

	Integer integer = dao.insertIntoAnTransmitLetter(letter);

	System.out.println(integer);
    }

    @Test
    public void seleAll() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	List<TransmitLetter> list = dao.selectAllTransmitLetter();

	for (TransmitLetter transmitLetter : list) {
	    System.out.println(transmitLetter.toString());
	}

    }

    @Test
    public void seleOneLetterById() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	TransmitLetter an = dao.selectAnTransmitLetterById(33);

	System.err.println(an.toString());
    }

    @Test
    public void seleLetterListByTransmitter() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	List<TransmitLetter> list = dao.selectLetterListByTransmitter("admin");

	for (TransmitLetter transmitLetter : list) {
	    System.out.println(transmitLetter.toString());
	}
    }

    @Test
    public void deleteByIds() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	Integer[] ids = { 11, 12 };

	Integer rows = dao.deleteLetterByIdArray(ids);
	System.out.println(rows);

    }

    @Test
    public void setStateByIds() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	int n = 3;
	Integer[] ids = { n, 4, 5 };

	Integer rows = dao.setStatusByIds(ids, 1);
	System.out.println(rows);
    }

    @Test
    public void updateTransmiterLetterById() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	TransmitLetter tl = new TransmitLetter();

	tl.setReceiver("1448616092@qq.com");
	tl.setTitle("englishOK");
	tl.setContent("upupupupupupupupupupupupupup");

	Date now = new Date();
	tl.setLaunchTime(now);
	tl.setLastEditTime(now);

	tl.setAttachmentFileName(null);
	tl.setStatus(0);
	tl.setId(5);

	Integer affect = dao.updateAnTransmitLetterById(tl);

	System.out.println(affect);
    }

    @Test
    public void seleTransmiterLetterListByStatus() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	List<TransmitLetter> list = dao.selectTransmitLettersByStatus(0);
	for (TransmitLetter transmitLetter : list) {
	    System.out.println(transmitLetter.toString());
	}
    }

    @Test
    public void updateRecycleTime() {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	Date now = new Date();

	Integer[] ids = { 11, 12, 13 };

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	String format = simpleDateFormat.format(now);

	Integer affects = dao.updateMoveInRecycleTimeByIds(ids, format);
	System.out.println(affects);
    }

}
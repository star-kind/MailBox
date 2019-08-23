package web.servlet.letter.box.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.kit.TransmiterLetterServletUtil;

/**
 * 将信件们从收发箱或未读之箱，移至垃圾箱或草稿箱等<br>
 * 具体如，发信箱内信件们可移至垃圾箱或草稿箱<br>
 * 收信箱内信件们可移至垃圾箱<br>
 * 未读之箱内信件们可移至垃圾箱或收信箱(已读)<br>
 * 
 * @author gzh
 *
 */
@WebServlet("/MoveLettersToBoxHandler")
public class MoveLettersToBoxHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	resp.setCharacterEncoding("utf-8");

	MoveLettersToBoxHandler handler = new MoveLettersToBoxHandler();

	TransmiterLetterServletUtil instance = TransmiterLetterServletUtil.getInstance();

	String ids = req.getParameter("ids");
	String forwardWhichBoxs = req.getParameter("forwardWhichBoxs");
	System.out.println(handler.getClass() + "::ids:" + ids + ",forwardWhichBoxs:" + forwardWhichBoxs);

	Integer[] integerArr = instance.conversion(ids);

	Integer affect = instance.forwardBoxOnCondition(forwardWhichBoxs, integerArr);
	System.err.println(handler.getClass() + "-affect-" + affect);

    }

}
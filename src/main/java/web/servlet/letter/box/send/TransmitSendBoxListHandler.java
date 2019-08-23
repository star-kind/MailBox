package web.servlet.letter.box.send;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransmitLetterDAO;
import entity.TransmitLetter;

/**
 * 折跃至发件箱列表页<br>
 * 
 * <info>与TransmitLetterExhibitionHandler雷同，考虑到整体结构麻烦的变动，暂无为<info>
 * @author gzh
 *
 */
@WebServlet("/transmittedLetterBoxList")
public class TransmitSendBoxListHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	TransmitLetterDAO dao = new TransmitLetterDAO();
	List<TransmitLetter> list = dao.selectTransmitLettersByStatus(1);
	
	req.setAttribute("list", list);
	req.getRequestDispatcher("TransmittedLetterBox.jsp").forward(req, resp);
    }

}
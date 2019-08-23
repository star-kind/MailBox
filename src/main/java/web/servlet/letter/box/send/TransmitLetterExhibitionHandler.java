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
 * 转发展示发信箱里的已送信件们<br>
 * <info>与TransmitSendBoxListHandler雷同，考虑到整体结构麻烦的变动，暂无为<info>
 * 
 * @author gzh
 *
 */
@WebServlet("/exhibit_transmit_list")
public class TransmitLetterExhibitionHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	List<TransmitLetter> list = dao.selectTransmitLettersByStatus(1);

	req.setAttribute("list", list);
	req.getRequestDispatcher("TransmittedLetterBox.jsp").forward(req, resp);
    }

}
package web.servlet.letter.box.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import dao.TransmitLetterDAO;
import entity.TransmitLetter;
import web.ex.ErrorCodeEnum;
import web.kit.TransmiterLetterServletUtil;

/**
 * 发送一份＂新写＂的邮件(注：与修改后再发送的Handler有所不同)
 * 
 * @author gzh
 *
 */
@WebServlet(value = "/sendMail", initParams = { @WebInitParam(name = "savePath", value = "uploads") })
public class TransLetterSendNewLetterHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // class variable
    private String savePath; // 上传文件的存储路径
    ServletContext application;
    private Map<String, String> parameters = new HashMap();

    @Override
    public void init(ServletConfig config) throws ServletException {
	savePath = config.getInitParameter("savePath"); // 获取文件的存储路径
	application = config.getServletContext();// 初始化ServletContext
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	// String domain = "@aliyun.com";// domain 领域，范围，范畴

	File file = doAttachment(req);// 获取上传的文件

	TransmiterLetterServletUtil instance = TransmiterLetterServletUtil.getInstance();

	MultiPartEmail email = new MultiPartEmail();

	try {
	    /* 以下为发送带附件邮件的行为 */
	    email.setCharset("utf-8");
	    email.setHostName(instance.getServerAddressByName(parameters.get("transmitter")));// STMP服务器
	    email.setAuthentication(parameters.get("transmitter"), (String) req.getSession().getAttribute("Κωδικός"));// authentication身份验证
	    email.addTo(parameters.get("receiver"));
	    email.setFrom(parameters.get("transmitter"));
	    email.setSubject(parameters.get("topic"));
	    email.setMsg(parameters.get("content"));

	    /* 如果存在附件 */
	    if (!(file == null)) {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(file.getPath());
		System.err.println("File Path: " + file.getPath());
		attachment.setDescription(EmailAttachment.ATTACHMENT); // 设置附件的描述
		attachment.setName(file.getName());
		email.attach(attachment);// 将附件绑至邮件上

		parameters.put("attach", file.getName());// 将附件名存入散列表
	    }
	    email.send();// 发送邮件

	    req.setAttribute("success", ErrorCodeEnum.SEND_EMAIL_SUCCESS.getDesc());

	    // 增入sqlite
	    setInTransmiterLette(parameters);

	} catch (EmailException e) {
	    e.printStackTrace();
	    req.setAttribute("defated", ErrorCodeEnum.SEND_EMAIL_DEFATED.getDesc());
	}

	req.getRequestDispatcher("SendsEmailResult.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
    }

    /**
     * 将文件上传至服务器
     * 
     * @param request
     * @return
     */
    public File doAttachment(HttpServletRequest request) {
	File file = null;

	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);

	String realPath = application.getRealPath("/");// 获取项目容器实际路径
	String uri = realPath + savePath;
	System.err.println("\nuri:" + uri + "\nrealPath:" + realPath + "\nsavePath:" + savePath + "\n");

	File uploadFolder = new File(uri);// 上传之附件保存之文件夹之实例对象

	if (!uploadFolder.exists()) {
	    uploadFolder.mkdir();
	}

	try {
	    System.out.println("uploadFolder.CanonicalPath:" + uploadFolder.getCanonicalPath());

	    /*
	     * 获取所上传的所有文件和参数的集合对象<br> <br> form必须要有enctype="multipart/form-data"属性设置 <br>
	     * 标记中有"enctype"属性，该表单提交之后不能通过request.getParameter("name")取值<br>
	     */
	    List items = upload.parseRequest(request);

	    Iterator<FileItem> iterator = items.iterator();

	    while (iterator.hasNext()) {
		FileItem item = (FileItem) iterator.next();

		if (item.isFormField()) {// 如果是表单项的情况，将属性和值进行存储，即未上传附件
		    // 将表单字段名作为key,字段值作为value,置入Map中
		    parameters.put(item.getFieldName(), item.getString("utf-8"));

		    /* 遍历map */
		    for (Map.Entry<String, String> entry : parameters.entrySet()) {
			System.out.print(" key: " + entry.getKey() + ",value: " + entry.getValue());
		    }
		    System.out.print("\n");
		} else {
		    // 若是file类型之表单，即上传有附件
		    if (!(item.getName() == null) && !"".equals(item.getName())) {
			File tempFile = new File(item.getName());// item.getName()上传文件在客户端的完整路径名称

			System.out.println("item.getName()--" + item.getName());

			// 根据给定的路径名创建一个新的文件实例，ServletContext容器的路径+文件名
			file = new File(uri, tempFile.getName());
			System.err.println("file.CanonicalPath:" + file.getCanonicalPath());

			item.write(file);
		    }
		}
	    }
	} catch (FileUploadException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (Throwable e) {
	    e.printStackTrace();
	}

	return file;
    }

    /**
     * 添增进数据表
     * 
     * @param param
     * @return
     */
    public Integer setInTransmiterLette(Map<String, String> param) {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	TransmitLetter letter = new TransmitLetter();

	// 发件者邮箱
	String transmitter = param.get("transmitter");
	letter.setTransmitter(transmitter);

	// 收信者邮箱
	String receiver = param.get("receiver");
	letter.setReceiver(receiver);

	// 标题
	String title = param.get("title");
	letter.setTitle(title);

	// 正文
	String content = param.get("content");
	letter.setContent(content);

	// 状态为一
	letter.setStatus(1);

	Date now = new Date();
	// 创建时间为现在
	letter.setComposeTime(now);

	// 发送时间为现在
	letter.setLaunchTime(now);

	// 上次编辑时间为现在
	letter.setLastEditTime(now);

	// 移入回收站时间不存在
	letter.setMoveInRecycleTime(null);

	// 附件名
	String attach = param.get("attach");
	System.out.println("attach:" + attach);
	letter.setAttachmentFileName(attach);

	letter.setFeature(1);

	Integer row = dao.insertIntoAnTransmitLetter(letter);
	System.out.println("row:" + row);
	return row;
    }

}
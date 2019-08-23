package web.servlet.letter.box.send;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import web.ex.ErrorCodeEnum;
import web.kit.TransmiterLetterServletUtil;

/**
 * 获取修改后的信件数据并立即发送给目标
 * 
 * @author gzh
 *
 */
@WebServlet("/transmit_letter_send_revamp")
public class TransmitLetterSendRevampHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Map<String, String> map = new HashMap();

    /* 上传文件之配置 */
    private static final int MEMORY_THRESHOLD = 3 * 1024 * 1024;// 阈值
    private static final int MAX_FILE_SIZE = 8 * 1024 * 1024;
    private static final int MAX_REQUEST_SIZE = 12 * 1024 * 1024;

    /* 上传文件存储目录 */
    private static final String UPLOAD_DIRECTORY = "upload";

    /** *上传数据及保存文件 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");

	if (!ServletFileUpload.isMultipartContent(req)) {// 检测是否为多媒体文件上传
	    // 若非，停止
	    PrintWriter writer = resp.getWriter();
	    writer.print("Error:表单必须包含属性:enctype=multipart/form-data");
	    writer.flush();
	    return;
	}

	File file = doAttachment(req);// 执行方法，获取上传的文件

	TransmiterLetterServletUtil instance = TransmiterLetterServletUtil.getInstance();

	MultiPartEmail email = new MultiPartEmail();

	try {
	    email.setCharset("utf-8");
	    email.setHostName(instance.getServerAddressByName(map.get("transmitter")));// STMP服务器
	    email.setAuthentication(map.get("transmitter"), (String) req.getSession().getAttribute("Κωδικός"));// authentication身份验证
	    email.addTo(map.get("receiver"));
	    email.setFrom(map.get("transmitter"));
	    email.setSubject(map.get("title"));
	    email.setMsg(map.get("content"));

	    if (!(file == null)) {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(file.getPath());
		System.err.println("File Path: " + file.getPath());
		attachment.setDescription(EmailAttachment.ATTACHMENT); // 设置附件的描述
		attachment.setName(file.getName());
		email.attach(attachment);// 将附件绑至邮件上

		map.put("attach", file.getName());// 将附件名存入散列表
	    }

	    email.send();

	    instance.revampTransmiterLetter(map, 1);

	    req.setAttribute("hint", ErrorCodeEnum.SEND_EMAIL_SUCCESS.getDesc());
	} catch (Exception e) {
	    e.printStackTrace();
	    req.setAttribute("hint", ErrorCodeEnum.SEND_EMAIL_DEFATED.getDesc());
	}

	req.getRequestDispatcher("ManipulateLetterResult.jsp").forward(req, resp);
    }

    /**
     * 将文件上传至汤姆猫服务器
     * 
     * @param rq
     * @return
     */
    public File doAttachment(HttpServletRequest rq) {
	File storeFile = null;

	DiskFileItemFactory factory = new DiskFileItemFactory(); // 配置上传参数
	factory.setSizeThreshold(MEMORY_THRESHOLD); // 设置内存阈值上限，超过上限将产生临时文件存于临时文件夹里

	// 获取项目绝对路径+自建临时文件夹
	String absolute = rq.getServletContext().getContextPath() + "/temporary_directory";
	System.out.println("temporary_directory:" + absolute);

	File temporaryDirectory = new File(absolute);
	if (!temporaryDirectory.exists()) {// 若不存，则建之
	    temporaryDirectory.mkdir();
	}

	factory.setRepository(temporaryDirectory);// 设置临时存储目录

	ServletFileUpload upload = new ServletFileUpload(factory);

	// 设置文件上传最大值
	upload.setFileSizeMax(MAX_FILE_SIZE);

	// 设置请求之最大值，含文件及表单数据
	upload.setSizeMax(MAX_REQUEST_SIZE);

	// 设置字符集格式
	upload.setHeaderEncoding("utf-8");

	// 构造临时路径以存储上传之文件，此路径相对于当前应用的目录
	String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
	System.out.println("uploadPath:" + uploadPath);

	File uploadDir = new File(uploadPath);
	if (!uploadDir.exists()) {
	    uploadDir.mkdir();
	}

	try {
	    System.out.println("uploadDir-canonical-path:" + uploadDir.getCanonicalPath());// canonical规范的，经典的

	    List<FileItem> formItems = upload.parseRequest(rq);// 解析请求内容，提取文件数据

	    if (!(formItems == null) && formItems.size() > 0) {
		// 迭代表单数据
		for (FileItem item : formItems) {
		    // 处理不在表单中的字段
		    if (!item.isFormField() && !(item.getName() == null) && !"".equals(item.getName())) {
			String fileName = new File(item.getName()).getName();
			String filePath = uploadPath + File.separator + fileName;
			System.out.println("filePath:" + filePath + "\nfileName:" + fileName);

			storeFile = new File(filePath);
			item.write(storeFile);// 存入至硬盘

		    } else {
			map.put(item.getFieldName(), item.getString("utf-8"));

			for (Map.Entry<String, String> e : map.entrySet()) {
			    System.out.print("  map-key:" + e.getKey() + " -->  map-value:" + e.getValue());
			}
			System.out.println("\n");

		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return storeFile;
    }

}
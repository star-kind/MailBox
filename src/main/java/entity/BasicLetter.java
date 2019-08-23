package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 收信与送信之超类; <br>
 * transmitter 发信人;<br>
 * receiver 收信人; <br>
 * title 信件标题; <br>
 * content信件正文内容; <br>
 * moveInRecycleTime移入回收站之时;<br>
 * status 状态;<br>
 * attachmentFileName 附件文件名;<br>
 * 
 * 发信:status ("0-回收站,1-已发送(发件箱),2-未发送(草稿)");<br>
 * 收信:status 状态(0-回收站,1-已阅,2-未阅);<br>
 * 
 * @author gzh<br>
 * 
 */
public class BasicLetter implements Serializable {
    private static final long serialVersionUID = -9101378579060799777L;

    private String transmitter;
    private String receiver;
    private String title;
    private String content;
    private Date moveInRecycleTime;
    private Integer status;
    private String attachmentFileName;
    private Integer feature;

    public String getTransmitter() {
	return transmitter;
    }

    public void setTransmitter(String transmitter) {
	this.transmitter = transmitter;
    }

    public String getReceiver() {
	return receiver;
    }

    public void setReceiver(String receiver) {
	this.receiver = receiver;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Date getMoveInRecycleTime() {
	return moveInRecycleTime;
    }

    public void setMoveInRecycleTime(Date moveInRecycleTime) {
	this.moveInRecycleTime = moveInRecycleTime;
    }

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public String getAttachmentFileName() {
	return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
	this.attachmentFileName = attachmentFileName;
    }

    public Integer getFeature() {
	return feature;
    }

    public void setFeature(Integer feature) {
	this.feature = feature;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("BasicLetter [transmitter=");
	builder.append(transmitter);
	builder.append(", receiver=");
	builder.append(receiver);
	builder.append(", title=");
	builder.append(title);
	builder.append(", content=");
	builder.append(content);
	builder.append(", moveInRecycleTime=");
	builder.append(moveInRecycleTime);
	builder.append(", status=");
	builder.append(status);
	builder.append(", attachmentFileName=");
	builder.append(attachmentFileName);
	builder.append(", feature=");
	builder.append(feature);
	builder.append("]");
	return builder.toString();
    }

}
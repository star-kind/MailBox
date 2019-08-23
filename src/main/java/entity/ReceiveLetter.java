package entity;

/**
 * 收件箱
 * 
 * @author gzh
 *
 */
public class ReceiveLetter extends BasicLetter {
    private static final long serialVersionUID = -7662769675244586654L;

    private Integer id;
    private String receiveTime;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getReceiveTime() {
	return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
	this.receiveTime = receiveTime;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ReceiveLetter [id=");
	builder.append(id);
	builder.append(", receiveTime=");
	builder.append(receiveTime);
	builder.append(", getId()=");
	builder.append(getId());
	builder.append(", getReceiveTime()=");
	builder.append(getReceiveTime());
	builder.append(", getTransmitter()=");
	builder.append(getTransmitter());
	builder.append(", getReceiver()=");
	builder.append(getReceiver());
	builder.append(", getTitle()=");
	builder.append(getTitle());
	builder.append(", getContent()=");
	builder.append(getContent());
	builder.append(", getMoveInRecycleTime()=");
	builder.append(getMoveInRecycleTime());
	builder.append(", getStatus()=");
	builder.append(getStatus());
	builder.append(", getAttachmentFileName()=");
	builder.append(getAttachmentFileName());
	builder.append(", getFeature()=");
	builder.append(getFeature());
	builder.append("]");
	return builder.toString();
    }

}
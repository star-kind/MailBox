package entity;

import java.util.Date;

/**
 * 
 * @author gzh
 *
 */
public class TransmitLetter extends BasicLetter {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date composeTime;
    private Date launchTime;
    private Date lastEditTime;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Date getComposeTime() {
	return composeTime;
    }

    public void setComposeTime(Date composeTime) {
	this.composeTime = composeTime;
    }

    public Date getLaunchTime() {
	return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
	this.launchTime = launchTime;
    }

    public Date getLastEditTime() {
	return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
	this.lastEditTime = lastEditTime;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("TransmitLetter [id=");
	builder.append(id);
	builder.append(", composeTime=");
	builder.append(composeTime);
	builder.append(", launchTime=");
	builder.append(launchTime);
	builder.append(", lastEditTime=");
	builder.append(lastEditTime);
	builder.append(", getId()=");
	builder.append(getId());
	builder.append(", getComposeTime()=");
	builder.append(getComposeTime());
	builder.append(", getLaunchTime()=");
	builder.append(getLaunchTime());
	builder.append(", getLastEditTime()=");
	builder.append(getLastEditTime());
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
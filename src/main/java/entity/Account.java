package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号实体类
 * 
 * @author gzh
 *
 */
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8709211717537305867L;

	private Integer acid;
	private Integer gender;
	private String acname;
	private String password;
	private String salt;
	private String email;
	private Date birth;
	private Date regTime;

	public Integer getAcid() {
		return acid;
	}
	public void setAcid(Integer acid) {
		this.acid = acid;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAcname() {
		return acname;
	}
	public void setAcname(String acname) {
		this.acname = acname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [acid=");
		builder.append(acid);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", acname=");
		builder.append(acname);
		builder.append(", password=");
		builder.append(password);
		builder.append(", salt=");
		builder.append(salt);
		builder.append(", email=");
		builder.append(email);
		builder.append(", birth=");
		builder.append(birth);
		builder.append(", regTime=");
		builder.append(regTime);
		builder.append("]");
		return builder.toString();
	}

}
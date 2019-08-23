package web.kit;

/**
 * SMTP服务器枚举
 * 
 * @author gzh
 *
 */
public enum EnumPostboxSMTPServer {
    ONE_SIX_THREE("163", "smtp.163.com"),
    /*
     * 
     */
    ALIYUN("aliyun", "smtp.mail.aliyun.com"),
    /*
     * 
     */
    TWENTY_ONE("21cn", "smtp.21cn.com"),
    /*
     * 
     */
    SINA("sina", "smtp.sina.com"),
    /*
     * 
     */
    QQ("qq", "smtp.qq.com"),
    /*
     * 
     */
    INSTANCE;

    private String name;
    private String serverAddress;

    private EnumPostboxSMTPServer(String name, String serverAddress) {
	this.name = name;
	this.serverAddress = serverAddress;
    }

    private String getName() {
	return name;
    }

    private void setName(String name) {
	this.name = name;
    }

    private String getServerAddress() {
	return serverAddress;
    }

    private void setServerAddress(String serverAddress) {
	this.serverAddress = serverAddress;
    }

    public String getServerAddressByName(String name) {
	for (EnumPostboxSMTPServer smtp : EnumPostboxSMTPServer.values()) {
	    System.out.println("序数词(如第一、第二等):" + smtp.ordinal());

	    if (smtp.getName().equals(name)) {
		return smtp.serverAddress;
	    }
	}
	return null;

    }

    private EnumPostboxSMTPServer() {
    }
    
}
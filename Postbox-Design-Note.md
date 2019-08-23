sqlite数据库3.0使用总结:

+ 不支持comment.<br>

+ 主键不允许用int类型,Integer代之.<br>

+ 自增AUTOINCREMENT与not null相斥.<br>

+ sqlite3数据库对于字段的操作仅限于增加字段,如果想要删除字段或修改字段名则需要用复制整个页表的形式(sqlite3没有提供直接修改删除字段的功能).<br>

+ Cannot add a NOT NULL column with default value NULL;(not null 与 default 得一齐起)<br>

+ 使用jdbc代码查询时加别名会报错:SQLException: no such column: 'xxx',建议本项目库表字段名字直接连写,不要用 "_" 分隔;
<br>

_______________________________________________________________________________________

+ 命令:git pull origin master --allow-unrelated-histories 	# 可消除远程与本地之间的代码差异<br>

+ git命令历史<br>
	* git init<br>
	* git status<br>
	
	* git add 'filename'        #add .  为添加当前所有文件及目录<br>

	* git commit -m 'lastest commit'<br>
	* git remote add origin git@gitee.com:xxx/yyy.git<br>
	* git pull origin master<br>
	* git pull origin master --allow-unrelated-histories<br>
	* git push -u origin master<br>
_________________________________________________________

Failed to load resource: the server responded with a status of 404 ()<br>

jsp页面方便解决,通过引入el表达式: ${pageContext.request.contextPath}/xxx<br>
---------------------------------------------------------

Servlet项目jsp发送请求报404,已写@WebServlet,但未扫描到Handler路径.
<br>
解决:前往web.xml,检查:<br>
(0) version是否大于3.0<br>
(1) metadata-complete是否为false<br>

正确内容:<br>
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	metadata-complete="false" version="3.1" />
<br>
注：路径前记得加＇/＇，不然启动服务器会报错<br>
------------------------------------------

java.lang.ClassNotFoundException: org.sqlite.JDBC
<br>
原因:<br>
和classLoader有关,对于纯java项目,它不存在WEB-INF目录,所以在引入jar包的时候一般都是通过buildpath直接引入,例如我要引入Spring3X,那么先定义一个user library,然后通过build path引入. <br>

纯java项目使用的本地自己的JRE,那么classLoader在加载jar和class时候是分开的,对于我们自己编写的class,会在APP_HOME/bin下.<br> 导入的jar包或者user library的配置信息会出现在APP_HOME/.classpath文件中,<br>ClassLoader会很智能去加载这些classes和jar; .classpath文件内容如下：
<br>

``````
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
    <classpathentry kind="src" path="src"/>
    <classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/jre6"/>
    <classpathentry kind="con" path="org.eclipse.jdt.USER_LIBRARY/Spring3.1.0"/>
    <classpathentry kind="lib" path="E:/Jar_Framework/cglib/cglib-2.2.2.jar" sourcepath="E:/Jar_Framework/cglib/cglib-src-2.2.2.jar"/>
    <classpathentry kind="lib" path="E:/Jar_Framework/commons-logging-1.1.1-bin/commons-logging-1.1.1/commons-logging-1.1.1.jar"/>
    <classpathentry kind="lib" path="E:/Jar_Framework/aopalliance-1.0/aopalliance-1.0.jar"/>
    <classpathentry kind="lib" path="E:/Jar_Framework/cglib/cglib-nodep-2.2.2.jar"/>
    <classpathentry kind="lib" path="E:/Jar_Framework/spring revelant/aspectj-1.6.12.jar"/>
    <classpathentry kind="lib" path="E:/Jar_Framework/spring revelant/aspectjweaver-1.6.8.jar"/>
    <classpathentry kind="lib" path="E:/Jar_Framework/spring revelant/asm-3.2.jar"/>
    <classpathentry kind="output" path="bin"/>
</classpath>
``````

<br>
这样ClassLoader就会正确的找到所有需要的类. 而对于java web项目,<br>就不一样了,虽然eclipse的workspace中仍然有.classpath文件,<br>但即使你导入的了自己定义的user library,<br>它也不会出现在.classpath中,这就是问题的关键. 

对于java web项目,它最终不是通过本地的JRE去运行,<br>而是部署到web服务器,如Tomcat、Weblogic、WebSphere等,这些服务器都实现了自身的类加载器. 

以Tomcat典型结果为例,它的四组目录结构common、server、shared、webapps分别对应四个不同的自定义类加载器CommonClassLoader、CatalinaClassLoader、SharedClassLoader和WebappClassLoader,<br>WebappClassLoader加载器专门负责加载webapps下面各个web项目的WEB-INF下的类库. <br>而我们通过user library引入的jar包自然不会被WebappClassLoader加载器加载,所以必然会报ClassNotFoundException. 

解决:<br>
第一步-添加驱动依赖:<br>
<!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
		<dependency>
			<groupId>org.xerial</groupId>
			<artifactId>sqlite-jdbc</artifactId>
			<version>3.23.1</version>
		</dependency>
		
第二步-将jar包移至WEBINF目录下的lib文件夹内(没有lib文件夹就自创一个),<br>然后右键工程-->properties-->Java build path-->classpath-->add JARs-->选中lib文件夹里的jar包<br>

第三步-再执行一次创表语句<br>
----------------------------------------------------------
RuntimeException: org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: account).<br>

解决:<br>
但是用sqlite命令行是可以做访问 account 那张表的.
<br>
最后发现原来在定义dataSource的时候必须制定db的完整路径：<br>
``````
private static String dirURI;
	
	static {
		dirURI = System.getProperty("user.dir");//user.dir指定了当前的路径 
		System.out.println(dirURI);
	}
	
``````<br>
----------------------------------------------------------

tomcat启动报错:<br>
Caused by: 
java.lang.IllegalStateException:<br>
 Unable to complete the scan for annotations for web application [/MailBox] due to a StackOverflowError.<br> 
 Possible root causes include a too low setting for -Xss and illegal cyclic inheritance dependencies. <br>
<br> 
The class hierarchy being processed was [org.sqlite.SQLiteConnection->org.sqlite.jdbc4.JDBC4Connection->
<br>
org.sqlite.jdbc3.JDBC3Connection->org.sqlite.SQLiteConnection]

翻译:非法状态异常：由于堆栈溢出错误，无法完成对Web应用程序[/mailbox]<br>
批注的扫描。可能的根本原因包括-xss设置太低以及非法的循环继承依赖项。
<br>正在处理的类层次结构是
<br>
[org.sqlite.sqliteConnection->org.sqlite.jdbc4.jdbc4连接->org.sqlite.jdbc3.jdbc3连接->org.sqlite.sqliteConnection]
<br>
这个问题的根本原因是jar包冲突，继承关系刚好相反，故造成tomcat启动的循环依赖问题，<br>
导致堆栈溢出。所以应该考虑的是如何解决jar依赖问题.<br>
<br>
解决:<br>
修改sqlite-jdbc依赖为如下(即与数据库版本一致):<br>
<!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
		<dependency>
			<groupId>org.xerial</groupId>
			<artifactId>sqlite-jdbc</artifactId>
			<version>3.23.1</version>
		</dependency>
----------------------------------------------------------
<br>
Caused by: org.sqlite.SQLiteException:
<br>
[SQLITE_CONSTRAINT] Abort due to constraint violation (UNIQUE constraint failed: account.acname)<br>
原因：粗心大意,insert(statement)重复执行了两次<br>
----------------------------------------------------------
ajax获取到的中文内容呈乱码<br>
<br>
解决:<br>
- 在后台如下处理:<br>
		String acc00 = req.getParameter("account");<br>
		String acc = URLDecoder.decode(acc00, "UTF-8");//在此转换编码<br>
		System.out.println("received: " + acc);<br>
		
- ajax方法加上参数: 'contentType': "application/x-www-form-urlencoded; charset=UTF-8"<br>
----------------------------------------------------------

required属性仅在submit事件被触发时才起效.<br>
----------------------------------------------------------

路径跳转要一再注意,若代码一切正确却404,记住:跳转时只有最后一个'/'之后面的字符串有变化.<br>
----------------------------------------------------------

视图层前台文件引入js报错,如:GET http://localhost:8080/Jquery/jq.js 404
<br>或<br>:ValidateCode.js:3 GET http://localhost:8080/Jquery/jq.js net::ERR_ABORTED 404

常规办法解决(不一定100%真正起效):<br>
第0步:添加依赖<br>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>4.3.22.RELEASE</version>
		</dependency>

第一步:<br>
在resources目录下创建spring-mvc.xml:
于其中添加内容:
	<!-- 用默认的Servlet来响应静态文件 -->
	<mvc:default-servlet-handler />
	
第二部:<br>
于web.xml内加上:
	<!-- 配置前端控制器 -->
	<servlet>
		<servlet-name>SpringMVC</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath*:spring-*.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
----------------------------------------------------------

若在HTML内联标签中已书写<br>
"onclick='xxx()'/onsubmit='xxx()'/onchange='xxx()'"等写法时,
<br>
不宜将对应之JavaScript函数封装在外部文件,当存在于当前页中.
<br>
----------------------------------------------------------

- 新增技术项:
0.使用了单例模式<br>
1.数据库MySQL换为sqlite v3.0<br>
2.随机验证码<br>
3.实现文件上传及发送<br>
4.使用了枚举<br>
5.XMLHttpRequest<br>

- 改而不变:前端脚本方面增加了JavaScript代码比率,jQuery占比减少.
----------------------------------------------------------

总体功能需求:<br>
- 写信,收信<br>
- 查看信件(单件和列表)<br>
- 删除信件(单/多)<br>
----------------------------------------------------------

一封信包含之功能与要素:<br>
- 要素:<br>
  + 收件人<br>
  + 标题<br>
  + 正文<br>
  + 附件(文件上传)<br>
  + 发送者<br>

- 功能:<br> 0.发送;<br> 1.定时发送<br>
----------------------------------------------------------
###本方至他人<br>
``````
发送之信件表字段设计: (
	ID Integer PRIMARY KEY AUTOINCREMENT,
	发信人not null,
	收信人not null,
	标题not null,
	正文not null,
	创建之时(年月日时分秒)datetime default null,
	发送之时(年月日时分秒)datetime default null,
	上次编辑时间 default null,
	移入回收站之时(年月日时分秒)datetime default null,
	状态 ("0-回收站,1-已发送(发件箱),2-未发送(草稿)")
	);
	
``````
<br>
###他者至本方<br>
``````
收信之表:(
	ID Integer PRIMARY KEY AUTOINCREMENT,
	发信人not null,
	收信人not null,
	信件标题not null,
	信件内容not null,
	收信时间datetime default null,
	移入回收站之时datetime default null,
	状态 (0-回收站,1-已阅,2-未阅)
);
``````
----------------------------------------------------------

电子邮件的在网络中传输和网页一样需要遵从特定的协议，常用的电子邮件协议包括:<br>
<br>SMTP，POP3，IMAP。其中邮件的创建和发送只需要用到SMTP协议。
<br>SMTP 是Simple Mail Transfer Protocol 的简称，即简单邮件传输协议。<br>

所需jar包:java-mail-1.4.5.jar<br>

* SMTP( Simple Mail Transfer Protocol ):电子邮件发送协议。<br>当我们使用客户端程序发送邮件时，<br>
  客户端程序先把邮件发送给我们设置SMTP服务器，然后SMTP服务器再根据收件人的地址，把邮件中转给接受方的SMTP服务器，<br>以便让邮件收件人最终使用POP3或者IMAP方式收取邮件。<br>JavaMail API不包含SMIP的服务程序，<br>所以使用JavaMail技术发送邮件，需要其他SMTP服务器的支持。<br>

* POP3协议（Post Office Protocol V3）:是一种收取邮件的协议，<br>
  通过这个协议，可以让邮件客户端程序把存在POP3服务器上的邮件接收到本机上进行处理。<br>

* IMAP协议（Internet Message Access Protocol）：<br>
  IMAP是更加高级的用于接收邮件的协议。<br>

* MIME协议（Multipurpose Internet Mail Extensions）:<br>
  它不是邮件传输协议，但对传输内容的文本、附件及其他内容定义了格式。<br>
----------------------------------------------------------

创一个专门的超类以整合收发双表属性字段,single pojo bean继承之.<br>
	发信人not null;<br>
	收信人not null;<br>
	信件标题not null;<br>
	信件内容not null;<br>
	移入回收站之时(年月日时分秒)datetime default null;<br>
	状态;<br>
----------------------------------------------------------

----------------------------------------------------------

发信原理本质为"借调"其他商业平台的邮箱发送邮件，所以所借调之邮箱，<br>必须得开启smtp服务，要求开通授权码的，优先开通并使用授权码.<br>
有授权码的，按以下操作:<br>

- setAuthentication(发件者邮箱, 发件者邮箱授权码);		//发件者邮箱必须加上@domain网址,例如:@126.com 或　@qq.com<br>

- addTo(收件者邮箱);	//必须加上@domain网址,例如:@126.com 或　@qq.com<br>

- email.setFrom(发件者邮箱);	 	//必须加上@domain网址,例如:@126.com 或　@qq.com<br>

<br>
无要求开通smtp等服务授权码的邮箱，如21cn，可如下操作：<br>

* 注册名必须与邮箱之用户名相同，如官方邮箱全名为:test@21cn.com,则注册账户名亦必须为test；<br>

* 此时setAuthentication(发件者邮箱, 发件者邮箱真正的密码);<br>

* email.addTo(parameters.get("receiver"));		//在前台页面填写收件人时亦必须加上后缀网址＠domain，例如:@126.com 或　@qq.com<br>

* email.setFrom(parameters.get("transmitter") + ＠domain);<br>

* 若果收信者之收信箱未找到刚发送的邮件，可查看一下垃圾箱<br>
----------------------------------------------------------

添加外部jar的时候，注意须添加到 WEB-INF 下 lib 目录里面，并添加到 build path，否则会报错:<br>

java.lang.ClassNotFoundException: ...<br>
----------------------------------------------------------

优化更新：<br>
0.注册名必须含有＠ｄｏｍａｉｎ（如＠21ｃｎ.ｃｏｍ），如此于发送邮件时，后台不必再手动更换邮箱域名。<br>

1.创建一个枚举或者集合，包含现已支持ＳＭＴＰ收发邮件服务的邮箱服务器地址名，<br>包括:smtp.mail.aliyun.com、smtp.21cn.com、smtp.qq.com（必须开通授权码） 、smtp.163.com、smtp.sina.com，<br>根据获取的发信者邮箱域名遍历选中相应的接收协议服务器。<br>

2.删除email字段的非空约束；<br>

3.发送邮件需要密码的话，参考req.getSession().setAttribute("acid", account.getAcid());<br>
----------------------------------------------------------

熟悉项目业务流程是开发首要，尤其于出错排障之时。<br>
----------------------------------------------------------

时间类型Date在DAO层逻辑代码中不能直接作为参数，须转化为字符串．<br>
----------------------------------------------------------

查看：建一个公共handler，根据前台传与的参数＂源箱＂决定前往哪张jsp.<br>
----------------------------------------------------------

二表皆新增一列feature(int)，1指发信表，0指收信表<br>
ps:似乎无用处
----------------------------------------------------------

现今仅能对基于发信表的系统部分进行操作，收信模块中止开发．<br>
----------------------------------------------------------

#工程竣工总结
<br>
工程名：邮件发送系统(基于Maven+Servlet+Java Bean)，目前支持QQ/21cn/163/阿里云/新浪五种邮箱发送邮件．<br>

0.开发环境：Ubuntu
1.开发工具：Eclipse+Sublime Text+VS Code
2.管理工具:Maven
3.数据库:Sqlite v3.0
4.服务端服务器 Tomcat v9.0
5.客户端:Google Chrome/Firefox
6.邮件发送协议：SMTP

项目框架:无<br>

涉及的设计模式：单例模式<br>

所用语言
- 后端:Sqlite+java<br>
- 前端:HTML+CSS,JavaScript+JQuery,JSTL+JSP EL<br>

模块及其可用功能：<br>
+ 账户模块：<br>
<ul>
	<li>注册：注册资料包括用户名(无法重复)，第二邮箱，密码(MD5盐值加密)，性别，出生日期。<br>
	其中用户名必须按正式邮箱域名(username@domain.com)来注册，否则报错.<br>
	注：若想向外发送邮件成功，必须用已存在的真实邮箱账号．<br>
	<li>登录：用户名或密码有误会在当前页面提示；登录前须输入视图上正确的随机验证码，否则提醒报错。<br>
	登陆成功后，进入面板主页.
</ul>

* 邮件发送模块
<ol>
<li>编写一封新的信件，收件人、主题、内容必填，可上传附件发送，新邮件发送成功后，会自动移入发件箱．<br>
注：收件方最好应开通SMTP服务协议，如此方可收取邮件.<br>
</ol>

+ 发件箱
<ol>
<li>查看箱内全部已发送邮件的预览信息(收信者/标题/附件名/发信时间)<br>
<li>查看一封邮件的详情内容，并编辑此封邮件，可将编辑好的信件再次重新发送；或移至回收站；或将一封邮件存至草稿箱<br>
<li>可多选将多份信件移至回收站<br>
<li>可多选彻底删除多份邮件<br>
</ol>

+ 草稿箱
<ol>
<li>查看箱内全部草稿信件的预览信息(收信者/标题/附件名/信件创建时间/上次改动时间)<br>
<li>查看一封草稿邮件的详情，并可将该封信件再次重新发送，或者编辑这封草稿(编辑好之后，可再次重新发送，或移至回收站)。<br>
<li>可多选将多份信件移至回收站<br>
<li>可多选彻底删除多份草稿信件<br>
</ol>

+ 垃圾箱
<ol>
<li>查看箱内全部信件的预览信息(发件者/收信者/标题/附件名/移至垃圾箱时间)<br>
<li>查看一封垃圾邮件的详情，并可将该封信件再次重新发送，或者编辑这封草稿(编辑好之后，可再次重新发送，或移至回收站)。<br>
<li>可多选彻底删除多份垃圾邮件<br>
<li>可多选将多份垃圾邮件恢复至收件箱/发件箱/草稿箱<br>
</ol>
__________________________________________________________


package com.img.gen.conmon;

import java.io.File;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.alibaba.fastjson.JSONObject;
import com.sun.mail.smtp.SMTPAddressFailedException;

public class EmailTools {
	// 邮件服务器及登录用户、密码,发送邮箱
	private static String userName;// 用户名
	private static String passWord;// 密码
	private static String smtpServer;// 邮件服务器
	private static String from;// 发件人
	private static String mail_socksProxyHost;// 本地代理host
	private static String mail_socksProxyPort;// 本地代理port
	private static String mail_socksProxyUser;// 本地代理用户
	private static String mail_socksProxyPassword;// 本地代理密码
	private static Log log = LogFactory.getLog(EmailTools.class);
	
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 
			10,30,TimeUnit.SECONDS, 
			new ArrayBlockingQueue<Runnable>(100,true), 
			new ThreadPoolExecutor.DiscardOldestPolicy()); 
	// 从配置文件中获取相应的账户信息
	static {
		//Properties props = new Properties();
		//props.load(EmailTools.class.getClassLoader().getResourceAsStream("email/mail.properties"));
		userName = PropertyConfigurer.getStringProperty("mail_username");// 设置发件人用户名
		passWord = PropertyConfigurer.getStringProperty("mail_password");// 设置发件人密码
		smtpServer = PropertyConfigurer.getStringProperty("mail.smtp.server");// 设置发件人邮件服务器
		from = PropertyConfigurer.getStringProperty("mail_from_addr");// 设置发件人邮箱
		mail_socksProxyHost = PropertyConfigurer.getStringProperty("mail_socksProxyHost");// 代理地址
		mail_socksProxyPort = PropertyConfigurer.getStringProperty("mail_socksProxyPort");// 代理端口号
		mail_socksProxyUser = PropertyConfigurer.getStringProperty("mail_socksProxyUser");// 代理用户名
		mail_socksProxyPassword = PropertyConfigurer.getStringProperty("mail_socksProxyPassword");// 代理密码
	}
	
	/**
	 * 发送邮件
	 * 
	 * @return 成功-true;失败-false
	 */
	public static boolean send(List<String> tolist, EmailDTO dto) {
		return send(tolist, null, dto);
	}

	/**
	 * 发送邮件
	 * 
	 * @return 成功-true;失败-false
	 */
	public static boolean send(List<String> tolist, List<String> cclist, EmailDTO dto) {
		try {
			Properties props = System.getProperties();
			props.setProperty("proxySet", "true");
			if (mail_socksProxyHost != null && !mail_socksProxyHost.equals("")) {
				props.setProperty("socksProxyHost", mail_socksProxyHost);// 代理地址
			}
			if (mail_socksProxyPort != null && !mail_socksProxyPort.equals("")) {
				props.setProperty("socksProxyPort", mail_socksProxyPort);// 代理端口号
			}
			if (mail_socksProxyUser != null && !mail_socksProxyUser.equals("")) {
				props.setProperty("socksProxyUser", mail_socksProxyUser);
			}
			if (mail_socksProxyPassword != null && !mail_socksProxyPassword.equals("")) {
				props.setProperty("socksProxyPassword", mail_socksProxyPassword);
			}
			props.setProperty("mail.smtp.host", smtpServer);// 设置邮件发送服务器
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			// 使用验证
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(userName, passWord);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from,dto.getOperator() != null && !"".equals(dto.getOperator())?dto.getOperator():"系统管理员"));// 设置发件人信息
		       
			// 是否有收件人
			if (tolist != null && tolist.size() > 0) {
				Address[] addressTO = new InternetAddress[tolist.size()]; // 收件人
				for (int i = 0; i < tolist.size(); i++) {
					addressTO[i] = new InternetAddress(tolist.get(i));
				}
				message.setRecipients(Message.RecipientType.TO, addressTO);// 设置收件人信息
				if (cclist != null && cclist.size() > 0) {
					Address[] addressCC = new InternetAddress[cclist.size()];// 抄送人
					for (int j = 0; j < cclist.size(); j++) {
						addressCC[j] = new InternetAddress(cclist.get(j));
					}
					message.setRecipients(Message.RecipientType.CC, addressCC);
				}
				message.setSubject(dto.getSubject());// 设置邮件主题
				Multipart mm = new MimeMultipart(); // 新建一个MimeMultipart对象用来存放多个BodyPart对象
				BodyPart mdp = new MimeBodyPart(); // 新建一个存放信件内容的BodyPart对象
				mdp.setContent(dto.getBody(), "text/html;charset=gb2312"); // 给BodyPart对象设置内容和格式/编码方式
				mm.addBodyPart(mdp); // 将含有信件内容的BodyPart加入到MimeMultipart对象中
				BodyPart mbpf = new MimeBodyPart();
				String filepath = dto.getFilename();
				if(filepath != null && StringHelper.isEmpty(filepath)) {//如果包含附件就增加附件
					FileDataSource fds = new FileDataSource(filepath);// 附件
					mbpf.setDataHandler(new DataHandler(fds));
					mbpf.setFileName(MimeUtility.encodeWord(fds.getName(), "GB2312", null));
					mm.addBodyPart(mbpf);
				}
				message.setContent(mm); // 把mm作为消息对象的内容
				message.setSentDate(new Date());// 设置发送时间
				message.saveChanges();
				Transport.send(message);// 发送邮件操作
				return true;
			}
		} catch (AuthenticationFailedException afx) {
			if (log.isDebugEnabled()) {
				//TODO log i18n
	            log.debug("服务邮箱配置异常");
	        }
		} catch (SendFailedException sfx) {
			if(sfx.getCause() instanceof SMTPAddressFailedException) {
				SMTPAddressFailedException smtpfx = (SMTPAddressFailedException)sfx.getNextException();
				String missingAddress = smtpfx.getAddress().toString();
				if (log.isDebugEnabled()) {
					//TODO log i18n
		            log.debug("收件人地址" + missingAddress + "不存在");
		        }
				tolist.remove(missingAddress);
				if (cclist != null && cclist.size() > 0) {
					cclist.remove(missingAddress);
				}
				send(tolist, cclist, dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送邮件
	 * @param toEmails    收件人，支持群发
	 * @param subject     邮件主题
	 * @param templateID  邮件模版
	 * @param isWithFile  默认false 不带附件
	 * @param map         模版对应参数
     * @return 成功-true;失败-false
	 */
	public static boolean sendFileMail(EmailDTO req) { 
		System.out.println("发送邮件入参==========================" + JSONObject.toJSONString(req));
		boolean isSucess = false;
		try {
			final JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
	        // 设定mail server  
	        senderImpl.setHost(req.getServer() != null && !"".equals(req.getServer())?req.getServer():smtpServer);  
	        senderImpl.setUsername(req.getUserName() != null && !"".equals(req.getUserName())?req.getUserName():userName);  
	        senderImpl.setPassword(req.getPassWord() != null && !"".equals(req.getPassWord())?req.getPassWord():passWord); 
	        Properties props = new Properties();  //此处必须设置，
			props.put("mail.smtp.auth", "true");  //不设置会出现 553错误
			props.put("mail.debug", "false");
			senderImpl.setJavaMailProperties(props); 
			// 使用验证
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(userName, passWord);
				}
			});
			senderImpl.setSession(session);
	        // 建立HTML邮件消息  
	        final MimeMessage mailMessage = senderImpl.createMimeMessage();  
	        // true表示开始附件模式  
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");  
	  
	        // 设置收件人，寄件人  
	        messageHelper.setTo(req.getToEmails());
	        messageHelper.setFrom(new InternetAddress(req.getFrom() != null && !"".equals(req.getFrom())?req.getFrom():from,req.getOperator() != null && !"".equals(req.getOperator())?MimeUtility.encodeText(req.getOperator(), "UTF-8", "B"):MimeUtility.encodeText("系统管理员", "UTF-8", "B")));
	        // 邮件主题
	        messageHelper.setSubject(req.getSubject());  
	        String content = "";
	        if (req.getEmailType() != 1) {
	        	// 采取 text方式读取
	        	content = req.getContent();
	        } else {
	        	// 采取 Velocity方式读取
	        	content = fromVMToString(req.getTemplateID(),req.getParamMap());
	        }
	        // true 表示启动HTML格式的邮件  
	        messageHelper.setText(content, true);  
	        
	      
	        // 判断是否含有附件 如果 true 则包含附件
	        if (req.isWithFile()) {
	        	Map<String,Object> fileresult = withFile(String.valueOf(req.getParamMap().get("fileUrl")),String.valueOf(req.getParamMap().get("fileName")));
	        	if (Boolean.getBoolean(fileresult.get("reslut").toString())) {
	                FileSystemResource file = (FileSystemResource) fileresult.get("file");
		            //附件名有中文可能出现乱码  
		            messageHelper.addAttachment(MimeUtility.encodeWord(fileresult.get("name").toString()), file);  
	        	}
	        }
	        // 发送邮件 
	        threadPool.execute(new Runnable() {  
	            public void run() {  
	                try {  
	                	 senderImpl.send(mailMessage);  
	                } catch (Exception ex) {  
	                	ex.printStackTrace(); 
	                }  
	            }  
	        });  
	       
	       // senderImpl.send(mailMessage); 
	        isSucess = true;
		} catch (MessagingException e) {
			
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch(Throwable e){
			e.printStackTrace();
			return false;
		}
		return isSucess;
    }
	
	/**
	 * 取得附件
	 * @param fileUrl
	 * @param fileName
	 * @return
	 */
	private static Map<String,Object> withFile(String fileUrl,String fileName) {
		Map<String,Object> result = new HashMap<String,Object>();
		FileSystemResource file = new FileSystemResource(new File(fileUrl)); 
		if (file.exists()) {
			// 附件存在
			result.put("name", fileName);
			result.put("file", file);
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		return result;
	}
	
	/**
	 * 根据模版ID 加载邮件内容
	 * @param templateID
	 * @return
	 * @throws Exception 
	 * @throws ParseErrorException 
	 * @throws ResourceNotFoundException 
	 */
	private static String fromVMToString(String templateID,Map<String,Object> map) {
		String result = "";
		  // 初始化并取得Velocity引擎  
        VelocityEngine ve = new VelocityEngine();  
        String basePath = EmailTools.class.getResource("/").getPath()+"velocity";
        System.out.println("==============="+ basePath);
        System.out.println("==============="+EmailTools.class.getClassLoader().getResourceAsStream(""));
        System.out.println("==============="+EmailTools.class.getClassLoader().getResourceAsStream("/"));
        // 设置模板的路径  
        Properties properties = new Properties();
        properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, basePath); 
        properties.setProperty(Velocity.RUNTIME_LOG, "/data/ops/deploy/webserver/CURRENT/tomcat-wwwp01/logs/velocity.log");
        // 初始花velocity 让设置的路径生效  
        ve.init(properties);

        
        //取得velocity的模版   
        Template t = ve.getTemplate(templateID+".vm","utf-8");    
  
        // 取得velocity的上下文context  
        VelocityContext context = new VelocityContext();   
        context.put("map", map);  
  
        // 输出流  
        StringWriter writer = new StringWriter();  
        // 转换输出  
		t.merge(context, writer);
	
        // 输出信息  
		result = writer.toString();
		return result;
	}
	
	public static void main(String[] args) {
		// 发送邮件方法
		/*List<String> tolist = new ArrayList<String>(); // 发件人集合
		tolist.add("15811102402@163.com");
		List<String> cclist = new ArrayList<String>();// 抄送人集合
		cclist.add("15811102402@163.com");
		cclist.add("51087095@qq.com");
		cclist.add("634470220@qq.com");
		EmailDTO dto = new EmailDTO();
		dto.setSubject("这是测试邮件");// 设置邮件标题
		dto.setBody("<h1>" + "<font color='RED'>" + "邮件测试" + ".</font></h1>");// 设置邮件的格式
		dto.setFilename("C:\\exp\\test.xls"); // 附件
		boolean sucess = EmailTools.send(tolist, cclist, dto);
		System.out.println(sucess);*/
		//String m = fromVMToString("send_email", new HashMap<>());
		//System.out.println(m);
		// 发送邮件方法2
		EmailDTO emailDTO = new EmailDTO();
		String[] toEmails = new String[2];
		toEmails[0] = "634470220@qq.com";
		toEmails[1] = "278871398@qq.com";
		emailDTO.setToEmails(toEmails);
		String subject = "新奥恩纽邮件测试系统";
		emailDTO.setSubject(subject);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone","13552940116");
		emailDTO.setParamMap(map);
		//String templateID = "send_email";
		//emailDTO.setTemplateID(templateID);
		emailDTO.setEmailType(2);
		emailDTO.setContent("<a href=onclick='alert(1)'>xx</a>");
		emailDTO.setOperator("系统管理员");
		// 测试其他邮箱
		/*emailDTO.setFrom("service_enn@126.com");
		emailDTO.setUserName("service_enn");
		emailDTO.setPassWord("nbbkqxfnfwgpqqvh");
		emailDTO.setServer("smtp.126.com");*/
		boolean sucess = sendFileMail(emailDTO);
		System.out.println(sucess);
	}
}

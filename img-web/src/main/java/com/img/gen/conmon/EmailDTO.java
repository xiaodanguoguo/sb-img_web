package com.img.gen.conmon;

import java.io.Serializable;
import java.util.Map;

public class EmailDTO implements Serializable {
	
	private String id;
	private String title; // 邮件标题
	private String emailAddr;// 邮件地址
	private String content; // 邮件内容
	private String type; // 邮件类型
	private String crtTm; // 创建时间
	private String remark; // 说明
	private String stateChar;// 状态
	private String sendNum; // 发送次数
	private String updTm; // 发送时间
	private String crtCde; // 发件人
	private String from; // 发件人
	private String[] toEmails; // 收件人
	private String subject; // 设置邮件主题
	private String body; // 设置邮件信息内容
	private String server; // 发送邮件服务器
	private String socksProxyHost;// 设置代理服务器地址
	private String socksProxyPort;// 设置代理服务器端口号
	private String socksProxyUser;// 设置代理服务器用户
	private String socksProxyPassword;// 设置代理服务器密码
	private String passWord;// 发送者账户密码
	private String userName;// 发送者账户名称
	private String filename;// 附件
	
	private String templateID;// 发送内容模板ID
	private boolean isWithFile;// 是否包含附件 默认否
	private Map<String,Object> paramMap;// 模版对应参数
	private Integer emailType;// 邮件类型 ， 1. 模板  2. 文本
	private String operator;// 操作人
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCrtTm() {
		return crtTm;
	}
	public void setCrtTm(String crtTm) {
		this.crtTm = crtTm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStateChar() {
		return stateChar;
	}
	public void setStateChar(String stateChar) {
		this.stateChar = stateChar;
	}
	public String getSendNum() {
		return sendNum;
	}
	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}
	public String getUpdTm() {
		return updTm;
	}
	public void setUpdTm(String updTm) {
		this.updTm = updTm;
	}
	public String getCrtCde() {
		return crtCde;
	}
	public void setCrtCde(String crtCde) {
		this.crtCde = crtCde;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getToEmails() {
		return toEmails;
	}
	public void setToEmails(String[] toEmails) {
		this.toEmails = toEmails;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getSocksProxyHost() {
		return socksProxyHost;
	}
	public void setSocksProxyHost(String socksProxyHost) {
		this.socksProxyHost = socksProxyHost;
	}
	public String getSocksProxyPort() {
		return socksProxyPort;
	}
	public void setSocksProxyPort(String socksProxyPort) {
		this.socksProxyPort = socksProxyPort;
	}
	public String getSocksProxyUser() {
		return socksProxyUser;
	}
	public void setSocksProxyUser(String socksProxyUser) {
		this.socksProxyUser = socksProxyUser;
	}
	public String getSocksProxyPassword() {
		return socksProxyPassword;
	}
	public void setSocksProxyPassword(String socksProxyPassword) {
		this.socksProxyPassword = socksProxyPassword;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getTemplateID() {
		return templateID;
	}
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}
	public boolean isWithFile() {
		return isWithFile;
	}
	public void setWithFile(boolean isWithFile) {
		this.isWithFile = isWithFile;
	}
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	public Integer getEmailType() {
		if (emailType == null || emailType == 0)
			emailType = 1;
		return emailType;
	}
	public void setEmailType(Integer emailType) {
		if (emailType == null || emailType == 0)
			emailType = 1;
		this.emailType = emailType;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}

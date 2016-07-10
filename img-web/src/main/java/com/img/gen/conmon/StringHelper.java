package com.img.gen.conmon;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 字符串辅助类
 * </p>
 * 
 * @author Dave
 * @version
 * @see org.apache.commons.lang.StringUtils
 *
 */
public class StringHelper extends StringUtils {

	private static String strDefaultKey = "national";

	private Cipher encryptCipher = null;

	private Cipher decryptCipher = null;

	public static String joinStringArray(String[] strArray, String separator) {
		if (strArray == null || strArray.length == 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("'").append(strArray[0]).append("'");
		for (int i = 1; i < strArray.length; i++) {
			buffer.append(separator).append("'").append(strArray[i]).append("'");
		}
		return buffer.toString();
	}

	/**
	 * 格式化小数保持最少和最多小数点.
	 * 
	 * @param num
	 * @param minFractionDigits
	 * @param maxFractionDigits
	 * @return
	 */
	public static String formatFraction(double num, int minFractionDigits, int maxFractionDigits) {
		// 输出固定小数点位数
		java.text.NumberFormat nb = java.text.NumberFormat.getInstance();
		nb.setMaximumFractionDigits(maxFractionDigits);
		nb.setMinimumFractionDigits(minFractionDigits);
		nb.setGroupingUsed(false);
		String rate = nb.format(num);

		return rate;
	}

	public static boolean isNumb(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		String regex = "^[1-9][0-9]*\\.[0-9]+$|^[1-9][0-9]*$|^0+\\.[0-9]+$";
		char c = str.charAt(0);
		boolean bool;
		if (c == '+' | c == '-') {
			bool = str.substring(1).matches(regex);
		} else {
			bool = str.matches(regex);
		}
		if (bool) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Same function as javascript's escape().
	 * 
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * Same function as javascript's unsecape().
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 将字符串转换为 int.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static int parseInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 将字符串转换为 long.
	 * 
	 * @param input
	 *            输入的字串
	 * @return 结果数字
	 */
	public static long parseLong(String input) {
		try {
			return Long.parseLong(input);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 将字符串转换为 long.
	 * 
	 * @param input
	 *            输入的字串
	 * @return 结果数字
	 */
	public static double parseDouble(String input) {
		try {
			return Double.parseDouble(input);
		} catch (Exception e) {
		}
		return 0;
	}

	// ------------------------------------ 字符串处理方法
	// ----------------------------------------------

	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, 并以大小写敏感方式进行查找
	 *
	 * @param source
	 *            需要替换的源字符串
	 * @param oldStr
	 *            需要被替换的老字符串
	 * @param newStr
	 *            替换为的新字符串
	 */
	public static String replace(String source, String oldStr, String newStr) {
		return replace(source, oldStr, newStr, true);
	}

	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, matchCase 为是否设置大小写敏感查找
	 *
	 * @param source
	 *            需要替换的源字符串
	 * @param oldStr
	 *            需要被替换的老字符串
	 * @param newStr
	 *            替换为的新字符串
	 * @param matchCase
	 *            是否需要按照大小写敏感方式查找
	 */
	public static String replace(String source, String oldStr, String newStr, boolean matchCase) {
		if (source == null) {
			return null;
		}
		// 首先检查旧字符串是否存在, 不存在就不进行替换
		if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1) {
			return source;
		}
		int findStartPos = 0;
		int a = 0;
		while (a > -1) {
			int b = 0;
			String str1, str2, str3, str4, strA, strB;
			str1 = source;
			str2 = str1.toLowerCase();
			str3 = oldStr;
			str4 = str3.toLowerCase();
			if (matchCase) {
				strA = str1;
				strB = str3;
			} else {
				strA = str2;
				strB = str4;
			}
			a = strA.indexOf(strB, findStartPos);
			if (a > -1) {
				b = oldStr.length();
				findStartPos = a + b;
				StringBuffer bbuf = new StringBuffer(source);
				source = bbuf.replace(a, a + b, newStr) + "";
				// 新的查找开始点位于替换后的字符串的结尾
				findStartPos = findStartPos + newStr.length() - b;
			}
		}
		return source;
	}

	/**
	 * 清除字符串结尾的空格.
	 *
	 * @param input
	 *            String 输入的字符串
	 * @return 转换结果
	 */
	public static String trimTailSpaces(String input) {
		if (isEmpty(input)) {
			return "";
		}

		String trimedString = input.trim();

		if (trimedString.length() == input.length()) {
			return input;
		}

		return input.substring(0, input.indexOf(trimedString) + trimedString.length());
	}

	/**
	 * Change the null string value to "", if not null, then return it self, use
	 * this to avoid display a null string to "null".
	 *
	 * @param input
	 *            the string to clear
	 * @return the result
	 */
	public static String clearNull(String input) {
		return isEmpty(input) ? "" : input;
	}

	/**
	 * Return the limited length string of the input string (added at:April 10,
	 * 2004).
	 *
	 * @param input
	 *            String
	 * @param maxLength
	 *            int
	 * @return String processed result
	 */
	public static String limitStringLength(String input, int maxLength) {
		if (isEmpty(input))
			return "";

		if (input.length() <= maxLength) {
			return input;
		} else {
			return input.substring(0, maxLength - 3) + "...";
		}

	}

	/**
	 * 将字符串转换为一个 JavaScript 的 alert 调用. eg: htmlAlert("What?"); returns
	 * &lt;SCRIPT language="JavaScript"&gt;alert("What?")&lt;/SCRIPT&gt;
	 *
	 * @param message
	 *            需要显示的信息
	 * @return 转换结果
	 */
	public static String scriptAlert(String message) {
		return "<SCRIPT language=\"JavaScript\">alert(\"" + message + "\");</SCRIPT>";
	}

	/**
	 * 将字符串转换为一个 JavaScript 的 document.location 改变调用. eg: htmlAlert("a.jsp");
	 * returns &lt;SCRIPT
	 * language="JavaScript"&gt;document.location="a.jsp";&lt;/SCRIPT&gt;
	 *
	 * @param url
	 *            需要显示的 URL 字符串
	 * @return 转换结果
	 */
	public static String scriptRedirect(String url) {
		return "<SCRIPT language=\"JavaScript\">document.location=\"" + url + "\";</SCRIPT>";
	}

	/**
	 * 返回脚本语句 &lt;SCRIPT language="JavaScript"&gt;history.back();&lt;/SCRIPT&gt;
	 *
	 * @return 脚本语句
	 */
	public static String scriptHistoryBack() {
		return "<SCRIPT language=\"JavaScript\">history.back();</SCRIPT>";
	}

	/**
	 * 滤除帖子中的危险 HTML 代码, 主要是脚本代码, 滚动字幕代码以及脚本事件处理代码
	 *
	 * @param content
	 *            需要滤除的字符串
	 * @return 过滤的结果
	 */
	public static String replaceHtmlCode(String content) {
		if (isEmpty(content)) {
			return "";
		}
		// 需要滤除的脚本事件关键字
		String[] eventKeywords = { "onmouseover", "onmouseout", "onmousedown", "onmouseup", "onmousemove", "onclick",
				"ondblclick", "onkeypress", "onkeydown", "onkeyup", "ondragstart", "onerrorupdate", "onhelp",
				"onreadystatechange", "onrowenter", "onrowexit", "onselectstart", "onload", "onunload",
				"onbeforeunload", "onblur", "onerror", "onfocus", "onresize", "onscroll", "oncontextmenu" };
		content = replace(content, "<script", "&ltscript", false);
		content = replace(content, "</script", "&lt/script", false);
		content = replace(content, "<marquee", "&ltmarquee", false);
		content = replace(content, "</marquee", "&lt/marquee", false);
		// content = replace(content, "\r\n", "<BR>");
		// 滤除脚本事件代码
		for (int i = 0; i < eventKeywords.length; i++) {
			content = replace(content, eventKeywords[i], "_" + eventKeywords[i], false); // 添加一个"_",
																							// 使事件代码无效
		}
		return content;
	}

	/**
	 * 滤除 HTML 代码 为文本代码.
	 */
	public static String replaceHtmlToText(String input) {
		if (isEmpty(input)) {
			return "";
		}
		return setBr(setTag(input));
	}

	/**
	 * 滤除 HTML 标记. 因为 XML 中转义字符依然有效, 因此把特殊字符过滤成中文的全角字符.
	 *
	 * @author beansoft
	 * @param s
	 *            输入的字串
	 * @return 过滤后的字串
	 */
	public static String setTag(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		char ch;
		for (int i = 0; i < j; i++) {
			ch = s.charAt(i);
			if (ch == '<') {
				stringbuffer.append("&lt");
				// stringbuffer.append("〈");
			} else if (ch == '>') {
				stringbuffer.append("&gt");
				// stringbuffer.append("〉");
			} else if (ch == '&') {
				stringbuffer.append("&amp");
				// stringbuffer.append("〃");
			} else if (ch == '%') {
				stringbuffer.append("%%");
				// stringbuffer.append("※");
			} else {
				stringbuffer.append(ch);
			}
		}

		return stringbuffer.toString();
	}

	/** 滤除 BR 代码 */
	public static String setBr(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++) {

			if (s.charAt(i) == '\n' || s.charAt(i) == '\r') {
				continue;
			}
			stringbuffer.append(s.charAt(i));
		}

		return stringbuffer.toString();
	}

	/** 滤除空格 */
	public static String setNbsp(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++) {
			if (s.charAt(i) == ' ') {
				stringbuffer.append("&nbsp;");
			} else {
				stringbuffer.append(s.charAt(i) + "");
			}
		}
		return stringbuffer.toString();
	}

	/**
	 * 判断字符串是否全是数字字符或者点号.
	 *
	 * @param input
	 *            输入的字符串
	 * @return 判断结果, true 为全数字, false 为还有非数字字符
	 */
	public static boolean isNumeric(String input) {
		if (isEmpty(input)) {
			return false;
		}

		for (int i = 0; i < input.length(); i++) {
			char charAt = input.charAt(i);

			if (!Character.isDigit(charAt) && (charAt != '.')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否全是数字字符
	 *
	 * @param input
	 *            输入的字符串
	 * @return 判断结果, true 为全数字, false 为还有非数字字符
	 */
	public static boolean isNum(String input) {
		if (isEmpty(input)) {
			return false;
		}

		for (int i = 0; i < input.length(); i++) {
			char charAt = input.charAt(i);

			if (!Character.isDigit(charAt)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher match = pattern.matcher(str);
		return match.matches();
	}

	/**
	 * 是否为小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("\\d+\\.\\d+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 转换由表单读取的数据的内码(从 ISO8859 转换到 gb2312).
	 *
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String toChi(String input) {
		try {
			byte[] bytes = input.getBytes("ISO8859-1");
			return new String(bytes, "GBK");
		} catch (Exception ex) {
		}
		return input;
	}

	/**
	 * 转换由表单读取的数据的内码到 ISO(从 GBK 转换到ISO8859-1).
	 *
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String toISO(String input) {
		return changeEncoding(input, "GBK", "ISO8859-1");
	}

	/**
	 * 转换字符串的内码.
	 *
	 * @param input
	 *            输入的字符串
	 * @param sourceEncoding
	 *            源字符集名称
	 * @param targetEncoding
	 *            目标字符集名称
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String changeEncoding(String input, String sourceEncoding, String targetEncoding) {
		if (input == null || input.equals("")) {
			return input;
		}

		try {
			byte[] bytes = input.getBytes(sourceEncoding);
			return new String(bytes, targetEncoding);
		} catch (Exception ex) {
		}
		return input;
	}

	/**
	 * 将单个的 ' 换成 ''; SQL 规则:如果单引号中的字符串包含一个嵌入的引号,可以使用两个单引号表示嵌入的单引号.
	 */

	public static String replaceSql(String input) {
		return replace(input, "'", "''");
	}

	/**
	 * 对给定字符进行 URL 编码
	 */
	public static String encode(String value) {
		if (isEmpty(value)) {
			return "";
		}

		try {
			value = java.net.URLEncoder.encode(value, "GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * 对给定字符进行 URL 解码
	 *
	 * @param value
	 *            解码前的字符串
	 * @return 解码后的字符串
	 */
	public static String decode(String value) {
		if (isEmpty(value)) {
			return "";
		}

		try {
			return java.net.URLDecoder.decode(value, "GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * 获得输入字符串的字节长度(即二进制字节数), 用于发送短信时判断是否超出长度.
	 *
	 * @param input
	 *            输入字符串
	 * @return 字符串的字节长度(不是 Unicode 长度)
	 */
	public static int getBytesLength(String input) {
		if (input == null) {
			return 0;
		}

		int bytesLength = input.getBytes().length;

		// System.out.println("bytes length is:" + bytesLength);

		return bytesLength;
	}

	/**
	 * 检验字符串是否未空, 如果是, 则返回给定的出错信息.
	 *
	 * @param input
	 *            输入的字符串
	 * @param errorMsg
	 *            出错信息
	 * @return 空串返回出错信息
	 */
	public static String isEmpty(String input, String errorMsg) {
		if (isEmpty(input)) {
			return errorMsg;
		}
		return "";
	}

	/**
	 * 得到文件的扩展名.
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the extension portion of the file's name.
	 */
	public static String getExtension(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}

	/**
	 * 得到文件的前缀名.
	 *
	 * @date 2005-10-18
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the prefix portion of the file's name.
	 */
	public static String getPrefix(String fileName) {
		if (fileName != null) {
			fileName = fileName.replace('\\', '/');

			if (fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}

			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(0, i);
			}
		}
		return "";
	}

	/**
	 * 得到文件的短路径, 不保护目录.
	 *
	 * @date 2005-10-18
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the short version of the file's name.
	 */
	public static String getShortFileName(String fileName) {
		if (fileName != null) {
			String oldFileName = new String(fileName);

			fileName = fileName.replace('\\', '/');

			// Handle dir
			if (fileName.endsWith("/")) {
				int idx = fileName.indexOf('/');
				if (idx == -1 || idx == fileName.length() - 1) {
					return oldFileName;
				} else {
					return oldFileName.substring(idx + 1, fileName.length() - 1);
				}

			}
			if (fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}

			return fileName;
		}
		return "";
	}

	/**
	 * Gets the absolute pathname of the class or resource file containing the
	 * specified class or resource name, as prescribed by the current classpath.
	 *
	 * @param resourceName
	 *            Name of the class or resource name.
	 * @return the absolute pathname of the given resource
	 * @throws Exception
	 */
	public static String getPath(String resourceName) throws Exception {

		if (!resourceName.startsWith("/")) {
			resourceName = "/" + resourceName;
		}

		// resourceName = resourceName.replace('.', '/');

		java.net.URL classUrl = new StringHelper().getClass().getResource(resourceName);

		if (classUrl != null) {
			// System.out.println("\nClass '" + className +
			// "' found in \n'" + classUrl.getFile() + "'");
			// System.out.println("\n资源 '" + resourceName +
			// "' 在文件 \n'" + classUrl.getFile() + "' 中找到.");

			return classUrl.getFile();
		}
		// System.out.println("\nClass '" + className +
		// "' not found in \n'" +
		// System.getProperty("java.class.path") + "'");
		// System.out.println("\n资源 '" + resourceName +
		// "' 没有在类路径 \n'" +
		// System.getProperty("java.class.path") + "' 中找到");
		return null;
	}

	/**
	 * 将 TEXT 文本转换为 HTML 代码, 已便于网页正确的显示出来.
	 *
	 * @param input
	 *            输入的文本字符串
	 * @return 转换后的 HTML 代码
	 */
	public static String textToHtml(String input) {
		if (isEmpty(input)) {
			return "";
		}

		input = replace(input, "<", "&#60;");
		input = replace(input, ">", "&#62;");

		input = replace(input, "\n", "<br>\n");
		input = replace(input, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		input = replace(input, "  ", "&nbsp;&nbsp;");

		return input;
	}

	public static String toQuoteMark(String s) {
		s = replaceString(s, "'", "&#39;");
		s = replaceString(s, "\"", "&#34;");
		s = replaceString(s, "\r\n", "\n");
		return s;
	}

	public static String replaceChar(String s, char c, char c1) {
		if (s == null) {
			return "";
		}
		return s.replace(c, c1);
	}

	public static String replaceString(String s, String s1, String s2) {
		if (s == null || s1 == null || s2 == null) {
			return "";
		}
		return s.replaceAll(s1, s2);
	}

	public static String toHtml(String s) {
		s = replaceString(s, "<", "&#60;");
		s = replaceString(s, ">", "&#62;");
		return s;
	}

	public static String toBR(String s) {
		s = replaceString(s, "\n", "<br>\n");
		s = replaceString(s, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		s = replaceString(s, "  ", "&nbsp;&nbsp;");
		return s;
	}

	public static String toSQL(String s) {
		s = replaceString(s, "\r\n", "\n");
		return s;
	}

	public static String replaceEnter(String s) throws NullPointerException {
		return s.replaceAll("\n", "<br>");
	}

	public static String replacebr(String s) throws NullPointerException {
		return s.replaceAll("<br>", "\n");
	}

	public static String replaceQuote(String s) throws NullPointerException {
		return s.replaceAll("'", "''");
	}

	/**
	 * 过滤字符串空格
	 *
	 * @param s
	 * @date:2009-08-18 add by cf
	 * @return
	 */
	public static String filterBlank(String string_blank) {
		StringTokenizer st = new StringTokenizer(string_blank, " ");
		string_blank = "";
		while (st.hasMoreTokens())
			string_blank += st.nextToken();
		return string_blank;
	}

	/**
	 *
	 * 功能说明:将非空字符串的两侧空格去掉，如传入字符串为空时返回"",传入字符串不为空时，返回去除两边空格的字符串
	 *
	 * @param str
	 *            传入的字符串
	 * @return 传入字符串为空时返回"",传入字符串不为空时，返回去除两边空格的字符串
	 */
	public static String filterNullTrim(String str) {

		if (str == null || str.equals("null") || str.equals(null) || str.equals("") || str.equals("&nbsp;"))
			return "";
		else
			return str.trim();
	}

	/**
	 *
	 * 功能说明:将非空字符串的两侧空格去掉，如传入字符串为空时返回0,传入字符串不为空时，返回去除两边空格的字符串
	 *
	 * @param str
	 *            传入的字符串
	 * @return 传入字符串为空时返回"",传入字符串不为空时，返回去除两边空格的字符串
	 */
	public static int filterNullTrim_int(String str) {

		if (str == null || str.equals("null") || str.equals(null) || str.equals("") || str.equals("&nbsp;"))
			return 0;
		else
			return Integer.parseInt(str.trim());
	}

	/**
	 *
	 * 功能说明:将非空字符串的两侧空格去掉，如传入字符串为空时返回0l,传入字符串不为空时，返回去除两边空格的字符串
	 *
	 * @param str
	 *            传入的字符串
	 * @return 传入字符串为空时返回"",传入字符串不为空时，返回去除两边空格的字符串
	 */
	public static Long filterNullTrim_long(String str) {

		if (str == null || str.equals("null") || str.equals(null) || str.equals("") || str.equals("&nbsp;"))
			return 0l;
		else
			return Long.parseLong(str.trim());
	}

	/**
	 * 返回页数
	 *
	 * @param size
	 * @param pageSize
	 * @return
	 */
	public static int getPages(int size, int pageSize) {
		if (pageSize < 1)
			return 0;
		int pages = (size % pageSize == 0) ? (size / pageSize) : ((size / pageSize) + 1);
		return pages;
	}

	/**
	 * 将字符串转换为 int.
	 *
	 * @param str
	 * @param defaultVal
	 *            默认值
	 * @return
	 */
	public static int parseInt(String str, int defaultVal) {
		int rs = defaultVal;
		try {
			rs = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
		return rs;
	}

	public static String stringToJson(String s) {
		if (s == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 格式化数字
	 *
	 * @param m
	 * @param pattern
	 * @return
	 */
	public static String formatDecimal(Double m, String pattern) {
		try {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(m);
		} catch (Exception e) {
			System.err.println(m + "格式化出错了");
			// e.printStackTrace();
		}
		return "0";
	}

	/**
	 *
	 * @param m
	 * @return 1,323.22 #,##0.00#
	 */
	public static String formatDecimal(Double m) {
		String pattern = "#,##0.00#";
		return formatDecimal(m, pattern);
	}

	/**
	 * 字符串不能等于null或空，否则抛出异常
	 *
	 * @param obj
	 *            被判断的字符串
	 * @param objName
	 *            字符串变量的名称
	 */
	public static void notEmpty(String obj, String msg) {
		if (obj == null || obj.trim().equals("")) {
			throw new RuntimeException(msg);
		}
	}

	/**
	 * 对象不能为null，否则抛出异常
	 *
	 * @param obj
	 *            被判断的对象
	 * @param objName
	 *            对象的名字
	 */
	public static void notNull(Object obj, String msg) {
		if (obj == null) {
			throw new RuntimeException(msg);
		}
	}

	/**
	 * 对象不存在
	 *
	 * @param obj
	 */
	public static void notExist(String obj) {
		if (obj == null || obj.trim().equals("")) {
			throw new RuntimeException("操作失败");
		}
	}

	/**
	 * 对象不存在
	 *
	 * @param obj
	 */
	public static void notExist(String[] obj) {
		if (obj == null || obj.length == 0) {
			throw new RuntimeException("操作失败");
		}
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 *
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 *
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 默认构造方法，使用默认密钥
	 *
	 * @throws Exception
	 */
	public StringHelper() throws Exception {
		this(strDefaultKey);
	}

	/**
	 * 判断map里面有没有指定的整数数据，有返回，没有返回-1
	 *
	 * @param string
	 * @param map
	 * @return
	 */
	public static int toIntFromMap(String string, Map<String, Object> map) {

		int ret = -1;
		if (map.containsKey(string)) {
			Object object = map.get(string);
			if (object instanceof Integer) {
				try {
					ret = Integer.parseInt(object.toString());
				} catch (Exception e) {
				}
			}
		}
		return ret;
	}

	/**
	 * 判断map里面有没有指定的字符数据，有返回，没有返回null
	 *
	 * @param string
	 * @param map
	 * @return
	 */
	public static String toStringFromMap(String string, Map<String, Object> map) {

		String ret = null;
		if (map.containsKey(string)) {
			Object object = map.get(string);
			if (object instanceof String) {
				try {
					ret = object.toString();
				} catch (Exception e) {
				}
			}
		}
		return ret;
	}

	/**
	 * 指定密钥构造方法
	 *
	 * @param strKey
	 *            指定的密钥
	 * @throws Exception
	 */
	public StringHelper(String strKey) throws Exception {
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 加密字节数组
	 *
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 *
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 *
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 *
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 *
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	/**
	 *
	 * 字符串非空判断
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static String TransactSQLInjection(String sql) {
		if (sql != null && !"".equals(sql)) {
			// return sql.replaceAll(".*([';]+|(--)+).*", "");
			sql = sql.replace(" ", "");
			sql = sql.replace("--", "");
			sql = sql.replace("'", "");
			sql = sql.replace("{", "");
			sql = sql.replace("}", "");
			sql = sql.replace("[", "");
			sql = sql.replace("]", "");
			sql = sql.replace("(", "");
			sql = sql.replace(")", "");
			sql = sql.replace("=", "");
			sql = sql.replace("+", "");
			// sql = sql.replace("-","");
			sql = sql.replace(";", "");
			// sql = sql.replace("%","");
			sql = sql.replace("?", "");
			sql = sql.replace("&", "");
			sql = sql.replace(":", "");
		}
		return sql;
	}

	/**
	 * 过滤html代码
	 *
	 * @param sb1
	 *            要过滤的字符串
	 * @return 过滤html代码后的字符串
	 */
	public static String filterHTML(String sb1) {
		int num = 0;
		String tt = "";
		if (sb1 != null && !"".equals(sb1)) {
			num = sb1.length();
			StringBuffer sb = new StringBuffer(sb1);
			String[] dd = new String[] { "html", "body", "iframe", "frame", "frameset", "script", "div", "p", "span",
					"a", "img", "strong", "select", "option", "input", "textarea", "table", "tr", "tbody", "td", "h2",
					"h3", "SOHUMPCODE", "hr", "em", "form", "br", "center", "colgroup", "col", "style", "!--", "HTML",
					"BODY", "IFRAME", "FRAME", "FRAMESET", "SCRIPT", "DIV", "P", "SPAN", "A", "IMG", "STRONG", "SELECT",
					"OPTION", "INPUT", "TEXTAREA", "TABLE", "TR", "TBODY", "TD", "H2", "H3", "SOHUMPCODE", "HR", "EM",
					"FORM", "BR", "CENTER", "COLGROUP", "COL", "STYLE", "font", "FONT" };

			int len = dd.length;
			for (int i = 0; i < len; i++) {
				sb = repalceTag(sb, dd[i]);
			}
			tt = sb.toString();
			tt = tt.replace("<SOHUSUBHEAD>.{0,}</SOHUSUBHEAD>", "");
			int index = tt.indexOf("<![CDATA[");
			while (index != -1) {
				if (tt.indexOf("]]>", index) > -1) {
					tt = tt.replace("]]>", "");
				}
				tt = tt.replace("<![CDATA[", "");
				index = tt.indexOf("<![CDATA[");
			}
			tt = tt.replace("\r\n", "");
			tt = tt.replace("\n", "");
			if (tt.length() > num) {
				tt = tt.substring(0, num);
			}
			tt = tt.replace("<", "&lt;");
			tt = tt.replace(">", "&gt;");
			tt = tt.replace("\"", "&quet;");
			tt = tt.replace("'", "\\\\'");
		}
		tt = tt.replaceAll("&lt;br&gt;", "");
		tt = tt.replaceAll("&lt;br/&gt;", "");
		tt = tt.replaceAll("&lt;p/&gt;", "");
		tt = tt.replaceAll("&lt;p&gt;", "");
		tt = tt.replaceAll("&lt;", "");
		tt = tt.replaceAll("&gt;", "");
		return tt;
	}

	/**
	 * 替换字符串
	 *
	 * @param arg
	 *            源字符串
	 * @param tag
	 *            要过滤的标签
	 * @return 目标字符串
	 */
	public static StringBuffer repalceTag(StringBuffer arg, String tag) {
		// <\\s*"+tag+"\\s+([^>]*)\\s*>
		Pattern pattern = Pattern.compile("<" + tag + "[^<]{0,}>|</" + tag + "\\s*>",
				Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(arg);
		boolean bl = matcher.find();
		StringBuffer sb = new StringBuffer();
		while (bl) {
			matcher.appendReplacement(sb, "");
			bl = matcher.find();
		}
		matcher.appendTail(sb);
		return sb;
	}
	
	/**
	 * 得到合法的字符串
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String getLegalStr(String sourceStr) {
		String result = "";
		if (isEmpty(sourceStr)) {
			result = "";
		} else {
			result = filterHTML(TransactSQLInjection(sourceStr.trim()));
		}
		return result;
	}

	public static String md5Hex(String data) {
		if (data == null) {
			throw new IllegalArgumentException("data must not be null");
		}

		byte[] bytes = digest("MD5", data);

		return toHexString(bytes);
	}

	private static String toHexString(byte[] bytes) {
		int l = bytes.length;

		char[] out = new char[l << 1];

		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & bytes[i]) >>> 4];
			out[j++] = DIGITS[0x0F & bytes[i]];
		}

		return new String(out);
	}

	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	private static byte[] digest(String algorithm, String data) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		return digest.digest(data.getBytes());
	}

	/**
	 * 判断map里面有没有指定的浮点数据，有返回，没有返回-1
	 *
	 * @param string
	 * @param map
	 * @return
	 */
	public static double toDoubleFromMap(String string, Map<String, Object> map) {

		double ret = -1;
		if (map.containsKey(string)) {
			Object object = map.get(string);
			if (object instanceof Double) {
				try {
					ret = Double.parseDouble(object.toString());
				} catch (Exception e) {
				}
			}
		}
		return ret;
	}

	public static boolean checkEmail(String email) {
		String result = "";
		if (email == null || email.length() == 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
		Matcher matcher = pattern.matcher(email);
		while (matcher.find()) {
			result = matcher.group();
		}
		if (result != null && result.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static Integer stringToNumber(String str) {
		if (!isEmpty(str) && isNumber(str)) {
			return new Integer(str);
		}
		return -1;
	}

	/**
	 * Solr字符转义处理，否则查询下列字符会报查询错误。 特殊字符串：+ – && || ! ( ) { } [ ] ^ ” ~ * ? : \ 空格
	 * 
	 * @param input
	 * @return
	 */
	public static String transformMetachar(String input) {
		StringBuffer sb = new StringBuffer();
		try {
			String regex = "[+\\-&|!(){}\\[\\]^\"~*?:(\\)\\s]";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			while (matcher.find()) {
				matcher.appendReplacement(sb, "\\\\" + matcher.group());
			}
			matcher.appendTail(sb);
		} catch (Exception e) {
			// TODO
		}
		return input;
		// return sb.toString();
	}
}

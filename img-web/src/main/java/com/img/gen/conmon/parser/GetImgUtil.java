package com.img.gen.conmon.parser;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class GetImgUtil {

	// 循环访问所有节点，输出包含关键字的值节点
	public static void extractKeyWordHtml(String url, String keyword) throws Exception {
		// 生成一个解析器对象，用网页的 url 作为参数
		Parser parser = new Parser(url);
		// 设置网页的编码,这里只是请求了一个 utf-8 编码网页
		parser.setEncoding("utf-8");
		// 迭代所有节点, null 表示不使用 NodeFilter
		NodeList list = parser.parse(null);
		// 从初始的节点列表跌倒所有的节点
		processNodeList(list, keyword);
	}

	private static void processNodeList(NodeList list, String keyword) throws Exception {
		// 迭代开始
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			// 得到该节点的子节点列表
			NodeList childList = node.getChildren();
			// 孩子节点为空，说明是值节点
			if (null == childList) {
				String result = node.toHtml();
				// 得到值节点的值
				// String result = node.toPlainTextString();
				// 若包含关键字
				if (result.indexOf(keyword) != -1) {
					String bigImgId = "";
					String href = node.getParent().getParent().toHtml();
					href = "http://www.doubean.com/face/"
							+ href.substring(href.indexOf("make."), href.lastIndexOf("\"></a>"));

					// 生成一个解析器对象，用网页的 url 作为参数
					Parser parser = new Parser(href);
					// 设置网页的编码,这里只是请求了一个 gb2312 编码网页
					parser.setEncoding("utf-8");
					// 迭代所有节点, null 表示不使用 NodeFilter
					NodeList detailList = parser.parse(null);
					SimpleNodeIterator elements = detailList.elements();

					while (elements.hasMoreNodes()) {
						Node element = elements.nextNode();
						String html = element.toHtml();
						// 如果html包含图片地址，下载图片 大图
						if (html.indexOf("store/face") != -1) {
							int begin = html.indexOf(
									"<img id=\"ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ImageResult\" alt=\"generated picture\" class=\"JIATHIS_IMG_OK\" src=\"")
									+ 5;
							html = "http://www.doubean.com/face/" + html.substring(begin, html.indexOf("?c=", begin));
							String bigImgUrl = html.replace(
									"id=\"ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ImageResult\" alt=\"generated picture\" class=\"JIATHIS_IMG_OK\" src=\"",
									"");
							bigImgId = String.valueOf((int) (Math.random() * 100000) + 1000000);
							downloadImg(bigImgUrl, "F:\\img\\" + bigImgId, bigImgId + "big.jpg");
						}
					}

					String src = "http://www.doubean.com/"
							+ result.substring(result.indexOf("src=\"") + 6, result.lastIndexOf("\""));
					String smailImgName = bigImgId + "smail.jpg";
					downloadImg(src, "F:\\img\\" + bigImgId, smailImgName);
				}
			} // end if
				// 孩子节点不为空，继续迭代该孩子节点
			else
				processNodeList(childList, keyword);
		} // end wile
	}

	public static void downloadImg(String imgUrl, String fileAddr, String imgName) throws Exception {
		System.out.println(imgUrl);
		HttpClient client = new HttpClient();
		HttpMethod get = new GetMethod(imgUrl);
		client.executeMethod(get);
		File storeFile = new File(fileAddr);
		if (!storeFile.exists())
			storeFile.mkdirs();
		storeFile = new File(fileAddr + "\\" + imgName);
		FileOutputStream output = new FileOutputStream(storeFile);
		// 得到网络资源的字节数组,并写入文件
		output.write(get.getResponseBody());
		output.close();
	}

	public static void main(String[] args) throws Exception {
		for (int i = 1; i < 39; i++) {
			String url = "http://www.doubean.com/face/ListWithImage.aspx?pn=" + i;
			extractKeyWordHtml(url, "<img alt=\"");
		}
	}
}

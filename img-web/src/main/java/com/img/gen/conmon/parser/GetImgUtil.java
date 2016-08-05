package com.img.gen.conmon.parser;

import java.io.File;
import java.io.FileOutputStream;

import com.img.gen.conmon.IdHelper;
import com.img.gen.controller.dto.ImgInfoDTO;
import com.img.gen.dao.model.ImgResource;
import com.img.gen.service.ImgResourceService;
import com.img.gen.service.QiniuUploadService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;
import org.springframework.beans.factory.annotation.Autowired;

public class GetImgUtil {

	@Autowired
	private QiniuUploadService qiniuUploadService;

	@Autowired
	private ImgResourceService imgResourceService;

	// 循环访问所有节点，输出包含关键字的值节点
	public void extractKeyWordHtml(String url, String keyword) throws Exception {
		// 生成一个解析器对象，用网页的 url 作为参数
		Parser parser = new Parser(url);
		// 设置网页的编码,这里只是请求了一个 utf-8 编码网页
		parser.setEncoding("utf-8");
		// 迭代所有节点, null 表示不使用 NodeFilter
		NodeList list = parser.parse(null);
		// 从初始的节点列表跌倒所有的节点
		processNodeList(list, keyword);
	}

	private void processNodeList(NodeList list, String keyword) throws Exception {
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
					ImgResource imgResource = new ImgResource();

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
							String urlHtml = "http://www.doubean.com/face/" + html.substring(begin, html.indexOf("?c=", begin));
							String bigImgUrl = urlHtml.replace(
									"id=\"ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ImageResult\" alt=\"generated picture\" class=\"JIATHIS_IMG_OK\" src=\"",
									"");

							begin = html.indexOf("class=\"head-title\">"); //
							String name = html.substring(begin, html.indexOf("</h1>", begin));
							imgResource.setImgName(name);
//							bigImgId = String.valueOf((int) (Math.random() * 100000) + 1000000);
							bigImgId = IdHelper.generateShortUUID();
							ImgInfoDTO imgInfoDTO = downloadImg(bigImgUrl, bigImgId + "big.jpg");
							imgResource.setImgSize(imgInfoDTO.getLength());
							imgResource.setImgUrl(imgInfoDTO.getUrl());
						}
					}

					String src = "http://www.doubean.com/"
							+ result.substring(result.indexOf("src=\"") + 6, result.lastIndexOf("\""));
					String smailImgName = bigImgId + "smail.jpg";
					ImgInfoDTO samllImgInfoDTO = downloadImg(src, smailImgName);
					imgResource.setSmallImgUrl(samllImgInfoDTO.getUrl());
					imgResource.setSamilImgSize(samllImgInfoDTO.getLength());

					imgResourceService.createImgResource(imgResource);
				}
			} // end if
				// 孩子节点不为空，继续迭代该孩子节点
			else
				processNodeList(childList, keyword);
		} // end wile
	}


	/**
	 * 下载图片
	 * @param imgUrl
	 * @param imgName
	 * @throws Exception
     */
	public ImgInfoDTO downloadImg(String imgUrl, String imgName) throws Exception {
		System.out.println(imgUrl);
		HttpClient client = new HttpClient();
		HttpMethod get = new GetMethod(imgUrl);
		client.executeMethod(get);

		byte[] responseBody = get.getResponseBody();

		String url = qiniuUploadService.upload(responseBody, imgName);

		return new ImgInfoDTO(url, responseBody.length);
//		File storeFile = new File(fileAddr);
//		if (!storeFile.exists())
//			storeFile.mkdirs();
//		storeFile = new File(fileAddr + File.pathSeparator + imgName);
//		FileOutputStream output = new FileOutputStream(storeFile);
        //得到网络资源的字节数组,并写入文件  
//        output.write(get.getResponseBody());
//        output.close();
	}

	public static void main(String[] args) throws Exception {
		GetImgUtil imgUtil = new GetImgUtil();
		for (int i = 1; i < 39; i++) {
			String url = "http://www.doubean.com/face/ListWithImage.aspx?pn=" + i;
			imgUtil.extractKeyWordHtml(url, "<img alt=\"");
		}
	}
}

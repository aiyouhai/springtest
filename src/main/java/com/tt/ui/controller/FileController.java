package com.tt.ui.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.ui.pojo.FileDomain;
import com.tt.ui.vo.ResponseObject;

@Controller
@RequestMapping("/file")
public class FileController {

	// byte[] getBytes() 以字节数组的形式返回文件的内容
	// String getContentType() 返回文件的内容类型
	// InputStream getInputStream() 返回一个InputStream，从中读取文件的内容
	// StringgetName()返回请求参数的名称
	// String getOriginalFillename() 返回客户端提交的原始文件名称
	// long getSize()返回文件的大小，单位为字节
	// boolean isEmpty() 判断被上传文件是否为空
	// void transferTo(Filedestination)将上传文件保存到目标目录下

	@RequestMapping("/uploadone")
	@ResponseBody
	public ResponseObject<String> oneFileUpload(@ModelAttribute FileDomain fileDomain, HttpServletRequest request)
			throws SizeLimitExceededException {
		String filename = fileDomain.getMyfile().getOriginalFilename();
		String parent = "G:\\file";
		File file = new File(parent, filename);
		if (!file.exists()) {
			file.mkdirs();
		}

		try {
			fileDomain.getMyfile().transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseObject<String>(200, "上传成功");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/one")
	public String oneFile(HttpServletResponse response, HttpSession session, HttpServletRequest request, Model model) {

		return "onefile";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/many")
	public String manyFile(HttpServletResponse response, HttpSession session, HttpServletRequest request, Model model) {

		return "manyfiles";
	}

	@RequestMapping("/uploadmany")
	@ResponseBody
	public ResponseObject<String> manyFileUpload(@ModelAttribute FileDomain fileDomain, HttpServletRequest request)
			throws SizeLimitExceededException {

		return null;
	}
}

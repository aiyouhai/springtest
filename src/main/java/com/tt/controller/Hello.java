package com.tt.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.tt.ui.pojo.Person;
import com.tt.utils.JWTUtils;
import com.tt.utils.RSAUtils;

import io.jsonwebtoken.Claims;
import sun.misc.BASE64Decoder;

@Controller
@RequestMapping(value = "/hello")
public class Hello extends AbsController {
	@RequestMapping(value = "/1")
	@ResponseBody
	public String helloMvc() {
		HashMap<String, Object> hash = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name1", "lii");
		hashMap.put("name2", null);
		hashMap.put("name3", "");
		hashMap.put("name4", 0);
		hashMap.put("name5", 6);
		ArrayList<Map> arrayList = new ArrayList<Map>();
		arrayList.add(hash);
		arrayList.add(hashMap);
		for (Map map : arrayList) {
			Object object = null;

			try {
				object = hash.get("name2");
			} catch (Exception e) {
				System.out.println("异常信息" + e.toString());
				throw new RuntimeException(e.toString());

			}

			System.out.println(object);
			System.out.println(hashMap.get("name1"));
		}
		return "index";
	}

	@RequestMapping(value = "/2")
	@ResponseBody
	public String hello2(HttpServletRequest request, HttpServletResponse response,
			@RequestBody HashMap<String, Object> map) {
		String header = request.getHeader("token");
		Claims checkToken = JWTUtils.checkToken(header);
		if (checkToken == null) {
			map.put("code", "403");
		} else {

			map.put("token", header);
		}
		System.out.println(header);

		return JSONObject.toJSON(map).toString();
	}

	@RequestMapping(value = "/t")
	// @ResponseBody
	public String helloWldo(RedirectAttributes attr) {
		attr.addAttribute("test", "51gjie");// 跳转地址带上test参数
		attr.addFlashAttribute("u2", "51gjie");// 跳转地址不带上u2参数
		return "redirect:file";
	}

	@RequestMapping(value = "/3")
	@ResponseBody
	public Person hello3() {
		Person person = new Person();
		person.setPage(12);
		person.setPassword("11111");
		person.setPname("李四");
		return person;
	}

	@RequestMapping("/key")

	public String generate(HttpServletRequest request, HttpServletResponse response, Model model) {
		String publicKeyStr = "";
		try {
			Map<String, Object> initKey = RSAUtils.initKey();
			String privateKeyStr = RSAUtils.getPrivateKeyStr(initKey);
			publicKeyStr = RSAUtils.getPublicKeyStr(initKey);
			// 存入session
			HttpSession session = request.getSession();
			session.setAttribute("publicKey", publicKeyStr);
			session.setAttribute("privateKey", privateKeyStr);
			model.addAttribute("publicKeyStr", publicKeyStr);
			String basePath = getBasePath(request);
			model.addAttribute("basePath", basePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("创建公私钥错误");
		}

		return "ras";
	}

	@RequestMapping(value = "/useKey")
	@ResponseBody
	public String login(@RequestParam("uName") String uName, @RequestParam("pwd") String pwd,
			HttpServletRequest request) {
		// 获取 session中的 privateKey
		String privateKey = (String) request.getSession().getAttribute("privateKey");
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] encodedData = decoder.decodeBuffer(pwd);
			byte[] decrypt = RSAUtils.decrypt(encodedData, privateKey);
			String newPwd = new String(decrypt); // 解密后的密码

			Map parseObject = JSONObject.parseObject(newPwd, Map.class);

			System.out.println(parseObject.get("pwd"));
			System.out.println(parseObject.get("uName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/file")
	public String showFile(HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes attr) {
		String basePath = getBasePath(request);
		model.addAttribute("basePath", basePath);

		return "file";
	}

	@ResponseBody
	@PostMapping(value = "/addfile")
	public String addFile(HttpServletRequest request, HttpServletResponse response, AddFile addFile,
			MultipartFile[] file) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file2 = multipartRequest.getFile("filename");
		MultipartFile file3 = multipartRequest.getFile("pic");

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		String realPath = request.getSession().getServletContext().getRealPath("/file");
		System.out.println(realPath);
		for (MultipartFile multipartFile : fileMap.values()) {
			fileName = multipartFile.getOriginalFilename();
			// 获取文件的后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			// 文件上传路径
			String filePath = "G:" + File.separator + "file" + File.separator;
			// 解决中文问题,liunx 下中文路径,图片显示问题
			fileName = UUID.randomUUID() + suffixName;
			// File dest = new File(realPath + fileName);
			File dest = new File(realPath, fileName);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {
				// 上传
				multipartFile.transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			addFile.setlJ(realPath);
			addFile.setPiclJ(realPath);
			System.out.println(addFile.toString());
		}

		// Iterator<String> fileNames = multipartRequest.getFileNames();
		// MultiValueMap<String, MultipartFile> multiFileMap =
		// multipartRequest.getMultiFileMap();

		// 获取文件名

		return "上传文件成功";
	}

}

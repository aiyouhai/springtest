package com.tt.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.ui.pojo.GoodsModel;
import com.tt.ui.pojo.Person;

@Controller
@RequestMapping("/my")
public class ConverterController {
	@RequestMapping("/input")
	/*
	 * 使用@RequestParam
	 * ("goods")接收请求参数，然后调用自定义类型转换器GoodsConverter将字符串值转换为GoodsModel的对象gm
	 */
	public String input() {

		return "input";
	}

	@RequestMapping("/converter")
	@ResponseBody
	/*
	 * 使用@RequestParam
	 * ("goods")接收请求参数，然后调用自定义类型转换器GoodsConverter将字符串值转换为GoodsModel的对象gm
	 */
	public GoodsModel myConverter(GoodsModel gm, Model model) {
		GoodsModel goodsModel = new GoodsModel();
		goodsModel.setGoodsdate(gm.getGoodsdate());
		goodsModel.setGoodsname(gm.getGoodsname());
		goodsModel.setGoodsnumber(gm.getGoodsnumber());
		goodsModel.setGoodsprice(gm.getGoodsprice());
		// model.addAttribute("goods", goodsModel);
		return goodsModel;
	}

	@RequestMapping("/testJson")
	@ResponseBody
	public Person testJson(@RequestBody Person user) {
		// 打印接收的JSON格式数据
		System.out.println("pname=" + user.getPname() + ",password=" + user.getPassword() + ",page" + user.getPage());
		;
		// 返回JSON格式的响应
		return user;
	}

	@RequestMapping("/person")
	public String person() {
		return "person";
	}
}

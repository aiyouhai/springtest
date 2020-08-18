package com.tt.ConverterAndFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.tt.ui.pojo.GoodsModel;

@Component
public class GoodsConverter implements Converter<String, GoodsModel> {

	@Override
	public GoodsModel convert(String source) {
		// 创建一个Goods实例
		GoodsModel goods = new GoodsModel();
		// 以“，”分隔
		String stringvalues[] = source.split(",");
		if (stringvalues != null && stringvalues.length == 3) {
			// 为Goods实例赋值
			goods.setGoodsname(stringvalues[0]);
			goods.setGoodsprice(Double.parseDouble(stringvalues[1]));
			goods.setGoodsnumber(Integer.parseInt(stringvalues[2]));
			return goods;
		} else {
			throw new IllegalArgumentException(String.format("类型转换失败， 需要格式'apple, 10.58,200 ',但格式是[% s ] ", source));
		}
	}

}

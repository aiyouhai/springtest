package com.tt.ui.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsModel {
	private String goodsname;
	private double goodsprice;
	private int goodsnumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date goodsdate;
	// private String goodsdate;

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public double getGoodsprice() {
		return goodsprice;
	}

	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}

	public int getGoodsnumber() {
		return goodsnumber;
	}

	public void setGoodsnumber(int goodsnumber) {
		this.goodsnumber = goodsnumber;
	}

	public Date getGoodsdate() {
		return goodsdate;
	}

	public void setGoodsdate(Date goodsdate) {
		this.goodsdate = goodsdate;
	}

}

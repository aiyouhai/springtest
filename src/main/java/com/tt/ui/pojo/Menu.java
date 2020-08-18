package com.tt.ui.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

import com.tt.utils.AbstractEntity;

@Table(name="menu")
public class Menu extends AbstractEntity{
	public static final String MENU_TYPE = "menu";
	public static final String BUTTON_TYPE = "button";

	private String text;
	private String url;
	private Boolean homePage;
	private Boolean closeable;
	private String css;
	private Menu parentMenu;
	private String uid;
	private String type;
	private int menuNumber;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getHomePage() {
		return homePage;
	}
	public void setHomePage(Boolean homePage) {
		this.homePage = homePage;
	}
	public Boolean getCloseable() {
		return closeable;
	}
	public void setCloseable(Boolean closeable) {
		this.closeable = closeable;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	@Column(name="parent_id")
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMenuNumber() {
		return menuNumber;
	}
	public void setMenuNumber(int menuNumber) {
		this.menuNumber = menuNumber;
	}
	
}

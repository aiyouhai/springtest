package com.tt.controller;

public class AddFile {
	private String tM;
	private String lX;
	private String zY;
	private String lJ;
	private String piclJ;

	public String gettM() {
		return tM;
	}

	public void settM(String tM) {
		this.tM = tM;
	}

	public String getlX() {
		return lX;
	}

	public void setlX(String lX) {
		this.lX = lX;
	}

	public String getzY() {
		return zY;
	}

	public void setzY(String zY) {
		this.zY = zY;
	}

	public String getlJ() {
		return lJ;
	}

	public void setlJ(String lJ) {
		this.lJ = lJ;
	}

	public String getPiclJ() {
		return piclJ;
	}

	public void setPiclJ(String piclJ) {
		this.piclJ = piclJ;
	}

	@Override
	public String toString() {
		return "AddFile [tM=" + tM + ", lX=" + lX + ", zY=" + zY + ", lJ=" + lJ + ", piclJ=" + piclJ + "]";
	}

}

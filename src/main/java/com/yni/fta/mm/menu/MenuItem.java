package com.yni.fta.mm.menu;

public class MenuItem {
	private String sysId;
	private String menuId;
	private String hgrnkMenuId;
	private String menuName;
	private String DirectUrl;
	private String doesMenuDisplay;
	private String menuType;
	private int menuLevelNumber;
	private int menuSortSerial;
	private String menuIfMethod;
	private String selAuth;
	private String regAuth;
	private String updAuth;
	private String delAuth;
	private String excAuth;
	private String fleAuth;
	
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getHgrnkMenuId() {
		return hgrnkMenuId;
	}
	public void setHgrnkMenuId(String hgrnkMenuId) {
		this.hgrnkMenuId = hgrnkMenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getDirectUrl() {
		return DirectUrl;
	}
	public void setDirectUrl(String directUrl) {
		DirectUrl = directUrl;
	}
	public String getDoesMenuDisplay() {
		return doesMenuDisplay;
	}
	public void setDoesMenuDisplay(String doesMenuDisplay) {
		this.doesMenuDisplay = doesMenuDisplay;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public int getMenuLevelNumber() {
		return menuLevelNumber;
	}
	public void setMenuLevelNumber(int menuLevelNumber) {
		this.menuLevelNumber = menuLevelNumber;
	}
	public int getMenuSortSerial() {
		return menuSortSerial;
	}
	public void setMenuSortSerial(int menuSortSerial) {
		this.menuSortSerial = menuSortSerial;
	}
	//
	public String getMenuIfMethod() {
		return menuIfMethod;
	}
	public void setMenuIfMethod(String menuIfMethod) {
		this.menuIfMethod = menuIfMethod;
	}
	
	public String getSelAuth() {
		return selAuth;
	}
	public void setSelAuth(String selAuth) {
		this.selAuth = selAuth;
	}
	public String getRegAuth() {
		return regAuth;
	}
	public void setRegAuth(String regAuth) {
		this.regAuth = regAuth;
	}
	public String getUpdAuth() {
		return updAuth;
	}
	public void setUpdAuth(String updAuth) {
		this.updAuth = updAuth;
	}
	public String getDelAuth() {
		return delAuth;
	}
	public void setDelAuth(String delAuth) {
		this.delAuth = delAuth;
	}
	public String getExcAuth() {
		return excAuth;
	}
	public void setExcAuth(String excAuth) {
		this.excAuth = excAuth;
	}
	public String getFleAuth() {
		return fleAuth;
	}
	public void setFleAuth(String fleAuth) {
		this.fleAuth = fleAuth;
	}
	
	@Override
	public String toString() {
		return "{\"sysId\":\"" + sysId + "\",\"menuId\":\"" + menuId + "\",\"hgrnkMenuId\":\"" + hgrnkMenuId
				+ "\",\"menuName\":\"" + menuName + "\",\"DirectUrl\":\"" + DirectUrl + "\",\"doesMenuDisplay\":\""
				+ doesMenuDisplay + "\",\"menuType\":\"" + menuType + "\",\"menuLevelNumber\":\"" + menuLevelNumber
				+ "\",\"menuSortSerial\":\"" + menuSortSerial + "\",\"menuIfMethod\":\"" + menuIfMethod + "\""
				+ ",\"selAuth\":\"" + selAuth + "\",\"regAuth\":\"" + regAuth + "\""
				+ ",\"updAuth\":\"" + updAuth + "\",\"delAuth\":\"" + delAuth + "\""
				+ ",\"excAuth\":\"" + excAuth + "\",\"fleAuth\":\"" + fleAuth + "\"}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DirectUrl == null) ? 0 : DirectUrl.hashCode());
		result = prime * result + ((doesMenuDisplay == null) ? 0 : doesMenuDisplay.hashCode());
		result = prime * result + ((hgrnkMenuId == null) ? 0 : hgrnkMenuId.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + menuLevelNumber;
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + menuSortSerial;
		result = prime * result + ((menuType == null) ? 0 : menuType.hashCode());
		result = prime * result + ((sysId == null) ? 0 : sysId.hashCode());
		result = prime * result + ((menuIfMethod == null) ? 0 : menuIfMethod.hashCode());
		
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		MenuItem other = (MenuItem) obj;
		
		if (DirectUrl == null) {
			if (other.DirectUrl != null) return false;
		} else if (!DirectUrl.equals(other.DirectUrl)) return false;
		
		if (doesMenuDisplay == null) {
			if (other.doesMenuDisplay != null) return false;
		} else if (!doesMenuDisplay.equals(other.doesMenuDisplay)) return false;
		
		if (hgrnkMenuId == null) {
			if (other.hgrnkMenuId != null) return false;
		} else if (!hgrnkMenuId.equals(other.hgrnkMenuId)) return false;
		
		if (menuId == null) {
			if (other.menuId != null) return false;
		} else if (!menuId.equals(other.menuId)) return false;
		
		if (menuLevelNumber != other.menuLevelNumber) return false;
		
		if (menuName == null) {
			if (other.menuName != null) return false;
		} else if (!menuName.equals(other.menuName)) return false;
		
		if (menuSortSerial != other.menuSortSerial) return false;
		
		if (menuType == null) {
			if (other.menuType != null) return false;
		} else if (!menuType.equals(other.menuType)) return false;
		
		if (sysId == null) {
			if (other.sysId != null) return false;
		} else if (!sysId.equals(other.sysId)) return false;
		
		return true;
	}
	
	
}

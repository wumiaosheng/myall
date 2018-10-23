package com.neo.hapi.entity;

import java.util.List;

import com.neo.common.entity.MyBatisEntity;

public class HapMenu extends MyBatisEntity {

	private long parentid;
	private long systemId;
	private java.lang.String menuname;
	private java.lang.String menuurl;
	private java.lang.String funccode;
	private int menulevel;
	private java.lang.String levelcode;
	private int menuorder;
	private java.lang.String navurl;
	private java.lang.String iconurl;
	
	private List<HapMenu>  childMenu;

	public long getParentid() {
		return parentid;
	}
	
	public void setParentid(long parentid){ 
        this.parentid = parentid;
    }
	public long getSystemId() {
		return systemId;
	}
	
	public void setSystemId(long systemId){ 
        this.systemId = systemId;
    }
	public java.lang.String getMenuname() {
		return menuname;
	}
	
	public void setMenuname(java.lang.String menuname){ 
        this.menuname = menuname;
    }
	public java.lang.String getMenuurl() {
		return menuurl;
	}
	
	public void setMenuurl(java.lang.String menuurl){ 
        this.menuurl = menuurl;
    }
	public java.lang.String getFunccode() {
		return funccode;
	}
	
	public void setFunccode(java.lang.String funccode){ 
        this.funccode = funccode;
    }
	public int getMenulevel() {
		return menulevel;
	}
	
	public void setMenulevel(int menulevel){ 
        this.menulevel = menulevel;
    }
	public java.lang.String getLevelcode() {
		return levelcode;
	}
	
	public void setLevelcode(java.lang.String levelcode){ 
        this.levelcode = levelcode;
    }
	public int getMenuorder() {
		return menuorder;
	}
	
	public void setMenuorder(int menuorder){ 
        this.menuorder = menuorder;
    }
	public java.lang.String getNavurl() {
		return navurl;
	}
	
	public void setNavurl(java.lang.String navurl){ 
        this.navurl = navurl;
    }
	public java.lang.String getIconurl() {
		return iconurl;
	}
	
	public void setIconurl(java.lang.String iconurl){ 
        this.iconurl = iconurl;
    }

	public List<HapMenu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<HapMenu> childMenu) {
		this.childMenu = childMenu;
	}

}
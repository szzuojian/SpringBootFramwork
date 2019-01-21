package com.sword.framework.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class SysRole {
	@Id
	@GeneratedValue(generator = "roleidGenerator")
	@GenericGenerator(name = "roleidGenerator", strategy = "uuid")
	private String id;
	private String name;
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<SysMenu> menus;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public boolean hasMenu(String url) {
		List<SysMenu> menus = getMenus();
		for (SysMenu sysMenu : menus) {
			if (sysMenu.getUrl().equals(url)) {
				return true;
			}
		}
		return false;
	}

}

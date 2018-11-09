package com.sword.framework.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.sword.framework.dao.SysRoleRepositroy;
import com.sword.framework.entity.SysMenu;
import com.sword.framework.entity.SysRole;

@Service
public class SowrdInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private SysRoleRepositroy sysRoleRepository;

	private HashMap<String, Collection<ConfigAttribute>> map = null;

	/**
	 * 加载权限表中所有权限
	 */
	public void loadResourceDefine() {
		map = new HashMap<>();

		// 临时变量
		Collection<ConfigAttribute> array;
		ConfigAttribute cfg;

		// 查询所有角色资源，建立缓存
		// FIXME:这里可以用redis缓存，提高加载速度
		List<SysRole> roles = sysRoleRepository.findAll();
		for (SysRole role : roles) {
			array = new ArrayList<>();
			// 此处添加菜单对应的角色名称到ConfigAttribute的集合中去。
			// 此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
			List<SysMenu> menuLs = role.getMenus();
			// 循环菜单，构建缓存
			for (SysMenu menu : menuLs) {
				// 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
				cfg = new SecurityConfig(role.getName());
				// 若缓存中还没有URL，则新增
				if (map.get(menu.getUrl()) == null) {
					array.add(cfg);
					map.put(menu.getUrl(), array);
				} else {
					// 若已经存在，则插入
					map.get(menu.getUrl()).add(cfg);
				}
			}
		}

	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map == null)
			loadResourceDefine();
		// object 中包含用户请求的request 信息
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
		String resUrl;
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			resUrl = iter.next();
			matcher = new AntPathRequestMatcher(resUrl);
			if (matcher.matches(request)) {
				return map.get(resUrl);
			}
		}

		// 如果为空资源，则返回一个空角色，避免所有资源都不要权限了
		Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
		returnCollection.add(new SecurityConfig("ROLE_NULL"));
		return returnCollection;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}

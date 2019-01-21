package com.sword.framework.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sword.framework.service.CustomUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Autowired
	private AccessDecisionManager urlAccessDecisionManager;

	@Autowired
	private AuthenticationEntryPoint swordAuthenticationEntryPoint;

	@Value("${webSecurityConfig.excludeCheckUrlPattern}")
	private String excludeCheckUrlPattern;

	@Bean
	protected UserDetailsService customUserService() {
		return new CustomUserService();
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				// 设置UserDetailsService
				.userDetailsService(customUserService())
				// 使用BCrypt进行密码的hash
				.passwordEncoder(passwordEncoder());
	}

	// 装载BCrypt密码编码器
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		if (StringUtils.isNotBlank(excludeCheckUrlPattern)) {
			String[] excludeMapping = excludeCheckUrlPattern.split(",");
			for (String pattern : excludeMapping) {
				web.ignoring().antMatchers(pattern.trim());
			}
		}
	}

	/**
	 * 
	 */
	@Autowired
	private JwtAuthenticationTokenFilter filter;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// 由于使用的是JWT，我们这里不需要csrf
				.cors().and().csrf().disable()
				// 基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// 在这里添加jwt的filter，注意顺序，否则会造成URL过滤器先执行
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).authorizeRequests()
				// 验证URL权限
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O interceptor) {
						interceptor.setSecurityMetadataSource(securityMetadataSource);
						interceptor.setAccessDecisionManager(urlAccessDecisionManager);
						return interceptor;
					}

				}).and().exceptionHandling().authenticationEntryPoint(swordAuthenticationEntryPoint).and()
				.authorizeRequests()
				// 除上面外的所有请求全部需要鉴权认证
				.anyRequest().authenticated();

		// 菜单权限控制
		// httpSecurity.addFilterBefore(urlFilterSecurityInterceptorBean(),FilterSecurityInterceptor.class);

		// 禁用缓存
		httpSecurity.headers().cacheControl();
	}
}

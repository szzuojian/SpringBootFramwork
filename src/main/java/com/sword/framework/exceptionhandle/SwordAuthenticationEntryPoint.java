package com.sword.framework.exceptionhandle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sword.framework.constant.Constants;
import com.sword.framework.dto.ReturnInfo;

@Service
public class SwordAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String errorMsg = ExceptionUtils.getMessage(authException);

		ReturnInfo ret = new ReturnInfo();
		ret.setSuccess(Constants.FAIL);
		ret.setException(errorMsg);
		ret.setMessage(authException.getMessage());

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(ret));
	}

}

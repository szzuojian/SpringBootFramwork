package com.sword.framework.exceptionhandle;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sword.framework.constant.Constants;
import com.sword.framework.dto.ReturnInfo;

/**
 * mvc异常处理器
 * 
 * @author jelly
 *
 */
@Service
public class MvcExceptionResolver implements HandlerExceptionResolver {
	private Logger logger = Logger.getLogger(MvcExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String errorMsg = ExceptionUtils.getMessage(ex);
			logger.error(ex);

			ReturnInfo ret = new ReturnInfo();
			ret.setSuccess(Constants.FAIL);
			ret.setException(errorMsg);
			ret.setMessage(ex.getMessage());

			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(ret));
			return new ModelAndView();

		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return new ModelAndView();

	}

}

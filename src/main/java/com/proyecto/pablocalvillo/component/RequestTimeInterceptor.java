package com.proyecto.pablocalvillo.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.proyecto.pablocalvillo.controller.ParticipationController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor implements HandlerInterceptor {
	
	private static final Log LOGGER = LogFactory.getLog(ParticipationController.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		LOGGER.info("--- REQUEST URL: '" + request.getRequestURI().toString() + 
				"' --- TOTAL TIME: '" + (System.currentTimeMillis() - startTime) + "' ms");
		
	}
	
	

}

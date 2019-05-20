package com.petstore.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	final static Logger logger = LoggerFactory.getLogger(CorsFilter.class);
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
//		logger.debug("ENTRY");
		final HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Pragma, Cache-Control, Expires");
		response.setHeader("Access-Control-Max-Age", "3600");

		HttpServletRequest request = ((HttpServletRequest)req);
		logger.debug("==================== CORS Filter Starts ====================");
		logger.debug("Request URL: " +request.getRequestURL());
		logger.debug("Session ID: " + (request.getSession()!= null ? request.getSession().getId() : "Session is null"));
		logger.debug("Method: " +request.getMethod());

		logger.debug("==================== Request Headers ====================");
		Enumeration<?> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            logger.debug(key +": " +value);

        }
        logger.debug("==================== Request Parameters ====================");
        request.getParameterMap().forEach((key,value)->{logger.debug(key +": " +request.getParameter(key) + " value: "+ value );});

        logger.debug("==================== CORS Filter Ends ====================");

		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}
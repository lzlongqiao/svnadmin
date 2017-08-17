package org.svnadmin.util.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.svnadmin.common.util.PrintUtils;

/**
 * @ClassName: ParameterFilter
 * @Description: 特殊字符拦截过滤器
 * @author 
 * @date 2015年7月7日 下午8:24:59
 * @version V1.0
 */
@WebFilter(filterName="ParameterFilter",urlPatterns="/*")
public class ParameterFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(!request.getRequestURI().contains(".")){
			PrintUtils.print(request);
			if(request.getRequestURI().endsWith("/")){
				response.sendRedirect("login");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
}


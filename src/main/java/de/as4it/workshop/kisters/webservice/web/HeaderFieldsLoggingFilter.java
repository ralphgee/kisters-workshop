package de.as4it.workshop.kisters.webservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

@WebFilter(urlPatterns = "/images")
public class HeaderFieldsLoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // default implementation ignored
    }

    private final Logger log = LoggerFactory.getLogger(HeaderFieldsLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Enumeration<String> headerNames = ((HttpServletRequest) servletRequest).getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            log.info("Name: {} with Value {}",headerName,((HttpServletRequest) servletRequest).getHeader(headerName));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // default implementation ignored
    }
}

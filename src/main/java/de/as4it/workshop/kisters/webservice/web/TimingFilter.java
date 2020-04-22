package de.as4it.workshop.kisters.webservice.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter//(urlPatterns = "/images")
public class TimingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // default implementation ignored
    }

    private final Logger log = LoggerFactory.getLogger(TimingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Incoming Request: {} {}", ((HttpServletRequest) servletRequest).getMethod(), ((HttpServletRequest) servletRequest).getRequestURL());
        StopWatch watch = new StopWatch("Timing");
        String id = UUID.randomUUID().toString();
        watch.start(id);
        filterChain.doFilter(servletRequest, servletResponse);
        watch.stop();
        log.info("Outgoing Response for {} {}: {} in {} seconds", ((HttpServletRequest) servletRequest).getMethod(), ((HttpServletRequest) servletRequest).getRequestURL(), ((HttpServletResponse) servletResponse).getStatus(), watch.getLastTaskInfo().getTimeSeconds());
    }

    @Override
    public void destroy() {
        // default implementation ignored
    }
}

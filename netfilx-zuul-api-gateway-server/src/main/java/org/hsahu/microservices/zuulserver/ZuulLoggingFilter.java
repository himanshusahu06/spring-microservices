package org.hsahu.microservices.zuulserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul filtering that does only logging, other filters can be related to
 * rate limiting, logging, security etc.
 */
@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    /**
     * to classify a filter by type. Standard types in Zuul are
     * "pre" for pre-routing filtering (before executing the request),
     * "post" for post-routing filters (after executing the request),
     * "error" for error handling (when request execution hs thrown any error).
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * if multiple ZuulFilters are added, filters will be applied based or ordering
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @throws ZuulException if an error occurs during execution.
     */
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        // just log the request
        log.info("request -> {}, request URI -> {}", request, request.getRequestURI());
        return null;
    }
}

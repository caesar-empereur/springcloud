package com;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yang on 2018/1/9.
 */
public class ZuulAccessFilter extends ZuulFilter {
    
    private final Log log = LogFactory.getLog(this.getClass());
    
    @Override
    public String filterType() {
        return "pre";
    }
    
    @Override
    public int filterOrder() {
        return 0;
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info(request.getRequestURI());
        
        Object token = request.getParameter("token");
        if (token == null) {
            log.info("token null");
            requestContext.setSendZuulResponse(false);  //设置不要zuul对它进行路由转发
            requestContext.setResponseStatusCode(401);
            return null;
        }
        return token;
    }
}

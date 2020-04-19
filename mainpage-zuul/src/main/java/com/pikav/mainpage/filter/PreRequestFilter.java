package com.pikav.mainpage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 在过滤器中存储客户端发起请求的时间戳
 *
 * */
@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter {

    // 设置过滤器的类型： 请求之前
    @Override
    public String filterType() {

        return FilterConstants.PRE_TYPE;
    }

    // 定义过滤器的执行顺序
    @Override
    public int filterOrder() {

        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    // 是否启用当前过滤器
    @Override
    public boolean shouldFilter() {

        return true;
    }

    // 过滤器执行
    @Override
    public Object run() throws ZuulException {

        // 用于在过滤器之间传递消息, 键值对的形式，实质上就是一个 currentHashMap
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());

        return null;
    }
}

package io.github.dathin.boot.dathinstarterclient.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthorizationPropagationInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest webRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
            propagateHeaderIfNotExplicit(requestTemplate, HttpHeaders.AUTHORIZATION, webRequest.getHeader(HttpHeaders.AUTHORIZATION));
        }
    }

    private void propagateHeaderIfNotExplicit(RequestTemplate requestTemplate, String key, String value) {
        if(!requestTemplate.headers().containsKey(key)) {
            requestTemplate.header(key, value);
        }
    }

}

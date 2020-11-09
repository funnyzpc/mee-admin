package com.mee.core.annotion;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @auther funnyzpc
 * @description 重写FormAuthenticationFilter防止重定向错误
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final String context_path="/mee";

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token,Subject subject,ServletRequest request, ServletResponse response)throws Exception {
        this.redirectToSavedRequest(request, response,getSuccessUrl(),context_path);
        return false;
    }

    private static void redirectToSavedRequest(ServletRequest request, ServletResponse response, String fallbackUrl,String context_path)throws IOException {
        String successUrl = null;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
            successUrl = savedRequest.getRequestUrl();
        }
        if (successUrl == null || !successUrl.equalsIgnoreCase(context_path)) {
            successUrl = fallbackUrl;
        }
        if (successUrl == null) {
            throw new IllegalStateException("Success not Config");
        }
        WebUtils.issueRedirect(request, response, successUrl, null, false);
    }

}
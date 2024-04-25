package com.Cat.sCorner.Cat.sCorner.config;

import com.Cat.sCorner.Cat.sCorner.entity.UserPrincipal;
import com.Cat.sCorner.Cat.sCorner.exception.BizExceptionKit;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecContext {
    public static Integer getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal userPrincipal) {
            return userPrincipal.getId();
        }
        BizExceptionKit.of("get user id error").throwIt();
        return null;
    }
}

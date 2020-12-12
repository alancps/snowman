package com.snowman.touristspot.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class JWTUtils {
	public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}

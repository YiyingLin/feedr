package com.feedr.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieService {
    public static Optional<String> getUserTypeCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("userType"))
                .map(Cookie::getValue)
                .findFirst();
    }

    public static Optional<String> getUsernameCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("username"))
                .findFirst()
                .map(Cookie::getValue);
    }
}

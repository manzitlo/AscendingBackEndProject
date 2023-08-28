package org.ascending.project.filter;

import io.jsonwebtoken.Claims;
import org.ascending.project.model.User;
import org.ascending.project.service.JWTService;
import org.ascending.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final Set<String> ALLOWED_PATHS = new HashSet<>(Arrays.asList("", "/login", "logger", "register"));

    private static final Set<String> IGNORED_PATH = new HashSet<>(Arrays.asList("/auth", "/signup"));
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Start to do authorization");
        HttpServletRequest req = (HttpServletRequest) servletRequest;


        // If it's an OPTIONS request (CORS preflight), just continue in the chain.
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        logger.info("Start to do authorization");


        int statusCode = authorization(req);
        if (statusCode == HttpServletResponse.SC_ACCEPTED){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendError(statusCode);
        }
    }
    private int authorization(HttpServletRequest req) {

        String uri = req.getRequestURI();
        if (IGNORED_PATH.contains(uri)){
            return HttpServletResponse.SC_ACCEPTED;
        }

        String token = req.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (token == null || token.isEmpty()) {
            logger.warn("Missing Authorization header");
            return HttpServletResponse.SC_UNAUTHORIZED;
        }

        Claims claims;

        try {
            claims = jwtService.decryptToken(token);
        } catch (Exception e) {
            logger.error("Error decrypting token", e);
            return HttpServletResponse.SC_UNAUTHORIZED;
        }

        logger.info("After parsing JWT token, claims.getId()={}", claims.getId());
        if (claims.getId() == null) {
            logger.warn("No ID in token claims");
            return HttpServletResponse.SC_UNAUTHORIZED;
        }

        User u = userService.getUserById(Long.valueOf(claims.getId()));
        if (u == null) {
            logger.warn("No user found with ID {}", claims.getId());
            return HttpServletResponse.SC_UNAUTHORIZED;
        }

        String allowedResources = getAllowResourcesBasedOnVerb(req.getMethod(), claims);
        for (String s : allowedResources.split(",")) {
            String url_trim = uri.trim().toLowerCase();
            String s_trim = s.trim().toLowerCase();

            logger.info("url - {}; s - {}; {} - {}", url_trim, "\"" + s_trim + "\"", url_trim.startsWith(s_trim), !s_trim.equals(""));

            if (!s_trim.equals("") && url_trim.startsWith(s_trim)) {
                return HttpServletResponse.SC_ACCEPTED;
            }
        }

        logger.warn("User {} not authorized for resource {}", u.getId(), uri);
        return HttpServletResponse.SC_FORBIDDEN;
    }

    private String getAllowResourcesBasedOnVerb(String verb, Claims claims) {
        switch (verb) {
            case "GET":
                return (String) claims.get("allowedResources");
            case "POST":
                return (String) claims.get("allowedCreateResources");
            case "PUT":
            case "PATCH":
                return (String) claims.get("allowedUpdateResources");
            case "DELETE":
                return (String) claims.get("allowedDeleteResources");
            default:
                return "";
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

package edu.school21.springboot42.filters;

import edu.school21.springboot42.models.Role;
import edu.school21.springboot42.models.User;
import edu.school21.springboot42.models.UserDetailsImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFilter extends GenericFilterBean {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        String homePage;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) { // user logged in

            User user = ((UserDetailsImpl) auth.getPrincipal()).getUser();
            if (user.getRole().equals(Role.ADMIN))
                homePage = "/admin/panel";
            else
                homePage = "/films";

            if (requestURI.equals("/"))
                resp.sendRedirect(homePage);
            else if ((requestURI.startsWith("/admin") || requestURI.startsWith("/profile")) && homePage.startsWith("/films"))
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            else if (requestURI.startsWith("/signIn") || requestURI.startsWith("/signUp"))
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            else
                chain.doFilter(request, response);

        } else
            chain.doFilter(request, response);
    }
}
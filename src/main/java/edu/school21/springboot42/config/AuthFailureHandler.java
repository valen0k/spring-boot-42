package edu.school21.springboot42.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler
        implements AuthenticationFailureHandler {

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest req,
            HttpServletResponse resp,
            AuthenticationException e)
            throws IOException {

        String error = e.getMessage();
        if (error.equals("Bad credentials"))
            strategy.sendRedirect(req, resp, "/signIn?error");
        else if (error.equals("User account is locked"))
            strategy.sendRedirect(req, resp, "/signIn?not_confirmed");
        else
            strategy.sendRedirect(req, resp, "/signIn");
    }
}

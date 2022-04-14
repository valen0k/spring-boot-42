package edu.school21.springboot42.config;

import edu.school21.springboot42.models.LogInfo;
import edu.school21.springboot42.models.User;
import edu.school21.springboot42.models.UserDetailsImpl;
import edu.school21.springboot42.services.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    LogInfoService logInfoService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        logInfoService.saveLogInfo(new LogInfo(user, request.getRemoteAddr()));
        redirectStrategy.sendRedirect(request, response, "/films");
    }
}

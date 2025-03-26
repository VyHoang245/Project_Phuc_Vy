package com.project.vpweb.failueHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String errorMessage = "Invalid username or password.";
        if (exception instanceof BadCredentialsException) {
            errorMessage = "Incorrect password. Please try again.";
        }
        if (exception instanceof LockedException) {
            errorMessage = "Your account is locked. Contact support.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Your account is disabled. Please contact admin.";
        }

        response.sendRedirect("/login?error=" + errorMessage);
    }
}


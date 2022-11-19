package com.codegym.config.sercurity;

import com.codegym.comon.Security_Email;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Configuration
public class SmtpAuthenticator extends Authenticator {
    public SmtpAuthenticator() {
        super();
    }

    @Bean
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String username = Security_Email.MY_EMAIL;
        String password = Security_Email.MY_PASSWORD;
        if ((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0)) {
            return new PasswordAuthentication(username, password);
        }
        return null;
    }
}


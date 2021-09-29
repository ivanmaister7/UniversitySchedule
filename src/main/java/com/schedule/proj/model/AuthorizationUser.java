package com.schedule.proj.model;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationUser {
    private String login;
    private byte[] password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
}

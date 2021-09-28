package com.tablefi.model;

public class AuthorizationUser {
    private String login;
    private byte[] password;

    public AuthorizationUser(String login, byte[] password) {
        this.login = login;
        this.password = password;
    }

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

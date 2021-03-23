package io.github.wojtekmarcin.memobox.model;

public class UserWriteModel {
    private String login;
    private String password;

    public UserWriteModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

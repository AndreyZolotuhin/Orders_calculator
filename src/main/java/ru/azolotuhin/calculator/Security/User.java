package ru.azolotuhin.calculator.Security;

import java.util.List;
import java.util.Objects;

public class User {

    private String name;
    private String password;

    List<AuthorizationConstants.Rule> rulesList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AuthorizationConstants.Rule> getRulesList() {
        return rulesList;
    }

    public void setRulesList(List<AuthorizationConstants.Rule> rulesList) {
        this.rulesList = rulesList;
    }

    public User(String name, String password, List<AuthorizationConstants.Rule> rulesList) {
        this.name = name;
        this.password = password;
        this.rulesList = rulesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}

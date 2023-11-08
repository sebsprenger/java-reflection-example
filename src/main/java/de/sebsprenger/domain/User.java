package de.sebsprenger.domain;

@MyAnnotation
public class User {

    String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPasswordHash() {
        return String.valueOf(password.hashCode());
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", password=" + password +
                '}';
    }

}

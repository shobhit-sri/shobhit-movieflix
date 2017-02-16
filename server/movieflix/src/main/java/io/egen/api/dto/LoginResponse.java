package io.egen.api.dto;

public class LoginResponse {
    public String token;
    public String role;

    public LoginResponse(final String token, final String role) {
        this.token = token;
        this.role = role;
    }
}
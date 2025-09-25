package com.example.Login.dto;

import java.util.List;

public record UserInfoResponse(String username, String email, List<String> roles) {
}

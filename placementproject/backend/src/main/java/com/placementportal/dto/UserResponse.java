package com.placementportal.dto;

import com.placementportal.models.User;

public record UserResponse(Long id, String email, User.Role role, String displayName) {
}

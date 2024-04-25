package com.Cat.sCorner.Cat.sCorner.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
}

package com.back.backaio.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private Long usuarioId;
    private String token;

    public LoginResponse(Long usuarioId, String token) {
        this.usuarioId = usuarioId;
        this.token = token;
    }
}

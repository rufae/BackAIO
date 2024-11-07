package com.back.backaio.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
public class UsuarioDTO {

    private Long usuarioId;
    private String username;
    private Date fechaRegistro;

    public UsuarioDTO(Long usuarioId, String username, Date fechaRegistro) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.fechaRegistro = fechaRegistro;
    }


}

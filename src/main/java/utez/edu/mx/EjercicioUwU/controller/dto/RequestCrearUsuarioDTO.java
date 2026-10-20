package utez.edu.mx.EjercicioUwU.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCrearUsuarioDTO {

    private Long id;

    // datos de persona
    @NotBlank(message = "el nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "el apellido es obligatorio")
    private String primerApellido;

    private String segundoApellido;

    private Boolean activo;
}




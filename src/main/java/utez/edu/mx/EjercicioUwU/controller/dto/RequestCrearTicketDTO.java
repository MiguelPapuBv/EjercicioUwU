package utez.edu.mx.EjercicioUwU.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.EjercicioUwU.model.ticket.EstadoTicket;
import utez.edu.mx.EjercicioUwU.model.ticket.PrioridadTicket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCrearTicketDTO {

    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private EstadoTicket estado;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;
}
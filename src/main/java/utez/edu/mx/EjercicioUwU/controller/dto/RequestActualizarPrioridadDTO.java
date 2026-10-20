package utez.edu.mx.EjercicioUwU.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.EjercicioUwU.model.ticket.PrioridadTicket;

@Getter
@Setter
public class RequestActualizarPrioridadDTO {
    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;
}
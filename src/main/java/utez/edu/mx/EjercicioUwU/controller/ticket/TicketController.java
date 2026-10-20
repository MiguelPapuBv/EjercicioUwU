package utez.edu.mx.EjercicioUwU.controller.ticket;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.EjercicioUwU.controller.dto.RequestActualizarPrioridadDTO;
import utez.edu.mx.EjercicioUwU.controller.dto.RequestCrearTicketDTO;
import utez.edu.mx.EjercicioUwU.model.ticket.Ticket;
import utez.edu.mx.EjercicioUwU.service.ticket.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> crearTicket(@Valid @RequestBody RequestCrearTicketDTO dto) {
        try {
            Ticket ticketCreado = ticketService.crearTicket(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketCreado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}//estado")
    public ResponseEntity<?> actualizarEstadoTicket(@PathVariable Long id) {
        try {
            Ticket ticketActualizado = ticketService.actualizarEstado(id);
            return ResponseEntity.ok(ticketActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}//prioridad")
    public ResponseEntity<?> reasignarPrioridad(
            @PathVariable Long id,
            @Valid @RequestBody RequestActualizarPrioridadDTO dto) {
        try {
            Ticket ticketActualizado = ticketService.reasignarPrioridad(id, dto.getPrioridad());
            return ResponseEntity.ok(ticketActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerTicketsPorUsuario(@PathVariable Long idUsuario) {
        try {
            List<Ticket> tickets = ticketService.obtenerTicketsPorUsuario(idUsuario);
            return ResponseEntity.ok(tickets);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package utez.edu.mx.EjercicioUwU.service.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.EjercicioUwU.controller.dto.RequestCrearTicketDTO;
import utez.edu.mx.EjercicioUwU.model.ticket.EstadoTicket;
import utez.edu.mx.EjercicioUwU.model.ticket.PrioridadTicket;
import utez.edu.mx.EjercicioUwU.model.ticket.Ticket;
import utez.edu.mx.EjercicioUwU.model.usuario.Usuario;
import utez.edu.mx.EjercicioUwU.repository.ticket.TicketRepository;
import utez.edu.mx.EjercicioUwU.repository.usuario.UsuarioRepository;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Ticket crearTicket(RequestCrearTicketDTO dto) {
        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new IllegalStateException("El usuario no está activo, no puede crear tickets");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion().trim());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket actualizarEstado(Long idTicket) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + idTicket));

        EstadoTicket estadoActual = ticket.getEstado();

        if (estadoActual == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (estadoActual == EstadoTicket.EN_PROCESO) {
            ticket.setEstado(EstadoTicket.CERRADO);
        } else if (estadoActual == EstadoTicket.CERRADO) {
            throw new IllegalStateException("El ticket ya está CERRADO y no se puede modificar su estado");
        }

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket reasignarPrioridad(Long idTicket, PrioridadTicket nuevaPrioridad) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + idTicket));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new IllegalStateException("No se puede reasignar la prioridad de un ticket CERRADO");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public List<Ticket> obtenerTicketsPorUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + idUsuario);
        }
        return ticketRepository.findByUsuarioId(idUsuario);
    }
}
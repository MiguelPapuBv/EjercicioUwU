package utez.edu.mx.EjercicioUwU.repository.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.EjercicioUwU.model.ticket.EstadoTicket;
import utez.edu.mx.EjercicioUwU.model.ticket.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUsuarioId(Long usuarioId);

    List<Ticket> findByEstado(EstadoTicket estado);
}
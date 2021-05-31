package soto.api.eventos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import soto.api.eventos.models.Convidado;
import soto.api.eventos.models.Evento;
														//tipo de Id
public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

	List<Convidado> findByEvento(Evento evento);
}

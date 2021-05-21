package soto.api.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import soto.api.eventos.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	

}

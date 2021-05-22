package soto.api.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import soto.api.eventos.models.Evento;
import soto.api.eventos.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {

	@Autowired
	private EventoRepository er;

	@GetMapping("/form")
	public String form() {
		return "eventos/formEvento";
	}

	@PostMapping
	public String adicionar(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		return "eventos/evento-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> op = er.findById(id);
		if (op.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}

		md.setViewName("eventos/detalhes");
		Evento evento = op.get();
		md.addObject("evento", evento);

		return md;
	}

}

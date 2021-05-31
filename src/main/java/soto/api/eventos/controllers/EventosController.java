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

import soto.api.eventos.models.Convidado;
import soto.api.eventos.models.Evento;
import soto.api.eventos.repositories.ConvidadoRepository;
import soto.api.eventos.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {

	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;

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
		
		List<Convidado> convidados = cr.findByEvento(evento);
		md.addObject("convidados", convidados);

		return md;
	}
	
	@PostMapping("/{idEvento}")
	public String salvarConvidado(@PathVariable Long idEvento, Convidado convidado) {
		
		System.out.println("Id do evento: " + idEvento);
		System.out.println(convidado);
		
		Optional<Evento> op =  er.findById(idEvento);
		if (op.isEmpty()) {
			return "redirect:/eventos";
		}
		
		Evento evento = op.get();
		convidado.setEvento(evento);
		
		cr.save(convidado);
		
		return "redirect:/eventos/{idEvento}";

	}
	
	@GetMapping("/{id}/remover")
	public String apagarEvento(@PathVariable Long id) {
		
		Optional<Evento> opt = er.findById(id);
		
		if (!opt.isEmpty()) {
			//apagar
			//exemplo 
			Evento evento = opt.get();
			
		List<Convidado> convidados = cr.findByEvento(evento);
		
			cr.deleteAll(convidados);
			er.delete(evento);
			
			
			//er.delete(opt.get()); mesma Solucao
		}
		
		return "redirect:/eventos";
	}
	
	@GetMapping("/{id}/removerConvidado") 
	public String apagarConvidado(@PathVariable Long id) {
		
		Optional<Convidado> opt = cr.findById(id);
		
		if (!opt.isEmpty()) {
			//apagar
			//exemplo 
			Convidado convidado = opt.get();
			
		//List<Convidado> convidados = cr.findByEvento(evento);
		 
			//cr.deleteAll(convidados);
			cr.delete(convidado);
			
			
			//er.delete(opt.get()); mesma Solucao
		}
		
		return "redirect:/detalhes";
	}
	

}

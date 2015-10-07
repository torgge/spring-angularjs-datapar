package py.com.datapar.enquete.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.com.datapar.enquete.model.Enquete;
import py.com.datapar.enquete.model.EnqueteParticipante;
import py.com.datapar.enquete.model.EnquetePergunta;
import py.com.datapar.enquete.model.Participante;
import py.com.datapar.enquete.model.Pergunta;
import py.com.datapar.enquete.model.PerguntaOpcao;
import py.com.datapar.enquete.model.RespostaOpcao;
import py.com.datapar.enquete.model.TipoPergunta;
import py.com.datapar.enquete.model.TipoResultado;
import py.com.datapar.enquete.model.json.EnqueteJson;
import py.com.datapar.enquete.repository.EnqueteParticipanteRepository;
import py.com.datapar.enquete.repository.EnquetePerguntaRepository;
import py.com.datapar.enquete.repository.EnqueteRepository;
import py.com.datapar.enquete.repository.ParticipanteRepository;
import py.com.datapar.enquete.repository.PerguntaOpcaoRepository;
import py.com.datapar.enquete.repository.PerguntaRepository;
import py.com.datapar.enquete.repository.RespostaOpcaoRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class EnqueteResource {

	@Autowired
	private ParticipanteRepository participante;

	@Autowired
	private EnqueteParticipanteRepository enqueteParticipante;

	@Autowired
	private PerguntaRepository pergunta;

	@Autowired
	private EnquetePerguntaRepository enquetePergunta;

	@Autowired
	private EnqueteRepository repository;

	@Autowired
	private PerguntaOpcaoRepository perguntaOpcao;

	@Autowired
	private RespostaOpcaoRepository respostaOpcaoRepository;

	@RequestMapping("/enquete/ativa")
	public List<EnqueteJson> listaEnquetesAtivas() {
		// update tab_enquete set ativa = b'1';
		List<EnqueteJson> listaEnquetes = new ArrayList<>();

		List<EnquetePergunta> enquetesAtivas = enquetePergunta.findByEnqueteAtivaTrue();
		for (EnquetePergunta e : enquetesAtivas) {

			List<String> opcoes = new ArrayList<>();
			List<PerguntaOpcao> po = perguntaOpcao.findByPerguntaId(e.getPergunta().getId());
			for (PerguntaOpcao p : po) {
				opcoes.add(p.getDescricao());
			}

			listaEnquetes.add(new EnqueteJson(e.getId(), // id da pergunta
					e.getPergunta().getDescricao(), // descricao pergunta
					opcoes, // opcoes da pergunta
					"" // resposta
			));
		}

		return listaEnquetes;
	}

	@RequestMapping(value = "/enquete/gravar", method = RequestMethod.POST)
	public void addEnquete(@RequestBody final List<EnqueteJson> enqueteJson) {
		
		System.out.println(enqueteJson);
		
		if (enqueteJson != null) {
			
			for (EnqueteJson e : enqueteJson) {
				respostaOpcaoRepository.setResposta(e.getId(), e.getResposta());
			}
		}

	}

	
	
	
	@RequestMapping("/enquete")
	public Iterable<Enquete> listaEnquetes() {
		return repository.findAll();
	}

	@RequestMapping("/enquete/{id}")
	public Enquete findByNome(@PathVariable long id) {
		return repository.findOne(id);
	}


	@RequestMapping(value = "/enquete", method = RequestMethod.POST)
	public Enquete addEnquetes(@RequestBody final Enquete enquete) {
		Enquete e = repository.save(enquete);
		return e;
	}

	@RequestMapping(value = "/enquete", method = RequestMethod.PUT)
	public Enquete updateEnquete(@RequestBody final Enquete enquete) {
		Enquete e = repository.save(enquete);
		return e;
	}

	@RequestMapping(value = "/enquete/{id}", method = RequestMethod.DELETE)
	public void deleteEnquete(@PathVariable final long id) {
		repository.delete(id);
	}

	@Transactional
	@RequestMapping("/enquete/gerar/{numero}")
	public Iterable<Enquete> gerar100(@PathVariable long numero) {
		if (numero == 0) {
			numero = 10;
		}
		;

		int x;
		for (x = 1; x <= 10; x++) {
			Participante p = new Participante(x, "Participante-" + x, "email_" + x + "@gmail.com", true, true);
			participante.save(p);
			Pergunta pe = new Pergunta(x, "Pergunta-" + x, TipoPergunta.ALTERNATIVA);
			pergunta.save(pe);
			
			perguntaOpcao.save(new PerguntaOpcao(0, pe, "Verde"));
			perguntaOpcao.save(new PerguntaOpcao(0, pe, "Amarelo"));
			perguntaOpcao.save(new PerguntaOpcao(0, pe, "Preto"));
			
		}

		for (int i = 1; i <= numero; i++) {
			Enquete e = new Enquete(i, "Enquete-" + i, "descricao-" + i, new Date(), new Date(), TipoResultado.PUBLICO,
					null, null, true);
			repository.save(e);
			for (int j = 1; j <= 10; j++) {
				enqueteParticipante.save(new EnqueteParticipante(0, e, new Participante(j, null, null, true, true)));
			}
			for (int k = 1; k <= 10; k++) {
				Pergunta p = new Pergunta(k, null, TipoPergunta.ALTERNATIVA);
				EnquetePergunta ep = new EnquetePergunta(k, e, p);
				enquetePergunta.save(ep);
				
				RespostaOpcao ro = new RespostaOpcao( 0 , p , new Participante(k,"","",true,true),"" );
				respostaOpcaoRepository.save(ro);
				
			}
		}
		return repository.findAll();
	}

}

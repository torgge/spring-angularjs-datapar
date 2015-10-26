package py.com.datapar.app.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import py.com.datapar.app.model.Enquete;
import py.com.datapar.app.model.EnqueteParticipante;
import py.com.datapar.app.model.EnquetePergunta;
import py.com.datapar.app.model.Participante;
import py.com.datapar.app.model.Pergunta;
import py.com.datapar.app.model.PerguntaOpcao;
import py.com.datapar.app.model.RespostaOpcao;
import py.com.datapar.app.model.TipoParticipante;
import py.com.datapar.app.model.TipoPergunta;
import py.com.datapar.app.model.TipoResultado;
import py.com.datapar.app.model.json.EnqueteJson;
import py.com.datapar.app.repository.EnqueteParticipanteRepository;
import py.com.datapar.app.repository.EnquetePerguntaRepository;
import py.com.datapar.app.repository.EnqueteRepository;
import py.com.datapar.app.repository.ParticipanteRepository;
import py.com.datapar.app.repository.PerguntaOpcaoRepository;
import py.com.datapar.app.repository.PerguntaRepository;
import py.com.datapar.app.repository.RespostaOpcaoRepository;

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
//	@ApiOperation(httpMethod = "GET", value = "Lista de enquete ativas")
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

	@Transactional
//	@ApiOperation(httpMethod = "POST", value = "Gravar Enquete")
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
	//@ApiOperation(httpMethod = "GET", value = "Lista atodas as enquetes")
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
			Participante p = new Participante(x, "Participante-" + x, "email_" + x + "@gmail.com", true, TipoParticipante.ADMIN);
			participante.save(p);
			Pergunta pe = new Pergunta(x, "Pergunta-" + x, TipoPergunta.ALTERNATIVA);
			pergunta.save(pe);

			perguntaOpcao.save(new PerguntaOpcao(0, pe, "Verde"));
			perguntaOpcao.save(new PerguntaOpcao(0, pe, "Amarelo"));
			perguntaOpcao.save(new PerguntaOpcao(0, pe, "Preto"));
			

		}

		for (long i = 1; i <= numero; i++) {
			Enquete e = new Enquete(i, "Enquete-" + i, "descricao-" + i, new Date(), new Date(), TipoResultado.PUBLICO,
					null, null, true);
			repository.save(e);
			for (long j = 1; j <= 10; j++) {
				enqueteParticipante.save(new EnqueteParticipante(0, e, new Participante(j, "", "", true, TipoParticipante.ADMIN )));
			}


			Pergunta p = new Pergunta(i, null, TipoPergunta.ALTERNATIVA);
			EnquetePergunta ep = new EnquetePergunta(i, e, p);
            enquetePergunta.save(ep);
            
            RespostaOpcao ro = new RespostaOpcao(0, ep, new Participante( i, "", "", true , TipoParticipante.ADMIN ), "");
    		respostaOpcaoRepository.save(ro);

		}
		return repository.findAll();
	}

	
	@RequestMapping("/event")
	public SseEmitter getRealTimeMessageAction( HttpServletRequest request) throws Throwable {
		final SseEmitter emitter = new SseEmitter();
		emitter.send( listaEnquetesAtivas() );
		emitter.complete();
		return emitter;
	}
	
}

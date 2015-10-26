package py.com.datapar.enquete.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import py.com.datapar.enquete.model.Participante;
import py.com.datapar.enquete.repository.ParticipanteRepository;

@RestController
public class Autorizacao {

	@Autowired
	private ParticipanteRepository participanteRepository;

	@RequestMapping("/whoami")
	public String whoAmI(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = "R: " + request.getRemoteAddr();
		}
		return ipAddress;
	}

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public boolean autenticaUsuario(HttpServletRequest request, @RequestBody Participante participante) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = "R: " + request.getRemoteAddr();
		}
		return usuarioAutorizado(participante);
	}

	@RequestMapping(value = "/autenticaToken", method = RequestMethod.POST)
	public String autenticaUsuarioRetornaToken(HttpServletRequest request, @RequestBody Participante participante)
			throws IOException, ServletException {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = "R: " + request.getRemoteAddr();
		}

		String token = participante.getNome();

		if (usuarioAutorizado(participante)) {

			try {
				final Claims claims = Jwts.parser().setSigningKey("123456").parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
			} catch (final SignatureException e) {
				throw new ServletException("Invalid token.");
			}
		}

		return token;
	}

	private boolean usuarioAutorizado(Participante participante) {
		boolean ret = false;
		Participante p = participanteRepository.findByNomeIgnoreCase(participante.getNome());
		if (p != null) {
			ret = participante.getSenha().equals(p.getSenha());
		}
		return ret;
	}

}

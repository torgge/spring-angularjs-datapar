package py.com.datapar.enquete.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null ) {  //|| !authHeader.startsWith("Bearer ")
            throw new ServletException("Missing or invalid Authorization header: "+request.getHeader("Authorization"));
        }

        final String token = authHeader; //.substring(7); // The part after "Bearer "
        
        System.out.println("token:"+token);
        
        if ( ! token.equals("@#$%¨&")) {
        	throw new ServletException("Invalid token.");
        }

        /*  Usar quando tiver tela de login   
     	try {
            final Claims claims = Jwts.parser().setSigningKey("@#$%¨&")
                .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        }
        catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }
        */
        
        chain.doFilter(req, res);
    }

}
## Projetos para avaliação de estratégias de segurança de aplicações web

### (I) Spring Security 

### (II) Spring Security

### (III) Spring Security 

### (IV) JWT

#### O projeto EnqueteSB2 utiliza a estratégia do JWT (JSON Web Token). 

<p align="center">
<img src="https://camo.githubusercontent.com/7a5f442d1c4a49fb1e0a97625be8694aad2026b5/68747470733a2f2f646c2e64726f70626f7875736572636f6e74656e742e636f6d2f752f32313636353130352f636f6f6b69652d746f6b656e2d617574682e706e67" width="350">
</p>

Source: https://github.com/auth0/blog/blob/master/_posts/2014-01-07-angularjs-authentication-with-cookies-vs-token.markdown

#### End-point de autentição 

```java

	@RequestMapping(value = "/autenticaToken", method = RequestMethod.POST)
	public LoginResponse autenticaUsuarioRetornaToken(HttpServletRequest request,
			@RequestBody Participante participante) throws IOException, ServletException {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = "R: " + request.getRemoteAddr();
		}

		Participante participanteEncontrado = participanteRepository.findByNomeIgnoreCase(participante.getNome());
		
		if (participanteEncontrado != null && participante.getSenha().equals(participanteEncontrado.getSenha()) ) {

			try {

				return new LoginResponse(Jwts.builder().setSubject(participante.getNome())
						.claim("roles", participante.getAdmin().toString()).setIssuedAt(new Date())
						.signWith(SignatureAlgorithm.HS256, "lyndontavares").compact());

			} catch (final SignatureException e) {
				throw new ServletException("Invalid token.");
			}

		}

		return null;
	}

```

Contato: integraldomino@gmail.com

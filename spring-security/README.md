## Projetos para avaliação de estratégias de segurança de aplicações web

### (I) Token-based 

### (II) Json-based (JWT)

O projeto EnqueteSB2 utiliza a estratégia do JWT (JSON Web Token). Comparação Token-based e Cookie-based

<p align="center">
<img src="https://camo.githubusercontent.com/7a5f442d1c4a49fb1e0a97625be8694aad2026b5/68747470733a2f2f646c2e64726f70626f7875736572636f6e74656e742e636f6d2f752f32313636353130352f636f6f6b69652d746f6b656e2d617574682e706e67" width="700">
</p>

Source: https://github.com/auth0/blog/blob/master/_posts/2014-01-07-angularjs-authentication-with-cookies-vs-token.markdown

### (II-a) End-point de autenticação na app EnqueteSB2

```java

@RequestMapping(value = "/autenticaToken", method = RequestMethod.POST)
public LoginResponse autenticaUsuarioRetornaToken(HttpServletRequest request,

	@RequestBody Participante participante) throws IOException, ServletException {

	// Validar IP cliente
	// String ipAddress = request.getHeader("X-FORWARDED-FOR");
	// if (ipAddress == null) {
	// 	ipAddress = request.getRemoteAddr();
	// }

	Participante participanteEncontrado = participanteRepository.findByNomeIgnoreCase(participante.getNome());
		
	if (participanteEncontrado != null && participante.getSenha().equals(participanteEncontrado.getSenha())) {

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
### (II-b) Filtro de segurança com validação do Token.

Todas as rotas /api/* requerem autenticação.

```java

	@Bean
	public FilterRegistrationBean jwtFilter() {
        	final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        	registrationBean.setFilter(new JwtFilter());
        	registrationBean.addUrlPatterns("/api/*");
        	return registrationBean;
	}

```

### (II-c) Token gerado com Algorithm.HS256

Algoritmo implementado na app *MasterChicoSB5*.

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/spring-security/wiki/insomnia.png" width="700">
</p>

Em criptografia, HMAC (Hash-based Message Authentication Code) é uma construção específica para calcular o código de autenticação de mensagem (MAC) envolvendo uma função hash criptográfica em combinação com uma chave secreta. Da mesma forma que em qualquer MAC, este pode ser usado para simultaneamente verificar tanto a integridade como a autenticidade de uma mensagem. Qualquer função hash criptográfica, tal como MD5 ou SHA-1, pode ser usada no cálculo do HMAC; o algoritmo MAC resultante é denominado HMAC-MD5 ou HMAC-SHA1 em conformidade. A força criptográfica do HMAC depende da força da criptográfica da função hash subjacente, do tamanho do hash produzido como saída em bits, e do tamanho e da qualidade da chave criptográfica.

source: https://pt.wikipedia.org/wiki/HMAC

Contato: integraldomino@gmail.com

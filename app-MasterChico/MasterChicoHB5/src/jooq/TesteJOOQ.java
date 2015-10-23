package jooq;

	import org.jooq.util.GenerationTool;
	import org.jooq.util.jaxb.Configuration;
	import org.jooq.util.jaxb.Database;
	import org.jooq.util.jaxb.Generator;
	import org.jooq.util.jaxb.Jdbc;
	import org.jooq.util.jaxb.Target;
	 
	public class TesteJOOQ {
	 
		public static void main(String[] args) throws Exception {
			Configuration configuration = new Configuration()
			    .withJdbc(new Jdbc()
			        .withDriver("com.mysql.jdbc.Driver")
			        .withUrl("jdbc:mysql://localhost:3306/masterchico")
			        .withUser("root")
			        .withPassword("1234"))
			    .withGenerator(new Generator()
			        .withName("org.jooq.util.DefaultGenerator")
			        .withDatabase(new Database()
			            .withName("org.jooq.util.mysql.MySQLDatabase")
			            .withIncludes(".*")
			            .withExcludes("")
			            .withInputSchema("masterchico"))
			        .withTarget(new Target()
			            .withPackageName("jooq.classesgeradas")
			            .withDirectory("/java/work-cursoSET2015/MasterChicoHB5/src")));
	 
			GenerationTool.main(configuration);
		}
	 
	}
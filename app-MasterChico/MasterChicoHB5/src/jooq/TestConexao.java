package jooq;

import static jooq.classesgeradas.Tables.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class TestConexao {
	 
	public static void main(String[] args) {
		Connection conn = null;
 
		String userName = "root";
		String password = "1234";
		String url = "jdbc:mysql://localhost:3306/masterchico";
 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
 
			//buscando dados no banco
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			Result<Record> result = create.select().from(TAB_MERCADERIA).fetch();
 
			//iterando os resultados
			for (Record r : result) {
				BigDecimal q = r.getValue(TAB_MERCADERIA.QUANTIDADE);
				String n = r.getValue(TAB_MERCADERIA.NOME);
 
				System.out.println("Mercaderia: " + n + " quantidade: " + q);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
			}
		}
 
	}
}
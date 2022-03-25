package br.com.sp.turisticopoint.turisticopoint.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class AppConfig {
	// método que cria a conexão do banco de dados
	// o @Bean instancia esse método assim que a aplicação inicia
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		// driver do banco de dados
		dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		// caminho para o meu banco de dados
		dmds.setUrl("jdbc:mysql://localhost:3307/turisticopoint");
		// usuario do meu banco
		dmds.setUsername("root");
		// senha do meu banco
		dmds.setPassword("root");
		return dmds;
	}
	
	// método que ?
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		// referenciando o tipo de banco de dados que estamos usando
		adapter.setDatabase(Database.MYSQL);
		// referenciando a linguagem que estamos usando (mais ou menos isso, nao entendi muito bem)
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		// prepara a conexão com o banco, para poder mandar as instruções
		adapter.setPrepareConnection(true);
		// gerando o ddl(linguagem de definição de dados), ele cria as tabelas pra gente
		adapter.setGenerateDdl(true);
		// esse comando imprime no console as instruções que são enviadas para o banco (opcional)
		adapter.setShowSql(true);
		return adapter;
	}
}

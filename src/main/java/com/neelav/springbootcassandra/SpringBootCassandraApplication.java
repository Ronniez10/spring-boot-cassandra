package com.neelav.springbootcassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraTemplate;

import java.net.InetSocketAddress;

@SpringBootApplication
public class SpringBootCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCassandraApplication.class, args);
	}

	public CqlSession session() {
		return CqlSession.builder().withLocalDatacenter("datacenter1")
				.addContactPoint(InetSocketAddress.createUnresolved("127.0.0.1", 9042))
				//.withAuthCredentials(dbProperties.getCassandraUserName(), dbProperties.getCassandraPassword())
				.withKeyspace("javatechie").build();
	}


	@Bean
	@ConditionalOnMissingBean
	public CassandraTemplate cassandraTemplate(CqlSession session) throws Exception {
		return new CassandraTemplate(session);
	}

}

package com.unimed.apicassandra;

import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.metadata.EndPoint;

public class Test {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
	
	private static Person newPerson(String name) {
		return new Person(UUID.randomUUID().toString(), name, Timestamp.from(Instant.now()));
	}

	public static void main(String[] args) {

	
			InetSocketAddress pp = new InetSocketAddress("172.18.0.2", 9042);
		    CqlSession cqlSession = CqlSession.builder().withKeyspace("store").addContactPoint(pp).build();

		    CassandraOperations template = new CassandraTemplate(cqlSession);

		    Person jonDoe = template.insert(newPerson("Jon Doe"));

		    LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getId());

		    template.truncate(Person.class);
		    cqlSession.close();

	}

}

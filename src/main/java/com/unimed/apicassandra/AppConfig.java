package com.unimed.apicassandra;

import com.datastax.oss.driver.api.core.CqlSession;

public class AppConfig {

	public CqlSession session() {
		return CqlSession.builder().withKeyspace("store").build();
	}
}

package com.app.AppConfig;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.app.controllers.OffersRestfullController;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(OffersRestfullController.class);
	}
}

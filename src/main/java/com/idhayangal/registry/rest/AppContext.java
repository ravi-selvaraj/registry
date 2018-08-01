package com.idhayangal.registry.rest;

import java.awt.event.ComponentListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.idhayangal.registry.worker.DBConnectionPool;

import java.util.logging.*;

public class AppContext implements ServletContextListener {
	static final Logger LOG = Logger.getLogger(AppContext.class.getName());

	public void contextDestroyed(ServletContextEvent arg0) {
		LOG.info("Registry App is shutting down...");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		LOG.info("Registry App is starting up...");
		DBConnectionPool.Initialize();
	}
}

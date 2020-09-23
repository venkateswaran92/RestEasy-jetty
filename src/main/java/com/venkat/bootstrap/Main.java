package com.venkat.bootstrap;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

/**
 * @author Venkateswaran.T 
 */
public class Main {

	static final String APPLICATION_PATH = "/api";
	static final String CONTEXT_ROOT = "/";

	public Main() {
	}

	public static void main(String[] args) throws Exception {
		try {
			new Main().run();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void run() throws Exception {

		final int port = 8080;
		final Server server = new Server(port);

		// Setup the basic Application "context" at "/".
		// This is also known as the handler tree (in Jetty speak).
		final ServletContextHandler context = new ServletContextHandler(server, CONTEXT_ROOT);

		// Setup RESTEasy's HttpServletDispatcher at "/api/*".
		final ServletHolder restEasyServlet = new ServletHolder(new HttpServletDispatcher());
		restEasyServlet.setInitParameter("resteasy.servlet.mapping.prefix", APPLICATION_PATH);
		restEasyServlet.setInitParameter("javax.ws.rs.Application",
				"com.venkat.config.RestEasyApplication");
		//filters
		restEasyServlet.setInitParameter("resteasy.providers",
				"com.venkat.flters.PreIntercetpor,com.venkat.flters.SecurityInterceptor,com.venkat.flters.PostIntercetpor");
		context.addServlet(restEasyServlet, APPLICATION_PATH + "/*");

		// Setup the DefaultServlet at "/".
		final ServletHolder defaultServlet = new ServletHolder(new DefaultServlet());
		context.addServlet(defaultServlet, CONTEXT_ROOT);

		// Set the path to our static (Swagger UI) resources
		String resourceBasePath = Main.class.getResource("/swagger-ui").toExternalForm();
		context.setResourceBase(resourceBasePath);
		context.setWelcomeFiles(new String[] { "index.html" });

		server.start();
		server.join();
	}
}
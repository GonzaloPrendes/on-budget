package com.onbudget.service.server;

import java.io.IOException;
import com.onbudget.service.config.ConfigurationProperty;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public final class GrizzlyServer implements Server {

    private final ConfigurationProperty port;
    
    private final ResourceConfig config;

    private final String context;

    private final String staticContext;

    public GrizzlyServer(final ConfigurationProperty port, final ResourceConfig config) {
        this(
            port,
            config,
            "/api",
            "/"
        );
    }

    public GrizzlyServer(final ConfigurationProperty port, final ResourceConfig config,
            final String context, final String staticContext) {
        this.port = port;
        this.config = config;
        this.context = context;
        this.staticContext = staticContext;
    }
    
    @Override
    public void start() {
        try {
            final String context = this.context.startsWith("/") ? this.context : "/" + this.context;
            final String staticContext = this.staticContext.startsWith("/") ? this.staticContext : "/" + this.staticContext;
            final WebappContext webContext = new WebappContext("grizzly web context", "");
            final ServletRegistration servletRegistration = webContext.addServlet("Jersey", new ServletContainer(config));
            servletRegistration.addMapping(context.endsWith("/") ? context + "*" : context + "/*");
            final HttpServer httpServer = HttpServer.createSimpleServer(null, this.port.optionalValue().map(Integer::valueOf).get());
            final HttpHandler httpHandler = new CLStaticHttpHandler(this.getClass().getClassLoader(),"/static/");
            httpServer.getServerConfiguration().addHttpHandler(
                httpHandler, staticContext.endsWith("/") ? staticContext + "*" : staticContext + "/*"
            );
            webContext.deploy(httpServer);
            httpServer.start();
        } catch (final IOException ex) {
           throw new RuntimeException(ex); 
        }
    }
}

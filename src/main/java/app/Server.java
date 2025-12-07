package app;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import org.glassfish.jersey.server.ResourceConfig;
import service.StudentService;

public class Server {

  public static CountDownLatch SHUTDOWN_LATCH = new CountDownLatch(1);

  public static void main(String[] args) throws IOException, InterruptedException {
    // Create configuration object for webserver instance
    ResourceConfig config = new ResourceConfig();
    // Register REST-resources (i.e. service classes) with the webserver
    config.register(ServerExceptionMapper.class); // generic error handling
    config.register(StudentService.class);
    // add further REST-resources like this:
    // config.register(XyzService.class);

    // Create webserver instance and start it
    HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
    HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
    // Context is part of the URI directly after  http://domain.tld:port/
    server.createContext("/api/v1", handler);
    server.start();

    // Wait for main thread to end
    SHUTDOWN_LATCH.await(); // whoever is calling SHUTDOWN_LATCH.countDown() will end the server
    server.stop(0);
  }
}

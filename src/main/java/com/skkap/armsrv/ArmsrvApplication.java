package com.skkap.armsrv;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.thrift.THttpService;

@SpringBootApplication
public class ArmsrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmsrvApplication.class, args);

        ServerBuilder sb = new ServerBuilder();
        sb.port(8080, SessionProtocol.HTTP);

        // Add a simple 'Hello, world!' service.
        sb.service("/", (ctx, res) -> HttpResponse.of("Hello, world!"));

        sb.service("/user", THttpService.of(new MyUserService()));

        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        // Wait until the server is ready.
        future.join();
    }
}

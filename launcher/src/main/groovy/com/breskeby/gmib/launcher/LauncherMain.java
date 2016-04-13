package com.breskeby.gmib.launcher;

import com.breskeby.gmib.http.handler.RepositoryRequestHandler;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

import java.net.URI;

public class LauncherMain {

    public static void main(String... args) throws Exception {
        RatpackServer.start(server -> server
                .serverConfig(ServerConfig.embedded().publicAddress(new URI("http://company.org")))
                .registryOf(registry -> registry.add(new RepositoryRequestHandler()))
                .handlers(chain -> chain
                        .prefix("repository", ctx -> {
                            ctx.all(RepositoryRequestHandler.class);
                        }))
        );
    }
}

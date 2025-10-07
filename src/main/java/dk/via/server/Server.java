package dk.via.server;

import io.grpc.ServerBuilder;

public class Server
{

    public static void main(String[] args) throws Exception {

    }


    private void run() throws Exception {

        io.grpc.Server server = ServerBuilder
                .forPort(1234)
                .addService(new ServerImpl())
                .build();
        server.start();
        server.awaitTermination();

    }

}

package dk.via.server;

import dk.via.server.persistence.animal.AnimalDAOImpl;
import dk.via.server.persistence.part.PartDAOImpl;
import dk.via.server.persistence.product.ProductDAOImpl;
import dk.via.server.persistence.tray.TrayDAOImpl;
import dk.via.server.service.animal.AnimalServiceImpl;
import dk.via.server.service.part.PartServiceImpl;
import dk.via.server.service.product.ProductServiceImpl;
import dk.via.server.service.tray.TraySeriveImpl;
import io.grpc.ServerBuilder;

public class Server
{

    public static void main(String[] args) throws Exception {

    }


    private void run() throws Exception {
/*
        Server server = ServerBuilder
                .forPort(1234)
                .addService(new ServerImpl())
                .build();
        server.start();
        server.awaitTermination();
*/
    }

}

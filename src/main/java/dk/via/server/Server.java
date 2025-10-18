package dk.via.server;

import dk.via.server.persistence.animal.AnimalDAO;
import dk.via.server.persistence.animal.AnimalDAOImpl;
import dk.via.server.persistence.part.PartDAO;
import dk.via.server.persistence.part.PartDAOImpl;
import dk.via.server.persistence.product.ProductDAO;
import dk.via.server.persistence.product.ProductDAOImpl;
import dk.via.server.persistence.tray.TrayDAO;
import dk.via.server.persistence.tray.TrayDAOImpl;
import io.grpc.ServerBuilder;

public class Server
{
    private AnimalDAO animalDAO = AnimalDAOImpl.getInstance();
    private TrayDAO trayDAO = TrayDAOImpl.getInstance();
    private ProductDAO productDAO = ProductDAOImpl.getInstance();
    private PartDAO partDAO = PartDAOImpl.getInstance();

    public static void main(String[] args) throws Exception {
        System.out.println("Server started...");
        new Server().run();
    }


    private void run() throws Exception {

        io.grpc.Server server = ServerBuilder
                .forPort(1234)
                .addService(new ServerImpl(animalDAO,trayDAO,partDAO,productDAO))
                .build();
        server.start();
        server.awaitTermination();

    }

}

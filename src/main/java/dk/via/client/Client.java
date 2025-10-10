package dk.via.client;

import dk.via.GetAnimalRequest;
import dk.via.GetAnimalResponse;
import dk.via.SlaughterHouseGrpc;
import dk.via.domain.Animal;
import dk.via.dto.DTOFactory;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client
{
    public static void main(String[] args) {
        new Client().run();
    }
    private ManagedChannel managedChannel = ManagedChannelBuilder
            .forAddress("localhost", 1234)
            .usePlaintext()
            .build();
    private SlaughterHouseGrpc.SlaughterHouseBlockingStub stub =
            SlaughterHouseGrpc.newBlockingStub(managedChannel);
    private void run(){



        managedChannel.shutdown();
    }


    private Animal getAnimal(int regNumber){
        try{
            GetAnimalRequest request = DTOFactory.createGetAnimalRequest(regNumber);
            GetAnimalResponse response = stub.getAnimal(request);
            return DTOFactory.createAnimal(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

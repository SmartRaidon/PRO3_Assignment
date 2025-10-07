package dk.via.client;

import dk.via.SlaughterHouseGrpc;
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

}

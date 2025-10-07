package dk.via.server;

import dk.via.*;
import io.grpc.stub.StreamObserver;

public class ServerImpl extends SlaughterHouseGrpc.SlaughterHouseImplBase {


    @Override
    public void getAnimal(GetAnimalRequest request, StreamObserver<GetAnimalResponse> responseObserver) {
        super.getAnimal(request, responseObserver);
    }

    @Override
    public void getAnimals(GetAnimalsRequest request, StreamObserver<GetAnimalsResponse> responseObserver) {
        super.getAnimals(request, responseObserver);
    }

    @Override
    public void getPart(GetPartRequest request, StreamObserver<GetPartResponse> responseObserver) {
        super.getPart(request, responseObserver);
    }

    @Override
    public void getParts(GetPartsRequest request, StreamObserver<GetPartsResponse> responseObserver) {
        super.getParts(request, responseObserver);
    }

    @Override
    public void getTray(GetTrayRequest request, StreamObserver<GetTrayResponse> responseObserver) {
        super.getTray(request, responseObserver);
    }

    @Override
    public void getTrays(GetTraysRequest request, StreamObserver<GetTraysResponse> responseObserver) {
        super.getTrays(request, responseObserver);
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {
        super.getProduct(request, responseObserver);
    }

    @Override
    public void getProducts(GetProductsRequest request, StreamObserver<GetProductsResponse> responseObserver) {
        super.getProducts(request, responseObserver);
    }
}

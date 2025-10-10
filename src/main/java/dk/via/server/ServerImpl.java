package dk.via.server;

import dk.via.*;
import dk.via.domain.Animal;
import dk.via.domain.Part;
import dk.via.domain.Product;
import dk.via.domain.Tray;
import dk.via.dto.DTOFactory;
import dk.via.server.persistence.animal.AnimalDAO;
import dk.via.server.persistence.part.PartDAO;
import dk.via.server.persistence.product.ProductDAO;
import dk.via.server.persistence.tray.TrayDAO;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class ServerImpl extends SlaughterHouseGrpc.SlaughterHouseImplBase {

    private AnimalDAO animalDAO;
    private TrayDAO trayDAO;
    private PartDAO partDAO;
    private ProductDAO productDAO;
    ServerImpl(AnimalDAO animalDAO,TrayDAO trayDAO,PartDAO partDAO,ProductDAO productDAO) {
        this.animalDAO = animalDAO;
        this.trayDAO = trayDAO;
        this.partDAO = partDAO;
        this.productDAO = productDAO;
    }
    @Override
    public void getAnimal(GetAnimalRequest request, StreamObserver<GetAnimalResponse> responseObserver) {


        try{
            int registrationNumber =request.getRegistrationNumber();
            Animal animalToFind = animalDAO.getAnimalById(registrationNumber);
            if (animalToFind != null) {
                GetAnimalResponse response = DTOFactory.createAnimalResponse(animalToFind);
                responseObserver.onNext(response);
            }else responseObserver.onError(
                    new Exception("Animal not found for registration number something went wrong BRO: " + registrationNumber)
            );
            responseObserver.onCompleted();

        }catch (Exception e){
            e.printStackTrace();
            responseObserver.onError(e);
        }


    }

    @Override
    public void getAnimals(GetAnimalsRequest request, StreamObserver<GetAnimalsResponse> responseObserver) {

        try{

            List<Animal> animals = animalDAO.getAll();
            if (!animals.isEmpty()) {
                GetAnimalsResponse response = DTOFactory.createGetAnimalsResponse(animals);
                responseObserver.onNext(response);
            }else responseObserver.onError(
                    new Exception("No animals list found can be empty or something xd")
            );

            responseObserver.onCompleted();
        }catch (Exception e){
            e.printStackTrace();
            responseObserver.onError(e);
        }



    }

    @Override
    public void getPart(GetPartRequest request, StreamObserver<GetPartResponse> responseObserver) {

        try{

            Part partToFind = partDAO.getPartById(request.getRegNum());
            if (partToFind != null) {
                GetPartResponse response = DTOFactory.createGetPartResponse(partToFind);
            responseObserver.onNext(response);
            }else responseObserver.onError(
                    new Exception("Cannot find part, probably wrong registration number")
            );

            responseObserver.onCompleted();

        }catch (Exception e){
            e.printStackTrace();
            responseObserver.onError(e);
        }

    }

    @Override
    public void getParts(GetPartsRequest request, StreamObserver<GetPartsResponse> responseObserver) {

        try {

            List<Part> partsList = partDAO.getAllParts();
            if (!partsList.isEmpty()) {
                GetPartsResponse response = DTOFactory.createGetPartsResponse(partsList);
                responseObserver.onNext(response);

            }else responseObserver.onError( new Exception("No parts list found can be empty or something xd"));

            responseObserver.onCompleted();
        }catch (Exception e){
            e.printStackTrace();
            responseObserver.onError(e);

        }
    }

    @Override
    public void getTray(GetTrayRequest request, StreamObserver<GetTrayResponse> responseObserver) {


         try{
           Tray trayToFind  = trayDAO.getTrayById(request.getTrayNum());
             if(trayToFind != null) {
                GetTrayResponse response = DTOFactory.createGetTrayResponse(trayToFind);
                 responseObserver.onNext(response);


             }else responseObserver.onError(new Exception("Cannot find tray with given regnumber "+ request.getTrayNum()));
                responseObserver.onCompleted();

         }catch (Exception e){
             e.printStackTrace();
             responseObserver.onError(e);
         }
    }

    @Override
    public void getTrays(GetTraysRequest request, StreamObserver<GetTraysResponse> responseObserver) {

        try{
            List<Tray> traysList = trayDAO.getAll();
            if (!traysList.isEmpty()) {
                GetTraysResponse response = DTOFactory.createGetTraysResponse(traysList);
                responseObserver.onNext(response);
            }else responseObserver.onError( new Exception("No trays found can be empty or something xd"));
            responseObserver.onCompleted();

        }catch (Exception e){
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {
        try{
            Product productToFind = productDAO.getProductById(request.getProductNum());
            if (productToFind != null) {
                GetProductResponse response = DTOFactory.createGetProductResponse(productToFind);
                responseObserver.onNext(response);

            }else responseObserver.onError( new Exception("Cannot find product with given regnumber "+ request.getProductNum()));
                responseObserver.onCompleted();
        } catch (Exception e) {
           e.printStackTrace();
           responseObserver.onError(e);
        }
    }

    @Override
    public void getProducts(GetProductsRequest request, StreamObserver<GetProductsResponse> responseObserver) {
        try{
        List<Product> productsList = productDAO.getAll();
        if (!productsList.isEmpty()) {
            GetProductsResponse response = DTOFactory.createGetProductsResponse(productsList);
            responseObserver.onNext(response);
        }else responseObserver.onError( new Exception("No products found, can be empty or something xd"));
        responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }



}

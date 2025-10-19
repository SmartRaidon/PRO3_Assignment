package dk.via.client;

import dk.via.*;
import dk.via.domain.Animal;
import dk.via.domain.Part;
import dk.via.domain.Product;
import dk.via.domain.Tray;
import dk.via.dto.DTOFactory;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class Client
{
    public static void main(String[] args) {
        new Client().run();
    }

    private final ManagedChannel managedChannel = ManagedChannelBuilder
            .forAddress("localhost", 1234)
            .usePlaintext()
            .build();

    private final SlaughterHouseGrpc.SlaughterHouseBlockingStub stub =
            SlaughterHouseGrpc.newBlockingStub(managedChannel);

    private void run() {

        System.out.println("\n--- GET ONE ANIMAL by id ---");
        Animal a = getAnimal(1);
        if (a != null)
            System.out.println(a.getType() + " " + a.getRegNumber() + " " + a.getWeight());

        System.out.println("\n--- Animal 1 in these products: ---");
        List<Integer> productNums = getProductsByAnimalID(1);
        if(productNums != null) {
            for (Integer pn : productNums)
                System.out.print(pn + " ");
        } else System.out.println("No products found for animal 1");

        System.out.println("\n--- Animals included in product 1: ---");
        List<Integer> animalNums = getAnimalsByProductID(1);
        if(animalNums != null) {
            for (Integer an : animalNums)
                System.out.print(an + " ");
        } else System.out.println("No animals found for product 1");

        System.out.println("\n--- GET ALL ANIMALS ---");
        List<Animal> animals = getAnimals();
        for (Animal an : animals)
            System.out.println(an.getType() + " " + an.getRegNumber() + " " + an.getWeight());

        System.out.println("\n--- GET ONE PRODUCT by id---");
        Product p = getProduct(1);
        if (p != null)
            System.out.println("Product #" + p.getProductNumber() + " (parts: " + p.getParts().size() + ")");

        System.out.println("\n--- GET ALL PRODUCTS ---");
        List<Product> products = getProducts();
        System.out.println("Total products: " + products.size());

        System.out.println("\n--- GET ONE TRAY by id ---");
        Tray t = getTray(1);
        if (t != null)
            System.out.println("Tray #" + t.getTrayNumber() + " weight: " + t.getCurrentWeight());

        System.out.println("\n--- GET ALL TRAYS ---");
        List<Tray> trays = getTrays();
        System.out.println("Total trays: " + trays.size());

        managedChannel.shutdown();
    }

    private List<Integer> getProductsByAnimalID(int animalID) {
        try {
            GetProductsForAnimalRequest request = DTOFactory.createGetProductsForAnimalRequest(animalID);
            GetProductsForAnimalResponse response = stub.getProductsForAnimal(request);
            return DTOFactory.createProductNums(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Integer> getAnimalsByProductID(int productID) {
        try {
            GetAnimalsForProductRequest request = DTOFactory.createGetAnimalsForProductRequest(productID);
            GetAnimalsForProductResponse response = stub.getAnimalsForProduct(request);
            return  DTOFactory.createAnimalNums(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Animal getAnimal(int regNumber) {
        try {
            GetAnimalRequest request = DTOFactory.createGetAnimalRequest(regNumber);
            GetAnimalResponse response = stub.getAnimal(request);
            return DTOFactory.createAnimal(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Animal> getAnimals() {
        try {
            GetAnimalsRequest request = DTOFactory.createGetAnimalsRequest();
            GetAnimalsResponse response = stub.getAnimals(request);
            return DTOFactory.createAnimals(response);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private Product getProduct(int productNum) {
        try {
            GetProductRequest request = DTOFactory.createGetProductRequest(productNum);
            GetProductResponse response = stub.getProduct(request);
            return DTOFactory.createProduct(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Product> getProducts() {
        try {
            GetProductsRequest request = DTOFactory.createProductsRequest();
            GetProductsResponse response = stub.getProducts(request);
            return DTOFactory.createProducts(response);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private Tray getTray(int trayNum) {
        try {
            GetTrayRequest request = DTOFactory.createGetTrayRequest(trayNum);
            GetTrayResponse response = stub.getTray(request);
            return DTOFactory.createTray(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Tray> getTrays() {
        try {
            GetTraysRequest request = DTOFactory.createTraysRequest();
            GetTraysResponse response = stub.getTrays(request);
            return DTOFactory.createTrays(response);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }


}

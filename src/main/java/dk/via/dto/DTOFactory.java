package dk.via.dto;

import dk.via.*;
import dk.via.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DTOFactory
{

  public static DTOAnimal createDTOAnimal(Animal animal)
  {

    return DTOAnimal.newBuilder()
        .setType(animal.getType())
        .setRegistrationNumber(animal.getRegNumber())
        .setWeight(animal.getWeight())
        .build();
  }

  public static DTOPart createDTOPart(Part part)
  {
    return DTOPart.newBuilder().setRegNum(part.getRegNumber())
        .setTrayNum(part.getTrayNumber())
        .setOriginNum(part.getOriginNumber())
        .setProductNum(part.getProductNumber())
        .setType(part.getType())
        .setWeight(part.getWeight()).build();
  }

  public static DTOTray createDTOTray(Tray tray)
  {
    DTOTray.Builder builder = DTOTray.newBuilder()
        .setTrayNum(tray.getTrayNumber())
        .setMaxCapacity(tray.getMaxWeight())
        .setCurrentWeight(tray.getCurrentWeight());
    if (tray.getParts() != null && !tray.getParts().isEmpty())
    {
      for (Part p : tray.getParts())
      {
        builder.addParts(createDTOPart(p));
      }

    }
    return builder.build();
  }

  public static DTOProduct createDTOProduct(Product product)
  {
    DTOProduct.Builder builder = DTOProduct.newBuilder()
        .setProductNum(product.getProductNumber());
    if (product.getParts() != null && !product.getParts().isEmpty())
    {
      for (Part p : product.getParts())
      {
        builder.addParts(createDTOPart(p));
      }

    }
    return builder.build();
  }

  public static GetAnimalRequest createGetAnimalRequest(int registrationNumber) {
    return GetAnimalRequest.newBuilder()
        .setRegistrationNumber(registrationNumber)
        .build();
  }


  public static GetAnimalResponse createAnimalResponse(Animal animal){
    return GetAnimalResponse.newBuilder()
        .setAnimal (createDTOAnimal(animal))
        .build();
  }

  public static GetAnimalsRequest createGetAnimalsRequest()
  { return GetAnimalsRequest.newBuilder()
      .build();
  }

  public static GetAnimalsResponse createGetAnimalsResponse(List<Animal> animals)
  {
    ArrayList<DTOAnimal> list = new ArrayList<>();
    for (Animal a : animals)
    {
      list.add(createDTOAnimal(a));

    } return GetAnimalsResponse.newBuilder()
      .addAllAnimals(list)
      .build();
  }

  public static GetPartRequest createGetPartRequest(int regNumber)
  { return GetPartRequest.newBuilder()
      .setRegNum(regNumber)
      .build();
  }

  public static GetPartResponse createGetPartResponse(Part part){
    return GetPartResponse.newBuilder()
        .setPart(createDTOPart(part))
        .build();
  }

  public static GetPartsRequest createPartsRequest()
  {
    return GetPartsRequest.newBuilder()
        .build();
  }

  public static GetPartsResponse createGetPartsResponse(List<Part> parts)
  {
    ArrayList<DTOPart> list = new ArrayList<>();
    for (Part p : parts)
    {
      list.add(createDTOPart(p));

    } return GetPartsResponse.newBuilder()
      .addAllParts(list)
      .build();
  }


  public static GetTrayRequest createGetTrayRequest(int trayNum)
  { return GetTrayRequest.newBuilder()
      .setTrayNum(trayNum)
      .build();
  }

  public static GetTrayResponse createGetTrayResponse(Tray tray){
    return GetTrayResponse.newBuilder()
        .setTray(createDTOTray(tray))
        .build();
  }

  public static GetTraysRequest createTraysRequest()
  {
    return GetTraysRequest.newBuilder()
        .build();
  }

  public static GetTraysResponse createGetTraysResponse(List<Tray> trays)
  {
    ArrayList<DTOTray> list = new ArrayList<>();
    for (Tray t : trays)
    {
      list.add(createDTOTray(t));

    } return GetTraysResponse.newBuilder()
      .addAllTrays(list)
      .build();
  }


  public static GetProductRequest createGetProductRequest(int productNum)
  { return GetProductRequest.newBuilder()
      .setProductNum(productNum)
      .build();
  }

  public static GetProductResponse createGetProductResponse(Product product){
    return GetProductResponse.newBuilder()
        .setProduct(createDTOProduct(product))
        .build();
  }

  public static GetProductsRequest createProductsRequest()
  {
    return GetProductsRequest.newBuilder()
        .build();
  }

  public static GetProductsResponse createGetProductsResponse(List<Product> products)
  {
    ArrayList<DTOProduct> list = new ArrayList<>();
    for (Product p : products)
    {
      list.add(createDTOProduct(p));

    } return GetProductsResponse.newBuilder()
      .addAllProducts(list)
      .build();
  }
  public static Animal createAnimal(GetAnimalResponse a) {
    return createAnimal(a.getAnimal());
  }

  public static Animal createAnimal(DTOAnimal dto){

    return new Animal(dto.getType(), dto.getRegistrationNumber(), dto.getWeight());

  }

  public static List<Animal> createAnimals(GetAnimalsResponse r) {
    List<Animal> res = new ArrayList<>(r.getAnimalsCount());
    for (DTOAnimal a : r.getAnimalsList()) res.add(createAnimal(a));
    return res;
  }
  //
  public static Part createPart(GetPartResponse p) {
    return createPart(p.getPart());
  }

  public static Part createPart(DTOPart dto){

    return new Part(dto.getRegNum(),dto.getTrayNum(),dto.getOriginNum(),dto.getProductNum(),dto.getType(),dto.getWeight());
  }

  public static List<Part> createParts(GetPartsResponse r) {
    List<Part> res = new ArrayList<>(r.getPartsCount());
    for (DTOPart p : r.getPartsList()) res.add(createPart(p));
    return res;
  }

  public static Tray createTray(GetTrayResponse t) {
    return createTray(t.getTray());
  }




  public static Tray createTray(DTOTray dto){
    List<Part> parts = new ArrayList<>(dto.getPartsCount());
    for (DTOPart p : dto.getPartsList()) parts.add(createPart(p));
    return new Tray(dto.getMaxCapacity(),dto.getTrayNum(),dto.getCurrentWeight(),parts);


  }

  public static List<Tray> createTrays(GetTraysResponse r) {
    List<Tray> res = new ArrayList<>(r.getTraysCount());
    for (DTOTray t : r.getTraysList()) res.add(createTray(t));
    return res;
  }

  public static Product createProduct(GetProductResponse p) {
    return createProduct(p.getProduct());
  }

  public static Product createProduct(DTOProduct dto){
    List<Part> parts = new ArrayList<>(dto.getPartsCount());
    for (DTOPart p : dto.getPartsList()) parts.add(createPart(p));
    return new Product(dto.getProductNum(),parts);


  }

  public static List<Product> createProducts(GetProductsResponse r) {
    List<Product> res = new ArrayList<>(r.getProductsCount());
    for (DTOProduct p : r.getProductsList()) res.add(createProduct(p));
    return res;
  }

  public static GetAnimalsForProductRequest createGetAnimalsForProductRequest(int productNum) {
    return GetAnimalsForProductRequest.newBuilder()
        .setProductNum(productNum)
        .build();
  }

  public static GetAnimalsForProductResponse createGetAnimalsForProductResponse(List<Integer> animalNums) {
    return GetAnimalsForProductResponse.newBuilder()
        .addAllAnimalNums(animalNums)
        .build();
  }

  public static GetProductsForAnimalRequest createGetProductsForAnimalRequest(int animalNum) {
    return GetProductsForAnimalRequest.newBuilder()
        .setAnimalNum(animalNum)
        .build();
  }

  public static GetProductsForAnimalResponse createGetProductsForAnimalResponse(List<Integer> productNums) {
    return GetProductsForAnimalResponse.newBuilder()
        .addAllProductNums(productNums)
        .build();
  }

  public static List<Integer> createAnimalNums(GetAnimalsForProductResponse response) {
    return new ArrayList<>(response.getAnimalNumsList());
  }

  public static List<Integer> createProductNums(GetProductsForAnimalResponse response) {
    return new ArrayList<>(response.getProductNumsList());
  }

  }






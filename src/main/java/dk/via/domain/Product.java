package dk.via.domain;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productNumber;
    private String type;
    private List<Part> parts;

    public Product(int productNumber, String type) {
        this.productNumber = productNumber;
        this.type = type;
        this.parts = new ArrayList<>();
    }

    public void addPart(Part part) {
        parts.add(part);
    }
    public void removePart(Part part) {
        parts.remove(part);
    }
    public int getProductNumber() {
        return productNumber;
    }
    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
    public List<Part> getParts() {
        return parts;
    }
    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}

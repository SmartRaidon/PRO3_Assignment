package dk.via.domain;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productNumber;
    private List<Part> parts;

    public Product(int productNumber) {
        this.productNumber = productNumber;
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
}

package dk.via.domain;

import java.util.Objects;

public class Part {
    private int regNumber;
    private int trayNumber;
    private int originNumber;
    private int productNumber;
    private String type;
    private double weight;

    public Part(int regNumber, int trayNumber, int originNumber,
                int productNumber, String type, double weight) {
        this.regNumber = regNumber;
        this.trayNumber = trayNumber;
        this.originNumber = originNumber;
        this.productNumber = productNumber;
        this.type = type;
        this.weight = weight;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getOriginNumber() {
        return originNumber;
    }

    public void setOriginNumber(int originNumber) {
        this.originNumber = originNumber;
    }

    public int getTrayNumber() {
        return trayNumber;
    }

    public void setTrayNumber(int trayNumber) {
        this.trayNumber = trayNumber;
    }

    @Override
    public String toString() {
        return "Part{" +
                "regNumber=" + regNumber +
                ", trayNumber=" + trayNumber +
                ", originNumber=" + originNumber +
                ", productNumber=" + productNumber +
                ", type='" + type +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        Part other = (Part) obj;
        return regNumber == other.regNumber &&
                trayNumber == other.trayNumber &&
                originNumber == other.originNumber &&
                productNumber == other.productNumber &&
                type.equals(other.type) &&
                weight == other.weight;
    }
}
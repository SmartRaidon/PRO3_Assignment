package dk.via.domain;

import java.util.ArrayList;
import java.util.List;

public class Tray {
    private int trayNumber;
    private String partType;
    private double maxWeight;
    private double currentWeight;
    private List<Part> parts;

    public Tray()
    {
        this.trayNumber = -1;
        this.partType = "";
        this.maxWeight = 0;
        this.currentWeight = 0;
        this.parts = new ArrayList<>();
    }

    public Tray(int trayNumber, String partType, double maxCapacity, double currentWeight)
    {
        this.trayNumber = trayNumber;
        this.partType = partType;
        this.maxWeight = maxCapacity;
        this.currentWeight = currentWeight;
        this.parts = new ArrayList<>();
    }

    public Tray(double maxCapacity,int trayNum,  double currentWeight, List<Part> parts) {
        this.trayNumber = trayNum;
        this.maxWeight = maxCapacity;
        this.currentWeight = currentWeight;
        this.parts = parts;
    }


    public int getTrayNumber() {
        return trayNumber;
    }

    public void setTrayNumber(int trayNumber) {
        this.trayNumber = trayNumber;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public double getMaxWeight()
    {
        return maxWeight;
    }

    public double getCurrentWeight()
    {
        return currentWeight;
    }

    public List<Part> getParts()
    {
        return parts;
    }

    public void setMaxWeight(double maxWeight)
    {
        this.maxWeight = maxWeight;
    }

    public void addWeight(double weight)
    {

        double sum=0;
        sum= currentWeight+weight;
        if(sum < maxWeight)
        {
            currentWeight = sum;
        }
        else {
            System.out.println("Maximum weight capacity exceeded!");
        }
    }

    public void addPart(Part part)
    {
        parts.add(part);
    }

    public Part getPart(int index)
    {
        return parts.get(index);
    }

    public void listParts()
    {
        for(Part part : parts)
        {
            System.out.println(part);
        }
    }
}

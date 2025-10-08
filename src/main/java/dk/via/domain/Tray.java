package dk.via.domain;

import java.util.ArrayList;
import java.util.List;

public class Tray {
    private int trayNumber;
    private String partType;
    private double maxCapacity;
    private double currentWeight;
    private List<Part> parts;

    public Tray(int trayNumber, String partType, double maxCapacity, double currentWeight)
    {
        this.trayNumber = trayNumber;
        this.partType = partType;
        this.maxCapacity = maxCapacity;
        this.currentWeight = currentWeight;
        this.parts = new ArrayList<>();
    }

    public Tray(double maxCapacity,int trayNum,  double currentWeight, List<Part> parts) {
        this.trayNumber = trayNum;
        this.maxCapacity = maxCapacity;
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

    public double getMaxCapacity()
    {
        return maxCapacity;
    }

    public double getCurrentWeight()
    {
        return currentWeight;
    }

    public List<Part> getParts()
    {
        return parts;
    }

    public void setMaxCapacity(double maxCapacity)
    {
        this.maxCapacity = maxCapacity;
    }

    public void addWeight(double weight)
    {

        double sum=0;
        sum= currentWeight+weight;
        if(sum < maxCapacity)
        {
            currentWeight = sum;
        }
        else {
            System.out.println("Maximum capacity exceeded");
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

package dk.via.domain;

import java.util.ArrayList;
import java.util.List;

public class Tray {
    private int trayNumber;
    private double maxCapacity;
    private double currentWeight;
    private List<Part> parts;

    public Tray(double maxCapacity, int trayNumber)
    {
        this.trayNumber = trayNumber;
        this.maxCapacity = maxCapacity;
        this.currentWeight = 0;
        this.parts = new ArrayList<Part>();
    }

    public int getTrayNumber() {
        return trayNumber;
    }

    public void setTrayNumber(int trayNumber) {
        this.trayNumber = trayNumber;
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

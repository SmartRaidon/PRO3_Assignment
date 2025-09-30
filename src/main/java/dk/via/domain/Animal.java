package dk.via.domain;

public class Animal {
    private String type;
    private int regNumber;
    private double weight;

    public Animal(String type, int regNumber, double weight)
    {
        this.type = type;
        this.regNumber = regNumber;
        this.weight = weight;
    }

    public String getType()
    {
        return type;
    }

    public int getRegNumber()
    {
        return regNumber;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setRegNumber(int regNumber)
    {
        this.regNumber = regNumber;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}

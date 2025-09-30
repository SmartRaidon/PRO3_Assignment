package dk.via.domain;

public class Part {
    private int regNumber;
    private int originNumber;

    private double weight;

    public Part(int regNumber, int originNumber, double weight)
    {
        this.regNumber = regNumber;
        this.originNumber = originNumber;
        this.weight = weight;
    }

    public int getRegNumber()
    {
        return regNumber;
    }

    public int getOriginNumber()
    {
        return originNumber;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setRegNumber(int regNumber)
    {
        this.regNumber = regNumber;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public void setOriginNumber(int originNumber)
    {
        this.originNumber = originNumber;
    }
}

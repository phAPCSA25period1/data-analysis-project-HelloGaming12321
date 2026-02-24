public class Data {
    private String name;
    private double spend;
    private double infantMortality;
    public Data(String dataName, double dataSpend, double dataInfantMortality){
        name = dataName;
        spend = dataSpend;
        infantMortality = dataInfantMortality;
    }
    public double getSpend(){
        return spend;
    }
    public double getInfantMortality(){
        return infantMortality;
    }
    public String toString(){
        return "The r value is something";
    }
}
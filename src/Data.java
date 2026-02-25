import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Data {
    private double spend;
    private double infantMortality;
    public Data(double dataSpend, double dataInfantMortality){
        spend = dataSpend;
        infantMortality = dataInfantMortality;
    }
    public static Data[] readFile(File fileName) throws FileNotFoundException {
        Scanner scanOne = new Scanner(fileName);
        int i = 0;
        scanOne.nextLine();
        while(scanOne.hasNext()){
            //AI Wrote the Spliter to remove commas in Quotations
            String[] line = scanOne.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            if(!line[9].equals("") && !line[12].equals("")){
            i++;
            }
        }
        scanOne.close();
        Data[] healthToInfant = new Data[i];
        Scanner scanTwo = new Scanner(fileName);
        int j = 0;
        scanTwo.nextLine();
        while(scanTwo.hasNext()){
            //AI Wrote the Spliter to remove commas in Quotations
            String[] line = scanTwo.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            if(!line[9].equals("") && !line[12].equals("")){
                healthToInfant[j] = new Data(Double.parseDouble(line[9]), Double.parseDouble(line[12]));
                j++;
            }
        }
        scanTwo.close();
        return healthToInfant;
    }
    public double getSpend(){
        return spend;
    }
    public double getInfantMortality(){
        return infantMortality;
    }
    public static double getTotalSpend(Data[] healthToInfant){
        double totalSpend = 0;
        for(int i = 0; i < healthToInfant.length; i++){
            totalSpend += healthToInfant[i].getSpend();
        }
        return totalSpend;
    }
    public static double getTotalInfantMortality(Data[] healthToInfant){
        double totalInfantMortality = 0;
        for(int i = 0; i < healthToInfant.length; i++){
            totalInfantMortality += healthToInfant[i].getInfantMortality();
        }
        return totalInfantMortality;
    }
    public static double getR(Data[] healthToInfant){
        double averageSpend = getTotalSpend(healthToInfant) / healthToInfant.length;
        double averageInfantMortality = getTotalInfantMortality(healthToInfant) / healthToInfant.length;
        double rTop = 0;
        double rBottomX = 0;
        double rBottomY = 0;
        for(int i = 0; i < healthToInfant.length; i++){
            rTop += ((healthToInfant[i].getSpend() - averageSpend) * (healthToInfant[i].getInfantMortality() - averageInfantMortality));
            rBottomX += Math.pow((healthToInfant[i].getSpend() - averageSpend), 2);
            rBottomY += Math.pow((healthToInfant[i].getInfantMortality() - averageInfantMortality), 2);
        }
        double r = (rTop / Math.sqrt(rBottomX * rBottomY));
        return r;
    }
    public static String toString(Data[] healthToInfant){
        double r = getR(healthToInfant);
        String returnString = "------------------------------------------------------------------------------------------------------------------------------------------------------\nThe R Value (Correlation Coefficient) For Your Array Is: " + r + "\n";
        if(r >= .7){
            returnString += "Data indicates a strong positive correlation: higher health expenditure as a percentage of GDP is associated with an increased infant mortality rate.";
        }
        else if(r >= .3){
            returnString += "Data indicates a moderate positive correlation: higher health expenditure as a percentage of GDP is associated with an increased infant mortality rate.";
        }
        else if(r >= 0){
            returnString += "Data indicates a weak positive correlation: higher health expenditure as a percentage of GDP is associated with an increased infant mortality rate.";
        }
        else if(r > -.3){
            returnString += "Data indicates a weak negative correlation: higher health expenditure as a percentage of GDP is associated with a decreased infant mortality rate.";
        }
        else if(r > -.7){
            returnString += "Data indicates a moderate negative correlation: higher health expenditure as a percentage of GDP is associated with a decreased infant mortality rate.";
        }
        else{
            returnString += "Data indicates a strong negative correlation: higher health expenditure as a percentage of GDP is associated with a decreased infant mortality rate.";
        }
        returnString += "\n------------------------------------------------------------------------------------------------------------------------------------------------------";
        return returnString;
    }
}
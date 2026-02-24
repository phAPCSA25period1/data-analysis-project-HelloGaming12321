import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("WorldIndicators2000.csv");
        Scanner scan = new Scanner(file);
        int i = 0;
        scan.nextLine();
        while(scan.hasNext()){
            String[] line = scan.nextLine().split(",");
            if(!line[9].equals("") && !line[12].equals("")){
            i++;
            }
        }
        scan.close();

        Data[] healthToInfant = new Data[i];

        Scanner scanTwo = new Scanner(file);
        int j = 0;
        scanTwo.nextLine();
        while(scanTwo.hasNext()){
            String[] line = scanTwo.nextLine().split(",");
            if(!line[9].equals("") && !line[12].equals("")){
                healthToInfant[j] = new Data(line[0], Double.parseDouble(line[9]), Double.parseDouble(line[12]));
                j++;
            }
        }
        scanTwo.close();
        double totalSpend = 0;
        for(int k = 0; k < i; k++){
            totalSpend += healthToInfant[k].getSpend();
        }
        double totalInfantMortality = 0;
        for(int k = 0; k < i; k++){
            totalInfantMortality += healthToInfant[k].getInfantMortality();
        }
        double averageSpend = totalSpend / i;
        double averageInfantMortality = totalInfantMortality / i;
        double rTop = 0;
        double rBottomX = 0;
        double rBottomY = 0;
        for(int k = 0; k < i; k++){
            rTop += ((healthToInfant[k].getSpend() - averageSpend) * (healthToInfant[k].getInfantMortality() - averageInfantMortality));
            rBottomX += Math.pow((healthToInfant[k].getSpend() - averageSpend), 2);
            rBottomY += Math.pow((healthToInfant[k].getInfantMortality() - averageInfantMortality), 2);
        }
        double r = (rTop / Math.sqrt(rBottomX * rBottomY));
        System.out.println(r);
    }
}
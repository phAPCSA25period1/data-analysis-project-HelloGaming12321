import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("WorldIndicators2000.csv");
        Data[] healthToInfant = Data.readFile(file);
        System.out.println(Data.toString(healthToInfant));
    }
}
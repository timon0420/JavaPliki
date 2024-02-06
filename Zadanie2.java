import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class Zadanie2 {
    public static void main(String[] args) throws FileNotFoundException{
        File plik = new File("src/plik.txt");
        Scanner odczyt = new Scanner(plik);
        String[] dane = odczytDanych(plik, odczyt);
        zmiannaCeny(dane);
        PrintWriter zapis = new PrintWriter("src/wynik1.txt");
        zapis(zapis, dane);
    }
    static String[] odczytDanych(File plik, Scanner odczyt){
        int l = (int) plik.length();
        String[] dane = new String[l];
        for (int i = 0; i < l; i++){
            try{
                dane[i] = odczyt.nextLine();
            } catch (NoSuchElementException e){
                break;
            }
        }
        return dane;
    }
    static void zmiannaCeny(String[] dane){
        for (int i = 0; i < dane.length; i++){
            if (dane[i] != null){
                String[] nowa;
                nowa = dane[i].split("-");
                if (Integer.parseInt(nowa[2]) == 1 && nowa[3].equals("Wies")){
                    nowa[1] = Double.toString(Double.parseDouble(nowa[1])-(Double.parseDouble(nowa[1])/10));
                    dane[i] = nowa[0]+"-"+nowa[1]+"-"+nowa[2]+"-"+nowa[3];
                }
                else {
                    dane[i] = null;
                }
            }
        }
    }
    static void zapis(PrintWriter zapis, String[] dane){
        for (String dana : dane){
            if (dana != null){
                zapis.print(dana + "\n");
            }
        }
        zapis.close();
    }
}

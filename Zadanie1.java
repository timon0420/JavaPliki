import java.io.File;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
public class Zadanie1 {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter("src/plik.txt");
        zapisLiczb(zapis);
        File plik = new File("src/plik.txt");
        Scanner odczyt = new Scanner(plik);
        String[] liczby = odczytLiczb(odczyt, plik);
        double srednia = obliczanieSredniej(liczby);
        PrintWriter wyniki1 = new PrintWriter("src/wynik1.txt");
        PrintWriter wyniki2 = new PrintWriter("src/wynik2.txt");
        zapisWynikow(wyniki1, wyniki2, srednia, liczby);
    }
    static void zapisLiczb(PrintWriter zapis){
        Random rand = new Random();
        for (int i = 0; i < 1000; i++){
            zapis.print(rand.nextInt()+"\n");
        }
        zapis.close();
    }
    static String[] odczytLiczb(Scanner odczyt, File plik){
        String[] liczby = new String[1000];
        int i = 0;
        while (i < plik.length()){
            try{
                liczby[i] = odczyt.nextLine();
                i++;
            } catch (NoSuchElementException e){
                break;
            }
        }
        return liczby;
    }
    static double obliczanieSredniej(String[] liczby){
        int suma = 0;
        for (String liczba : liczby){
            suma += Integer.parseInt(liczba);
        }
        return (double) suma /liczby.length;
    }
    static void zapisWynikow(PrintWriter wyniki1, PrintWriter wyniki2, double srednia, String[] liczby){
        for (String liczba : liczby){
            if (Integer.parseInt(liczba) < srednia){
                wyniki1.print(liczba + "\n");
            } else if (Integer.parseInt(liczba) > srednia) {
                wyniki2.print(liczba + "\n");
            }
        }
        wyniki1.close();
        wyniki2.close();
    }
}

import java.io.File;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Zadanie3 {
    public static void main(String[] args) throws FileNotFoundException{
        File plik = new File("src/plik.txt");
        Scanner odczyt = new Scanner(plik);
        String txt = txt(plik, odczyt);
        System.out.println(liczbaSlow(txt));
        System.out.println(sredniaDlugoscZdania(txt));;
    }
    static String txt(File plik, Scanner odczyt){
        String tekst = "";
        int i = 0;
        while (i < plik.length()){
            try{
                tekst += odczyt.nextLine() + " ";
                i++;
            } catch (NoSuchElementException e){
                break;
            }
        }
        return tekst;
    }
    static int liczbaSlow(String txt){
        return txt.split(" ").length;
    }
    static double sredniaDlugoscZdania(String txt){
        int liczbaSpacji = 0;
        int liczbaZnakow = 0;
        for (int i = 0; i < txt.length(); i++){
            if (txt.charAt(i) == '.'){
                liczbaSpacji += 1;
            } else if (txt.charAt(i) != ' ') {
                liczbaZnakow += 1;
            }
        }
        return (double) liczbaZnakow /liczbaSpacji;
    }
}

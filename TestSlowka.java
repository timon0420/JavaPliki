import java.io.*;
import java.util.*;
import javax.swing.*;
public class TestSlowka {
    public static void main(String[] args) throws FileNotFoundException {
        String[][] dane = odczyt("slowka");
        program(dane);

    }
    static String[][] odczyt(String nazwaPliku) throws FileNotFoundException {
        File plik = new File("src/"+nazwaPliku+".txt");
        Scanner odczyt = new Scanner(plik);
        String[][] dane = new String[2][(int)plik.length()];
        for (int i = 0; i < plik.length(); i++){
            try {
                String[] bufor = odczyt.nextLine().split("-");
                if (bufor[0] != null && !bufor[1].equals("null")){
                    dane[0][i] = bufor[0];
                    dane[1][i] = bufor[1];
                }
            } catch (NoSuchElementException e){
                break;
            }
        }
        return dane;
    }
    static int len(String[][] tab){
        int len = 0;
        for (int i = 0; i < tab[0].length; i++){
            if (tab[0][i] == null){
                break;
            }
            else len++;
        }
        return len;
    }
    static boolean sprawdzanieCzyElementJestWTablicy(int liczba, int[] tab){
        for (int j : tab) {
            if (liczba == j) return true;
        }
        return false;
    }
    static int[] losowaKolejnosc(int rozmiarPliku){
        Random rand = new Random();
        int[] tab = new int[rozmiarPliku];
        int i = 0;
        while (true){
            int liczbaRandomowa = rand.nextInt(0, tab.length);
            if (!sprawdzanieCzyElementJestWTablicy(liczbaRandomowa, tab)){
                tab[i] = liczbaRandomowa;
                i++;
                if (i == rozmiarPliku-1) return tab;
            }
        }
    }
    static String odpowiedz(String slowo){
        return JOptionPane.showInputDialog("Odpowiedź - "+slowo);
    }
    static void program(String[][] dane){
        Scanner scan = new Scanner(System.in);
        int len = len(dane);
        int blad = 0;
        int[] losowaKolejnosc = losowaKolejnosc(len);
        for (int i = 0; i < len; ){
            String odp = odpowiedz(dane[1][losowaKolejnosc[i]]);
            if (odp.equals(dane[0][losowaKolejnosc[i]].strip())){
                i++;
                JOptionPane.showMessageDialog(null,"Dobra odpowiedź");
            }
            else {
                blad++;
                JOptionPane.showMessageDialog(null,"Zła odpowiedź");
                if (blad == 2){
                    JOptionPane.showMessageDialog(null, "Poprawna odpowiedź - "+dane[0][losowaKolejnosc[i]]);
                    blad = 0;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Koniec");
    }
}

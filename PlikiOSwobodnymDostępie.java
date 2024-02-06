import java.io.*;
import java.util.*;
public class PlikiOSwobodnymDostępie {
    public static void main(String[] args) throws IOException {
        RandomAccessFile r;
        boolean q = true;
        Scanner scan = new Scanner(System.in);
        while (q){
            System.out.println("1.Zapis");
            System.out.println("2.Odczyt");
            System.out.println("3.Koniec");
            System.out.print("Wybór: ");
            switch (scan.nextInt()){
                case 1:
                    r = new RandomAccessFile("src/dane.dat", "rw");
                    zapisywanie(r);
                    break;
                case 2:
                    r = new RandomAccessFile("src/dane.dat", "rw");
                    odczytIZmianaCeny(r);
                    break;
                case 3:
                    q = false;
                    break;
                default:
                    System.out.println("BŁĄD");
                    break;
            }
        }
    }
    static void zapisywanie(RandomAccessFile r){
        try{
            Scanner scan = new Scanner(System.in);
            System.out.print("Nazwa domu: ");
            String nazwa = scan.nextLine();
            System.out.print("Liczba kondygnacji: ");
            int kondygnacje = scan.nextInt();
            System.out.print("Cena: ");
            double cena = scan.nextDouble();
            String lokalizacja = "";
            System.out.println("Lokalizacja: ");
            System.out.println("1.Miasto");
            System.out.println("2.Wieś");
            System.out.print("Lokalizacja: ");
            switch (scan.nextInt()){
                case 1:
                    lokalizacja = "miasto";
                    break;
                case 2:
                    lokalizacja = "wieś";
                    break;
                default:
                    lokalizacja = "null";
                    break;
            }
            r.seek(r.length());
            r.writeUTF(nazwa);
            r.writeInt(kondygnacje);
            r.writeDouble(cena);
            r.writeUTF(lokalizacja);
            r.close();
        } catch (IOException e){
            System.out.println("BŁĄD");
        }
    }
    static void odczytIZmianaCeny(RandomAccessFile r) throws IOException{
        while (r.getFilePointer() < r.length()){
            try {
                String nazwa = r.readUTF();
                int kondygnacje = r.readInt();
                int miejsceCeny = (int) r.getFilePointer();
                double cena = r.readDouble();
                String lokalizacja = r.readUTF();
                int koniec = (int) r.getFilePointer();
                System.out.println(nazwa + " " + kondygnacje + " " + cena + " " + lokalizacja);
                if (kondygnacje == 1 && lokalizacja.equals("wieś")){
                    cena = cena*0.9;
                    r.seek(miejsceCeny);
                    r.writeDouble(cena);
                }
                r.seek(koniec);
            } catch (IOException e){
                break;
            }
        }
        r.close();
    }
}

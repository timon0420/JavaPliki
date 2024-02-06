import java.io.*;
import java.util.ArrayList;


public class PlikiDanych {
    public static void main(String[] args){
        zapisywanie();
        System.out.println(przetwarzajPlik("src/dane.dat", "Piła"));;

    }
    static void zapisywanie() {
        try{
            FileOutputStream fos = new FileOutputStream("src/dane.dat");
            DataOutputStream dos = new DataOutputStream(fos);
            String[] nazwa = {"Piła", "Młotek", "Siekiera"};
            double[] cena = {56.99, 12.99, 78.99};
            int[] sztuki = {120, 83, 103};
            for (int i = 0; i < nazwa.length; i++){
                dos.writeUTF(nazwa[i]);
                dos.writeDouble(cena[i]);
                dos.writeInt(sztuki[i]);
            }
            fos.close();
            dos.close();
        } catch (IOException e){
            System.out.println("BŁĄD");
        }
    }
    static int przetwarzajPlik(String nazwaPliku, String nazwaTowaru){
        FileInputStream fis;
        DataInputStream dis;
        ArrayList<String> nazwa = new ArrayList<>();
        ArrayList<Double> cena = new ArrayList<>();
        ArrayList<Integer> sztuki = new ArrayList<>();
        //odczytywanie
        try{
            fis = new FileInputStream(nazwaPliku);
            dis = new DataInputStream(fis);
            while (true){
                try {
                    nazwa.add(dis.readUTF());
                    cena.add(dis.readDouble());
                    sztuki.add(dis.readInt());
                } catch (IOException e){
                    break;
                }
            }
            dis.close();
            fis.close();
        }catch (IOException x){
            System.out.println("Błąd");
        }
        //zmiana ceny
        for (int i = 0; i < sztuki.size(); i++){
            if (sztuki.get(i) >= 100){
                cena.set(i, cena.get(i)*0.9);
            }
        }
        for (int i = 0; i < nazwa.size(); i++){
            if (nazwa.get(i).equals(nazwaTowaru)){
                return sztuki.get(i);
            }
        }
        //zapisywanie
        FileOutputStream fos;
        DataOutputStream dos;
        try{
            fos = new FileOutputStream(nazwaPliku);
            dos = new DataOutputStream(fos);
            for (int i = 0; i < nazwa.size(); i++){
                dos.writeUTF(nazwa.get(i));
                dos.writeDouble(cena.get(i));
                dos.writeInt(sztuki.get(i));
            }
            dos.close();
            fos.close();
        } catch (IOException x){
            System.out.println("Błąd");
        }

        return 0;
    }
}
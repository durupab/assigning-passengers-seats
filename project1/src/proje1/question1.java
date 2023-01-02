
package proje1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class question1 {

    public static void main(String[] args) throws IOException {

        Random rnd =new Random();
        
        double [][] dm = new double [40][40];
        
        for (int i=0 ; i<40; i++){ //random degerlerle distance matrix olusturuldu.
            for(int j=0; j<40; j++ ) {
                if (j == i){
                    dm[i][j] =0;
                }
                else{
                    double distance = rnd.nextDouble() *10;
                    dm[i][j] = dm[j][i] = distance;
                }
            }
        }
        
        for (int i=0 ; i<40; i++){
        	System.out.println();   //distance matrix yazdýrýldý.
            for(int j=0; j<40; j++) {
                System.out.print(String.format("%,.2f", dm[i][j])+ " ");
            }
            
        }
        System.out.println();
        System.out.println(); // estetik amacli bosluk birakmak icin
        String [] names = new String[40];
        String dosya = "C:\\Users\\durup\\Desktop\\data\\project1\\names.txt";
        Scanner fileIn = null;
        
        try {
            fileIn = new Scanner(new FileInputStream(dosya));
        }
        catch(FileNotFoundException e) {
            System.out.println("Dosya okunamadý.");
            System.exit(0);
        }
        
        int c = 0;
        while (fileIn.hasNext()) { //names'e dosyadan okunarak oluþturuldu.
            String name = fileIn.nextLine();
            names[c]= name;
            c++;
        }
        
        boolean [] isSitting = new boolean[40]; //yolcu daha önceden yerleþtirilmiþ mi (default false)
        
        int ky [][] = new int [40][2];
        
        for(int i=0; i<40; i++){
            ky[i][0]= i+1;
        }
        
        double kd [][] = new double [40][2];
        int first = rnd.nextInt(40);
        ky[0][1] = first; //1.koltuða yolcu atama
        kd[0][1] = 0;
        isSitting[first] = true;
        System.out.print(first + names[first] + " ");
        
        int min_Y = -1;  //2.koltuða yolcu atama
        double minD = 10;
        for (int s=0; s<40; s++) {           
            if (!isSitting[s]) {
                double dist = dm[s][ky[0][1]];
                if (dist < minD) {
                    minD = dist;
                    min_Y = s;
                }      
            }
        }
        
        ky[1][1] = min_Y;
        kd[1][1] = minD;
        isSitting[min_Y] = true;
        System.out.print(min_Y + names[min_Y] + " ");
        
        min_Y = -1;  //3.koltuða yolcu atama
        minD = 10;
        for (int s=0; s<40; s++) {           
            if (!isSitting[s]) {
                double dist = dm[s][ky[1][1]];
                if (dist < minD) {
                    minD = dist;
                    min_Y = s;
                }      
            }
        }
        ky[2][1] = min_Y;
        kd[2][1] = minD;
        isSitting[min_Y] = true;
        System.out.print(min_Y + names[min_Y] + " ");
        
        min_Y = -1;  //4.koltuða yolcu atama
        minD = 10;
        for (int s=0; s<40; s++) {           
            if (!isSitting[s]) {
                double dist = dm[s][ky[2][1]];
                if (dist < minD) {
                    minD = dist;
                    min_Y = s;
                }      
            }
        }
        ky[3][1] = min_Y;
        kd[3][1] = minD;
        isSitting[min_Y] = true;
        System.out.print(min_Y + names[min_Y] + " ");
        
        
        for (int i=5; i<41; i++) {
            
            if (i%4 == 1) {  
                double min = 20;
                int minY = -1;                
                for (int s=0; s<40; s++) {
                    if (!isSitting[s]) {
                        double dist = dm[s][ky[i-5][1]] + dm[s][ky[i-4][1]];
                        if (dist < min) {
                            min = dist;
                            minY = s;
                        }      
                    }
                }
                ky[i-1][1] = minY;
                kd[i-1][1] = min;
                isSitting[minY] = true;
                System.out.println();
                System.out.print(minY + names[minY] + " ");
            }
            
            else if (i%4 == 2 || i%4 == 3) {
                double min = 40;
                int minY = -1;               
                for (int s=0; s<40; s++) {
                    if (!isSitting[s]) {
                        double dist = dm[s][ky[i-6][1]] + dm[s][ky[i-5][1]] + dm[s][ky[i-4][1]] + dm[s][ky[i-2][1]];
                        if (dist < min) {
                            min = dist;
                            minY = s;
                        }  
                    }      
                }
                ky[i-1][1] = minY;
                kd[i-1][1] = min;
                isSitting[minY] = true;
                System.out.print(minY + names[minY] + " ");
            }
            
            else { //(i%4 == 0) 
                double min = 30;
                int minY = -1;               
                for (int s=0; s<40; s++) {
                    if (!isSitting[s]) {
                        double dist = dm[s][ky[i-6][1]] + dm[s][ky[i-5][1]] + dm[s][ky[i-2][1]];
                        if (dist < min) {
                            min = dist;
                            minY = s;
                        }  
                    }      
                }
                ky[i-1][1] = minY;
                kd[i-1][1] = min;
                isSitting[minY] = true;
                
                System.out.print(minY + names[minY] + " ");
            }
            
        }
        
        double totalDistance = 0;
        System.out.println();
        for (int i=0; i<40; i++){
            totalDistance += kd[i][1];
            if (i%4==0){
                System.out.println();
            }
            System.out.print(String.format("%,.2f", kd[i][1]) + " ");
        }
        
        System.out.println();
        System.out.println();
        System.out.println(String.format("%,.2f", totalDistance));
        
    }

}

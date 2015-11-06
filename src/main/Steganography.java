package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by viniciuslm on 05/11/15.
 */
public class Steganography {

    public static void main(String[] agrs) {
        System.out.println("Please, insert the image path: ");
        Scanner scan = new Scanner(System.in);
        String imagePath = scan.nextLine();
        try {
            byte[] data = Files.readAllBytes(Paths.get(imagePath));
            String newByte = "";
            List<String> newBinaries = new ArrayList<>();
            int count = 0;
            for (int i = 54; i < data.length; i++) {
                if (count == 8) {
                    newBinaries.add(newByte);
                    newByte = "";
                    count = 0;
                }
                newByte = Integer.toBinaryString(data[i] & 255 | 256).substring(1).charAt(7) + newByte;
                count++;
            }
            for(int i = 0 ; i< newBinaries.size() ; i++){
                System.out.print((char) Integer.parseInt(newBinaries.get(i), 2));
            }
        } catch (IOException e) {
            System.out.println("Error. Image can't be loaded.");
        }
    }

}

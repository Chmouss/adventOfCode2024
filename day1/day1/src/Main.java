import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int totalDistance = 0;

        //creating lists that will contain all the input numbers
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        //read the txt file
        try{
        File file = new File("./src/inputs.txt");
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()){
            String data = fileReader.nextLine();
            String[] parts = data.split(" {3}"); //split each line into substrings where there are 3 spaces
            list1.add(Integer.parseInt(parts[0])); // add each element
            list2.add(Integer.parseInt(parts[1]));
        }
        } catch (FileNotFoundException e){
            System.out.println("error while reading the file");
            e.printStackTrace();
        }



        //**********  PART 1  **********//


        //sorting both lists to have the int in the correct order
        Collections.sort(list1);
        Collections.sort(list2);

        System.out.println("base total distance == " + totalDistance);

        //iterate on both lists to give the distance between each number (ex 5 and 6 distance == 1)
        System.out.println("list 1 : ");
        for (int i = 0; i < list1.size(); i++){
            if(list1.get(i) >= list2.get(i)){ // if list 1 element is greater or equals to list 2 elem
                totalDistance += (list1.get(i) - list2.get(i));
                System.out.println(list1.get(i) + "-" + list2.get(i) + "=" + (list1.get(i) - list2.get(i)));
            } else {
                totalDistance += (list2.get(i) - list1.get(i));
                System.out.println(list2.get(i) + "-" + list1.get(i) + "=" + (list2.get(i) - list1.get(i)));
            }
            System.out.println("current total distance" + totalDistance);
        }

        System.out.println("total max distance == " + totalDistance);



        //**********  PART 2  **********//

        int similarityScore = 0;
/*
        //code pas du tout optimisé
        for(int i = 0; i < list1.size(); i++){
            int nbTimesMultiply = 0;
            for(int j = 0; j < list2.size(); j++){
                if(list1.get(i).equals(list2.get(j)))
                    nbTimesMultiply++;
            }
            similarityScore += (list1.get(i) * nbTimesMultiply);
        }
        */

        // pour faire plus optimisé, faire map occurences sur list2, key == nombre et value == nombre d'occurences
        // comme ça pas besoin de parcourir autant de fois la list2 ....

        HashMap<Integer, Integer> mapOccurences = new HashMap<>();
        for (int i = 0; i < list2.size(); i++){
            if(mapOccurences.containsKey(list2.get(i))){
                mapOccurences.put(list2.get(i), mapOccurences.get(list2.get(i)) +1);
            } else {
                mapOccurences.put(list2.get(i), 1);
            }
        }

        for(Integer integer : mapOccurences.keySet()){
            System.out.println(integer);
        }

        for (int i = 0; i < list1.size(); i++){
            if(mapOccurences.containsKey(list1.get(i)))
                similarityScore += (list1.get(i) * mapOccurences.get(list1.get(i)));
        }

        System.out.println("similarityScore == " + similarityScore);

    }
}

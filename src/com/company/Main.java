/* Classname: Main

  @version 23.06.2020
  @author Kryukov Kirilo

  Module 4 task Final
  1. GLOSSARY - 10 points

  1.1. Download a text about Harry Potter.

  1.2. For each distinct word in the text calculate the number of occurrence.

  1.3. Use RegEx..

  1.4. Sort in the DESC mode by the number of occurrence..

  1.5. Find  the first 20 pairs.

  1.6  Find all the proper names

  1.7.  Count them and arrange in alphabetic order.

  1.8.   First 20 pairs and names write into to a file test.txt

  1.9.  Create a fine header for the file

  1.10  Use Java  Collections to demonstrate your experience (Map, List
*/

package com.company;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main (String[] args) throws IOException {
        String harry = new String(Files.readAllBytes(Paths
                .get("C:\\Users\\Kirill\\Desktop\\harry.txt")));
        //_______________________________________________________________________________

        //1.3. Use RegEx..
        Map<String, Integer> distinct = new HashMap<>();         // Map initialization
        String harryCleaned = harry.replaceAll("[^a-zA-Z0-9' ]", "");
        String[] harryWords = harryCleaned.split(" ");
        //_______________________________________________________________________________

        // 1.2. For each distinct word in the text calculate the number of occurrence.
        for (int i = 0; i < harryWords.length; i++) {
            if(!distinct.containsKey(harryWords[i])){
                distinct.put(harryWords[i],1);
            }else{
                Integer newValue = distinct.get(harryWords[i]);
                distinct.put(harryWords[i], newValue+1);
            }
        }
        distinct.forEach((el,a)->{
            System.out.println(el + " - " + a);
        });
        //_______________________________________________________________________________

        //1.4. Sort in the DESC mode by the number of occurrence..
        Map<String , Integer> sortedList = distinct.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        //print sorted list of all words
        sortedList.forEach((key,value)->{
            System.out.println(key + " - " + value);
        });
        //________________________________________________________________________________

        //1.5. Find  the first 20 pairs.
        List<String> keys = new ArrayList<>();
        Map<String, Integer> top20Words = new LinkedHashMap<>();
        sortedList.keySet().stream().forEach(key-> keys.add(key));
        for (int i = 0; i < 20; i++) {
            top20Words.put(keys.get(i), sortedList.get(keys.get(i)));
        }
        // define a path where first 20 pairs will be written
        Path path = Paths.get("C:\\Users\\Kirill\\Desktop\\harry.txt");
        //________________________________________________________________________________
        //print sorted list of all words
        top20Words.forEach((key,value)->{
            System.out.println(key + " - " + value);
        });
        //________________________________________________________________________________
        //1.6. Find all the proper names
        List<String> names = new ArrayList<>();

        Pattern p = Pattern.compile("\\b[A-Z][a-z]{3,}\\b");
        Matcher m = p.matcher(harry);
        while (m.find())
        {
            String word = m.group();
            names.add(word);
        }
        //________________________________________________________________________________
        //1.7.  Count them and arrange in alphabetic order.
        Collections.sort(names); // sort properNames in alphabetic order
        int properNamesAmount = names.size(); // count proper names
        System.out.println("Proper names amount: " + properNamesAmount);

        //________________________________________________________________________________
        // 1.8. First 20 pairs and names write into to a file test.txt
        // writing first 20 names to the file test.txt
        for (int i = 0; i < 20; i++)
        {
            // printing the result
            System.out.println(names.get(i));
            // write proper name to the file
            Files.write(path, (names.get(i) + "\n").getBytes(), StandardOpenOption.APPEND);


        }
    }

    }








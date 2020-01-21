package com.example.enkripsisms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class Decryption {

    public static String sortString(String inputString){
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);

    }

    public static int [] getIntKeys(String key, String sortedKey){
        //abersz
        //ascii code
        int[] result = new int[key.length()];
        for(int i=0; i<key.length(); i++){
            // ZEBRAS
            char charAt = key.charAt(i);
            int indexOf = sortedKey.indexOf(charAt);
            result[i] = indexOf;
        }

        return result;
    }


    public static String decryptColumnarTransposition (String key, String message) throws Exception{
        //1 validasi & get key
        String sortedKey = sortString(key);
        int[] keyArray = getIntKeys(key, sortedKey);


        //validasi message
        if (message.length() < key.length()){
            throw new Exception("KEY lebih besar dari MESSAGE");
        }

        int charToRemove = message.length() % key.length();
        message = message.substring(0, message.length() - charToRemove);

        int numberOfElementInList = message.length() / key.length();

        List<List> listGroup = new ArrayList<>();
        for(int i=0; i<key.length(); i++){
            List<Character> list = new ArrayList<>();
            listGroup.add(list);
        }

        for (int i = 0; i < message.length(); i++) {
            int listIndex = i / numberOfElementInList;
            listGroup.get(listIndex).add(message.charAt(i));
        }

        for (int i = 0; i < listGroup.size(); i++) {
            System.out.println(listGroup.get(i));
        }


        int[] keyIndex = getIntKeys(key, sortedKey);



        //populate list with message
        for(int i=0; i<message.length(); i++) {
            char charAt = message.charAt(i);

            int keyMod = i % key.length();
            int keyListGroup = keyArray[keyMod];

            listGroup.get(keyListGroup).add(charAt);
        }


        // recreate the message
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfElementInList; i++) {
            for (int j = 0; j < key.length(); j++) {
                int listIndex = keyIndex[j];
                Character character = (Character) listGroup.get(listIndex).get(i);
                sb.append(character);
            }
        }

        return sb.toString();
    }


}

package com.example.enkripsisms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encryption {

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


    public static String columnarTransposition (String key, String message) throws Exception{
        //1 validasi & get key
        String sortedKey = sortString(key);
        int[] keyArray = getIntKeys(key, sortedKey);

        System.out.println("keyArray=");
        for(int i=0; i<keyArray.length; i++){
            System.out.println(keyArray[i]);
        }

        //validasi message
        if (message.length() < key.length()){
            throw new Exception("KEY lebih besar dari MESSAGE");
        }
        int filler = 0;
        if (message.length() % key.length() > 0) {
            filler = key.length() - (message.length() % key.length());
        }
        for (int i=0; i<filler; i++){
            message += "X";
        }

        // 3 create list of list to process the message
        List<List> listGroup = new ArrayList<>();
        for(int i=0; i<key.length(); i++){
            List<Character> list = new ArrayList<>();
            listGroup.add(list);
        }

        //populate list with message
        for(int i=0; i<message.length(); i++) {
            char charAt = message.charAt(i);

            int keyMod = i % key.length();
            int keyListGroup = keyArray[keyMod];

            listGroup.get(keyListGroup).add(charAt);
        }

        for(int i=0; i<listGroup.size(); i++) {
            System.out.println(listGroup.get(i));
        }

        // recreate the message
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<listGroup.size(); i++){
            List<Character> chars = listGroup.get(i);
            for (int j=0; j<chars.size(); j++){
                sb.append(chars.get(j));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String key1 = "ZEBRAS";
        String key2 = "KOMA";
        String message = "WEAREDISCOVEREDFLEEATONCE";
        String singleColumnarTransposition = columnarTransposition(key1, message);
        System.out.println(singleColumnarTransposition);

        String doubleColumnarTransposition = columnarTransposition(key2, singleColumnarTransposition);
        System.out.println(doubleColumnarTransposition);
    }


}

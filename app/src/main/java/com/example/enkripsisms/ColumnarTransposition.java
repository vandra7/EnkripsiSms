package com.example.enkripsisms;
import java.util.*;

public class ColumnarTransposition {
    private String sortString(String inputString) {
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    private int[] getKeyIndex(String key, String sortedKey) {
        //abersz
        //ascii code
        int[] result = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            // ZEBRAS
            char charAt = key.charAt(i);
            int indexOf = sortedKey.indexOf(charAt);
            result[i] = indexOf;
        }

        return result;
    }

    private void validateKeyAndMessage(String key, String message) throws Exception {
        // message size >= key size
        if (message.length() < key.length()) {
            throw new Exception("message length is less than key length");
        }

        // key should not contain the same letter
        Set<Character> keySet = new HashSet<>();
        for (int i = 0; i < key.length(); i++) {
            keySet.add(key.charAt(i));
        }
        if (keySet.size() != key.length()) {
            throw new Exception("key contains the same letter");
        }
    }

    public String encryptWithColumnarTransposition(String key, String message) throws Exception {
        // fill the end of message with character X if message length is not factor of key length (message.length % key.length != 0)
        // the resulting message size will be factor of key length
        int filler = 0;
        if (message.length() % key.length() > 0) {
            filler = key.length() - (message.length() % key.length());
        }
        for (int i = 0; i < filler; i++) {
            message += "X";
        }
        System.out.println(message);

        // validate input parameters
        validateKeyAndMessage(key, message);

        // Get key index
        // example: from "ZEBRAS" to [5,2,1,3,0,4]
        String sortedKey = sortString(key);
        int[] keyIndex = getKeyIndex(key, sortedKey);
//        System.out.println("keyArray=");
//        for (int i = 0; i < keyArray.length; i++) {
//            System.out.println(keyArray[i]);
//        }

        // create list of list to collect the message
        List<List> listGroup = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            List<Character> list = new ArrayList<>();
            listGroup.add(list);
        }

        // populate list with message based on keyIndex
        for (int i = 0; i < message.length(); i++) {
            char charAt = message.charAt(i);

            int keyMod = i % key.length();
            int keyListGroup = keyIndex[keyMod];

            listGroup.get(keyListGroup).add(charAt);
        }

        for (int i = 0; i < listGroup.size(); i++) {
            System.out.println(listGroup.get(i));
        }

        // recreate the message
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listGroup.size(); i++) {
            List<Character> chars = listGroup.get(i);
            for (int j = 0; j < chars.size(); j++) {
                sb.append(chars.get(j));
            }
        }

        return sb.toString();
    }

    public String decryptWithColumnarTransposition(String key, String message) throws Exception {
        // validate input parameters
        validateKeyAndMessage(key, message);

        // remove x character fillers in message
        // x is computed by message.length % key.length
        int charToRemove = message.length() % key.length();
        message = message.substring(0, message.length() - charToRemove);
        System.out.println(message);

        // EVLNXACDTXESEAXROFOXDEECXWIREE -> WEAREDISCOVEREDFLEEATONCE
        //        [E, V, L, N, X]
        //        [A, C, D, T, X]
        //        [E, S, E, A, X]
        //        [R, O, F, O, X]
        //        [D, E, E, C, X]
        //        [W, I, R, E, E]
        int numberOfElementInList = message.length() / key.length();

        // create list of list to collect the message
        List<List<Character>> listGroup = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            List<Character> list = new ArrayList<>();
            listGroup.add(list);
        }

        // populate list with message
        // split every numberOfElementInList characters into a list
        for (int i = 0; i < message.length(); i++) {
            int listIndex = i / numberOfElementInList;
            listGroup.get(listIndex).add(message.charAt(i));
        }

        for (int i = 0; i < listGroup.size(); i++) {
            System.out.println(listGroup.get(i));
        }

        // Get key index
        // example: from "ZEBRAS" to [5,2,1,3,0,4]
        String sortedKey = sortString(key);
        int[] keyIndex = getKeyIndex(key, sortedKey);

        // recreate the message based on keyIndex
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfElementInList; i++) {
            for (int j = 0; j < key.length(); j++) {
                int listIndex = keyIndex[j];
                Character character = listGroup.get(listIndex).get(i);
                sb.append(character);
            }
        }

        return sb.toString();
    }


}

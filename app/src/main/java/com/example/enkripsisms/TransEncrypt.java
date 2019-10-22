package com.example.enkripsisms;

public final class TransEncrypt {

    /**
     * @author Alfa Khrisna
     * Encrypts a message with a double transposition cypher
     * @param msg
     * @param keyword
     * @return the encrypted message
     */
    public static String encrypt(String msg, String keyword) {
        // Get cypher length
        int c_length = keyword.length();

        // Get cypher order
        keyword = keyword.toUpperCase();
        int[] p_tab = new int[c_length];
        for(int i = 0; i < c_length; i++) {
            p_tab[i] = (int)keyword.charAt(i);
        }

        // Construction of string
        StringBuilder sb = new StringBuilder();
        char[] c_word = new char[c_length];
        msg = msg.replaceAll(" ", "");
        msg.toUpperCase();
        int pointer = 0;
        int min = 0;
        // TODO

        // Dummy return
        return null;
    }

    /**
     * @author Alfa Khrisna
     * Decrypts a double transposition cypher
     * @param msg
     * @param keyword
     * @return the decrypted cypher
     */
    public static String decrypt(String msg, String keyword) {
        return null;
    }
}

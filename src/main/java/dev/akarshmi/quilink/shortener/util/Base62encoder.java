package dev.akarshmi.quilink.shortener.util;


public class Base62encoder {
    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String encode(long value){
        StringBuilder sb = new StringBuilder();
        while (value > 0){
            sb.append(CHARS.charAt((int)(value % CHARS.length())));
            value /= CHARS.length();
        }
        return sb.reverse().toString();
    }

}

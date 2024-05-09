package com.kegmil.mvista;

public class URLGenerator {
  public static String generateSecureURL(String baseURL) {
    String token = "5c8d56a2-88b2-48a7-aec8-c9a8ac7af99a";
    return baseURL + "?token=" + token;
  }
}

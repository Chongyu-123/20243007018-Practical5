package com.example.practicalfive;

public class TextProcessor {
    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }
        String input = municipality.trim();
        String[] supported = {"Beijing", "Shanghai", "Chongqing", "Tianjin"};
        for (String name : supported) {
            if (name.equalsIgnoreCase(input)) {
                return name;
            }
        }
        return null;
    }
}

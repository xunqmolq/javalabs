package com.example.demo.controller;

public class LangUtil {
    private final String lang;

    public LangUtil(String lang) {
        if (!(lang.equals("ukr") || lang.equals("eng")))
            throw new IllegalArgumentException("Please choose eng or ukr / Будь ласка виберіть eng чи ukr");
        this.lang = lang;
    }

    public String getAlgoName() {
        return switch (lang) {
            case "ukr" -> "метод Сімпсона";
            case "eng" -> "Simpson's rule";
            default -> throw new IllegalArgumentException("Please choose eng or ukr / Будь ласка виберіть eng чи ukr");
        };
    }

    public String getLang() {
        return lang;
    }
}

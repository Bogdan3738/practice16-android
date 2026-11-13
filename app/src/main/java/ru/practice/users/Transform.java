package ru.practice.users;

/** Безопасное преобразование возраста: ошибочный ввод сохраняет прежнее значение. */
public final class Transform {
    private Transform() { }
    public static int parseIntOrDefault(String whatParse, int defaultValue) {
        try {
            return Integer.parseInt(whatParse == null ? "" : whatParse.trim());
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }
}

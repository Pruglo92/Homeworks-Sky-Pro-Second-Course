import java.util.Objects;
import java.util.Random;

public class Main {

    private static final String ALL_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_";

    public static void main(String[] args) {
        System.out.println(check("java_123_Java", "D_1hWiKjjP_9", "D_1hWiKjjP_9"));
    }

    public static boolean check(String login, String password, String confirmPassword) {
        try {
            validate(login, password, confirmPassword);
            System.out.println("Валидация данных успешно пройдена.");
            return true;
        } catch (WrongPasswordException | WrongLoginException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void validate(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {
        Random random = new Random();
        if (Objects.isNull(login) || login.length() > 20) {
            throw new WrongLoginException("Длина логина должна быть до 20-ти символов включительно.");
        }
        if (Objects.isNull(password) || password.length() > 19) {
            throw new WrongPasswordException("Длина пароля должна быть до 19 символов включительно.");
        }
        if (!Objects.equals(password, confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают.");
        }
        if (!(random.nextBoolean() ? checkStringByIterating(login) : checkStringWithRegex(login))) {
            throw new WrongLoginException("Логин содержит некорректные символы.");
        }
        if (!(random.nextBoolean() ? checkStringByIterating(password) : checkStringWithRegex(password))) {
            throw new WrongPasswordException("Пароль содержит некорректные символы.");
        }
    }


    //можем проверить посимвольно
    public static boolean checkStringByIterating(String string) {
        String[] strings = string.split("");
        for (String s : strings) {
            if (!ALL_CHARACTERS.contains(s)) {
                return false;
            }
        }
        return true;
    }

    //а можем с помощью регулярных выражений
    public static boolean checkStringWithRegex(String string) {
        return string.matches("^\\w+$");
    }
}
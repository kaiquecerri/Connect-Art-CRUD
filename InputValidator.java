import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.util.regex.Matcher;

public class InputValidator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String emailValidation(String oldEmail) {
        while (true) {
            String email = JOptionPane.showInputDialog("Digite seu email:", oldEmail);

            if (email == null) {
                Main.mainMenu();
                return null;
            }

            email = email.trim().replaceAll(",", "").toLowerCase();

            if(Main.emailExits(email) && !email.equals(oldEmail)) {
                JOptionPane.showMessageDialog(null, "Esse email já foi utilizado anteriormente.");
                continue;
            }

            if (validarEmail(email)) {
                return email;
            }

            JOptionPane.showMessageDialog(null, "Email inválido.");
        }
    }

    public static String emailValidation() {
        while (true) {
            String email = JOptionPane.showInputDialog("Digite seu email:");

            if (email == null) {
                Main.mainMenu();
                return null;
            }

            email = email.trim().replaceAll(",", "").toLowerCase();

            if(Main.emailExits(email)) {
                JOptionPane.showMessageDialog(null, "Esse email já foi utilizado anteriormente.");
                continue;
            }

            if (validarEmail(email)) {
                return email;
            }

            JOptionPane.showMessageDialog(null, "Email inválido.");
        }
    }

    public static String inputValidation(String message, String errorMessage, int minLength, String oldValue) {
        while (true) {
            String input = JOptionPane.showInputDialog(message, oldValue);

            if (input == null) {
                Main.mainMenu();
                return null;
            }

            input = input.trim().replaceAll(",", "");

            if (input.length() >= minLength) {
                return input;
            }

            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }

    public static String inputValidation(String message, String errorMessage, int minLength) {
    while (true) {
        String input = JOptionPane.showInputDialog(message);

        if (input == null) {
            Main.mainMenu();
            return null;
        }

        input = input.trim().replaceAll(",", "");

        if (input.length() >= minLength) {
            return input;
        }

        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
}


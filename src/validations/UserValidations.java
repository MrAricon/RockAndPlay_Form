
package validations;

/**
 *
 * @author polmi
 */
public class UserValidations {
    public static boolean checkName(String name) {
        if (name.length() < 1 || name.length() > 32) {
            System.out.println("Debe estar entre 1 y 32 caracteres");
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i))) {
                System.out.println("En este campo solo se pueden introducir letras");
                return false;
            }
        }

        System.out.println("Valor correcto");
        return true;
    }
    
    public static boolean checkSurname(String surname) {
        if (surname.length() < 1 || surname.length() > 32) {
            System.out.println("Debe estar entre 1 y 32 caracteres");
            return false;
        }

        for (int i = 0; i < surname.length(); i++) {
            if (!Character.isLetter(surname.charAt(i)) && surname.charAt(i) != ' ') {
                System.out.println("En este campo solo se pueden introducir letras y espacios");
                return false;
            }
        }

        System.out.println("Valor correcto");
        return true;
    }
    
    public static boolean checkUsername(String username) {
        if (username.length() < 1 || username.length() > 32) {
            System.out.println("Debe estar entre 1 y 32 caracteres");
            return false;
        }
        
        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                System.out.println("En este campo solo se pueden introducir letras y numeros");
                return false;
            }
        }
        
        System.out.println("Valor correcto");
        return true;
    }
    
    public static boolean checkEmail(String email) {
        if (email.length() < 2 || email.length() > 80) {
            return false;
        }
        int atIndex = email.indexOf('@');
        if (atIndex != -1 && atIndex < email.length() - 1) {
            int dotIndex = email.indexOf('.', atIndex);
            if (dotIndex != -1 && dotIndex < email.length() - 1) {
                int lastDotIndex = email.lastIndexOf('.');
                String domain = email.substring(lastDotIndex + 1);
                return domain.equals("com") || domain.equals("es") || domain.equals("net") 
                || domain.equals("cat") || domain.equals("edu") || domain.equals("co");
            }
        }
        return false;
    }
    
    public static boolean checkPassword(String password) {
        if (password.length() < 8 || password.length() > 32) {
            System.out.println("Debe estar entre 8 y 32 caracteres");
            return false;
        }
        System.out.println("Valor correcto");
        return true;
    }
    
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                System.out.println("En este campo solo se pueden introducir numeros");
                return false;
            }
        }
        System.out.println("Valor correcto");
        return true;
    }
    
    public static boolean verificarEntradas(String str) {
        if (str.length() < 1 || str.length() > 4) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                System.out.println("En este campo solo se pueden introducir numeros");
                return false;
            }
        }
        System.out.println("Valor correcto");
        return true;
    }
}



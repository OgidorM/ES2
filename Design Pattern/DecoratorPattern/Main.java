import com.es2.decorator.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Plain Auth ===");
        AuthInterface plainAuth = new Auth();
        try {
            plainAuth.auth("admin", "admin");
            System.out.println("Authentication successful!");
        } catch (AuthException | IOException e) {
            System.out.println("Authentication failed!");
        }

        System.out.println("\n=== Auth + Logging ===");
        AuthInterface loggedAuth = new Logging(new Auth());
        try {
            loggedAuth.auth("admin", "admin");
            System.out.println("Authentication successful!");
        } catch (AuthException | IOException e) {
            System.out.println("Authentication failed!");
        }

        System.out.println("\nAuth + CommonWordsValidator + Logging");
        AuthInterface fullyDecorated = new Logging(
                new CommonWordsValidator(
                        new Auth()));
        try {
            fullyDecorated.auth("admin", "admin");
            System.out.println("Authentication successful!");
        } catch (AuthException | IOException e) {
            System.out.println("Authentication failed!");
        }
    }
}
package testing;
import java.util.regex.*;

public class EmailValidator {

    // Regex pattern (case-insensitive)
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z][\\w]{5,9}@(umassd\\.edu|gmail\\.com)$";

    // pattern with CASE_INSENSITIVE flag
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    // validate full email address
    public static boolean validateEmailAddress(String emailAddress) {
        if (emailAddress == null) return false;
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    // local part (before @)
    public static String getLocalPart(String emailAddress) {
        if (validateEmailAddress(emailAddress)) {
            return emailAddress.split("@")[0];
        }
        return null;
    }

    // check umassd.edu account
    public static boolean isUmassAccount(String emailAddress) {
        if (!validateEmailAddress(emailAddress)) return false;
        return emailAddress.toLowerCase().endsWith("@umassd.edu");
    }

    // check gmail.com account
    public static boolean isGmailAccount(String emailAddress) {
        if (!validateEmailAddress(emailAddress)) return false;
        return emailAddress.toLowerCase().endsWith("@gmail.com");
    }

    // test cases
    public static void main(String[] args) {
        String[] testEmails = {
                "Alice123@umassd.edu",   // valid umassd email
                "bob_98@gmail.com",      // valid gmail email
                "a12345@gmail.com",      // invalid - local part too short (<5)
                "LongUserName1@umassd.edu", // invalid - too long (>10)
                "1startWithDigit@gmail.com", // invalid - starts with number
                "ValidUser@Yahoo.com"    // invalid - wrong email domain
        };

        for (int i = 0; i < testEmails.length; i++) {
            String email = testEmails[i];
            
            System.out.println("Testing: " + email);
            System.out.println("Valid? " + validateEmailAddress(email));
            System.out.println("Local Part: " + getLocalPart(email));
            System.out.println("Is Umass? " + isUmassAccount(email));
            System.out.println("Is Gmail? " + isGmailAccount(email));
            System.out.println("---------------------------");
        }
    }
}

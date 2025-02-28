// voting App Exception
class InvalidAgeException extends Exception { // checked
    public InvalidAgeException(String err) {
        super(err);
    }
}

public class VotingApp {
    static void validate(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Under age");
        } else {
            System.out.println("Welcome to  Vote");
        }
    }

    public static void main(String[] args) {
        try {
            validate(23);
            validate(16);
        } catch (Exception e) {

            System.out.println(e);
        }
        finally {
            System.out.println("Voting is a must!");
        }
    }

}
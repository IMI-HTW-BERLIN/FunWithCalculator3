package postfix;

public class MalformedInfixExpressionException extends Exception {
    MalformedInfixExpressionException() {

    }

    MalformedInfixExpressionException(String message) {
        super(message);
    }
}

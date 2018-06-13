package postfix;

public class MalformedPostfixExpressionException extends Exception {
    MalformedPostfixExpressionException() {

    }

    MalformedPostfixExpressionException(String message) {
        super(message);
    }
}

package decisionrules.exceptions;

public class DecisionRulesErrorException extends RuntimeException {
    public String cause;

    public DecisionRulesErrorException(String message, String cause) {
        super(message);
        this.cause = cause;
    }
}

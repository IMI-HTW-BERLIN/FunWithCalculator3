import set.Set;

public class CalcEngineSet extends CalcEnginePostfix {

    private char operator;
    private Set firstOperand;
    private Set secondOperand;
    private Set result;

    public CalcEngineSet() {
        super();
        result = null;
    }

    void setFirstOperand(Set operand) {
        firstOperand = operand;
    }

    void setSecondOperand(Set operand) {
        secondOperand = operand;
    }

    void setOperator(char operator) {
        this.operator = operator;
    }

    @Override
    void equals() {
        switch (operator) {
            case '+':
                result = firstOperand.combine(secondOperand);
                break;
            case '-':
                result = firstOperand.subtract(secondOperand);
                break;
            case '∩':
                result = firstOperand.intersect(secondOperand);
                break;
            default:
                System.err.println("Unknown operator!");
                break;
        }
    }

    Set getResult() {
        return result;
    }
}

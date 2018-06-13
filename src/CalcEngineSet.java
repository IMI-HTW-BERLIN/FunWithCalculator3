import set.Set;

public class CalcEngineSet extends CalcEnginePostfix {

    public CalcEngineSet() {
        super();
    }

    private char operator;
    private Set firstOperand;
    private Set secondOperand;

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
                firstOperand.combine(secondOperand);
                break;
            case '-':
                firstOperand.subtract(secondOperand);
                break;
            case '∩':
                firstOperand.intersect(secondOperand);
                break;
            default:
                System.err.println("Unknown operator!");
                break;
        }
    }
}

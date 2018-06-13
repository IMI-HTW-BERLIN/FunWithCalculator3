public class CalcEngineSet extends CalcEnginePostfix {

    public CalcEngineSet() {
        super();
    }

    private char operator;
    private

    void setOperator(char operator) {
        this.operator = operator;
    }

    @Override
    void equals() {
        switch (operator) {
            case '+':
                break;
            case '-':
                break;
            case '∩':
                break;
            default:
                System.err.println("Unknown operator!");
                break;
        }
    }
}

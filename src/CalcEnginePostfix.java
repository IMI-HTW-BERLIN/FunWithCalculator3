import postfix.MalformedInfixExpressionException;
import postfix.MalformedPostfixExpressionException;
import postfix.Postfix;

class CalcEnginePostfix {
    private StringBuilder sb;
    private Postfix pf;

    CalcEnginePostfix() {
        sb = new StringBuilder();
        pf = new Postfix();
    }

    void buttonPressed(String button) {
        sb.append(button);
    }

    void equals () {
        String pfx = null;
        try {
            pfx = pf.infixToPostfix(getDisplay());
        } catch (MalformedInfixExpressionException e) {
            clear();
            sb.append("error");

        }
        clear();
        try {
            sb.append(pf.evaluate(pfx));
        } catch (MalformedPostfixExpressionException e) {
            clear();
            sb.append("error");
        }
    }

    void clear () {
        sb.delete(0, sb.length());
    }

    String getDisplay () {
        return sb.toString();
    }

    boolean hexMode(boolean enabled) {
        boolean wasRounded = false;

        pf.hexMode(enabled);

        String displayValue = sb.toString();
        clear();

        if (!displayValue.equals("")) {
            if (enabled) {

                if (displayValue.matches("[0-9]+[.]?[0]*")) {
                    long displayValueLong = Math.round(Double.parseDouble(displayValue));
                    sb.append(Long.toHexString(displayValueLong).toUpperCase());
                } else {
                    sb.append(convertDecToHex(displayValue));
                    wasRounded = true;
                }
            } else {
                sb.append(convertHexToDec(displayValue));
            }
        }

        return wasRounded;
    }

    private String convertDecToHex(String decString) {
        int i = 0;
        StringBuilder result = new StringBuilder();

        while (i < decString.length()) {
            if (i == 0 && decString.substring(0, 1).equals("(")) {
                result.append("(");
                i++;
            }

            StringBuilder number = new StringBuilder();

            while (i < decString.length() && decString.substring(i, i + 1).matches("[0-9.]")) {
                number.append(decString, i, i + 1);
                i++;
            }

            String hexNumber = Long.toHexString(Math.round(Double.parseDouble(number.toString()))).toUpperCase();
            result.append(hexNumber);

            while (i < decString.length() && decString.substring(i, i + 1).matches("[+\\-*/^()]")) {
                result.append(decString, i, i + 1);
                i++;
            }
        }

        return result.toString();
    }

    private String convertHexToDec(String hexString) {
        int i = 0;
        StringBuilder result = new StringBuilder();

        while (i < hexString.length()) {
            if (i == 0 && hexString.substring(0, 1).equals("(")) {
                result.append("(");
                i++;
            }

            StringBuilder number = new StringBuilder();

            while (i < hexString.length() && hexString.substring(i, i + 1).matches("[0-9A-F]")) {
                number.append(hexString, i, i + 1);
                i++;
            }

            long decNumber = Long.decode("0x" + number.toString());
            result.append(decNumber);

            while (i < hexString.length() && hexString.substring(i, i + 1).matches("[+\\-*/^()]")) {
                result.append(hexString, i, i + 1);
                i++;
            }
        }

        return result.toString();
    }
}

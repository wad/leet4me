package org.wadhome.leetcode.stack;

public class BasicCalculator {

    enum State {undetermined, justGotOp, inNumber}

    public static int calculate(String s) {
        BasicCalculator.State state = BasicCalculator.State.undetermined;
        int sum = 0;
        boolean isInvertingParenthetical = false;
        String opStack = "";
        String numberMaker = "";
        char previousOp = '+';

        s = s.replace(" ", "");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    switch (state) {
                        case undetermined:
                            opStack += previousOp;
                            if (previousOp == '-') {
                                isInvertingParenthetical = !isInvertingParenthetical;
                            }
                            previousOp = '+';
                            break;
                        case justGotOp:
                            opStack += previousOp;
                            if (previousOp == '-') {
                                isInvertingParenthetical = !isInvertingParenthetical;
                            }
                            previousOp = '+';
                            state = State.undetermined;
                            break;
                        case inNumber:
                            throw new RuntimeException("Numeric digit in unexpected location");
                    }
                    break;
                case ')':
                    if (opStack.isEmpty()) {
                        throw new RuntimeException("Missing opening parenthesis");
                    }
                    char endingOp = opStack.charAt(opStack.length() - 1);
                    opStack = opStack.substring(0, opStack.length() - 1);
                    switch (state) {
                        case undetermined:
                            break;
                        case justGotOp:
                            throw new RuntimeException("Unexpected right parenthesis");
                        case inNumber:
                            sum += (Integer.parseInt(numberMaker)
                                    * (isInvertingParenthetical ? -1 : 1)
                                    * (previousOp == '-' ? -1 : 1));
                            break;
                    }
                    if (endingOp == '-') {
                        isInvertingParenthetical = !isInvertingParenthetical;
                    }
                    state = State.undetermined;
                    break;
                case '+':
                    switch (state) {
                        case undetermined:
                            break;
                        case justGotOp:
                            throw new RuntimeException("Unexpected operator");
                        case inNumber:
                            sum += (Integer.parseInt(numberMaker)
                                    * (isInvertingParenthetical ? -1 : 1)
                                    * (previousOp == '-' ? -1 : 1));
                            break;
                    }
                    previousOp = '+';
                    state = State.justGotOp;
                    break;
                case '-':
                    switch (state) {
                        case undetermined:
                            break;
                        case justGotOp:
                            throw new RuntimeException("Unexpected operator");
                        case inNumber:
                            sum += (Integer.parseInt(numberMaker)
                                    * (isInvertingParenthetical ? -1 : 1)
                                    * (previousOp == '-' ? -1 : 1));
                            break;
                    }
                    previousOp = '-';
                    state = State.justGotOp;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    switch (state) {
                        case undetermined:
                        case justGotOp:
                            numberMaker = "" + c;
                            state = State.inNumber;
                            break;
                        case inNumber:
                            numberMaker += c;
                            break;
                    }
                    break;
                default:
            }
        }
        if (!opStack.isEmpty()) {
            throw new RuntimeException("Missing closing parenthesis");
        }
        switch (state) {
            case undetermined:
                break;
            case justGotOp:
                throw new RuntimeException("Unexpected operator");
            case inNumber:
                sum += (Integer.parseInt(numberMaker)
                        * (isInvertingParenthetical ? -1 : 1)
                        * (previousOp == '-' ? -1 : 1));
                break;
        }
        return sum;
    }

}

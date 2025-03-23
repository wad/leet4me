package org.wadhome.leetcode.stack;

// https://leetcode.com/problems/basic-calculator/description/?envType=study-plan-v2&envId=top-interview-150
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

/*
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "1 + 1"
Output: 2
Example 2:

Input: s = " 2-1 + 2 "
Output: 3
Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23


Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
 */

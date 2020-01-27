public class Compf extends Stack {
    //Типы символов (скобки, знаки, операции, другое)
    protected final static int SYM_LEFT  = 0,
            SYM_RIGHT = 1,
            SYM_OPER  = 2,
            SYM_OTHER = 3,
            SYM_LBRACKET = 4,
            SYM_RBRACKET = 5;
    private int symType(char c) {
        switch (c) {
            case '(':
                return SYM_LEFT;
            case ')':
                return SYM_RIGHT;
            case '+':
            case '-':
            case '*':
            case '/':
                return SYM_OPER;
            case '{':
                return SYM_LBRACKET;
            case '}':
                return SYM_RBRACKET;
            default:
                return symOther(c);
        }
    }
    private void processSymbol(char c) {
        switch (symType(c)) {
            case SYM_LEFT:
                push(c);
                break;
            case SYM_RIGHT:
                processSuspendedSymbols(c);
                pop();
                break;
            case SYM_OPER:
                Calc.check = false;
                processSuspendedSymbols(c);
                push(c);
                break;
            case SYM_OTHER:
                nextOther(c);
                break;
            case SYM_LBRACKET:
                push(c);
                Calc.check = false;
                Calc.count++;
                break;
            case SYM_RBRACKET:
                processSuspendedSymbols(c);
                pop();
                Calc.check = false;
                Calc.count--;
                break;
        }
    }
    private void processSuspendedSymbols(char c) {
        while (precedes(top(), c))
            nextOper(pop());
    }
    private int priority(char c) {
        return c == '+' || c == '-' ? 1 : 2;
    }
    private boolean precedes(char a, char b) {
        if(symType(a) == SYM_LEFT || symType(a) == SYM_LBRACKET) return false;
        if(symType(b) == SYM_RIGHT || symType(b) == SYM_RBRACKET) return true;
        return priority(a) >= priority(b);
    }
    protected int symOther(char c) {
        if (c < 'a' || c > 'z') {
            System.out.println("Недопустимый символ: " + c);
            System.exit(0);
        }
        return SYM_OTHER;
    }
    protected void nextOper(char c) {
        System.out.print("" + c + " ");
    }
    protected void nextOther(char c) {
        nextOper(c);
    }
    public void compile(char[] str) {
        processSymbol('(');
        for(int i = 0; i < str.length; i++)
            processSymbol(str[i]);
        processSymbol(')');
        System.out.print("\n");
    }
}

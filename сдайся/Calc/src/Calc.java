public class Calc extends Compf {
    public static boolean check = false;
    public static int count = 0;
    private StackInt s;
    private static int char2int(char c) {
        return (int)c - (int)'0';
    }
    protected int symOther(char c) {
        if (c < '0' || c > '9') {
            System.out.println("Недопустимый символ: " + c);
            System.exit(0);
        }
        return SYM_OTHER;
    }
    protected void nextOper(char c) {
        int second = s.pop();
        int first = s.pop();
        switch (c) {
            case '+':
                s.push(first + second); break;
            case '-':
                s.push(first - second); break;
            case '*':
                s.push(first * second); break;
            case '/':
                s.push(first / second); break;
        }
    }
    protected void nextOther(char c) {
        s.push(char2int(c) * (int)Math.pow(char2int(c), count));
        if (check) checkNum();
        if (!check) check = true;
    }
    public Calc() {
        s = new StackInt();
    }
    public final void compile(char[] str) {
        check = false;
        super.compile(str);
        System.out.println("" + s.top());
    }
    private void checkNum()
    {
        int num2 = s.pop();
        int num1 = s.pop();
        if (num1 < 0) num2 *= -1;
        s.push(num1*10 + num2);
    }
}


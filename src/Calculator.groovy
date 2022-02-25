class Calculator {

    static int add(int a, int b) {
        a + b
    }

    static int subtract(int a, int b) {
        a - b
    }

    static int multiply(int a, int b) {
        a * b
    }

    static float divide(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("Cannot divide by zero")
        }
        a / b
    }

}

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10;   // 要求解的 Fibonacci 数列的项数
        
        // 输出第 n 项的结果
        System.out.println("第 " + n + " 项的值为：" + fibonacci(n));
    }
    
    // 递归求解 Fibonacci 数列第 n 项的值
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

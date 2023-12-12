public class FibonacciArray {
    public static void main(String[] args) {
        int n = 20;   // 要计算斐波那契数列的项数
        int[] fib = new int[n];  // 定义一维数组用于保存数列的各项值
        
        // 初始化斐波那契数列的前两个数值
        fib[0] = 0;
        fib[1] = 1;
        
        // 通过循环计算斐波那契数列的各项值，并将其存储到数组中
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        
        // 输出计算得到的斐波那契数列的各项值
        for (int i = 0; i < n; i++) {
            System.out.print(fib[i] + " ");
        }
    }
}
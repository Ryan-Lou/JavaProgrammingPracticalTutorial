public class FibonacciSeries {
    public static void main(String[] args) {
        int n = 20; // 指定要输出的斐波那契数列个数
        
        int first = 0;
        int second = 1;
        
        System.out.println("斐波那契数列前 " + n + " 个数字:");
        
        for (int i = 0; i < n; i++) {
            System.out.print(first + " ");
            
            int next = first + second;
            first = second;
            second = next;
        }
    }
}
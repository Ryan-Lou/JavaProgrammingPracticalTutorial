import java.util.Scanner;

public class GoldbachConjecture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个大于2的偶数或大于5的奇数：");
        int number = scanner.nextInt();
        
        if (number <= 2 || (number % 2 != 0 && number <= 5)) {
            System.out.println("输入错误，请重新运行程序并输入一个大于2的偶数或大于5的奇数。");
            return;
        }
        
        if (number % 2 == 0) {
            verifyEvenNumber(number);
        } else {
            verifyOddNumber(number);
        }
    }

    private static void verifyEvenNumber(int number) {
        boolean isGoldbach = false;
        
        for (int i = 2; i <= number / 2; i++) {
            if (isPrime(i) && isPrime(number - i)) {
                System.out.println(number + " = " + i + " + " + (number - i));
                isGoldbach = true;
                break;
            }
        }
        
        if (!isGoldbach) {
            System.out.println("哥德巴赫猜想不成立！");
        }
    }

    private static void verifyOddNumber(int number) {
        boolean isGoldbach = false;
        
        for (int i = 2; i <= number / 3; i++) {
            for (int j = i; j <= (number - i) / 2; j++) {
                if (isPrime(i) && isPrime(j) && isPrime(number - i - j)) {
                    System.out.println(number + " = " + i + " + " + j + " + " + (number - i - j));
                    isGoldbach = true;
                    break;
                }
            }
            
            if (isGoldbach) {
                break;
            }
        }
        
        if (!isGoldbach) {
            System.out.println("哥德巴赫猜想不成立！");
        }
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}
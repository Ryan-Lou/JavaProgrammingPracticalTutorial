public class PrimeNumbers {
    public static void main(String[] args) {
        int lowerBound = 10; // 指定范围的下界
        int upperBound = 500; // 指定范围的上界

        System.out.print("在范围 [" + lowerBound + ", " + upperBound + "] 内的素数：");
        printPrimeNumbers(lowerBound, upperBound);
    }

    private static void printPrimeNumbers(int lowerBound, int upperBound) {
        for (int number = lowerBound; number <= upperBound; number++) {
            if (isPrime(number)) {
                System.out.print(number + " ");
            }
        }
        System.out.println();
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

import java.util.Scanner;

public class NumberConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取要转换的字符串和进制
        System.out.print("请输入要转换的字符串：");
        String input = scanner.nextLine();
        System.out.print("请输入要转换的进制（2-36）：");
        int radix = scanner.nextInt();

        // 将字符串转换为整数
        int intValue = Integer.parseInt(input, radix);
        System.out.println("转换为整数（10进制）：" + intValue);

        // 将字符串转换为浮点数
        double doubleValue = Double.parseDouble(input);
        System.out.println("转换为浮点数：" + doubleValue);

        // 将整数转换为指定进制的字符串
        String intString = Integer.toString(intValue, radix);
        System.out.println("转换为" + radix + "进制字符串：" + intString);
    }
}

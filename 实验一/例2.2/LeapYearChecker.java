public class LeapYearChecker {
    public static void main(String[] args) {
        int year = 2023; // 输入要检查的年份
        
        boolean isLeap = year % 400 == 0 || year % 100 == 0 || year % 4 == 0;
        
        if (isLeap) {
            System.out.println(year + "年是闰年.");
        } else {
            System.out.println(year + "年不是闰年.");
        }
    }
}
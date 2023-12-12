public class WeekdayChecker {
    public static void main(String[] args) {
        int year = 2000;  // 年份
        int month = 12;    // 月份（1-12）
        int day = 12;     // 日期
        
        int weekday = getWeekday(year, month, day);
        String weekdayName = getWeekdayName(weekday);
        
        System.out.println(year + "年" + month + "月" + day + "日是星期" + weekdayName);
    }
    
    private static int getWeekday(int year, int month, int day) {
        int a = (14 - month) / 12;
        int y = year - a;
        int m = month + 12 * a - 2;
        int d = (day + y + y/4 - y/100 + y/400 + (31*m)/12) % 7;
        
        return d;
    }
    
    private static String getWeekdayName(int weekday) {
        String[] weekdays = {"日", "一", "二", "三", "四", "五", "六"};
        return weekdays[weekday];
    }
}
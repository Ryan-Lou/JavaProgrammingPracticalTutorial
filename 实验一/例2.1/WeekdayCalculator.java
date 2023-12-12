// -*- coding: UTF-8 -*-
public class WeekdayCalculator {
    public static void main(String[] args) {
        int week = 4; // 假设今天是星期一
        
        // 计算明天的星期值
        int tomorrow = (week + 1) % 7;
        
        // 计算昨天的星期值
        int yesterday = (week - 1 + 7) % 7;
JavaC.sublime-build

        
        // 打印结果
        System.out.println("今天是星期" + week);
        System.out.println("明天是星期" + tomorrow);
        System.out.println("昨天是星期" + yesterday);
    }
}
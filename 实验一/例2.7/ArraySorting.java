import java.util.Arrays;
import java.util.Random;

public class ArraySorting {
    public static void main(String[] args) {
        int n = 10;   // 数组的大小
        int range = 100;  // 随机整数的范围
        
        // 创建并初始化一维整数数组
        int[] arr = new int[n];
        
        // 生成随机整数并存储到数组中
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(range);
        }
        
        // 输出排序前的数组
        System.out.println("排序前的数组：");
        System.out.println(Arrays.toString(arr));
        
        // 使用直接排序法对数组进行排序（升序）
        directSort(arr);
        
        // 输出排序后的数组
        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));
    }
    
    // 直接排序法（插入排序）
    public static void directSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            arr[j + 1] = key;
        }
    }
}
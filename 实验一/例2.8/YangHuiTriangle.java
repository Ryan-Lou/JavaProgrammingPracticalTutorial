public class YangHuiTriangle {
    public static void main(String[] args) {
        int n = 9;   // 杨辉三角的层数
        
        // 创建并初始化二维数组
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = 1;
            triangle[i][i] = 1;
            
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
        }
        
        // 输出杨辉三角
        printTriangle(triangle);
    }
    
    // 输出二维数组（即杨辉三角）
    public static void printTriangle(int[][] triangle) {
        for (int[] row : triangle) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
   }
}

public class MagicSquare {
    public static void main(String[] args) {
        int n = 7;  // 幻方的阶数（奇数）
        
        // 创建并初始化二维数组
        int[][] magicSquare = new int[n][n];
        
        // 初始化幻方的起始位置
        int row = 0;
        int col = n / 2;
        
        // 连续摆数
        for (int num = 1; num <= n * n; num++) {
            magicSquare[row][col] = num;
            
            // 更新下一个位置
            row--;
            col++;
            
            // 检查是否越界，并作相应调整
            if (row < 0) {
                row = n - 1;
            }
            if (col == n) {
                col = 0;
            }
            if (magicSquare[row][col] != 0) {
                row += 2;
                col--;
                
                if (row >= n) {
                    row -= n;
                }
                if (col < 0) {
                    col = n - 1;
                }
            }
        }
        
        // 输出幻方
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(magicSquare[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
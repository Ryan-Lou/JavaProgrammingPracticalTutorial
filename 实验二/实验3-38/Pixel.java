import java.awt.Color;

public class Pixel {
    private int x;        // 像素的 x 坐标
    private int y;        // 像素的 y 坐标
    private Color color;  // 像素的颜色

    // 构造方法，接受坐标和颜色
    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // 获取像素的 x 坐标
    public int getX() {
        return x;
    }

    // 获取像素的 y 坐标
    public int getY() {
        return y;
    }

    // 获取像素的颜色
    public Color getColor() {
        return color;
    }

    // 设置像素的颜色
    public void setColor(Color color) {
        this.color = color;
    }

    public static void main(String[] args) {
        // 创建一个像素对象，指定坐标和颜色
        int xCoord = 10;
        int yCoord = 20;
        Color pixelColor = Color.RED;
        Pixel pixel = new Pixel(xCoord, yCoord, pixelColor);

        // 获取和设置像素颜色
        System.out.println("像素的颜色：" + pixel.getColor());
        pixel.setColor(Color.BLUE);
        System.out.println("像素的颜色：" + pixel.getColor());
    }
}
public class RGBColor {
    // 使用int整数存储RGB颜色值
    private int color;

    // 构造函数，接受红、绿、蓝三个元色值，范围为0-255
    public RGBColor(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException("颜色分量必须在0到255之间");
        }

        // 构建RGB颜色值，最高字节全1
        color = 0xFF000000 | (red << 16) | (green << 8) | blue;
    }

    // 获取红色分量
    public int getRed() {
        return (color >> 16) & 0xFF;
    }

    // 获取绿色分量
    public int getGreen() {
        return (color >> 8) & 0xFF;
    }

    // 获取蓝色分量
    public int getBlue() {
        return color & 0xFF;
    }

    // 获取完整的RGB颜色值
    public int getColor() {
        return color;
    }

    // 覆盖toString方法，用于显示RGB颜色值
    @Override
    public String toString() {
        return "RGB(" + getRed() + ", " + getGreen() + ", " + getBlue() + ")";
    }

    public static void main(String[] args) {
        // 创建一个蓝色的RGB颜色
        RGBColor blueColor = new RGBColor(0, 0, 255);
        System.out.println("颜色：" + blueColor);
    }
}
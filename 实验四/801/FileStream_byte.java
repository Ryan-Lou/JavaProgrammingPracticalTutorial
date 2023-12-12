import java.io.*;

public class FileStream_byte {
    public static void main(String[] args) throws IOException {
        // 以下使用文件字节输出流将多个整数-1写入filename指定文件名的文件
        String filename = "filestream.byte";
        OutputStream out = new FileOutputStream(filename);
        out.write(-1);
        out.write(-1);
        out.close();

        // 以下使用文件字节输入流从filename指定文件中按字节读取
        InputStream in = new FileInputStream(filename);

        int i;
        while ((i = in.read()) != -1) {
            System.out.print("\n" + i);
        }
        in.close();
    }
}

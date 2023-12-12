import javax.swing.*;
import java.io.*;

public class JTextAreaText {
    // 为文本区组件提供读取文本文件并添加到文本区的通用方法
    public static void readFrom(String filename, JTextArea text) {
        try {
            Reader reader = new FileReader(filename);
            // 文件字符输入流
            BufferedReader bufReader = new BufferedReader(reader);
            // 缓冲字符输入流
            text.setText("");
            // 清空文本区
            String line;
            while ((line = bufReader.readLine()) != null) {
                // 读取一行字符串，缓冲字符输入流结束返回null
                text.append(line + "\r\n");
                // 文本区添加line字符串，加换行
            }
            bufReader.close();
            reader.close();
        } catch (FileNotFoundException ex) {
            if (!filename.equals("")) {
                JOptionPane.showMessageDialog(null, "文件不存在：" + filename);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 将文本区的字符串写入指定文本文件的通用方法
    public static void writeTo(String filename, JTextArea text) {
        try {
            Writer writer = new FileWriter(filename);
            // 文件字符输出流
            writer.write(text.getText());
            // 写入文本区中的字符串
            writer.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "文件不存在：" + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

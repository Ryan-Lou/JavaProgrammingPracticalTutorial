import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KeywordCheckerWithGUI extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public KeywordCheckerWithGUI() {
        setTitle("关键字检查器");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        inputField = new JTextField();
        JButton checkButton = new JButton("检查关键字");
        resultLabel = new JLabel();

        panel.add(inputField);
        panel.add(checkButton);
        panel.add(resultLabel);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkKeyword();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void checkKeyword() {
        String input = inputField.getText().trim();

        if (!input.isEmpty()) {
            try {
                if (isKeyword(input)) {
                    resultLabel.setText("输入的字符串是 Java 关键字。");
                } else {
                    resultLabel.setText("输入的字符串不是 Java 关键字。");
                }
            } catch (IOException e) {
                e.printStackTrace();
                resultLabel.setText("读取关键字文件时出现错误。");
            }
        } else {
            resultLabel.setText("请输入有效的字符串。");
        }
    }

    private boolean isKeyword(String input) throws IOException {
        return Files.lines(Paths.get("keywords.txt"))
                .map(String::trim)
                .anyMatch(keyword -> keyword.equals(input));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KeywordCheckerWithGUI();
            }
        });
    }
}

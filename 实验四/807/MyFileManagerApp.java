import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Vector;

public class MyFileManagerApp {
    private JFrame myFrame;             // 主窗口
    private JList<String> myFileList;    // 文件列表
    private DefaultListModel<String> myListModel;  // 文件列表模型
    private JTextArea myTextArea;        // 文本编辑区
    private String myCurrentFile;        // 当前打开的文件路径

    public MyFileManagerApp() {
        myFrame = new JFrame("我的文件管理器和记事本");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(800, 600);

        JSplitPane mySplitPane = new JSplitPane();  // 分割窗格
        myFrame.add(mySplitPane, BorderLayout.CENTER);

        myListModel = new DefaultListModel<>();
        myFileList = new JList<>(myListModel);
        myFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        myFileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openSelectedFile();
                }
            }
        });

        myFileList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadSelectedFile();
                }
            }
        });

        JScrollPane myListScrollPane = new JScrollPane(myFileList);
        mySplitPane.setLeftComponent(myListScrollPane);

        myTextArea = new JTextArea();
        myTextArea.setLineWrap(true);
        myTextArea.setWrapStyleWord(true);

        JScrollPane myTextScrollPane = new JScrollPane(myTextArea);
        mySplitPane.setRightComponent(myTextScrollPane);

        JMenuBar myMenuBar = new JMenuBar();   // 菜单栏
        myFrame.setJMenuBar(myMenuBar);

        JMenu myFileMenu = new JMenu("文件");   // 文件菜单
        myMenuBar.add(myFileMenu);

        JMenuItem myNewMenuItem = new JMenuItem("新建");
        myNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewFile();
            }
        });
        myFileMenu.add(myNewMenuItem);

        JMenuItem myOpenMenuItem = new JMenuItem("打开");
        myOpenMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        myFileMenu.add(myOpenMenuItem);

        JMenuItem mySaveMenuItem = new JMenuItem("保存");
        mySaveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        myFileMenu.add(mySaveMenuItem);

        JMenuItem mySaveAsMenuItem = new JMenuItem("另存为");
        mySaveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsFile();
            }
        });
        myFileMenu.add(mySaveAsMenuItem);

        refreshFileList();  // 刷新文件列表

        myFrame.setVisible(true);
    }

    private void refreshFileList() {
        myListModel.clear();
        File[] files = new File(".").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                myListModel.addElement(file.getName());
            }
        }
    }

    private void createNewFile() {
        myTextArea.setText("");
        myCurrentFile = null;
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("文本文件 (*.txt)", "txt"));
        int result = fileChooser.showOpenDialog(myFrame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                myTextArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    myTextArea.append(line + "\n");
                }
                reader.close();
                myCurrentFile = selectedFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openSelectedFile() {
        String selectedFileName = myFileList.getSelectedValue();
        if (selectedFileName != null) {
            myCurrentFile = selectedFileName;
            openFile();
        }
    }

    private void loadSelectedFile() {
        String selectedFileName = myFileList.getSelectedValue();
        if (selectedFileName != null) {
            myCurrentFile = selectedFileName;
            File selectedFile = new File(selectedFileName);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                myTextArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    myTextArea.append(line + "\n");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        if (myCurrentFile != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(myCurrentFile));
                writer.write(myTextArea.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveAsFile();
        }
    }

    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("文本文件 (*.txt)", "txt"));
        int result = fileChooser.showSaveDialog(myFrame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                writer.write(myTextArea.getText());
                writer.close();
                myCurrentFile = selectedFile.getAbsolutePath();
                refreshFileList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFileManagerApp();
            }
        });
    }
}

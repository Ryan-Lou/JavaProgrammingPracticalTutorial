import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

// 显示音乐播放器文件列表的框架类，响应动作事件
public class ListJFrame extends JFrame implements ActionListener {

    private JTextField text_path, text_status;
    // 路径文本行，状态文本行
    private JComboBox<String> combox;
    // 过滤条件组合框
    private JList<File> jlist;
    // 显示文件列表的列表框
    private DefaultListModel<File> listmodel;
    // 列表框模型
    private int count = 0, size = 0;
    // 文件数，所有文件总字节数

    public ListJFrame() {
        super("音乐播放器的文件列表");
        this.setBounds(300, 240, 650, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 以下添加工具栏，其上添加路径文本行、过滤条件组合框；添加状态文本行
        JToolBar toolbar = new JToolBar();
        // 工具栏，默认水平方向
        this.getContentPane().add(toolbar, "North");
        // 框架北边添加工具栏
        toolbar.add(text_path = new JTextField("我的音乐"));
        text_path.addActionListener(this);
        this.getContentPane().add(text_status = new JTextField());
        // 框架南边添加状态文本

        String[] filternames = {"", "*.mp3", "*.wma", "*.*"};
        // 过滤条件数据项
        this.combox = new JComboBox<String>(filternames);
        // 过滤条件组合框
        this.combox.setEditable(true);
        // 组合框可编辑
        this.combox.addActionListener(this);
        // 组合框注册动作事件监听器
        toolbar.add(this.combox);

        // 以下在框架中间添加列表框
        this.listmodel = new DefaultListModel<File>();
        // 列表框模型
        this.jlist = new JList<File>(this.listmodel);
        // 列表框，指定列表框模型管理数据项
        this.getContentPane().add(new JScrollPane(this.jlist));
        // 框架添加包含列表框的滚动窗格
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.text_path || event.getSource() == this.combox) {
            String filter = (String) this.combox.getSelectedItem();
            // 获得组合框的过滤条件
            if (filter != null) {
                this.listmodel.removeAllElements();
                // 列表框模型删除所有数据项
                count = 0;
                size = 0;
                // count记录文件数，size记录所有文件总字节数
                addList(new File(this.text_path.getText()), new PrefixExtFileFilter(filter));
                this.text_status.setText("共有" + count + "个文件，总字节数为" + size);
            }
        }
    }

    // 将dir目录文件列表（由filter指定过滤条件）中的文件对象，添加到listmodel中，并
    // 计算文件数和字节总数，递归方法
    private void addList(File dir, PrefixExtFileFilter filter) {
        File[] files = dir.listFiles(filter);
        // 返回dir目录由filter指定过滤条件的文件列表
        if (files != null) {
            count += files.length;
            // 文件数
            for (int i = 0; i < files.length; i++) {
                this.listmodel.addElement(files[i]);
                // 列表框模型添加文件对象
                size += files[i].length();
                // 文件长度
            }
            files = dir.listFiles();
            // 返回dir目录的文件列表，没有过滤，包含所有文件和子目录
            for (int i = 0; i < files.length; i++)
            // 继续添加各子目录文件列表中的文件对象
                if (files[i].isDirectory())
                    // 判断指定file对象是否是目录
                    addList(files[i], filter); // 添加files[i]子目录文件列表中的文件对象，递归调用
        }
    }

    public static void main(String[] arg) {
        new ListJFrame();
    }
}

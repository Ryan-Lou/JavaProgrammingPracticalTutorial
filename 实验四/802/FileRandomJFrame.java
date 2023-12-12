import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class FileRandomJFrame extends JFrame implements ActionListener {
    private JPanel cmdpanel;
    private JTextField text_filename, text_count;
    private DefaultTableModel tablemodel;

    public FileRandomJFrame(String filename) {
        super("随机数序列");
        this.setBounds(300, 240, 530, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.cmdpanel = new JPanel();
        this.getContentPane().add(this.cmdpanel, "North");

        this.cmdpanel.add(new JLabel("随机数个数"));
        this.cmdpanel.add(this.text_count = new JTextField("", 5));
        this.text_count.addActionListener(this);

        String[] bstr = {"生成", "打开", "保存"};
        for (int i = 0; i < bstr.length; i++) {
            JButton button = new JButton(bstr[i]);
            this.cmdpanel.add(button);
            button.addActionListener(this);
        }

        this.cmdpanel.add(new JLabel("文件名"), 3);
        String[] title = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        this.cmdpanel.add(this.text_filename = new JTextField(filename, 10), 4);

        this.tablemodel = new DefaultTableModel(title, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        JTable jtable = new JTable(this.tablemodel);
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    Object value = JOptionPane.showInputDialog(null, "修改值", "修改单元格", JOptionPane.PLAIN_MESSAGE, null, null, tablemodel.getValueAt(row, column));
                    if (value != null) {
                        tablemodel.setValueAt(value, row, column);
                    }
                }
            }
        });

        this.getContentPane().add(new JScrollPane(jtable));
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("生成") || event.getSource() == this.text_count) {
            random(this.tablemodel, this.text_count);
        } else {
            switch (event.getActionCommand()) {
                case "打开":
                    readFrom(this.text_filename.getText(), this.tablemodel);
                    break;
                case "保存":
                    writeTo(this.text_filename.getText(), this.tablemodel);
            }
        }
    }

    protected void random(DefaultTableModel tablemodel, JTextField text) {
        try {
            int n = Integer.parseInt(text.getText());
            int columns = tablemodel.getColumnCount();
            int rows = (n % columns == 0) ? n / columns : n / columns + 1;
            tablemodel.setRowCount(rows);

            for (int i = 0; i < n; i++) {
                tablemodel.setValueAt((int) (Math.random() * 100), i / columns, i % columns);
            }

            for (int i = n; i / columns < rows && i % columns < columns; i++) {
                tablemodel.setValueAt(null, i / columns, i % columns);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "\"" + text.getText() + "\" 不能转换为整数，请重新输入！");
        }
    }

    protected void readFrom(String filename, DefaultTableModel tablemodel) {
        try {
            InputStream in = new FileInputStream(filename);
            DataInputStream datain = new DataInputStream(in);
            tablemodel.setRowCount(0);

            while (true) {
                try {
                    Object[] row = new Object[tablemodel.getColumnCount()];
                    for (int j = 0; j < tablemodel.getColumnCount(); j++) {
                        row[j] = datain.readInt();
                    }
                    tablemodel.addRow(row);
                } catch (EOFException eof) {
                    break;
                }
            }
            in.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "文件不存在：" + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "文件读取错误：" + ex.getMessage());
        }
    }

    protected void writeTo(String filename, DefaultTableModel tablemodel) {
        try {
            OutputStream out = new FileOutputStream(filename);
            DataOutputStream dataout = new DataOutputStream(out);

            for (int i = 0; i < tablemodel.getRowCount(); i++) {
                for (int j = 0; j < tablemodel.getColumnCount(); j++) {
                    if (tablemodel.getValueAt(i, j) != null) {
                        dataout.writeInt((Integer) tablemodel.getValueAt(i, j));
                    }
                }
            }
            out.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "文件写入错误：" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new FileRandomJFrame("random.txt");
    }
}

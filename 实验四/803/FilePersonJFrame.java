import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class FilePersonJFrame extends PersonJFrame implements WindowListener {
    private String filename;

    public FilePersonJFrame(Person[] pers, PersonJPanel person, String filename) {
        super(pers, person);
        this.filename = filename;
        this.setTitle("读写 Person 对象文件 " + filename);
        this.addWindowListener(this);

        // Read objects from the file when the frame is opened
        ListModelObjectFile.readFrom(this.filename, this.listmodel);
    }

    public FilePersonJFrame(String filename) {
        this(null, new PersonJPanel(), filename);
    }

    @Override
    public void windowClosing(WindowEvent event) {
        // Write objects to the file when the frame is closing
        ListModelObjectFile.writeTo(this.filename, this.listmodel);
    }

    // Other window event methods (unused)...

    public static void main(String[] arg) {
        new FilePersonJFrame("person.obj").setVisible(true);
    }
}

class ListModelObjectFile {
    // Write objects from the list model to the specified file
    public static <T> void writeTo(String filename, ListModel<T> listmodel) {
        try (ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (int i = 0; i < listmodel.getSize(); i++) {
                objout.writeObject(listmodel.getElementAt(i));
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "文件不存在。");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "写入文件时发生错误：" + ex.getMessage());
        }
    }

    // Read objects from the specified file and add them to the list model
    public static <T> void readFrom(String filename, DefaultListModel<T> listmodel) {
        try (ObjectInputStream objin = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    T object = (T) objin.readObject();
                    listmodel.addElement(object);
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "文件不存在。");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "读取文件时发生错误：" + ex.getMessage());
        }
    }
}

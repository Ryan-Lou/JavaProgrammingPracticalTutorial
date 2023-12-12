import java.io.File;
import java.io.FileFilter;

// 文件名过滤器类，实现文件过滤器接口
public class PrefixExtFileFilter implements FileFilter {

    // 文件名前缀子串，文件扩展名
    private String prefix = "", extension = "";

    // 构造方法，filterstr指定过滤条件。算法获得其中的文件名前缀和扩展名分别存储在prefix和
    // extension，没有参数或“*.*”都表示所有文件。
    public PrefixExtFileFilter(String filterstr) {
        // 将字符串中字母全部转换小写
        filterstr = filterstr.toLowerCase();

        int i = filterstr.indexOf('*');
        // 寻找通配符“*”
        if (i >= 0) {
            this.prefix = filterstr.substring(0, i);
        }

        i = filterstr.lastIndexOf('.');
        // “*”之前的字符串是文件名前缀，寻找最后的“.”
        if (i >= 0) {
            this.extension = filterstr.substring(i + 1);
        }

        // “.”后的字符串是文件扩展名
        if (this.extension.equals("*")) {
            // 识别“*.*”
            this.extension = "";
        }
    }

    // 没有过滤条件、显示所有文件和子目录列表
    public PrefixExtFileFilter() {
        this("");
        // “*.*”同义
    }

    // 过滤操作，若file文件对象的文件名前缀和扩展名与prefix，extension匹配，
    // 则返回true，接受file文件对象保留在文件列表中
    public boolean accept(File file) {
        if (!file.isFile()) {
            // 判断指定file对象是否是文件
            return false;
        }

        String filename = file.getName().toLowerCase();
        // 将文件名字符串转换成小写字母再比较
        return filename.startsWith(this.prefix) && filename.endsWith(this.extension);
    }
}

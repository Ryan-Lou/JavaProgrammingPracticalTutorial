// 互斥缓冲区类
public class LockedBuffer<T> {
    private T obj;

    // 向缓冲区写入数据，互斥方法
    public synchronized void put(T obj) {
        this.obj = obj;
    }

    // 从缓冲区获得数据，互斥方法
    public synchronized T get() {
        return this.obj;
    }
}
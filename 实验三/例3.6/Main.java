

// 发送线程类
class SendThread1<T> extends Thread {
    private LockedBuffer<T> buffer;
    private T[] objs;

    public SendThread1(T[] objs, LockedBuffer<T> buffer) {
        this.objs = objs;
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < this.objs.length; i++) {
            buffer.put(this.objs[i]);
            System.out.println(this.getClass().getName() + " put: " + this.objs[i]);
        }
        buffer.put(null); // 发送 null 作为结束标记
        System.out.println(this.getClass().getName() + " put: null");
    }
}

// 接收线程类
class ReceiveThread1<T> extends Thread {
    private LockedBuffer<T> buffer;

    public ReceiveThread1(LockedBuffer<T> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        T obj;
        do {
            obj = this.buffer.get();
            System.out.println("\t\t\t\t" + this.getClass().getName() + " get: " + obj);
        } while (obj != null);
    }
}

public class Main {
    public static void main(String args[]) {
        LockedBuffer<Integer> buffer = new LockedBuffer<Integer>();
        Integer[] objs = {1, 2, 3, 4};

        Thread sender = new SendThread1<>(objs, buffer);
        sender.start();

        Thread receiver = new ReceiveThread1<>(buffer);
        receiver.setPriority(sender.getPriority() - 1);
        receiver.start();
    }
}

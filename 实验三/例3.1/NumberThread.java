public class NumberRunnable implements Runnable {
    private int first, end;

    public NumberRunnable(int first, int end) {
        this.first = first;
        this.end = end;
    }

    public void run() {
        for (int i = first; i <= end; i += 2) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                // 线程休眠一段时间，模拟执行过程中的一些耗时操作
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 创建目标对象
        NumberRunnable target = new NumberRunnable(1, 20);

        // 以目标对象 target 创建线程对象，thread_odd 执行 target 的 run() 方法
        Thread thread_odd = new Thread(target, "奇数线程");
        thread_odd.start();

        // 创建另一个线程对象，执行另一个实例的 run() 方法
        new Thread(new NumberRunnable(2, 10), "偶数线程").start();
    }
}

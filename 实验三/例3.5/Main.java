// 账户类
class Account {
    private String name;
    private double balance;

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized double balance() {
        return balance;
    }

    public synchronized void put(double value) {
        balance += value;
    }

    public synchronized double get(double value) {
        if (value <= balance) {
            balance -= value;
            return value;
        } else {
            return 0;
        }
    }
}

// 存款线程类
class LockedSaveThread extends Thread {
    private Account account;
    private double value;

    public LockedSaveThread(Account account, double value) {
        this.account = account;
        this.value = value;
    }

    public void run() {
        synchronized (this.account) {
            double howMuch = this.account.balance();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.account.put(this.value);
            System.out.println(this.getClass().getName() + ", " + this.account.getName() +
                    "账户: 现有" + howMuch + ", 存入" + this.value + ", 余额" + this.account.balance());
        }
    }
}

// 取款线程类
class LockedFetchThread extends Thread {
    private Account account;
    private double value;

    public LockedFetchThread(Account account, double value) {
        this.account = account;
        this.value = value;
    }

    public void run() {
        synchronized (this.account) {
            double howMuch = this.account.balance();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            double fetchedValue = this.account.get(this.value);
            System.out.println(this.getClass().getName() + ", " + this.account.getName() +
                    "账户: 现有" + howMuch + ", 取走" + fetchedValue + ", 余额" + this.account.balance());
        }
    }
}

public class Main {
    public static void main(String args[]) {
        Account wang = new Account("Wang");
        (new LockedSaveThread(wang, 100)).start();
        (new LockedSaveThread(wang, 200)).start();
        (new LockedFetchThread(wang, 300)).start();
    }
}

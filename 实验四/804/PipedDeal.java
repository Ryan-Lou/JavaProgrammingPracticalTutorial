import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Font;
import javax.swing.*;


public class PipedDeal {
    public PipedDeal(int cardMax, int number) throws IOException {
        PipedInputStream[] pipedins = new PipedInputStream[number];
        PipedOutputStream[] pipedouts = new PipedOutputStream[number];

        for (int i = 0; i < number; i++) {
            pipedins[i] = new PipedInputStream();
            pipedouts[i] = new PipedOutputStream(pipedins[i]);
        }

        List<Integer> cards = new ArrayList<>();
        for (int i = 1; i <= cardMax; i++) {
            cards.add(i);
        }
        Collections.shuffle(cards);

        new CardSendToStreamThread(pipedouts, cards).start();

        String[] titles = {"北", "东", "南", "西"};
        int[] x = {300, 550, 50, 400};
        int[] y = {200, 204, 320, 430};

        for (int i = 0; i < number; i++) {
            new CardReceiveFromStreamJFrame(pipedins[i], titles[i], x[i], y[i]).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new PipedDeal(52, 4);
    }
}

class CardSendToStreamThread extends Thread {
    private OutputStream[] outs;
    private List<Integer> cards;

    public CardSendToStreamThread(OutputStream[] outs, List<Integer> cards) {
        this.outs = outs;
        this.cards = cards;
        this.setPriority(Thread.MAX_PRIORITY);
    }

    public void run() {
        DataOutputStream[] dataouts = new DataOutputStream[this.outs.length];

        for (int i = 0; i < dataouts.length; i++) {
            dataouts[i] = new DataOutputStream(this.outs[i]);
        }

        try {
            for (int cardValue : cards) {
                int receiverIndex = cardValue % dataouts.length;
                dataouts[receiverIndex].writeInt(cardValue);
            }

            for (DataOutputStream dataout : dataouts) {
                dataout.close();
            }

            for (OutputStream out : this.outs) {
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class CardReceiveFromStreamJFrame extends Thread {
    private InputStream in;
    private JTextArea text;

    public CardReceiveFromStreamJFrame(InputStream in, String title, int x, int y) {
        this.in = in;
        this.text = new JTextArea();
        this.text.setLineWrap(true);
        this.text.setEditable(false);
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));

        JFrame frame = new JFrame(title);
        frame.setBounds(x, y, 250, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this.text);
        frame.setVisible(true);
    }

    public void run() {
        DataInputStream datain = new DataInputStream(this.in);

        while (true) {
            try {
                int cardValue = datain.readInt();
                text.append(String.format("%4d", cardValue));
                Thread.sleep(100);
            } catch (IOException | InterruptedException ex) {
                break;
            }
        }

        try {
            datain.close();
            this.in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Ball {
    private int x, y, size;
    private int xSpeed, ySpeed;
    private Color color;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = color;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
    }

    public void checkCollision(int panelWidth, int panelHeight) {
        if (x - size < 0 || x + size > panelWidth) {
            xSpeed = -xSpeed;
        }
        if (y - size < 0 || y + size > panelHeight) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - size, y - size, 2 * size, 2 * size);
    }
}

class BallPanel extends JPanel {
    private List<Ball> balls = new ArrayList<>();

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public List<Ball> getBalls() {
        return balls;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }
}

public class BouncingBalls {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bouncing Balls");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BallPanel ballPanel = new BallPanel();

            JButton startButton = new JButton("Start");
            JSpinner speedSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int speed = (int) speedSpinner.getValue();
                    createBalls(ballPanel, speed);
                    startAnimation(ballPanel);
                }
            });

            JPanel controlPanel = new JPanel();
            controlPanel.add(startButton);
            controlPanel.add(new JLabel("Speed:"));
            controlPanel.add(speedSpinner);

            frame.add(ballPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);

            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void createBalls(BallPanel ballPanel, int speed) {
        ballPanel.getBalls().clear();
        ballPanel.addBall(new Ball(50, 50, 20, speed, speed, Color.BLUE));
        ballPanel.addBall(new Ball(100, 100, 15, -speed, -speed, Color.RED));
        ballPanel.addBall(new Ball(150, 150, 25, speed, -speed, Color.GREEN));
        ballPanel.addBall(new Ball(200, 200, 18, -speed, speed, Color.ORANGE));
        ballPanel.addBall(new Ball(250, 250, 22, speed, speed, Color.CYAN));
        ballPanel.addBall(new Ball(300, 300, 16, -speed, speed, Color.MAGENTA));
        ballPanel.addBall(new Ball(350, 350, 30, speed, -speed, Color.YELLOW));
    }

    private static void startAnimation(BallPanel ballPanel) {
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ball ball : ballPanel.getBalls()) {
                    ball.move();
                    ball.checkCollision(ballPanel.getWidth(), ballPanel.getHeight());
                }
                ballPanel.repaint();
            }
        });

        timer.start();
    }
}

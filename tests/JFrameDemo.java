import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by pinghua.wph on 2015/10/12.
 */
public class JFrameDemo {
    public static void main(String[] args){
        JFrame frame = new JFrame("window");
        Stage panel = new Stage();
        frame.setLayout(null); // 取消自动充满
        frame.add(panel);
        panel.setSize(10 * 35, 10 * 35);
        panel.setLocation(50, 50);
        frame.setSize(450, 480);
        panel.setBorder(new LineBorder(Color.black));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.requestFocus();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("HI"+e.getKeyCode());
            }
        });
    }
}

class Stage extends JPanel{
    public void paint(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.fill3DRect(50, 50, 30, 20, true);

    }
}
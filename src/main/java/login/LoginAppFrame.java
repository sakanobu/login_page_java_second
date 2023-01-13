package login;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginAppFrame extends JFrame {
  public LoginAppFrame() {
    setTitle("ログイン画面");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    int width = 720;
    int height = 600;
    setSize(width, height);
    setLayout(new FlowLayout());

    JPanel cardLayoutPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    cardLayoutPanel.setLayout(cardLayout);

    cardLayoutPanel.add(new LoginPagePanel(cardLayoutPanel, cardLayout), "loginPage");
    cardLayoutPanel.add(new ResultPagePanel(cardLayoutPanel, cardLayout), "resultPage");


    add(cardLayoutPanel);
  }

  public static void main(String[] args) {
    LoginAppFrame frame = new LoginAppFrame();
    frame.setVisible(true);
  }
}

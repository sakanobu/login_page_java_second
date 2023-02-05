package login;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginAppFrame extends JFrame {
  private String pageTitle = "ログイン画面";

  public static void main(String[] args) {
    LoginAppFrame frame = new LoginAppFrame();
    frame.setVisible(true);
  }

  public LoginAppFrame() {
    setTitle(pageTitle);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    int width = 720;
    int height = 600;
    setSize(width, height);
    setLayout(new FlowLayout());

    JPanel loginPagePanel = new LoginPagePanel();
    add(loginPagePanel);
  }

  public void change(JPanel panel) {
    pageTitle = pageTitle.equals("ログイン画面") ? "結果画面" : "ログイン画面";

    getContentPane().removeAll();

    super.add(panel);
    super.setTitle(pageTitle);

    revalidate();
    repaint();
  }
}

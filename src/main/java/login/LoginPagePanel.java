package login;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPagePanel extends JPanel {
  private final JTextField loginIdField;
  private final JTextField passwordField;

  public LoginPagePanel() {
    setLayout(null);
    int width = 720;
    int height = 400;
    setPreferredSize(new Dimension(width, height));

    JLabel loginIdLabel = new JLabel("login_id");
    loginIdLabel.setBounds(20, 30, 70, 30);
    loginIdLabel.setHorizontalAlignment(JLabel.LEFT);

    loginIdField = new JTextField();
    loginIdField.setBounds(90, 30, 620, 30);
    loginIdField.setHorizontalAlignment(JLabel.LEFT);

    JLabel passwordLabel = new JLabel("password");
    passwordLabel.setBounds(20, 80, 70, 30);
    passwordLabel.setHorizontalAlignment(JLabel.LEFT);

    passwordField = new JTextField();
    passwordField.setBounds(90, 80, 620, 30);
    passwordField.setHorizontalAlignment(JLabel.LEFT);

    JButton loginButton = new JButton("ログイン");
    loginButton.setBounds(320, 130, 95, 30);
    loginButton.addActionListener(new LoginPageListener(this));

    add(loginIdLabel);
    add(loginIdField);
    add(passwordLabel);
    add(passwordField);
    add(loginButton);
  }
}

package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPagePanel extends JPanel {
  private final JTextField loginIdField;
  private final JTextField passwordField;
  private final JLabel resultLabel;

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

    JPanel resultPanel = new JPanel();
    resultPanel.setPreferredSize(new Dimension(720, 30));
    resultPanel.setBounds(20, 130, 690, 30);
    resultPanel.setBackground(Color.WHITE);
    resultPanel.setLayout(new BorderLayout());

    resultLabel = new JLabel("結果表示欄");
    resultLabel.setHorizontalAlignment(JLabel.CENTER);

    resultPanel.add(resultLabel, BorderLayout.CENTER);

    JButton loginButton = new JButton("ログイン");
    loginButton.setBounds(320, 180, 95, 30);
    loginButton.addActionListener(new LoginPageListener(this));

    add(loginIdLabel);
    add(loginIdField);
    add(passwordLabel);
    add(passwordField);
    add(resultPanel);
    add(loginButton);
  }

  public String getLoginId() {
    return loginIdField.getText();
  }

  public String getPassword() {
    return passwordField.getText();
  }

  public void setResultLabelText(String resultText) {
    resultLabel.setText(resultText);
  }
}

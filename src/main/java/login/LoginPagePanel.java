package login;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPagePanel extends JPanel {
  private final JTextField loginIdField;
  private final JTextField passwordField;

  public LoginPagePanel(JPanel cardLayoutPanel, CardLayout cardLayout) {
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

    //    Button loginButton = new Button("ログイン");
    //    loginButton.addActionListener(new LoginPageListener(cardLayoutPanel, cardLayout));

    add(loginIdLabel);
    add(loginIdField);
    add(passwordLabel);
    add(passwordField);
    //    add(loginButton);
  }
}

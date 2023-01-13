package login;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LoginPageListener implements ActionListener {
  JPanel cardLayoutPanel;
  CardLayout cardLayout;

  public LoginPageListener(JPanel cardLayoutPanel, CardLayout cardLayout) {
    this.cardLayoutPanel = cardLayoutPanel;
    this.cardLayout = cardLayout;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("ログイン")) {
      cardLayout.last(cardLayoutPanel);
    }
  }
}

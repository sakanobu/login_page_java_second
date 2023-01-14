package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageListener implements ActionListener {
  private final LoginPagePanel loginPagePanel;

  public LoginPageListener(LoginPagePanel loginPagePanel) {
    this.loginPagePanel = loginPagePanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("ログイン")) {
      LoginAppFrame loginAppFrame = (LoginAppFrame) loginPagePanel.getTopLevelAncestor();
      loginAppFrame.change(new ResultPagePanel());
    }
  }
}

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
    String loginId = loginPagePanel.getLoginId();
    String password = loginPagePanel.getPassword();

    if (e.getActionCommand().equals("ログイン")) {
      // login_id 入力フィールドと password 入力フィールドが両方とも空の場合
      if (loginId.equals("") && password.equals("")) {
        loginPagePanel.setResultLabelText("login_idとpassword が空です。");
      } else if (loginId.equals("")) {
        loginPagePanel.setResultLabelText("login_idが空です。");
      } else if (password.equals("")) {
        loginPagePanel.setResultLabelText("passwordが空です。");
      }

      // ResultPagePanelへの画面遷移用のコード
      // LoginAppFrame loginAppFrame = (LoginAppFrame) loginPagePanel.getTopLevelAncestor();
      // loginAppFrame.change(new ResultPagePanel());
    }
  }
}

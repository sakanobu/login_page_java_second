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
      if (loginId.equals("")
          && password.equals("")) { // login_idフィールドとpasswordフィールドが両方とも空の場合
        loginPagePanel.setResultLabelText("login_idとpasswordが両方とも空です。");
      } else if (loginId.equals("")) { // login_idフィールドのみが空である場合
        loginPagePanel.setResultLabelText("login_idが空です。");
      } else if (password.equals("")) { // passwordフィールドのみが空の場合
        loginPagePanel.setResultLabelText("passwordが空です。");
      } else { // login_idフィールドとpasswordフィールドの両方に何らかの値が入力さている場合 
        User user = new UserDao().findUserByLoginIdAndPassword(loginId, password);

        if (user == null) { // 入力されたlogin_idフィールドの値とpasswordフィールドの値に何らかの問題がある場合
          UserAuthsTable userAuthsTable = new UserAuthsTable();
          boolean existLoginId = userAuthsTable.existLoginId(loginId);
          boolean existPassword = userAuthsTable.existPassword(password);

          if (!existLoginId
              && !existPassword) { // login_idフィールドの値とpasswordフィールドの値がどちらもuser_authsテーブルに存在しない場合
            loginPagePanel.setResultLabelText("login_idとpasswordが両方ともデータベースに存在しません。");
          } else if (!existLoginId) { // login_idフィールドの値のみがuser_authsテーブルに存在しない場合
            loginPagePanel.setResultLabelText("login_idがデータベースに存在しません。");
          }
        }
      }

      // ResultPagePanelへの画面遷移用のコード
      // LoginAppFrame loginAppFrame = (LoginAppFrame) loginPagePanel.getTopLevelAncestor();
      // loginAppFrame.change(new ResultPagePanel());
    }
  }
}

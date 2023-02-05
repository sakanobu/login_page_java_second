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

    // login_idフィールドとpasswordフィールドが両方とも空の場合
    if (loginId.equals("")
        && password.equals("")) {
      loginPagePanel.setResultLabelText("login_idとpasswordが両方とも空です。");
      return;
    }

    // login_idフィールドのみが空である場合
    if (loginId.equals("")) {
      loginPagePanel.setResultLabelText("login_idが空です。");
      return;
    }

    // passwordフィールドのみが空である場合
    if (password.equals("")) {
      loginPagePanel.setResultLabelText("passwordが空です。");
      return;
    }

    // login_idフィールドとpasswordフィールドの両方に何らかの値が入力されている場合
    UserDao userDao = new UserDao();
    User user = userDao.findUserByLoginIdAndPassword(loginId, password);

    // 入力されたlogin_idフィールドとpasswordフィールドの値に何らかの問題がある場合
    if (user == null) {
      boolean loginIdExists =
          userDao.queryForCheckingExistence(UserDao.CHECK_LOGIN_ID_QUERY,
              loginId);
      boolean passwordExists =
          userDao.queryForCheckingExistence(UserDao.CHECK_PASSWORD_QUERY,
              password);

      // login_idフィールドとpasswordフィールドの値がどちらもuser_authsテーブルに存在しない場合
      if (!loginIdExists
          && !passwordExists) {
        loginPagePanel.setResultLabelText("login_idとpasswordが両方ともデータベースに存在しません。");
        return;
      }

      // login_idフィールドの値のみがuser_authsテーブルに存在しない場合
      if (!loginIdExists) {
        loginPagePanel.setResultLabelText("login_idがデータベースに存在しません。");
        return;
      }

      // passwordフィールドの値のみがuser_authsテーブルに存在しない場合
      if (!passwordExists) {
        loginPagePanel.setResultLabelText("passwordがデータベースに存在しません。");
        return;
      }

      // login_idフィールドとpasswordフィールドの値の組み合わせが間違っている場合
      loginPagePanel.setResultLabelText("login_idとpasswordの組み合わせが間違っています。");
      return;
    }

    // 入力されたlogin_idフィールドとpasswordフィールドの値の組み合わせがが正しい場合
    if (user.retired()) { // usersテーブルのis_retiredカラムの値がTRUEであるユーザーであった場合
      loginPagePanel.setResultLabelText("退職しているユーザーです。");
      return;
    }

    // rolesテーブルのnameカラムの値が"ログイン不可"であるユーザーであった場合
    if (user.role()
        .equals("ログイン不可")) {
      loginPagePanel.setResultLabelText("ログインが許可されていないユーザーです。");
      return;
    }

    // ログインが許可されるユーザーのlogin_idとpasswordが入力された場合
    LoginAppFrame loginAppFrame = (LoginAppFrame) loginPagePanel.getTopLevelAncestor();
    loginAppFrame.change(new ResultPagePanel(user));
  }
}

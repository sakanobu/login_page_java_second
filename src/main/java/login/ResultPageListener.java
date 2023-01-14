package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPageListener implements ActionListener {
  private final ResultPagePanel resultPagePanel;

  public ResultPageListener(ResultPagePanel resultPagePanel) {
    this.resultPagePanel = resultPagePanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("戻る")) {
      LoginAppFrame loginAppFrame = (LoginAppFrame) resultPagePanel.getTopLevelAncestor();
      loginAppFrame.change(new LoginPagePanel());
    }
  }
}

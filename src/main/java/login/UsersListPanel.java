package login;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UsersListPanel extends JPanel {
  public UsersListPanel() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    int width = 720;
    int height = 370;
    setPreferredSize(new Dimension(width, height));
    setBackground(Color.WHITE);

    add(new JLabel("user_id  |  name  |  age  |  is_retired  |  role"));

    for (User user : new UserDao().findAll()
    ) {
      JLabel userRecordLabel =
          new JLabel(
              "%d,              %s,  %d,      %b,             %s\n".formatted(user.userId(),
                  user.name(),
                  user.age(),
                  user.retired(),
                  user.role()));
      add(userRecordLabel);
    }
  }
}

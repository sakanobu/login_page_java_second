package login;

import java.awt.Button;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPagePanel extends JPanel {
  public ResultPagePanel(User user) {
    setLayout(null);
    int width = 720;
    int height = 400;
    setPreferredSize(new Dimension(width, height));

    JLabel nameColumnLabel = new JLabel("name:");
    nameColumnLabel.setBounds(20, 30, 70, 30);
    nameColumnLabel.setHorizontalAlignment(JLabel.LEFT);

    JLabel nameLabel = new JLabel(user.name());
    nameLabel.setBounds(90, 30, 620, 30);
    nameLabel.setHorizontalAlignment(JLabel.LEFT);

    JLabel ageColumnLabel = new JLabel("age:");
    ageColumnLabel.setBounds(20, 80, 70, 30);
    ageColumnLabel.setHorizontalAlignment(JLabel.LEFT);

    JLabel ageLabel = new JLabel("%d 才".formatted(user.age()));
    ageLabel.setBounds(90, 80, 620, 30);
    ageLabel.setHorizontalAlignment(JLabel.LEFT);

    JLabel roleColumnLabel = new JLabel("role:");
    roleColumnLabel.setBounds(20, 130, 70, 30);
    roleColumnLabel.setHorizontalAlignment(JLabel.LEFT);

    JLabel roleLabel = new JLabel(user.role());
    roleLabel.setBounds(90, 130, 620, 30);
    roleLabel.setHorizontalAlignment(JLabel.LEFT);

    Button backButton = new Button("戻る");
    backButton.setBounds(320, 180, 95, 30);
    backButton.addActionListener(new ResultPageListener(this));

    add(nameColumnLabel);
    add(nameLabel);
    add(ageColumnLabel);
    add(ageLabel);
    add(roleColumnLabel);
    add(roleLabel);
    add(backButton);
  }
}

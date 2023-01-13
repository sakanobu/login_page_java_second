package login;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ResultPagePanel extends JPanel {
  public ResultPagePanel(JPanel cardLayoutPanel, CardLayout cardLayout) {
    setLayout(null);
    int width = 730;
    int height = 400;
    setPreferredSize(new Dimension(width, height));

    Button backButton = new Button("戻る");
    backButton.addActionListener(new ResultPageListener(cardLayoutPanel, cardLayout));

    add(backButton);
  }
}

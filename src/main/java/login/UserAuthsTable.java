package login;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthsTable {
  private final String url;
  private final String dbUserName;
  private final String dbPassword;
  private Connection con = null;
  private PreparedStatement ps = null;
  private ResultSet rs = null;
  public static final String CHECK_LOGIN_ID_QUERY = """
      SELECT
        ua.login_id
      FROM
        user_auths AS ua
      WHERE
        ua.login_id = ?;
      """;
  public static final String CHECK_PASSWORD_QUERY = """
      SELECT
      ua.password
        FROM
      user_auths AS ua
        WHERE
      ua.password = ?;
      """;

  public UserAuthsTable() {
    this.url = Dotenv.load().get("MYSQL_URL");
    this.dbUserName = Dotenv.load().get("MYSQL_USER_NAME");
    this.dbPassword = Dotenv.load().get("MYSQL_PASSWORD");
  }

  public boolean queryForCheckingExistence(String query, String target) {
    boolean targetExists = false;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(query);
      ps.setString(1, target);
      rs = ps.executeQuery();

      while (rs.next()) {
        targetExists = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return targetExists;
  }
}

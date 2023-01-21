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

  public UserAuthsTable() {
    this.url = Dotenv.load().get("MYSQL_URL");
    this.dbUserName = Dotenv.load().get("MYSQL_USER_NAME");
    this.dbPassword = Dotenv.load().get("MYSQL_PASSWORD");
  }

  public boolean existLoginId(String loginId) {
    boolean existLoginId = false;
    String sql = """
        SELECT
          ua.login_id
        FROM
          user_auths AS ua
        WHERE
          ua.login_id = ?;
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(sql);
      ps.setString(1, loginId);
      rs = ps.executeQuery();

      while (rs.next()) {
        existLoginId = true;
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

    return existLoginId;
  }

  public boolean existPassword(String password) {
    boolean existPassword = false;
    String sql = """
        SELECT
          ua.password
        FROM
          user_auths AS ua
        WHERE
          ua.password = ?;
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(sql);
      ps.setString(1, password);
      rs = ps.executeQuery();

      while (rs.next()) {
        existPassword = true;
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

    return existPassword;
  }
}

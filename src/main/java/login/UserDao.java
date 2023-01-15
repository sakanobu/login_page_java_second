package login;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
  private final String url;
  private final String dbUserName;
  private final String dbPassword;
  private Connection con = null;
  private PreparedStatement ps = null;
  private ResultSet rs = null;

  public static void main(String[] args) {
    UserDao userDao = new UserDao();

    System.out.println("\n↓ userDao.findUserByLoginIdAndPassword(\"login1\", \"password1\")");
    User user1 = userDao.findUserByLoginIdAndPassword("login1", "password1");
    System.out.printf("%s, %d, %b, %s, %s, %s\n", user1.getName(), user1.getAge(),
        user1.getRetired(),
        user1.getRole(), user1.getLoginId(), user1.getPassword());

    System.out.println(
        "\n↓ userDao.findUserByLoginIdAndPassword(\"login1\", \"incorrectPassword\")");
    User userNull = userDao.findUserByLoginIdAndPassword("login1", "incorrectPassword");
    if (userNull == null) {
      System.out.printf("%s", userNull);
    } else {
      System.out.printf("%s, %d, %b, %s, %s, %s\n", userNull.getName(), userNull.getAge(),
          userNull.getRetired(),
          userNull.getRole(), userNull.getLoginId(), userNull.getPassword());
    }
  }

  public UserDao() {
    this.url = Dotenv.load().get("MYSQL_URL");
    this.dbUserName = Dotenv.load().get("MYSQL_USER_NAME");
    this.dbPassword = Dotenv.load().get("MYSQL_PASSWORD");
  }

  public User findUserByLoginIdAndPassword(String loginId, String password) {
    User user = null;
    String sql = """
        SELECT
          u.name,
          u.age,
          u.is_retired,
          r.name,
          ua.login_id,
          ua.password
        FROM
          users AS u
          INNER JOIN roles AS r ON u.role_id = r.id
          INNER JOIN user_auths AS ua ON u.id = ua.user_id
        WHERE
          ua.login_id = ?
          AND ua.password = ?;
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(sql);
      ps.setString(1, loginId);
      ps.setString(2, password);
      rs = ps.executeQuery();

      while (rs.next()) {
        String name = rs.getString("u.name");
        int age = rs.getInt("u.age");
        String role = rs.getString("r.name");
        boolean retired = rs.getBoolean("u.is_retired");
        String uaLoginId = rs.getString("ua.login_id");
        String uaPassword = rs.getString("ua.password");

        user = new User(name, age, retired, role, uaLoginId, uaPassword);
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

    return user;
  }
}

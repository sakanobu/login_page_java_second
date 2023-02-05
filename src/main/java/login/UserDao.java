package login;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
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

  public static void main(String[] args) {
    UserDao userDao = new UserDao();

    System.out.println("\n↓ userDao.findAll()");
    for (User user : userDao.findAll()
    ) {
      System.out.printf(
          "%d, %s, %d, %b, %s, %s, %s\n".formatted(user.userId(), user.name(), user.age(),
              user.retired(), user.role(), user.loginId(), user.loginId()));
    }

    System.out.println("\n↓ userDao.findUserByLoginIdAndPassword(\"login1\", \"password1\")");
    User user1 = userDao.findUserByLoginIdAndPassword("login1", "password1");
    System.out.printf("%d, %s, %d, %b, %s, %s, %s\n", user1.userId(), user1.name(), user1.age(),
        user1.retired(), user1.role(), user1.loginId(), user1.password());

    System.out.println(
        "\n↓ userDao.findUserByLoginIdAndPassword(\"login1\", \"incorrectPassword\")");
    User userNull = userDao.findUserByLoginIdAndPassword("login1", "incorrectPassword");
    if (userNull == null) {
      System.out.printf("%s", userNull);
    } else {
      System.out.printf("%d, %s, %d, %b, %s, %s, %s\n", userNull.userId(), userNull.name(),
          userNull.age(), userNull.retired(), userNull.role(), userNull.loginId(),
          userNull.password());
    }
  }

  public UserDao() {
    this.url = Dotenv.load().get("MYSQL_URL");
    this.dbUserName = Dotenv.load().get("MYSQL_USER_NAME");
    this.dbPassword = Dotenv.load().get("MYSQL_PASSWORD");
  }

  public ArrayList<User> findAll() {
    ArrayList<User> allUserList = new ArrayList<>();
    String sql = """
        SELECT
          u.id,
          u.name,
          u.age,
          u.is_retired,
          r.name,
          ua.login_id,
          ua.password
        FROM
          users AS u
          INNER JOIN roles AS r ON u.role_id = r.id
          INNER JOIN user_auths AS ua ON u.id = ua.user_id;
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {
        int userId = rs.getInt("u.id");
        String name = rs.getString("u.name");
        int age = rs.getInt("u.age");
        String role = rs.getString("r.name");
        boolean retired = rs.getBoolean("u.is_retired");
        String loginId = rs.getString("ua.login_id");
        String password = rs.getString("ua.password");

        allUserList.add(new User(userId, name, age, retired, role, loginId, password));
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

    return allUserList;
  }

  public User findUserByLoginIdAndPassword(String inputLoginId, String inputPassword) {
    User user = null;
    String sql = """
        SELECT
          u.id,
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
      ps.setString(1, inputLoginId);
      ps.setString(2, inputPassword);
      rs = ps.executeQuery();

      while (rs.next()) {
        int userId = rs.getInt("u.id");
        String name = rs.getString("u.name");
        int age = rs.getInt("u.age");
        String role = rs.getString("r.name");
        boolean retired = rs.getBoolean("u.is_retired");
        String loginId = rs.getString("ua.login_id");
        String password = rs.getString("ua.password");

        user = new User(userId, name, age, retired, role, loginId, password);
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

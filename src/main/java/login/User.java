package login;

public class User {
  private final String name;
  private final int age;
  private final boolean retired;
  private final String role;
  private final String loginId;
  private final String password;

  public User(String name, int age, boolean retired, String role, String loginId,
              String password) {
    this.name = name;
    this.age = age;
    this.retired = retired;
    this.role = role;
    this.loginId = loginId;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getRole() {
    return role;
  }

  public boolean getRetired() {
    return retired;
  }

  public String getLoginId() {
    return loginId;
  }

  public String getPassword() {
    return password;
  }
}

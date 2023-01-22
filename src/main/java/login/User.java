package login;

public class User {
  private final String name;
  private final int age;
  private final boolean retired;
  private final String role;

  public User(String name, int age, boolean retired, String role) {
    this.name = name;
    this.age = age;
    this.retired = retired;
    this.role = role;
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
}

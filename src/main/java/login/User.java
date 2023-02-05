package login;

public record User(int userId, String name, int age, boolean retired, String role, String loginId,
                   String password) {
}

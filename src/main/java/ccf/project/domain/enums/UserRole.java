package ccf.project.domain.enums;

public enum UserRole {
    ADMIN(1),
    SALESMAN(2);

    public int code;

    UserRole(int code) {
        this.code = code;
    }
}

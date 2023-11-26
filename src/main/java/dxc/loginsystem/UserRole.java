package dxc.loginsystem;

public enum UserRole {
    ADMIN(0), USER(1);

    private final int roleId;

    UserRole(int role_id) {
        this.roleId = role_id;
    }

    public int getRoleId() {
        return roleId;
    }

    public static UserRole fromRoleId(int roleId) {
        for (UserRole role : UserRole.values()) {
            if (role.getRoleId() == roleId) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role ID: " + roleId);
    }

}

package bargamingBackend.bargaming.common.enums;

public enum Role {
    CLIENTE("CLIENTE", 1),
    VENDEDOR("VENDEDOR", 2),
    ADMIN("ADMIN", 3);

    private final String displayName;
    private final int accessLevel;

    Role(String displayName, int accessLevel) {
        this.displayName = displayName;
        this.accessLevel = accessLevel;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
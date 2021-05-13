package invi.utils;

public enum System {
    ANDROID(false),
    IOS(false);

    public boolean isActive;

    private System(boolean isActive) {
        this.isActive = isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}

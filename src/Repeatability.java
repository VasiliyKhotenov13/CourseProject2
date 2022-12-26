public enum Repeatability {

    ONCE("Однократно"),
    DAILY("Ежедневно"),
    WEEKLY("Еженедельно"),
    MONTHLY("Ежемесячно"),
    YEARLY("Ежегодно");

    private String typeRepeat;

    Repeatability(String typeRepeat) {
        this.typeRepeat = typeRepeat;
    }

    public String getTypeRepeat() {
        return typeRepeat;
    }
}

public abstract class Task {
    private final int id;
    private final String name;

    private final String description;

    public Task(int id, String name, String description) {
        this.id          = id;
        this.name        = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract StatusTask getStatus();
    public abstract TypeTask getTypeTask();

    @Override
    public abstract String toString();

}

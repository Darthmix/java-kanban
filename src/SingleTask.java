public class SingleTask extends Task {
    private final StatusTask statusTask;

    public SingleTask(int id, String name, String description,  StatusTask statusTask) {
        super(id, name, description);
        this.statusTask = statusTask;
    }

    @Override
    public StatusTask getStatus() {
        return statusTask;
    }

    public SingleTask withStatus(StatusTask statusTask) {
        return new SingleTask(
                this.getId(),
                this.getName(),
                this.getDescription(),
                statusTask
        );
    }

    @Override
    public TypeTask getTypeTask() {
        return TypeTask.REG;
    }

    @Override
    public String toString() {
        return "SingleTask{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", statusTask=" + statusTask +
                '}' + '\n';
    }
}

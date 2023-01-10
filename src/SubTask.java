public class SubTask extends Task{
    private final EpicTask epicTask;
    private final StatusTask statusTask;

    public SubTask(int id, String name, String description, StatusTask statusTask, EpicTask epic) {
        super(id, name, description);
        this.statusTask = statusTask;
        this.epicTask   = epic;
        this.epicTask.addSubTask(this);
    }

    public SubTask withStatus(StatusTask statusTask) {
        return new SubTask(
                this.getId(),
                this.getName(),
                this.getDescription(),
                statusTask,
                this.epicTask
        );
    }

    public void revoveFromEpic(){
        this.epicTask.removeSubTask(this);
    }

    @Override
    public StatusTask getStatus() {
        return statusTask;
    }

    @Override
    public TypeTask getTypeTask() {
        return TypeTask.SUB;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", statusTask=" + this.getStatus() +
                '}' + '\n';
    }
}

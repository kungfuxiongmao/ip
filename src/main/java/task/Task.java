package task;

public class Task {
    private boolean marked;
    private String content;

    public Task(String content) {
        this.marked = false;
        this.content = content;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + this.content;
        } else {
            return "[ ] " + this.content;
        }
    }
}

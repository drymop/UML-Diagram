

public class Relationship {
    Structure from, to;
    public Relationship(Structure from, Structure to) {
        this.from = from;
        this.to = to;
    }

    public Structure getFrom() {
        return from;
    }

    public Structure getTo() {
        return to;
    }
}
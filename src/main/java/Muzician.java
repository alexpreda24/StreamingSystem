public class Muzician extends Streamers {
    private Integer streamerType;
    private Integer ID;
    private String name;

    public Muzician(Integer streamerType, Integer ID, String name) {
        this.streamerType = streamerType;
        this.ID = ID;
        this.name = name;
    }
    public int getStreamerType() {
        return streamerType;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}

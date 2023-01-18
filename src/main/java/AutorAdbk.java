public class AutorAdbk extends Streamers{
    private int streamerType;
    private int ID;
    private String name;

   public AutorAdbk(int streamerType, int ID, String name) {
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

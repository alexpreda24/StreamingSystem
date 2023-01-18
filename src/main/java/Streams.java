import java.util.List;

public abstract class Streams {
    private int streamType;
    private int ID;
    private String name;
    private int streamGenre;
    private long noOfStreams;
    private int streamerID;
    private long length;
    private long dateAdded;
    private boolean isListened;

    public abstract boolean isListened();

    public abstract void setListened(boolean listened);

    private List<Streams> streams;

    public int getStreamType() {
        return streamType;
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getStreamGenre() {
        return streamGenre;
    }

    public long getNoOfStreams() {
        return noOfStreams;
    }

    public int getStreamerID() {
        return streamerID;
    }

    public long getLength() {
        return length;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public List<Streams> getStreams() {
        return streams;
    }
}

public class Podcast extends Streams {
    private int streamType;
    private int ID;
    private String name;
    private int streamGenre;
    private long noOfStreams;
    private int streamerID;
    private long length;

    private boolean isListened;

    @Override
    public boolean isListened() {
        return isListened;
    }

    public void setListened(boolean listened) {
        isListened = listened;
    }

    private long dateAdded;
    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }
    @Override
    public int getStreamType() {
        return streamType;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStreamGenre() {
        return streamGenre;
    }

    @Override
    public long getNoOfStreams() {
        return noOfStreams;
    }

    @Override
    public int getStreamerID() {
        return streamerID;
    }

    @Override
    public long getLength() {
        return length;
    }

    @Override
    public long getDateAdded() {
        return dateAdded;
    }
    private Podcast(PodcastBuilder streamsBuilder){
        this.streamType = streamsBuilder.streamType;
        this.ID = streamsBuilder.ID;
        this.name = streamsBuilder.name;
        this.streamGenre = streamsBuilder.streamGenre;
        this.noOfStreams = streamsBuilder.noOfStreams;
        this.streamerID = streamsBuilder.streamerID;
        this.length = streamsBuilder.length;
        this.dateAdded = streamsBuilder.dateAdded;
        this.isListened = false;
    }

    public static class PodcastBuilder{
        private int streamType;
        private int ID;
        private String name;
        private int streamGenre;
        private long noOfStreams;
        private int streamerID;
        private long length;
        private long dateAdded;
        private boolean isListened;
        public PodcastBuilder(int streamType, int ID, int streamGenre, long noOfStreams,
                                    int streamerID, long length, long dateAdded,String name){
            this.streamType = streamType;
            this.ID = ID;
            this.name = name;
            this.streamGenre = streamGenre;
            this.noOfStreams = noOfStreams;
            this.streamerID = streamerID;
            this.length = length;
            this.dateAdded = dateAdded;
            this.isListened = false;
        }
        public Podcast build(){
            return new Podcast(this);
        }
    }
}

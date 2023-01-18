import java.util.LinkedHashMap;

public class StreamsFactory {
    private static StreamsFactory instance;
        private LinkedHashMap<Integer,Streams> streams = new LinkedHashMap<Integer, Streams>();
    private StreamsFactory() {
    }

    public static StreamsFactory Instance() {
        if(instance == null) {
            instance = new StreamsFactory();
        }
        return new StreamsFactory();
    }

    public LinkedHashMap<Integer, Streams> getStreams() {
        return streams;
    }
    public  void setStreams() {
        streams = null;
    }

    public void createStream(int type, int ID, int streamGenre, long noOfStreams,
                             int streamerID, long length, long dateAdded, String name) {
        switch(type) {
            case 1:
                streams.put(ID,new PiesaMuzicala.PiesaMuzicalaBuilder(type,ID, streamGenre, noOfStreams, streamerID, length, dateAdded,name).build());
                break;
            case 2:
                streams.put(ID,new Podcast.PodcastBuilder(type,ID, streamGenre, noOfStreams, streamerID, length, dateAdded,name).build());
                break;
            case 3:
                streams.put(ID,new AudioBook.AudioBookBuilder(type,ID, streamGenre, noOfStreams, streamerID, length, dateAdded,name).build());
                break;

            default:
                break;
        }
    }
    public void removeStream(int ID){
        streams.remove(ID);
    }
    public void updateStreams(int ID,Streams st){
        streams.get(ID).setNoOfStreams(st.getNoOfStreams());
    }
}

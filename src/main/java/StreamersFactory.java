import java.util.LinkedHashMap;

public class StreamersFactory {
    private static StreamersFactory instance;
    private static LinkedHashMap<Integer,Streamers> streamers = new LinkedHashMap<>();
    private StreamersFactory() {
    }

    public static StreamersFactory Instance() {
        if(instance == null) {
            instance = new StreamersFactory();
        }
        return new StreamersFactory();
    }

    public static LinkedHashMap<Integer,Streamers> getStreamers() {
        return streamers;
    }

    public Streamers createStreamer(int type, int ID, String name) {
        switch(type) {
            case 1:
                streamers.put(ID,new Muzician(type,ID, name));
                return new Muzician(type,ID, name);
            case 2:
                streamers.put(ID,new Gazda(type,ID, name));
                return new Gazda(type,ID, name);
            case 3:
                streamers.put(ID,new AutorAdbk(type,ID, name));
                return new AutorAdbk(type,ID, name);
            default:
                return null;
        }
    }
    public void updateStreamer(int ID,Streams st){

    }


}

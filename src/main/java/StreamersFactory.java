import java.util.LinkedHashMap;

public class StreamersFactory {
    private static StreamersFactory instance;
    private  LinkedHashMap<Integer,Streamers> streamers = new LinkedHashMap<>();
    private StreamersFactory() {
    }

    public static StreamersFactory Instance() {
        if(instance == null) {
            instance = new StreamersFactory();
        }
        return new StreamersFactory();
    }

    public  LinkedHashMap<Integer,Streamers> getStreamers() {
        return streamers;
    }

    public void createStreamer(int type, int ID, String name) {
        switch(type) {
            case 1:
                streamers.put(ID,new Muzician(type,ID, name));
                break;
            case 2:
                streamers.put(ID,new Gazda(type,ID, name));
                break;
            case 3:
                streamers.put(ID,new AutorAdbk(type,ID, name));
                break;
            default:
                break;
        }
    }


}

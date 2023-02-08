import java.util.LinkedList;
import java.util.List;

public class Users {
    private final int ID;
    private final String name;
    private List<Integer> str = new LinkedList<>();

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getStreams() {
        return str;
    }

    public void setStr(int streamId) {
        this.str.add(streamId);
    }

    public void addStream(int streamID){
        this.str.add(streamID);

    }
    Users(Integer ID, String name, String streams){
        this.ID = ID;
        this.name = name;
        String[] s = streams.split(" ");
        for(int i = 0; i < s.length; i++){
            addStream(Integer.parseInt(s[i]));
        }
    }


}

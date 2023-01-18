import java.util.LinkedList;
import java.util.List;

public class Users {
    private final int ID;
    private final String name;
    private List<Integer> str = new LinkedList<>();
    private List<Boolean> isListened = new LinkedList<Boolean>();

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getStreams() {
        return str;
    }

    public void setStr(List<Integer> str,int streamId) {

        this.str.add(streamId);
    }

    public void addStream(int streamID){
        this.str.add(streamID);
        this.isListened.add(false);
    }

    public List<Boolean> getIsListened() {
        return isListened;
    }

    public void setIsListened(int i,boolean isListened) {
        this.isListened.set(i,isListened);
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

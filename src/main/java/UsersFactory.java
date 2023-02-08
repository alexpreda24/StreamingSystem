import java.util.LinkedHashMap;

public class UsersFactory {
    private static UsersFactory instance ;
    private LinkedHashMap<Integer,Users> users = new LinkedHashMap<>();
    public static UsersFactory Instance(){
        if(instance == null){
            instance = new UsersFactory();
        }
        return instance;
    }
    public void createUsers(Integer ID, String name,String streams){

        users.put(ID,new Users(ID,name,streams));
    }

    public LinkedHashMap<Integer, Users> getUsers() {
        return users;
    }


    public void updateUsers(int ID,int streamID){

        users.get(ID).setStr(streamID);
    }
}

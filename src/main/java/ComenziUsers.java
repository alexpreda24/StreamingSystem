import java.util.LinkedHashMap;

public class ComenziUsers extends Comanda{

    public void asculta(UsersFactory usersFactory,StreamsFactory streamsFactory,int userID,int streamID){
        LinkedHashMap<Integer,Streams> streams = streamsFactory.getStreams();
        streams.get(streamID).setNoOfStreams(streams.get(streamID).getNoOfStreams() + 1);
        streamsFactory.updateStreams(streamID,streams.get(streamID));
        usersFactory.updateUsers(userID,streamID);
    }
}

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.TimeZone;

public class ComenziUsers extends Comanda{

    public void asculta(LinkedHashMap<Integer,Users> users,StreamsFactory streamsFactory,int userID,int streamID){
        LinkedHashMap<Integer,Streams> streams = streamsFactory.getStreams();
        streams.get(streamID).setNoOfStreams(streams.get(streamID).getNoOfStreams() + 1);
        streamsFactory.updateStreams(streamID,streams.get(streamID));
        users.get(userID).setStr(streamID);
    }
    public String afiseaza(Streams s,StreamersFactory streamersFactory){
       LinkedHashMap<String,Object> map = new LinkedHashMap<>();
       StringBuilder sb = new StringBuilder();
        map.put("id", s.getID());
        map.put("name", s.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        LinkedHashMap<Integer,Streamers> streamers = streamersFactory.getStreamers();
        String name = streamers.get(s.getStreamerID()).getName();
        map.put("streamerName", name);
        map.put("noOfListenings", s.getNoOfStreams());
        int durata = (int) s.getLength();
        int ore = durata / 3600;
        int minute = (durata % 3600) / 60;
        int secunde = durata % 60;
        if (ore == 0) {
            String time = String.format("%02d:%02d", minute, secunde);
            map.put("length", time);
        } else {
            String time = String.format("%02d:%02d:%02d", ore, minute, secunde);
            map.put("length", time);
        }
        long date =  s.getDateAdded() ;//pentru ca e in GMT+2
        java.util.Date time=new java.util.Date(date * 1000);
        map.put("dateAdded", sdf.format(time));

        System.out.print("{");
        for(String key: map.keySet()){
            if(!key.equals("dateAdded"))
                sb.append("\"" + key + "\":\"" + map.get(key) + "\",");

            else
                sb.append("\"" + key + "\":\"" + sdf.format(time) + "\"");
        }

        sb.append("}");
        return sb.toString();
    }
}
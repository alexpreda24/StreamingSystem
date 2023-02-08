import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class ProiectPOO {
    public static void main(String[] args) {
        CSVReader reader;
        LinkedHashMap<Integer,Streamers> streamers;
        LinkedHashMap<Integer,Streams> streams;
        LinkedHashMap<Integer,Users> users = new LinkedHashMap<>();
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        if (args == null) {
            System.out.println("Nothing to read here");
        } else {
            try {
                String antentinput = "src/main/resources/";
                String streamersinput =  antentinput + args[0];
                String streamsinput = antentinput + args[1];
                String usersinput = antentinput + args[2];
                String commandsinput = antentinput + args[3];
                String[] line;
                reader = new CSVReader(new FileReader(streamersinput));
                StreamersFactory streamersFactory = StreamersFactory.Instance();
                StreamsFactory streamsFactory = StreamsFactory.Instance();
                reader.readNext();
                while ((line = reader.readNext()) != null) {
                  try {
                      streamersFactory.createStreamer(Integer.parseInt(line[0]), Integer.parseInt(line[1]), line[2]);
                  } catch (NumberFormatException e) {
                      e.printStackTrace();
                  }
                }
                reader = new CSVReader(new FileReader(streamsinput));
                reader.readNext();
                while ((line = reader.readNext()) != null) {
                    try {

                        streamsFactory.createStream(Integer.parseInt(line[0]), Integer.parseInt(line[1]),
                                Integer.parseInt(line[2]), Long.parseLong(line[3]), Integer.parseInt(line[4]),
                                Long.parseLong(line[5]), Long.parseLong(line[6]), line[7]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                reader = new CSVReader(new FileReader(usersinput));
                reader.readNext();
                while ((line = reader.readNext()) != null) {
                    try {
                        users.put(Integer.parseInt(line[0]),new Users(Integer.parseInt(line[0]), line[1], line[2]));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                BufferedReader br = new BufferedReader(new FileReader(commandsinput));
                String st;
                while ((st = br.readLine()) != null) {
                    try {
                        String[] line1 = st.split(" ");
                        if (line1[1].equals("LIST")) {
                            streams = streamsFactory.getStreams();
                            int cauta = Integer.parseInt(line1[0]);
                            int ok = 0;
                            for (Streams s : streams.values()) {
                                if (s.getStreamerID() == cauta) {
                                    if(ok == 0)System.out.print("[");
                                    ok++;
                                    if(ok > 1)System.out.print(",");
                                    map.put("id", s.getID());
                                    map.put("name", s.getName());
                                    streamers = streamersFactory.getStreamers();
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
                                    long date = s.getDateAdded();

                                    java.util.Date time=new java.util.Date(date * 1000);
                                    map.put("dateAdded", sdf.format(time));
                                    System.out.print("{");
                                    for(String key: map.keySet()){
                                       if(!key.equals("dateAdded"))
                                           System.out.print("\"" + key + "\":\"" + map.get(key) + "\",");
                                       else
                                           System.out.print("\"" + key + "\":\"" + sdf.format(time) + "\"");
                                    }
                                    System.out.print("}");
                                }

                            }
                            if(ok >= 1)System.out.println("]");
                            if (ok == 0) {
                                Users user = users.get(cauta);
                                List<Integer> list = user.getStreams();
                                System.out.print("[");
                                for (Integer i : list) {
                                    if(ok > 0)System.out.print(",");
                                    ok++;
                                    Streams s = streams.get(i);
                                        map.put("id", s.getID());
                                        map.put("name", s.getName());
                                        streamers = streamersFactory.getStreamers();
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
                                            System.out.print("\"" + key + "\":\"" + map.get(key) + "\",");

                                        else
                                            System.out.print("\"" + key + "\":\"" + sdf.format(time) + "\"");
                                    }

                                    System.out.print("}");
                                }
                                System.out.println("]");
                            }
                        }
                        if(line1[1].equals("ADD")){
                            int streamerId = Integer.parseInt(line1[0]);
                            int streamType = Integer.parseInt(line1[2]);
                            int streamId = Integer.parseInt(line1[3]);
                            int streamGenre = Integer.parseInt(line1[4]);
                            int noOfStreams = 0;
                            long streamLength = Long.parseLong(line1[5]);
                            StringBuilder sb = new StringBuilder();
                            for(int i = 6; i < line1.length; i++){
                                sb.append(line1[i]);
                                sb.append(" ");
                            }
                            String streamName = sb.toString();
                            long dateAdded = Instant.now().getEpochSecond();
                            streamsFactory.createStream(streamType, streamId, streamGenre,
                                    noOfStreams , streamerId, streamLength, dateAdded,streamName);
                        }
                        if(line1[1].equals("DELETE")){
                            int streamId = Integer.parseInt(line1[2]);
                            streamsFactory.removeStream(streamId);
                        }
                        if(line1[1].equals("LISTEN")){
                            int UserId = Integer.parseInt(line1[0]);
                            int streamId = Integer.parseInt(line1[2]);
                            ComenziUsers comandaUsers = new ComenziUsers();
                            comandaUsers.asculta(users,  streamsFactory,UserId, streamId);
                        }
                        if(line1[1].equals("RECOMMEND")){
                            int UserId = Integer.parseInt(line1[0]);
                            String type = line1[2];
                            Users user = users.get(UserId);
                            List<Integer> list = user.getStreams();
                            ArrayList<Streams> recomandari = new ArrayList<>();
                            streams = streamsFactory.getStreams();
                            for(Streams s : streams.values()) {
                                if (type.equals("SONG") && s instanceof PiesaMuzicala)
                                    if (!list.contains(s.getID())) {
                                        for (int i : list) {
                                            if (streams.get(i).getStreamerID() == s.getStreamerID())
                                                recomandari.add(s);
                                        }
                                    }
                                if (type.equals("PODCAST") && s instanceof Podcast)
                                    if (!list.contains(s.getID())){
                                        for (int i : list) {
                                            if (streams.get(i).getStreamerID() == s.getStreamerID())

                                                recomandari.add(s);
                                        }
                            }
                                if(type.equals("AUDIOBOOK") && s instanceof AudioBook)
                                    if(!list.contains(s.getID())) {
                                        for (int i : list) {
                                            if (streams.get(i).getStreamerID() == s.getStreamerID())
                                                recomandari.add(s);
                                        }
                                    }
                            }
                            Collections.sort(recomandari, new Comparator<Streams>() {
                                @Override
                                public int compare(Streams o1, Streams o2) {
                                    return (int)(o2.getNoOfStreams() - o1.getNoOfStreams());
                                }
                            });
                            int ok = 0;
                            System.out.print("[");
                            for(Streams s : recomandari){
                                ok++;
                                map.put("id", s.getID());
                                map.put("name", s.getName());
                                streamers = streamersFactory.getStreamers();
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
                                long date = s.getDateAdded() ;
                                java.util.Date time=new java.util.Date(date * 1000);

                                map.put("dateAdded", sdf.format(time));

                                System.out.print("{");
                                for(String key: map.keySet()){
                                    if(!key.equals("dateAdded"))
                                        System.out.print("\"" + key + "\":\"" + map.get(key) + "\",");
                                    else
                                        System.out.print("\"" + key + "\":\"" + sdf.format(time) + "\"");

                                }
                                System.out.print("}");
                                if(ok < 5 && ok < recomandari.size())System.out.print(",");
                                if(ok == 5 || ok == recomandari.size()){
                                    System.out.println("]");
                                    break;
                                }
                            }
                        }
                        if(line1[1].equals("SURPRISE")){
                            int UserId = Integer.parseInt(line1[0]);
                            String type = line1[2];
                            Users user = users.get(UserId);
                            List<Integer> list = user.getStreams();
                            ArrayList<Streams> recomandari = new ArrayList<>();
                            streams = streamsFactory.getStreams();
                            int find;
                            for(Streams s : streams.values()) {
                                if (type.equals("SONG") && s instanceof PiesaMuzicala)
                                    if (!list.contains(s.getID())) {
                                        find = 0;
                                        for (int i : list) {
                                            if (streams.get(i).getStreamerID() == s.getStreamerID()){
                                                find = 1;
                                                break;
                                            }
                                        }
                                        if(find == 0)
                                            recomandari.add(s);
                                    }
                                if (type.equals("PODCAST") && s instanceof Podcast)
                                    if (!list.contains(s.getID())){
                                        find = 0;
                                        for (int i : list) {
                                            if (streams.get(i).getStreamerID() == s.getStreamerID()){
                                                find = 1;
                                                break;
                                            }
                                        }
                                        if(find == 0)
                                            recomandari.add(s);
                                    }
                                if(type.equals("AUDIOBOOK") && s instanceof AudioBook)
                                    if(!list.contains(s.getID())) {
                                        find = 0;
                                        for (int i : list) {
                                            if (streams.get(i).getStreamerID() == s.getStreamerID()){
                                                find = 1;
                                                break;
                                            }
                                        }
                                        if(find == 0)
                                            recomandari.add(s);
                                    }
                            }
                            Collections.sort(recomandari, new Comparator<Streams>() {
                                @Override
                                public int compare(Streams o1, Streams o2) {
                                    if(o2.getDateAdded() == o1.getDateAdded())
                                        return (int )(o2.getNoOfStreams() - o1.getNoOfStreams());
                                    return (int)(o2.getDateAdded() - o1.getDateAdded());
                                }
                            });
                            int ok = 0;
                            System.out.print("[");
                            for(Streams s : recomandari){
                                ok++;
                                map.put("id", s.getID());
                                map.put("name", s.getName());
                                streamers = streamersFactory.getStreamers();
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
                                long date = s.getDateAdded() ;
                                java.util.Date time=new java.util.Date(date * 1000);

                                map.put("dateAdded", sdf.format(time));

                                System.out.print("{");
                                for(String key: map.keySet()){
                                    if(!key.equals("dateAdded"))
                                        System.out.print("\"" + key + "\":\"" + map.get(key) + "\",");
                                    else
                                        System.out.print("\"" + key + "\":\"" + sdf.format(time) + "\"");

                                }
                                System.out.print("}");
                                if(ok < 3 && ok < recomandari.size())System.out.print(",");
                                if(ok == 3 || ok == recomandari.size()){
                                    System.out.println("]");
                                    break;
                                }
                            }
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException | CsvValidationException e) {

               e.printStackTrace();
            }
        }
    }
}

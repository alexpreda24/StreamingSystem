import java.util.LinkedHashMap;

public abstract class Comanda {
    public abstract void asculta(LinkedHashMap<Integer,Users> usersFactory, StreamsFactory st, int userID, int streamID);
    public abstract String afiseaza(Streams s, StreamersFactory streamersFactory);
}
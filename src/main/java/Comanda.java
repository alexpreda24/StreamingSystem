public abstract class Comanda {
    private UsersFactory UserFactory;
    private StreamsFactory streamsFactory;
    public abstract void asculta(UsersFactory usersFactory,StreamsFactory st,int userID,int streamID);

}

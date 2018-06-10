package randomclient.event.join;

public class JoinServerEvent {
    public String ip;
    public int port;

    public JoinServerEvent(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}

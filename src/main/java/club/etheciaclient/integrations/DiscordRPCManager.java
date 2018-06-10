package club.etheciaclient.integrations;

import club.etheciaclient.RandomClient;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

public class DiscordRPCManager {

    private static DiscordRPCManager INSTANCE;
    private IPCClient client;

    private DiscordRPCManager(long appID) {
        this.client = new IPCClient(appID);
        this.client.setListener(new IPCListener() {
            @Override
            public void onReady(IPCClient client) {
                RandomClient.EVENT_BUS.register(new DiscordRPCUpdater(client));
            }
        });
        try {
            client.connect(DiscordBuild.ANY);
        } catch (NoDiscordClientException e) {
            RandomClient.LOGGER.warn("No Discord clients found!");
        }
    }

    public static void initDiscordRPC(long appID) {
        INSTANCE = new DiscordRPCManager(appID);
    }

}


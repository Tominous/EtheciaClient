package club.etheciaclient.integrations;

import club.etheciaclient.EtheciaClient;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import org.json.JSONObject;

import java.time.OffsetDateTime;

public class DiscordRPCManager {

    private static DiscordRPCManager INSTANCE;
    private IPCClient client;
    private DiscordRPCUpdater updater;

    private DiscordRPCManager(long appID) {
        this.client = new IPCClient(appID);
        this.client.setListener(new IPCListener() {
            @Override
            public void onReady(IPCClient client) {
                EtheciaClient.EVENT_BUS.register(DiscordRPCManager.this.updater = new DiscordRPCUpdater(client));
            }
        });
        try {
            client.connect(DiscordBuild.ANY);
        } catch (NoDiscordClientException e) {
            EtheciaClient.LOGGER.warn("No Discord clients found!");
        }
    }

    public static void initDiscordRPC(long appID) {
        INSTANCE = new DiscordRPCManager(appID);
    }

    public DiscordRPCManager setStatus(String newStatus) {
        updateField("state", newStatus);
        return this;
    }

    public DiscordRPCManager setDetails(String newDetails) {
        updateField("details", newDetails);
        return this;
    }

    public DiscordRPCManager setInstance(boolean instance) {
        updateField("instance", instance);
        return this;
    }

    public DiscordRPCManager setLargeImage(String key) {
        updateField("large_image", key);
        return this;
    }

    public DiscordRPCManager setSmallImage(String key) {
        updateField("small_image", key);
        return this;
    }

    private void updateField(String field, Object value) {
        JSONObject current = updater.getRichPrecense().toJson();
        Object smallImage = current.getJSONObject("assets").getString("small_image");
        Object largeImage = current.getJSONObject("assets").getString("large_image");
        Object instance = current.getBoolean("instance");
        Object details = current.getString("details");
        Object state = current.getString("state");
        switch (field) {
            case "small_image":
                smallImage = value;
                break;
            case "large_image":
                largeImage = value;
                break;
            case "instance":
                instance = value;
                break;
            case "details":
                details = value;
                break;
            case "state":
                state = value;
                break;
        }
        RichPresence.Builder builder = new RichPresence.Builder();
        builder.setSmallImage((String) smallImage)
                .setLargeImage((String) largeImage)
                .setInstance((boolean) instance)
                .setStartTimestamp(OffsetDateTime.now())
                .setDetails((String) details)
                .setState((String) state);
        updater.setRichPrecense(builder.build());
    }

}


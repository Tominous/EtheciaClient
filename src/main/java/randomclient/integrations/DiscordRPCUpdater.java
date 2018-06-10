package randomclient.integrations;

import com.google.common.eventbus.Subscribe;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.RichPresence;
import net.minecraft.client.Minecraft;
import randomclient.event.LeaveServerEvent;
import randomclient.event.join.JoinServerEvent;
import randomclient.event.join.JoinSingleplayerEvent;

import java.time.OffsetDateTime;

public class DiscordRPCUpdater {
    private final IPCClient client;
    DiscordRPCUpdater(IPCClient client) {
        this.client = client;
        // TODO: toggle
        RichPresence.Builder builder = new RichPresence.Builder();
        client.sendRichPresence(builder.setState("IGN: " + Minecraft.getMinecraft().getSession().getUsername())
        .setDetails("On the main menu")
        .setStartTimestamp(OffsetDateTime.now())
                .setLargeImage("main1")
                .setSmallImage("small1")
        .build());
    }
    @Subscribe
    public void onSinglePlayer(JoinSingleplayerEvent e) {
        RichPresence.Builder builder = new RichPresence.Builder();
        client.sendRichPresence(builder.setSmallImage("small1").setLargeImage("main1").setState("IGN: " + Minecraft.getMinecraft().getSession().getUsername()).setDetails("Playing Singleplayer").setStartTimestamp(OffsetDateTime.now()).build());
    }
    @Subscribe
    public void onServerJoin(JoinServerEvent e) {
        RichPresence.Builder builder = new RichPresence.Builder();
        client.sendRichPresence(builder.setSmallImage("small1").setLargeImage("main1").setState("IGN: " + Minecraft.getMinecraft().getSession().getUsername()).setDetails("Playing on " + e.ip).setStartTimestamp(OffsetDateTime.now()).build());
    }
    @Subscribe
    public void onServerLeave(LeaveServerEvent e) {
        RichPresence.Builder builder = new RichPresence.Builder();
        client.sendRichPresence(builder.setState("IGN: " + Minecraft.getMinecraft().getSession().getUsername())
                .setDetails("On the main menu")
                .setStartTimestamp(OffsetDateTime.now())
                .setLargeImage("main1")
                .setSmallImage("small1")
                .build());
    }
}

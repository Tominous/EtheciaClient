package club.etheciaclient.integrations;

import club.etheciaclient.event.LeaveServerEvent;
import club.etheciaclient.event.join.JoinServerEvent;
import club.etheciaclient.event.join.JoinSingleplayerEvent;
import club.etheciaclient.utils.StringUtils;
import com.google.common.eventbus.Subscribe;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.RichPresence;
import net.minecraft.client.Minecraft;

import java.time.OffsetDateTime;

public class DiscordRPCUpdater {
    private final IPCClient client;
    private RichPresence current;
    DiscordRPCUpdater(IPCClient client) {
        this.client = client;
        // TODO: toggle
        RichPresence.Builder builder = new RichPresence.Builder();
        setRichPrecense(builder.setState(StringUtils.replaceVowels("IGN: " + Minecraft.getMinecraft().getSession().getUsername()))
        .setDetails(StringUtils.replaceVowels("On the main menu"))
        .setStartTimestamp(OffsetDateTime.now())
                .setLargeImage("main1")
                .setSmallImage("small1")
        .build());
    }
    @Subscribe
    public void onSinglePlayer(JoinSingleplayerEvent e) {
        RichPresence.Builder builder = new RichPresence.Builder();
        setRichPrecense(builder.setSmallImage("small1").setLargeImage("main1").setState(StringUtils.replaceVowels("IGN: " + Minecraft.getMinecraft().getSession().getUsername())).setDetails(StringUtils.replaceVowels("Playing Singleplayer")).setStartTimestamp(OffsetDateTime.now()).build());
    }
    @Subscribe
    public void onServerJoin(JoinServerEvent e) {
        RichPresence.Builder builder = new RichPresence.Builder();
        setRichPrecense(builder.setSmallImage("small1").setLargeImage("main1").setState("IGN: " + Minecraft.getMinecraft().getSession().getUsername()).setDetails("Playing on " + e.ip).setStartTimestamp(OffsetDateTime.now()).build());
    }
    @Subscribe
    public void onServerLeave(LeaveServerEvent e) {
        RichPresence.Builder builder = new RichPresence.Builder();
        setRichPrecense(builder.setState(StringUtils.replaceVowels("IGN: " + Minecraft.getMinecraft().getSession().getUsername()))
                .setDetails(StringUtils.replaceVowels("On the main menu"))
                .setStartTimestamp(OffsetDateTime.now())
                .setLargeImage("main1")
                .setSmallImage("small1")
                .build());
    }
    public void setRichPrecense(RichPresence rpc) {
        client.sendRichPresence(rpc);
        System.out.println(rpc.toJson().toString());
        current = rpc;
    }
    public RichPresence getRichPrecense() {
        return current;
    }
}

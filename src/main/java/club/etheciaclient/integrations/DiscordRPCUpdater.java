package club.etheciaclient.integrations;

import club.etheciaclient.EtheciaClient;
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
    private EtheciaClient etheciaClient;

    DiscordRPCUpdater(IPCClient client) {
        this.client = client;
            RichPresence.Builder builder = new RichPresence.Builder();
            setRichPrecense(builder.setState(StringUtils.replaceVowels("IGN: " + Minecraft.getMinecraft().getSession().getUsername()))
                    .setDetails(StringUtils.replaceVowels("On the main menu"))
                    .setStartTimestamp(OffsetDateTime.now())
                    .setLargeImage("ethecia_large")
                    .setSmallImage("ethecia_small")
                    .build());
    }

    @Subscribe
    public void onSinglePlayer(JoinSingleplayerEvent e) {
            RichPresence.Builder builder = new RichPresence.Builder();
            setRichPrecense(builder.setSmallImage("ethecia_small").setLargeImage("ethecia_large").setState(StringUtils.replaceVowels("IGN: " + Minecraft.getMinecraft().getSession().getUsername())).setDetails(StringUtils.replaceVowels("Playing Singleplayer")).setStartTimestamp(OffsetDateTime.now()).build());
    }

    @Subscribe
    public void onServerJoin(JoinServerEvent e) {
            RichPresence.Builder builder = new RichPresence.Builder();
            setRichPrecense(builder.setSmallImage("ethecia_small").setLargeImage("ethecia_large").setState("IGN: " + Minecraft.getMinecraft().getSession().getUsername()).setDetails("Playing on " + e.ip).setStartTimestamp(OffsetDateTime.now()).build());
    }

    @Subscribe
    public void onServerLeave(LeaveServerEvent e) {
            RichPresence.Builder builder = new RichPresence.Builder();
            setRichPrecense(builder.setState(StringUtils.replaceVowels("IGN: " + Minecraft.getMinecraft().getSession().getUsername()))
                    .setDetails(StringUtils.replaceVowels("On the main menu"))
                    .setStartTimestamp(OffsetDateTime.now())
                    .setLargeImage("ethecia_large")
                    .setSmallImage("ethecia_small")
                    .build());
    }

    public void setRichPrecense(RichPresence rpc) {
        if (!EtheciaClient.RICH_PRESENCE) {
            EtheciaClient.LOGGER.info("Cancelling rich precense sending");
            return;
        }
        client.sendRichPresence(rpc);
        System.out.println(rpc.toJson().toString());
        current = rpc;
    }

    public void removeRichPresence() {

    }

    public RichPresence getRichPrecense() {
        return current;
    }
}

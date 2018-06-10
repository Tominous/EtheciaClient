package randomclient.integrations;

import com.github.psnrigner.discordrpcjava.*;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiscordRPCManager {

    public static final Logger LOGGER = LogManager.getLogger("Random Client: Discord RPC");

    public static void initDiscordRPC(String ApplicationID) {

        DiscordRpc discordRpc = new DiscordRpc();
        DiscordEventHandler discordEventHandler = new DiscordEventHandler() {
            @Override
            public void ready() {
                LOGGER.info("Discord RPC Ready!");
            }

            @Override
            public void disconnected(ErrorCode errorCode, String message) {
                LOGGER.info(message);
            }

            @Override
            public void errored(ErrorCode errorCode, String message) {
                LOGGER.info(message);
            }

            @Override
            public void joinGame(String joinSecret) {
                LOGGER.info(joinSecret);
            }

            @Override
            public void spectateGame(String spectateSecret) {
                LOGGER.info(spectateSecret);
            }

            @Override
            public void joinRequest(DiscordJoinRequest joinRequest) {
                LOGGER.info(joinRequest);
            }
        };

        try {
            discordRpc.init(ApplicationID, discordEventHandler, true, null);
            Thread.sleep(5000L);
            discordRpc.runCallbacks();

            long start = System.currentTimeMillis() / 1000L;
            long end = System.currentTimeMillis() / 1000L + 14400L;

            for (int i = 0; i < 10000; ++i) {
                DiscordRichPresence discordRichPresence = new DiscordRichPresence();
                discordRichPresence.setState("IGN: " + Minecraft.getMinecraft().thePlayer.getName());
                discordRichPresence.setDetails("Playing on: " + Minecraft.getMinecraft().getCurrentServerData().serverIP);
                discordRichPresence.setStartTimestamp(start);
                discordRichPresence.setEndTimestamp(end);
                discordRichPresence.setLargeImageKey("icon-large");
                discordRichPresence.setSmallImageKey("icon-small");
                discordRichPresence.setInstance(false);

                discordRpc.updatePresence(discordRichPresence);

                Thread.sleep(5000L);

                discordRpc.runCallbacks();

                Thread.sleep(5000L);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


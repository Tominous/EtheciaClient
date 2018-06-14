package club.etheciaclient;

import club.etheciaclient.event.InitializationEvent;
import club.etheciaclient.integrations.DiscordRPCManager;
import club.etheciaclient.mods.AbstractMod;
import club.etheciaclient.utils.EtheciaUtils;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class EtheciaClient {

    public static boolean EVERYTHING_IS_A_VOWEL = false;
    public static final EventBus EVENT_BUS = new EventBus();
    public static EtheciaClient INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger("Ethecia Client");
    private EtheciaUtils utils = new EtheciaUtils();
    public boolean RPC = true;

    @Subscribe
    public void init(InitializationEvent e) throws Throwable {
        LOGGER.info("Starting integrations");
        DiscordRPCManager.initDiscordRPC(455364782761967616L);
        Display.setTitle("EtheciaClient " + utils.getVersion());

        LOGGER.info("Starting Mods");
        AbstractMod.mcInit();
    }
}

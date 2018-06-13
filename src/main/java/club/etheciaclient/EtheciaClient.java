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

public class EtheciaClient {

    public static final boolean EVERYTHING_IS_A_VOWEL = false;
    public static final EventBus EVENT_BUS = new EventBus();
    public static EtheciaClient INSTANCE;
    private EtheciaUtils utils = new EtheciaUtils();
    public static final Logger LOGGER = LogManager.getLogger("Ethecia Client");



    @Subscribe
    public void init(InitializationEvent e) throws Throwable {
        LOGGER.info("Starting integrations");
        DiscordRPCManager.initDiscordRPC(455364782761967616L);
        Display.setTittle("EtheciaClient " + EtheciaUtils.getVersion());
        LOGGER.info("Initializing mods (the sequel)");
        AbstractMod.mcInit();
    }
}

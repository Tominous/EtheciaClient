package club.etheciaclient;

import club.etheciaclient.addons.discover.AddonDiscoverer;
import club.etheciaclient.config.Config;
import club.etheciaclient.config.ConfigOpt;
import club.etheciaclient.event.CloseEvent;
import club.etheciaclient.event.InitializationEvent;
import club.etheciaclient.integrations.DiscordRPCManager;
import club.etheciaclient.mods.AbstractMod;
import club.etheciaclient.utils.EtheciaUtils;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.File;

public class EtheciaClient {
    @ConfigOpt
    public static boolean EVERYTHING_IS_A_VOWEL = false;
    public static Config CONFIG;
    public static final EventBus EVENT_BUS = new EventBus();
    public static EtheciaClient INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger("Ethecia Client");
    private EtheciaUtils utils = new EtheciaUtils();
    @ConfigOpt
    public static boolean RICH_PRESENCE = true;

    @Subscribe
    public void init(InitializationEvent e) throws Throwable {
        LOGGER.info("Initializing config");
        File configFile = new File(Minecraft.getMinecraft().mcDataDir, "ethecia/config.yml");
        if (!configFile.getParentFile().isDirectory()) {
            configFile.getParentFile().mkdirs();
        }
        CONFIG = new Config(configFile);
        CONFIG.register(this);
        LOGGER.info("Starting Addons");
        try {
            AddonDiscoverer.mcInit();
        } catch (Throwable t) {
            LOGGER.warn("Failed to load addons", t);
        }

        LOGGER.info("Starting integrations");
        DiscordRPCManager.initDiscordRPC(455364782761967616L);
        Display.setTitle("EtheciaClient " + utils.getVersion());

        LOGGER.info("Starting Integrated Mods");
        AbstractMod.mcInit();
    }
    @Subscribe
    public void close(CloseEvent e) {
        CONFIG.save();
    }
}

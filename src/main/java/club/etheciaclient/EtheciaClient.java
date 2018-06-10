package club.etheciaclient;

import club.etheciaclient.event.InitializationEvent;
import club.etheciaclient.integrations.DiscordRPCManager;
import club.etheciaclient.utils.EthicaUtils;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EtheciaClient {

    public static final boolean EVERYTHING_IS_A_VOWEL = false;
    public static final EventBus EVENT_BUS = new EventBus();
    public static EtheciaClient INSTANCE;

    @Subscribe
    public void init(InitializationEvent e) {
        EthicaUtils.LOGGER.info("Intializing integrations");
        DiscordRPCManager.initDiscordRPC(455364782761967616L);
    }
}

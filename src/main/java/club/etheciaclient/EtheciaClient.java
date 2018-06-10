package club.etheciaclient;

import club.etheciaclient.event.InitializationEvent;
import club.etheciaclient.integrations.DiscordRPCManager;
import club.etheciaclient.utils.EthicaUtils;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EtheciaClient {

    public final boolean EVERYTHING_IS_A_VOWEL = false;
    public final EventBus EVENT_BUS = new EventBus();
    public static EtheciaClient INSTANCE;
    private EthicaUtils utils = new EthicaUtils();


    @Subscribe
    public void init(InitializationEvent e) {
        utils.LOGGER.info("Starting integrations");
        DiscordRPCManager.initDiscordRPC(455364782761967616L);
    }
}

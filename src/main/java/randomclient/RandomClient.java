package randomclient;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import randomclient.event.InitializationEvent;
import randomclient.integrations.DiscordRPCManager;

public class RandomClient {
    public static final EventBus EVENT_BUS = new EventBus();
    public static final Logger LOGGER = LogManager.getLogger("Random Client");
    public static RandomClient INSTANCE;

    @Subscribe
    public void init(InitializationEvent e) {
        LOGGER.info("Intializing integrations");
        DiscordRPCManager.initDiscordRPC(455364782761967616L);
    }
}

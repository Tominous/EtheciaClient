package randomclient;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import randomclient.event.PreInitializationEvent;

public class RandomClient {
    public static final EventBus EVENT_BUS = new EventBus();
    public static RandomClient INSTANCE;
    public RandomClient() {
        INSTANCE = this;
        EVENT_BUS.register(this);
    }
    @Subscribe
    public void preinit(PreInitializationEvent e) {
        System.out.println("preinit");
    }
}

package club.etheciaclient.mods.itemphysics;

import club.etheciaclient.event.RenderEvent;
import com.google.common.eventbus.Subscribe;

public class PhysicsEventHandler {
    @Subscribe
    public void onRender(RenderEvent e) {
        Physics.tick = System.nanoTime();
    }
}

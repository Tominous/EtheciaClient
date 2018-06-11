package club.etheciaclient.mods;

import club.etheciaclient.EtheciaClient;
import club.etheciaclient.event.CloseEvent;
import club.etheciaclient.mods.itemphysics.ItemPhysics;
import com.google.common.eventbus.Subscribe;
import net.minecraft.launchwrapper.Launch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractMod {
    public static final Class<?>[] TO_LOAD = new Class<?>[] {
        ItemPhysics.class
    };
    public static final List<AbstractMod> MODS = new ArrayList<>();
    public abstract void load() throws Throwable;

    public abstract void registerEventHandler();

    public abstract void close() throws Throwable;

    public abstract String getTransformerClass();

    public abstract Map<String, Object> getDebugInfo();

    public static void initTransformers() {
        for (Class<?> clazz : TO_LOAD) {
            try {
                initTransformer(clazz);
            } catch (Throwable t) {
                EtheciaClient.LOGGER.warn("Failed to register transformer of " + clazz.getSimpleName(), t);
            }
        }
    }

    public static void initTransformer(Class<?> clazz) throws Throwable {
        AbstractMod instance = (AbstractMod) clazz.newInstance();
        if (instance.getTransformerClass() != null) {
            Launch.classLoader.registerTransformer(instance.getTransformerClass());
        }
    }

    public static void init() throws Throwable {
        for (Class<?> clazz : TO_LOAD) {
            registerMod(clazz.newInstance());
        }
    }

    public static void mcInit() {
        for (AbstractMod mod : MODS) {
            mod.registerEventHandler();
            EtheciaClient.LOGGER.info("Registered event handler(s) of mod " + mod.getClass().getSimpleName());
        }
        EtheciaClient.EVENT_BUS.register(new ModListener());
        EtheciaClient.LOGGER.info("Registered all event handlers");
    }

    /**
     * Attempts to register a mod. May work while client is running, although not recommended.
     * @param mo The mod to initialize
     */
    public static void registerMod(Object mo) {
        try {
            AbstractMod mod = (AbstractMod) mo;
            initTransformer(mod.getClass());
            mod.load();
            MODS.add(mod);
            EtheciaClient.LOGGER.info("Successfully initialized mod " + mod.getClass().getSimpleName());
        } catch (Throwable t) {
            EtheciaClient.LOGGER.warn("An error occurred while initializing mod " + mo.getClass().getSimpleName(), t);
        }
    }

    /**
     * Initialization method of registerMod
     * @param mod the mod to initialize
     * @param o if this is passed it's internal
     */
    private static void registerMod(AbstractMod mod, Object o) {
        try {
            mod.load();
            MODS.add(mod);
            EtheciaClient.LOGGER.info("Successfully initialized mod " + mod.getClass().getSimpleName());
        } catch (Throwable t) {
            EtheciaClient.LOGGER.warn("An error occurred while initializing mod " + mod.getClass().getSimpleName(), t);
        }
    }

    public static class ModListener {
        @Subscribe
        public void onClose(CloseEvent e) throws Throwable {
            for (AbstractMod mod : MODS) {
                mod.close();
            }
        }
    }
}

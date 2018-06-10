package club.etheciaclient.launch;

import club.etheciaclient.RandomClient;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientTweaker implements ITweaker {
    private static final List<String> LAUNCH_ARGS = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        LAUNCH_ARGS.addAll(args);
        HashMap<String, Object> extra = new HashMap<>();
        extra.put("gameDir", gameDir);
        extra.put("assetsDir", assetsDir);
        extra.put("version", profile);
        addArgs(extra);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        RandomClient.LOGGER.info("Initializing bootstraps");
        MixinBootstrap.init();
        RandomClient.LOGGER.info("Adding mixin configuration");
        Mixins.addConfiguration("mixins.etheciaclient.json");
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        String[] arr = new String[LAUNCH_ARGS.size()];
        LAUNCH_ARGS.toArray(arr);
        return arr;
    }

    private void addArg(String label, String value) {
        if (!LAUNCH_ARGS.contains("--" + label) && value != null) {
            LAUNCH_ARGS.add("--" + label);
            LAUNCH_ARGS.add(value);
        }
    }

    private void addArg(String arg, File file) {
        addArg(arg, file.getAbsolutePath());
    }

    private void addArgs(Map<String, Object> args) {
        for (String s : args.keySet()) {
            if (args.get(s) instanceof File) {
                addArg(s, (File) args.get(s));
            } else {
                addArg(s, (String) args.get(s));
            }
        }
    }
}

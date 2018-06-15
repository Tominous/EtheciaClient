package club.etheciaclient.addons.discover;

import club.etheciaclient.EtheciaClient;
import club.etheciaclient.addons.Manifest;
import club.etheciaclient.launch.ClientTweaker;
import com.google.gson.Gson;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class AddonDiscoverer {
    private static final File ADDON_DIR = new File(ClientTweaker.GAME_DIR, "ethecia/addons");
    private static AddonDiscoverer INSTANCE;
    private List<Manifest> addons = new ArrayList<>();
    public static void init() throws IOException {
        new AddonDiscoverer().discoverAddons();
    }
    public static void mcInit() {
        for (int i = INSTANCE.addons.size(); i > 0; i--) {
            Manifest m = INSTANCE.addons.get(i);
            EtheciaClient.LOGGER.info("Checking addon main class " + m.mainClass);
            try {
                Class.forName(m.mainClass);
            } catch (ClassNotFoundException e) {
                INSTANCE.addons.remove(m);
            }
        }
    }
    private AddonDiscoverer() {
        AddonDiscoverer.INSTANCE = this;
    }
    private void discoverAddons() throws IOException {
        URL input = AddonDiscoverer.class.getResource("addonManifest.json");
        System.out.println(input + "");
        if (input != null) {
            addons.add(getAddonManifest(input.openStream()));
        }
        checkAddonDir(ADDON_DIR);
    }
    private void checkAddonDir(File dir) {
        EtheciaClient.LOGGER.info("Checking directory " + dir.getPath() + " for addons");
        if (!dir.isDirectory()) {
            if (!dir.mkdirs()) {
                EtheciaClient.LOGGER.warn("Failed to create addon directory");
                return;
            }
            return;
        }
        File[] files = dir.listFiles();
        assert files != null;
        List<File> extraAddonDirs = new ArrayList<>();
        for (File f : files) {
            boolean isAddon = isAddon(f);
            if (!isAddon && f.isDirectory()) {
                EtheciaClient.LOGGER.info("Adding extra addon directory " + f.getName());
                extraAddonDirs.add(f);
            } else if (isAddon) {
                try {
                    Launch.classLoader.addURL(f.toURI().toURL());
                    addons.add(getAddonManifest(f));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        for (File f : extraAddonDirs) {
            checkAddonDir(f);
        }
    }

    private boolean isAddon(File f) {
        if (f.isDirectory()) return false;
        try {
            ZipFile zip = new ZipFile(f);
            if (zip.getEntry("addonManifest.json") == null) {
                return false;
            }
            ZipEntry entry = zip.getEntry("addonManifest.json");
            InputStream input = zip.getInputStream(entry);
            new Gson().fromJson(new InputStreamReader(input), Manifest.class);
            return true;
        } catch (Exception e) {
            EtheciaClient.LOGGER.warn("An error occurred while determining if " + f.getName() + " is an addon.", e);
        }
        return false;
    }

    private Manifest getAddonManifest(File f) {
        try {
            ZipFile zip = new ZipFile(f);
            ZipEntry entry = zip.getEntry("addonManifest.json");
            InputStream input = zip.getInputStream(entry);
            return new Gson().fromJson(new InputStreamReader(input), Manifest.class);
        } catch (Exception e) {
            EtheciaClient.LOGGER.warn("An error occurred while getting the manifest for addon " + f.getName() + ".", e);
        }
        return null;
    }

    private Manifest getAddonManifest(InputStream input) {
        try {
            return new Gson().fromJson(new InputStreamReader(input), Manifest.class);
        } catch (Exception e) {
            EtheciaClient.LOGGER.warn("An error occurred while getting the manifest for an addon.", e);
        }
        return null;
    }

}

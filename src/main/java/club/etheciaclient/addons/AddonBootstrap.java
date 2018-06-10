package club.etheciaclient.addons;

import club.etheciaclient.addons.loader.DefaultAddonLoader;

import java.io.File;
import java.util.ArrayList;

public class AddonBootstrap {
    private static final File ADDON_DIR = new File("addons");

    Phase phase = Phase.NOT_STARTED;

    public static final ArrayList<File> ADDON_RESOURCE_PACKS = new ArrayList<>();

    private ArrayList<File> jars;

    private DefaultAddonLoader loader = new DefaultAddonLoader();

    private WorkspaceAddonLoader workspaceLoader = new WorkspaceAddonLoader();


}

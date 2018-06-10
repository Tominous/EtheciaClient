package club.etheciaclient.addons.loader;

import club.etheciaclient.addons.AddonManifest;

import java.io.File;

public abstract class AddonLoader {
    public abstract AddonManifest load(File file) throws Exception;
}

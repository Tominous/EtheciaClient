package club.etheciaclient.addons;

import java.util.ArrayList;
import java.util.List;

public class AddonManifest {
    public String name;
    public String version;
    public String mainClass;
    public List<String> mixinConfigs;
    public String tweakerClass;
    public List<String> dependencies = new ArrayList<>();
}

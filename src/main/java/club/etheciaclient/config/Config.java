package club.etheciaclient.config;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Config {
    private final Yaml yaml = new Yaml();
    private final List<Object> configObjects = new ArrayList<>();
    private final File file;
    private HashMap<String, Object> config = new HashMap<>();

    public Config(File configFile) {
        this.file = configFile;
        try {
            if (configFile.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null)
                    builder.append(line);

                String done = builder.toString();
                config = yaml.load(done);
            } else {
                config = new HashMap<>();
                saveFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(yaml.dump(config));
            bw.close();
            fw.close();
        } catch (Exception ignored) {
        }
    }

    public void save() {
        for (Object o : configObjects)
            saveToJsonFromRamObject(o);
        saveFile();
    }

    public Object register(Object object) {
        configObjects.add(object);
        loadToClass(object);
        return object;
    }

    private void loadToClass(Object o) {
        loadToClassObject(o);
    }

    private void loadToClassObject(Object object) {
        Class<?> c = object.getClass();
        if (!config.containsKey(c.getName())) config.put(c.getName(), new HashMap<>());
        Arrays.stream(c.getDeclaredFields()).filter(f -> f.isAnnotationPresent(ConfigOpt.class) && config.containsKey(c.getName())).forEach(f -> {
            f.setAccessible(true);
            HashMap<String, Object> tmp = (HashMap<String, Object>) config.get(c.getName());
            if (tmp.containsKey(f.getName())) {
                try {
                    f.set(object, tmp.get(f.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void saveToJsonFromRamObject(Object o) {
        loadToJson(o);
    }

    private void loadToJson(Object object) {
        Class<?> c = object.getClass();
        Arrays.stream(c.getDeclaredFields()).filter(f -> f.isAnnotationPresent(ConfigOpt.class) && config.containsKey(c.getName())).forEach(f -> {
            f.setAccessible(true);
            HashMap<String, Object> classObject = (HashMap<String, Object>) config.get(c.getName());
            try {
                classObject.put(f.getName(), f.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public HashMap<String, Object> getConfig() {
        return config;
    }
}

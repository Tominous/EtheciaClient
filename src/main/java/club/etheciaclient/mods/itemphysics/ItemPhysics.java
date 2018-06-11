package club.etheciaclient.mods.itemphysics;

import club.etheciaclient.EtheciaClient;
import club.etheciaclient.mods.AbstractMod;

import java.util.HashMap;
import java.util.Map;

public class ItemPhysics extends AbstractMod {
    public static float rotateSpeed;

    @Override
    public void load() throws Throwable {
        rotateSpeed = 1.0f; // TODO: Add config for this
    }

    @Override
    public void registerEventHandler() {
        EtheciaClient.EVENT_BUS.register(new PhysicsEventHandler());
    }

    @Override
    public void close() throws Throwable {}

    @Override
    public String getTransformerClass() {
        return "club.etheciaclient.mods.itemphysics.PhysicsTransformer";
    }

    @Override
    public Map<String, Object> getDebugInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("rotateSpeed", rotateSpeed);
        return map;
    }

}

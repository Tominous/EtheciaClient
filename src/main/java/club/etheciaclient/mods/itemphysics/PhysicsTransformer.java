package club.etheciaclient.mods.itemphysics;

import club.etheciaclient.EtheciaClient;
import net.minecraft.launchwrapper.IClassTransformer;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.tree.*;

public class PhysicsTransformer implements IClassTransformer {
    private static boolean obfuscated = true;
    private static String[] names = new String[] { ".", "net/minecraft/client/renderer/entity/RenderEntityItem", "doRender", "net/minecraft/entity/item/EntityItem", "net/minecraft/entity/Entity", "net/minecraft/client/renderer/entity/Render", "setPositionAndRotation2", "onUpdate", "isBurning", "attackEntityFrom", "net/minecraft/util/DamageSource", "health", "onCollideWithPlayer", "net/minecraft/entity/player/EntityPlayer", "interactFirst", "net/minecraft/item/ItemStack", "canBeCollidedWith", "net/minecraft/client/entity/EntityPlayerSP", "dropOneItem" };
    private static String[] namesOb = new String[] { "/", "bjf", "a", "uz", "pk", "biv", "a", "t_", "at", "a", "ow", "e", "d", "wn", "e", "zx", "ad", "bew", "a" };

    private static String patch(String input) {
        if (obfuscated) {
            for (int zahl = 0; zahl < names.length; ++zahl) {
                input = input.replace(names[zahl], namesOb[zahl]);
            }
        }
        return input;
    }

    public byte[] transform(String arg0, String arg1, byte[] arg2) {
        if (arg0.equals("bjf") | arg0.contains("net.minecraft.client.renderer.entity.RenderEntityItem")) {
            obfuscated = !arg0.contains("net.minecraft.client.renderer.entity.RenderEntityItem");
            EtheciaClient.LOGGER.info("[ItemPhysics] Patching " + arg0);
            arg2 = this.replaceMethodDoRender(arg2);
        }
        if (arg0.equals("uz") | arg0.equals("net.minecraft.entity.item.EntityItem")) {
            obfuscated = !arg0.contains("net.minecraft.entity.item.EntityItem");
            arg2 = this.addPositionMethod(arg2);
        }
        return arg2;
    }

    private byte[] replaceMethodDoRender(byte[] bytes) {
        String targetMethodName = patch("doRender");
        String targetDESC = patch("(Lnet/minecraft/entity/item/EntityItem;DDDFF)V");
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        for (MethodNode m : classNode.methods) {
            if (m.name.equals(targetMethodName) && m.desc.equals(targetDESC)) {
                m.localVariables.clear();
                m.instructions.clear();
                m.instructions.add(new VarInsnNode(25, 0));
                m.instructions.add(new VarInsnNode(25, 1));
                m.instructions.add(new VarInsnNode(24, 2));
                m.instructions.add(new VarInsnNode(24, 4));
                m.instructions.add(new VarInsnNode(24, 6));
                m.instructions.add(new VarInsnNode(23, 8));
                m.instructions.add(new VarInsnNode(23, 9));
                m.instructions.add(new MethodInsnNode(184, "club/etheciaclient/mods/itemphysics/Physics", "doRender", patch("(Lnet/minecraft/client/renderer/entity/RenderEntityItem;Lnet/minecraft/entity/Entity;DDDFF)V"), false));
                m.instructions.add(new VarInsnNode(25, 0));
                m.instructions.add(new VarInsnNode(25, 1));
                m.instructions.add(new VarInsnNode(24, 2));
                m.instructions.add(new VarInsnNode(24, 4));
                m.instructions.add(new VarInsnNode(24, 6));
                m.instructions.add(new VarInsnNode(23, 8));
                m.instructions.add(new VarInsnNode(23, 9));
                m.instructions.add(new MethodInsnNode(183, patch("net/minecraft/client/renderer/entity/Render"), targetMethodName, patch("(Lnet/minecraft/entity/Entity;DDDFF)V"), false));
                m.instructions.add(new InsnNode(177));
                break;
            }
        }
        ClassWriter writer = new ClassWriter(1);
        classNode.accept(writer);
        return writer.toByteArray();
    }

    private byte[] addPositionMethod(byte[] bytes) {
        String targetMethodName = patch("setPositionAndRotation2");
        String targetMethodDesc = "(DDDFFIZ)V";
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        MethodNode m = new MethodNode(1, targetMethodName, targetMethodDesc, null, null);
        LabelNode label = new LabelNode();
        m.instructions.add(label);
        m.instructions.add(new VarInsnNode(25, 0));
        m.instructions.add(new VarInsnNode(24, 1));
        m.instructions.add(new VarInsnNode(24, 3));
        m.instructions.add(new VarInsnNode(24, 5));
        m.instructions.add(new VarInsnNode(23, 7));
        m.instructions.add(new VarInsnNode(23, 8));
        m.instructions.add(new VarInsnNode(21, 9));
        m.instructions.add(new VarInsnNode(21, 10));
        m.instructions.add(new MethodInsnNode(184, "club/etheciaclient/mods/itemphysics/Physics", "setPositionAndRotation2", patch("(Lnet/minecraft/entity/item/EntityItem;DDDFFIZ)V"), false));
        m.instructions.add(new InsnNode(177));
        LabelNode label2 = new LabelNode();
        m.instructions.add(label2);
        m.localVariables.add(new LocalVariableNode("this", patch("Lnet/minecraft/entity/item/EntityItem;"), null, label, label2, 0));
        m.localVariables.add(new LocalVariableNode("x", "D", null, label, label2, 1));
        m.localVariables.add(new LocalVariableNode("y", "D", null, label, label2, 3));
        m.localVariables.add(new LocalVariableNode("z", "D", null, label, label2, 5));
        m.localVariables.add(new LocalVariableNode("yaw", "F", null, label, label2, 7));
        m.localVariables.add(new LocalVariableNode("pitch", "F", null, label, label2, 8));
        m.localVariables.add(new LocalVariableNode("posRotationIncrements", "I", null, label, label2, 9));
        m.localVariables.add(new LocalVariableNode("p_180426_10_", "Z", null, label, label2, 10));
        classNode.methods.add(m);
        ClassWriter writer = new ClassWriter(1);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}


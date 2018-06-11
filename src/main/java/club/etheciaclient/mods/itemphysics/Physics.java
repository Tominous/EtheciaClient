package club.etheciaclient.mods.itemphysics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class Physics {
    private static Minecraft mc = Minecraft.getMinecraft();
    static long tick;

    private static Random random = new Random();

    private static ResourceLocation getEntityTexture() {
        return TextureMap.locationBlocksTexture;
    }

    public static void setPositionAndRotation2(EntityItem item, double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_) {
        item.setPosition(x, y, z);
    }

    public static void doRender(RenderEntityItem renderer, Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        double rotation = (System.nanoTime() - tick) / 2500000.0 * ItemPhysics.rotateSpeed;
        if (!mc.inGameHasFocus) {
            rotation = 0.0;
        }
        EntityItem item = (EntityItem)entity;
        ItemStack itemstack = item.getEntityItem();
        int i;
        if (itemstack != null && itemstack.getItem() != null) {
            i = Item.getIdFromItem(itemstack.getItem()) + itemstack.getMetadata();
        }
        else {
            i = 187;
        }
        random.setSeed(i);
        renderer.bindTexture(getEntityTexture());
        renderer.getRenderManager().renderEngine.getTexture(getEntityTexture()).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        IBakedModel ibakedmodel = mc.getRenderItem().getItemModelMesher().getItemModel(itemstack);
        boolean flag2 = ibakedmodel.isGui3d();
        boolean is3D = ibakedmodel.isGui3d();
        int j = getModelCount(itemstack);
        GlStateManager.translate((float)x, (float)y, (float)z);
        if (ibakedmodel.isGui3d()) {
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(item.rotationYaw, 0.0f, 0.0f, 1.0f);
        if (is3D) {
            GlStateManager.translate(0.0, 0.0, -0.08);
        }
        else {
            GlStateManager.translate(0.0, 0.0, -0.04);
        }
        if (is3D || mc.getRenderManager().options != null) {
            if (is3D) {
                if (!item.onGround) {
                    double tRotation = rotation * 2.0;
                    int density = getDensity(item, false);
                    if (density == -3) density = getDensity(item, true);
                    tRotation /= density / 1000 * 10;
                    item.rotationPitch += (float)tRotation;
                }
            }
            if (!Double.isNaN(item.posX) && !Double.isNaN(item.posY) && !Double.isNaN(item.posZ) && item.worldObj != null) {
                if (item.onGround) {
                    item.rotationPitch = 0.0f;
                }
                else {
                    double tRotation = rotation * 2.0;
                    int density = getDensity(item, false);
                    if (density == -3) density = getDensity(item, true);
                    tRotation /= density / 1000 * 10;
                    item.rotationPitch += (float)tRotation;
                }
            }
            GlStateManager.rotate(item.rotationPitch, 1.0f, 0.0f, 0.0f);
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        for (int k = 0; k < j; ++k) {
            if (flag2) {
                GlStateManager.pushMatrix();
                if (k > 0) {
                    float f4 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f5 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f6 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    GlStateManager.translate(f4, f5, f6);
                }
                mc.getRenderItem().renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
            }
            else {
                GlStateManager.pushMatrix();
                mc.getRenderItem().renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
                GlStateManager.translate(0.0f, 0.0f, 0.05375f);
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        renderer.bindTexture(getEntityTexture());
        renderer.getRenderManager().renderEngine.getTexture(getEntityTexture()).restoreLastBlurMipmap();
    }

    private static int getModelCount(ItemStack stack) {
        int i = 1;
        if (stack.stackSize > 48) {
            i = 5;
        }
        else if (stack.stackSize > 32) {
            i = 4;
        }
        else if (stack.stackSize > 16) {
            i = 3;
        }
        else if (stack.stackSize > 1) {
            i = 2;
        }
        return i;
    }
    private static Integer getDensity(EntityItem item, boolean below) {
        double d0 = item.posY + item.getEyeHeight();
        int i2 = MathHelper.floor_double(item.posX);
        int j2 = MathHelper.floor_float((float)MathHelper.floor_double(d0));
        if (below) {
            --j2;
        }
        int k = MathHelper.floor_double(item.posZ);
        BlockPos pos = new BlockPos(i2, j2, k);
        Block block = item.worldObj.getBlockState(pos).getBlock();
        return block.getMaterial().equals(Material.water) ? 1000 : (block.getMaterial().equals(Material.lava) ? 3000 : -3);
    }
}
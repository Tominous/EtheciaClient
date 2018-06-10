package club.etheciaclient.mixins;

import club.etheciaclient.EtheciaClient;
import club.etheciaclient.utils.StringUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Locale;
import java.util.Random;

@Mixin(FontRenderer.class)
public abstract class MixinFontRenderer {
    @Shadow private boolean randomStyle;

    @Shadow private boolean boldStyle;

    @Shadow private boolean strikethroughStyle;

    @Shadow private boolean underlineStyle;

    @Shadow private boolean italicStyle;

    @Shadow private int[] colorCode;

    @Shadow private int textColor;

    @Shadow private float red;

    @Shadow private float blue;

    @Shadow private float green;

    @Shadow private float alpha;

    @Shadow public abstract int getCharWidth(char p_getCharWidth_1_);

    @Shadow public Random fontRandom;

    @Shadow private boolean unicodeFlag;

    @Shadow private float posX;

    @Shadow private float posY;

    @Shadow protected abstract float func_181559_a(char p_181559_1_, boolean p_181559_2_);

    @Shadow public int FONT_HEIGHT;

    /**
     * @author Amplifiable
     */
    @Overwrite
    private void renderStringAtPos(String p_renderStringAtPos_1_, boolean p_renderStringAtPos_2_) {
        for(int lvt_3_1_ = 0; lvt_3_1_ < p_renderStringAtPos_1_.length(); ++lvt_3_1_) {
            char lvt_4_1_ = p_renderStringAtPos_1_.charAt(lvt_3_1_);
            int lvt_5_1_;
            int lvt_6_1_;
            if (lvt_4_1_ == 167 && lvt_3_1_ + 1 < p_renderStringAtPos_1_.length()) {
                lvt_5_1_ = "0123456789abcdefklmnor".indexOf(p_renderStringAtPos_1_.toLowerCase(Locale.ENGLISH).charAt(lvt_3_1_ + 1));
                if (lvt_5_1_ < 16) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (lvt_5_1_ < 0 || lvt_5_1_ > 15) {
                        lvt_5_1_ = 15;
                    }

                    if (p_renderStringAtPos_2_) {
                        lvt_5_1_ += 16;
                    }

                    lvt_6_1_ = this.colorCode[lvt_5_1_];
                    this.textColor = lvt_6_1_;
                    GlStateManager.color((float)(lvt_6_1_ >> 16) / 255.0F, (float)(lvt_6_1_ >> 8 & 255) / 255.0F, (float)(lvt_6_1_ & 255) / 255.0F, this.alpha);
                } else if (lvt_5_1_ == 16) {
                    this.randomStyle = true;
                } else if (lvt_5_1_ == 17) {
                    this.boldStyle = true;
                } else if (lvt_5_1_ == 18) {
                    this.strikethroughStyle = true;
                } else if (lvt_5_1_ == 19) {
                    this.underlineStyle = true;
                } else if (lvt_5_1_ == 20) {
                    this.italicStyle = true;
                } else if (lvt_5_1_ == 21) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    GlStateManager.color(this.red, this.blue, this.green, this.alpha);
                }

                ++lvt_3_1_;
            } else {
                if (EtheciaClient.EVERYTHING_IS_A_VOWEL) lvt_4_1_ = StringUtils.replaceVowels(new String(new char[] {lvt_4_1_})).toCharArray()[0];
                lvt_5_1_ = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".indexOf(lvt_4_1_);
                if (this.randomStyle && lvt_5_1_ != -1) {
                    lvt_6_1_ = this.getCharWidth(lvt_4_1_);

                    char lvt_7_1_;
                    do {
                        lvt_5_1_ = this.fontRandom.nextInt("ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".length());
                        lvt_7_1_ = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".charAt(lvt_5_1_);
                    } while(lvt_6_1_ != this.getCharWidth(lvt_7_1_));

                    lvt_4_1_ = lvt_7_1_;
                }

                float lvt_6_3_ = this.unicodeFlag ? 0.5F : 1.0F;
                boolean lvt_7_2_ = (lvt_4_1_ == 0 || lvt_5_1_ == -1 || this.unicodeFlag) && p_renderStringAtPos_2_;
                if (lvt_7_2_) {
                    this.posX -= lvt_6_3_;
                    this.posY -= lvt_6_3_;
                }

                float lvt_8_1_ = this.func_181559_a(lvt_4_1_, this.italicStyle);
                if (lvt_7_2_) {
                    this.posX += lvt_6_3_;
                    this.posY += lvt_6_3_;
                }

                if (this.boldStyle) {
                    this.posX += lvt_6_3_;
                    if (lvt_7_2_) {
                        this.posX -= lvt_6_3_;
                        this.posY -= lvt_6_3_;
                    }

                    this.func_181559_a(lvt_4_1_, this.italicStyle);
                    this.posX -= lvt_6_3_;
                    if (lvt_7_2_) {
                        this.posX += lvt_6_3_;
                        this.posY += lvt_6_3_;
                    }

                    ++lvt_8_1_;
                }

                Tessellator lvt_9_2_;
                WorldRenderer lvt_10_2_;
                if (this.strikethroughStyle) {
                    lvt_9_2_ = Tessellator.getInstance();
                    lvt_10_2_ = lvt_9_2_.getWorldRenderer();
                    GlStateManager.disableTexture2D();
                    lvt_10_2_.begin(7, DefaultVertexFormats.POSITION);
                    lvt_10_2_.pos((double)this.posX, (double)(this.posY + (float)(this.FONT_HEIGHT / 2)), 0.0D).endVertex();
                    lvt_10_2_.pos((double)(this.posX + lvt_8_1_), (double)(this.posY + (float)(this.FONT_HEIGHT / 2)), 0.0D).endVertex();
                    lvt_10_2_.pos((double)(this.posX + lvt_8_1_), (double)(this.posY + (float)(this.FONT_HEIGHT / 2) - 1.0F), 0.0D).endVertex();
                    lvt_10_2_.pos((double)this.posX, (double)(this.posY + (float)(this.FONT_HEIGHT / 2) - 1.0F), 0.0D).endVertex();
                    lvt_9_2_.draw();
                    GlStateManager.enableTexture2D();
                }

                if (this.underlineStyle) {
                    lvt_9_2_ = Tessellator.getInstance();
                    lvt_10_2_ = lvt_9_2_.getWorldRenderer();
                    GlStateManager.disableTexture2D();
                    lvt_10_2_.begin(7, DefaultVertexFormats.POSITION);
                    int lvt_11_1_ = this.underlineStyle ? -1 : 0;
                    lvt_10_2_.pos((double)(this.posX + (float)lvt_11_1_), (double)(this.posY + (float)this.FONT_HEIGHT), 0.0D).endVertex();
                    lvt_10_2_.pos((double)(this.posX + lvt_8_1_), (double)(this.posY + (float)this.FONT_HEIGHT), 0.0D).endVertex();
                    lvt_10_2_.pos((double)(this.posX + lvt_8_1_), (double)(this.posY + (float)this.FONT_HEIGHT - 1.0F), 0.0D).endVertex();
                    lvt_10_2_.pos((double)(this.posX + (float)lvt_11_1_), (double)(this.posY + (float)this.FONT_HEIGHT - 1.0F), 0.0D).endVertex();
                    lvt_9_2_.draw();
                    GlStateManager.enableTexture2D();
                }

                this.posX += (float)((int)lvt_8_1_);
            }
        }

    }
}

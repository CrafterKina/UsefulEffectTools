package com.mods.kina.UETools.render;

import com.mods.kina.UETools.entity.EntityRidden;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDebug extends Render{
    private static final ResourceLocation boatTextures = new ResourceLocation("textures/entity/boat.png");
    /** instance of ModelBoat for rendering */
    protected ModelBase modelBoat;

    public RenderDebug() {
        shadowSize = 0.5F;
        modelBoat = new ModelBoat();
    }

    public void doRenderZabuton(EntityRidden entityzabuton, double d, double d1, double d2, float f, float f1) {
        /*// レンダリング実装
        // レンダリング
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(180F - f, 0.0F, 1.0F, 0.0F);

        getEntityTexture(entityzabuton);
        GL11.glScalef(-1F, -1F, 1.0F);
        modelBoat.render(entityzabuton, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();*/
        //不可視
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        doRenderZabuton((EntityRidden)entity, d, d1, d2, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return boatTextures;
    }
}

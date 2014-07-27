package com.mods.kina.UETools.proxy;

import com.mods.kina.UETools.entity.EntityRidden;
import com.mods.kina.UETools.render.RenderDebug;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
    public void registerRender(){
        RenderingRegistry.registerEntityRenderingHandler(EntityRidden.class, new RenderDebug());
    }
}

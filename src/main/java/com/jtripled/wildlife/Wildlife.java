package com.jtripled.wildlife;

import com.jtripled.voxen.mod.ModBase;
import com.jtripled.voxen.mod.Registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 * @author jtripled
 */
@Mod(modid = Wildlife.ID, name = Wildlife.NAME, version = Wildlife.VERSION)
@Mod.EventBusSubscriber
public class Wildlife extends ModBase
{
    public static final boolean DEBUG = true;
    
    @Mod.Instance(Wildlife.ID)
    public static Wildlife INSTANCE;
    
    public static final String ID = "wildlife";
    public static final String NAME = "Wildlife";
    public static final String VERSION = "1.0";
    
    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        preInit(event);
    }
    
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        init(event);
    }
    
    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event)
    {
        postInit(event);
    }
    
    @Override
    public Registry createRegistry()
    {
        return new WildlifeRegistry();
    }
}

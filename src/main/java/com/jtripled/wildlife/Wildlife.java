package com.jtripled.wildlife;

import com.jtripled.wildlife.mob.MobEntryDeer;
import com.jtripled.wildlife.mob.MobEntryPenguin;
import com.jtripled.wildlife.mob.MobEntrySquirrel;
import com.jtripled.wildlife.proxy.WildlifeCommonProxy;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

/**
 *
 * @author jtripled
 */
@Mod(modid = Wildlife.ID, name = Wildlife.NAME, version = Wildlife.VERSION)
@Mod.EventBusSubscriber
public class Wildlife
{
    @Mod.Instance(Wildlife.ID)
    public static Wildlife INSTANCE;
    
    @SidedProxy(serverSide = "com.jtripled.wildlife.proxy.WildlifeCommonProxy", clientSide = "com.jtripled.wildlife.proxy.WildlifeClientProxy")
    public static WildlifeCommonProxy PROXY;
    
    public static final String ID = "wildlife";
    public static final String NAME = "Wildlife";
    public static final String VERSION = "1.0";
    
    public static final MobEntryDeer DEER = new MobEntryDeer();
    public static final MobEntryPenguin PENGUIN = new MobEntryPenguin();
    public static final MobEntrySquirrel SQUIRREL = new MobEntrySquirrel();
    
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        DEER.registerSpawn();
        PENGUIN.registerSpawn();
        SQUIRREL.registerSpawn();
    }
    
    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(DEER);
        event.getRegistry().register(PENGUIN);
        event.getRegistry().register(SQUIRREL);
    }
    
    @SubscribeEvent
    public static void onRegisterModels(ModelRegistryEvent event)
    {
        DEER.registerRenderer();
        PENGUIN.registerRenderer();
        SQUIRREL.registerRenderer();
    }
}

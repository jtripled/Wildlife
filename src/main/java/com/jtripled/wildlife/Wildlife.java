package com.jtripled.wildlife;

import com.jtripled.wildlife.mob.entry.*;
import com.jtripled.wildlife.mob.*;
import com.jtripled.wildlife.proxy.WildlifeCommonProxy;
import net.minecraft.util.SoundEvent;
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
    public static final boolean DEBUG = true;
    
    @Mod.Instance(Wildlife.ID)
    public static Wildlife INSTANCE;
    
    @SidedProxy(serverSide = "com.jtripled.wildlife.proxy.WildlifeCommonProxy", clientSide = "com.jtripled.wildlife.proxy.WildlifeClientProxy")
    public static WildlifeCommonProxy PROXY;
    
    public static final String ID = "wildlife";
    public static final String NAME = "Wildlife";
    public static final String VERSION = "1.0";
    
    public static final MobEntryBird BIRD = new MobEntryBird();
    public static final MobEntryButterfly BUTTERFLY = new MobEntryButterfly();
    public static final MobEntryDeer DEER = new MobEntryDeer();
    public static final MobEntryFirefly FIREFLY = new MobEntryFirefly();
    public static final MobEntryPenguin PENGUIN = new MobEntryPenguin();
    public static final MobEntrySquirrel SQUIRREL = new MobEntrySquirrel();
    
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        BIRD.registerSpawn();
        BUTTERFLY.registerSpawn();
        DEER.registerSpawn();
        FIREFLY.registerSpawn();
        PENGUIN.registerSpawn();
        SQUIRREL.registerSpawn();
    }
    
    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(BIRD);
        event.getRegistry().register(BUTTERFLY);
        event.getRegistry().register(DEER);
        event.getRegistry().register(FIREFLY);
        event.getRegistry().register(PENGUIN);
        event.getRegistry().register(SQUIRREL);
    }
    
    @SubscribeEvent
    public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().register(MobBird.AMBIENT_SOUND);
        event.getRegistry().register(MobBird.DEATH_SOUND);
        event.getRegistry().register(MobBird.HURT_SOUND);
        event.getRegistry().register(MobPenguin.AMBIENT_SOUND);
        event.getRegistry().register(MobPenguin.DEATH_SOUND);
        event.getRegistry().register(MobPenguin.HURT_SOUND);
        event.getRegistry().register(MobSquirrel.AMBIENT_SOUND);
        event.getRegistry().register(MobSquirrel.DEATH_SOUND);
        event.getRegistry().register(MobSquirrel.HURT_SOUND);
    }
    
    @SubscribeEvent
    public static void onRegisterModels(ModelRegistryEvent event)
    {
        BIRD.registerRenderer();
        BUTTERFLY.registerRenderer();
        DEER.registerRenderer();
        FIREFLY.registerRenderer();
        PENGUIN.registerRenderer();
        SQUIRREL.registerRenderer();
    }
}

package com.jtripled.wildlife;

import com.jtripled.voxen.mod.RegistrationHandler;
import com.jtripled.voxen.mod.Registry;
import com.jtripled.wildlife.mob.registration.*;

/**
 *
 * @author jtripled
 */
public class WildlifeRegistry implements Registry
{
    public static final MobRegistrationBird BIRD = new MobRegistrationBird();
    public static final MobRegistrationButterfly BUTTERFLY = new MobRegistrationButterfly();
    public static final MobRegistrationDeer DEER = new MobRegistrationDeer();
    public static final MobRegistrationFirefly FIREFLY = new MobRegistrationFirefly();
    public static final MobRegistrationPenguin PENGUIN = new MobRegistrationPenguin();
    public static final MobRegistrationSquirrel SQUIRREL = new MobRegistrationSquirrel();
    
    @Override
    public void onRegisterEntities(RegistrationHandler handler)
    {
        handler.registerEntity(BIRD);
        handler.registerEntity(BUTTERFLY);
        handler.registerEntity(DEER);
        handler.registerEntity(FIREFLY);
        handler.registerEntity(PENGUIN);
        handler.registerEntity(SQUIRREL);
    }
}

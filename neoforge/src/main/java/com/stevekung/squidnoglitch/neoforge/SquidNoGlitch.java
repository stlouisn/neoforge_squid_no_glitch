package com.stevekung.squidnoglitch.neoforge;

import com.stevekung.squidnoglitch.SquidNoGlitchCommon;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(SquidNoGlitchCommon.MOD_ID)
public class SquidNoGlitch {

    public SquidNoGlitch(IEventBus modBus) {
        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(SquidNoGlitchCommon::commonInit);
    }

}
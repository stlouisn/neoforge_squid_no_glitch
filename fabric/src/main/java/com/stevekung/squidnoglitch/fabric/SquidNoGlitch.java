package com.stevekung.squidnoglitch.fabric;

import com.stevekung.squidnoglitch.SquidNoGlitchCommon;
import net.fabricmc.api.ModInitializer;

public class SquidNoGlitch implements ModInitializer {

    @Override
    public void onInitialize() {
        SquidNoGlitchCommon.commonInit();
    }

}
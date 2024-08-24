package com.stevekung.squidnoglitch.fabric;

import net.fabricmc.api.ModInitializer;

import com.stevekung.squidnoglitch.SquidNoGlitchCommon;

public final class SquidNoGlitch implements ModInitializer {

    @Override
    public void onInitialize() {
        SquidNoGlitchCommon.commonInit();
    }

}
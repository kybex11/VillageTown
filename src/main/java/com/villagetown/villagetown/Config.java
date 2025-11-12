package com.villagetown.villagetown;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = VillageTown.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue ENABLE_EXAMPLE_FEATURE = BUILDER
            .comment("Включить ли примерную функцию")
            .define("enableExampleFeature", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean enableExampleFeature;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        enableExampleFeature = ENABLE_EXAMPLE_FEATURE.get();
    }
}

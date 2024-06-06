package com.luxzi.chunklim;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ChunkLim.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<Double> CHUNK_DELAY = BUILDER
        .comment("Dicates how much new chunk generation should be delayed, in milliseconds.")
        .define("chunkDelay", 200D);

    private static final ForgeConfigSpec.ConfigValue<Integer> TRIGGER_TPS = BUILDER
        .comment("At what TPS and below chunk delaying will trigger")
        .define("triggerTps", 10);

    private static final ForgeConfigSpec.BooleanValue ENABLED = BUILDER
        .comment("Enables and disables this mod")
        .define("enabled", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double chunkDelay;
    public static int triggerTps;
    public static boolean enabled;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        chunkDelay = CHUNK_DELAY.get();
        triggerTps = TRIGGER_TPS.get();
        enabled = ENABLED.get();
    }
}
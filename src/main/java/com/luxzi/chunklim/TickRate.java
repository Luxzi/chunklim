package com.luxzi.chunklim;

import net.minecraft.server.MinecraftServer;

public class TickRate {
    private static MinecraftServer SERVER = ChunkLim.SERVER;

    // TODO Implement tickrate calculation
    public static double getTickRate()
    {
       double meanTickTime = 20 * SERVER.getAverageTickTime() / 1000;
       double meanTickRate = Math.min(meanTickTime, 20.0);

       System.out.println("TickRate: " + meanTickRate);
       return meanTickRate;
    }
}

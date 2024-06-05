package com.luxzi.chunklim;

import net.minecraft.server.MinecraftServer;

public class TickRate {
    private static MinecraftServer SERVER = ChunkLim.SERVER;

    public static double getTickRate()
    {
        double meanTickRate = 20.0;
        if (SERVER.getTickCount() % 20 == 0)
        {
           double meanTickTime = SERVER.getAverageTickTime() * 1.0E-6D;
           meanTickRate = Math.min(1000.0/meanTickTime, 20.0);
    
           System.out.println("TickRate: " + meanTickRate);
        }
        
        return meanTickRate;
    }
}

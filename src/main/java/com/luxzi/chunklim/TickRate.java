package com.luxzi.chunklim;

import net.minecraft.server.MinecraftServer;

public class TickRate {
    private static MinecraftServer SERVER = ChunkLim.SERVER;

    private static long mean(long[] values)
    {
        long sum = 0L;
        for (long v : values)
            sum += v;
        return sum / values.length;
    }

    public static double getTickRate()
    {
        double meanTickRate = 20.0;
        if (SERVER.getTickCount() % 20 == 0)
        {
           double meanTickTime = mean(SERVER.tickTimes) * 100.0;
           meanTickRate = 1000.0/meanTickTime;
        }
        
        return meanTickRate;
    }
}

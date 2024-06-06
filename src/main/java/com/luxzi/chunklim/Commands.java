package com.luxzi.chunklim;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class Commands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("chunklim")
            .requires(cs -> cs.hasPermission(4))
            .then(net.minecraft.commands.Commands.literal("enable")
                .executes(ctx -> {
                    Config.ENABLED.set(true);
                    ctx.getSource().sendSystemMessage(Component.literal("[ChunkLim] ChunkLim Enabled!")); // Test response
                    return Command.SINGLE_SUCCESS;
                })
            )
            .then(net.minecraft.commands.Commands.literal("disable")
                .executes(ctx -> {
                    Config.ENABLED.set(false);
                    ctx.getSource().sendSystemMessage(Component.literal("[ChunkLim] ChunkLim Disabled!")); // Test response
                    return Command.SINGLE_SUCCESS;
                })
            )
            .then(net.minecraft.commands.Commands.literal("delay")
                .then(net.minecraft.commands.Commands.argument("delay", DoubleArgumentType.doubleArg())
                    .executes(ctx -> {
                        double delay = DoubleArgumentType.getDouble(ctx, "delay");
                        if (delay < 0) {
                            ctx.getSource().sendFailure(Component.literal("Delay must be greater than 0!"));
                            return -1;
                        }
                        Config.CHUNK_DELAY.set(delay);
                        ctx.getSource().sendSystemMessage(Component.literal("[ChunkLim] Delay set to " + delay + "ms!"));
                        return Command.SINGLE_SUCCESS;
                    })
                )
                .executes(ctx -> {
                    ctx.getSource().sendSystemMessage(Component.literal("[ChunkLim] Current delay: " + Config.chunkDelay + "ms"));
                    return Command.SINGLE_SUCCESS;
                })
            )
            .then(net.minecraft.commands.Commands.literal("triggerTps")
                .then(net.minecraft.commands.Commands.argument("triggerTps", DoubleArgumentType.doubleArg())
                    .executes(ctx -> {
                        double triggerTps = DoubleArgumentType.getDouble(ctx, "triggerTps");
                        if (triggerTps < 0 || triggerTps > 20) {
                            ctx.getSource().sendFailure(Component.literal("Trigger TPS must be between 1 and 20!"));
                            return -1;
                        }
                        Config.TRIGGER_TPS.set(triggerTps);
                        ctx.getSource().sendSystemMessage(Component.literal("[ChunkLim] Trigger TPS set to " + triggerTps + "!"));
                        return Command.SINGLE_SUCCESS;
                    })
                )
                .executes(ctx -> {
                    ctx.getSource().sendSystemMessage(Component.literal("[ChunkLim] Current trigger TPS: " + Config.triggerTps));
                    return Command.SINGLE_SUCCESS;
                })
            )
            .executes(ctx -> {
                ctx.getSource().sendSystemMessage(Component.literal("[ChunkLim] Help Page:\n/chunklim enable - Enables ChunkLim\n/chunklim disable - Disables ChunkLim\n/chunklim delay [delay] - Sets the delay in milliseconds\n/chunklim triggerTps [triggerTps] - Sets the trigger TPS\n"));
                return Command.SINGLE_SUCCESS;
            })
        );
    }
}

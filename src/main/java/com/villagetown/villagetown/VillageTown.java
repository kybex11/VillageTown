package com.villagetown.villagetown;

import com.mojang.logging.LogUtils;
import com.villagetown.villagetown.items.ModItems;
import com.villagetown.villagetown.screen.NPCMenu;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(VillageTown.MODID)
public class VillageTown {

    public static final String MODID = "villagetown";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VillageTown() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Village Town {} Has Been Initialized!", MODID);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP!");
    }

    @SubscribeEvent
    public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        var persistentData = player.getPersistentData();
        var data = persistentData.getCompound(ServerPlayer.PERSISTED_NBT_TAG);

        if (!data.getBoolean("villagetown_hasJoinedBefore")) {
            player.sendSystemMessage(Component.literal("§aДобро пожаловать в новый мир Village Town!"));
            player.sendSystemMessage(Component.literal("§7С текущего момента вы можете создать своё поселение, нажав ПКМ по блоку 'Лагерь'."));
            player.getInventory().add(new net.minecraft.world.item.ItemStack(ModItems.CAMP.get()));
            data.putBoolean("villagetown_hasJoinedBefore", true);
            persistentData.put(ServerPlayer.PERSISTED_NBT_TAG, data);
        }
    }

    @SubscribeEvent
    public void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("vt")
                        .then(Commands.literal("show")
                                .then(Commands.literal("dialog")
                                        .executes(context -> {
                                            net.minecraft.client.Minecraft.getInstance()
                                                    .setScreen(new NPCMenu(Component.literal("Диалог с NPC")));
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("help"))
                                        .executes(context -> {
                                            pla
                                            return 1;
                                        })
        );
    }
}

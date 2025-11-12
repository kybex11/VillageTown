package com.villagetown.villagetown.items;

import com.villagetown.villagetown.VillageTown;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VillageTown.MODID);

    public static final RegistryObject<Item> CAMP = ITEMS.register("camp",
            () -> new com.villagetown.villagetown.items.ItemCamp(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

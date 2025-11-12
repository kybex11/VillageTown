package com.villagetown.villagetown.items;

import com.villagetown.villagetown.screen.CampMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemCamp extends Item {

    public ItemCamp(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!world.isClientSide) {
            player.sendSystemMessage(Component.literal("Вы использовали Camp!"));
            itemstack.shrink(1);
        } else {
            net.minecraft.client.Minecraft.getInstance().setScreen(
                    new CampMenu(Component.literal("Camp Menu"))
            );
        }

        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }


}

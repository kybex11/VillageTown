package com.villagetown.villagetown.npc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Entity extends Mob {
    public Entity(EntityType<? extends Entity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("Привет, я NPC!"));
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    public void die(DamageSource damageSource) {
        super.die(damageSource);

        if (!this.level().isClientSide) {
            String killerName = "неизвестным существом";

            if (damageSource.getEntity() instanceof Player player) {
                killerName = "игроком " + player.getName().getString();
            } else if (damageSource.getEntity() instanceof LivingEntity livingEntity) {
                killerName = livingEntity.getType().getDescription().getString(); 
            }

            Component message = Component.literal("NPC был убит " + killerName + "!");
            this.level().players().forEach(p -> p.sendSystemMessage(message));
        }
    }
}

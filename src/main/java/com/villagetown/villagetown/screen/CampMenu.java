package com.villagetown.villagetown.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class CampMenu extends Screen {

    private Button cancelButton;

    public CampMenu(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();

        int buttonWidth = 80;
        int buttonHeight = 20;
        int x = this.width - buttonWidth - 10;
        int y = 10;

        this.cancelButton = Button.builder(Component.literal("Отмена"), button -> {
            this.onClose();
        }).pos(x, y).size(buttonWidth, buttonHeight).build();

        this.addRenderableWidget(this.cancelButton);
    }

    private void giveCampItemToPlayer() {
        if (Minecraft.getInstance().player != null) {
            var player = Minecraft.getInstance().player;
            var level = player.level();

            Item campItem = net.minecraftforge.registries.ForgeRegistries.ITEMS
                    .getValue(new ResourceLocation("villagetown", "camp"));

            if (campItem != null) {
                ItemStack stack = new ItemStack(campItem);
                if (!player.getInventory().add(stack)) {
                    player.drop(stack, false);
                }
            } else {
                player.displayClientMessage(Component.literal("§cОшибка: предмет villagetown:camp не найден!"), false);
            }
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        String text = "Пожалуйста, выберите стиль лагеря";
        int textWidth = this.font.width(text);
        int textX = (this.width - textWidth) / 2;
        int textY = (this.height / 2) - 150;

        graphics.drawString(this.font, text, textX, textY, 0xFFFFFF);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

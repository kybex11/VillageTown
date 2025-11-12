package com.villagetown.villagetown.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NPCMenu extends Screen {
    private static final ResourceLocation DIALOG_TEXTURE =
        new ResourceLocation("villagetown", "textures/gui/dialog.png");

    private static final ResourceLocation TAB_LEFT_SIDE_TWO_TEXTURE = 
        new ResourceLocation("villagetown", "textures/gui/tab_left_side2.png");

    private final int dialogImageWidth = 256;
    private final int dialogImageHeight = 180;

    private final int tabLeftSideTwoWidth = 26;
    private final int tabLeftSideTwoHeight = 36;

    public NPCMenu(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {

        this.renderBackground(graphics);

        int x = (this.width - dialogImageWidth) / 2;
        int y = (this.height - dialogImageHeight) / 2;

        int tabLeftSideX = (this.width - tabLeftSideTwoWidth) / 2 - 130;
        int tabLeftSideY = (this.height - tabLeftSideTwoHeight) / 2 - 70;
        
        graphics.blit(
            TAB_LEFT_SIDE_TWO_TEXTURE,
            tabLeftSideX, tabLeftSideY,
            0, 0,
            tabLeftSideTwoWidth, tabLeftSideTwoHeight,
            tabLeftSideTwoWidth, tabLeftSideTwoHeight
        );

        graphics.blit(
            DIALOG_TEXTURE,
            x, y,
            0, 0,
            dialogImageWidth, dialogImageHeight,
            dialogImageWidth, dialogImageHeight
        );


        graphics.drawCenteredString(this.font, this.title, this.width / 2, y + 10, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
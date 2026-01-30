package net.shadow.farmersmarket;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Set;

public class FarmersMarketClient implements ClientModInitializer {

    public static final Identifier WYRM_RIPTIDE_TEXTURE = Identifier.of(FarmersMarket.MOD_ID, "textures/entity/wyrm_spear.png");

    @Override
    public void onInitializeClient() {

    }

}

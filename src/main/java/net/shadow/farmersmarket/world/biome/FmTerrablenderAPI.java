package net.shadow.farmersmarket.world.biome;

import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.world.biome.surface.FMMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class FmTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, FarmersMarket.MOD_ID, FMMaterialRules.makeRules());
    }
}
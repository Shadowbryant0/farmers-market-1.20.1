package net.shadow.farmersmarket.item.custom.misc;



import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSources;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;

public class Tide extends Item {
    public Tide(Settings settings) {
        super(settings);
    }
    private static final int COOLDOWN_TICKS = 24000;
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
BlockPos Pos = context.getBlockPos();
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
//            if(serverWorld.getBiome(Pos) == BiomeTags.IS_DEEP_OCEAN)    {
//                serverWorld.setWeather(0, 12000, true, true); // Rain for 5 minutes
//            if (player != null) {
//                player.sendMessage(Text.literal("Let it Storm."), true);
//                {
//        player.getItemCooldownManager().set(this, 13000);
//    }
//            }
//                return ActionResult.SUCCESS;
//            }
            serverWorld.setWeather(0, 12000, true, true); // Rain for 5 minutes
            if (player != null) {
                player.sendMessage(Text.literal("Let it Storm."), true);
                player.addExperienceLevels(1);
                {
                    player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
                }
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
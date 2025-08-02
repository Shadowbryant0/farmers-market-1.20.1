package net.shadow.farmersmarket.item.custom;



import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class Rainmaker extends Item {
    public Rainmaker(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            serverWorld.setWeather(0, 12000, true, true); // Rain for 5 minutes
            if (player != null) {
                player.sendMessage(Text.literal("Let it Storm."), true);
                player.addExperienceLevels(1);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
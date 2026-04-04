package net.shadow.farmersmarket.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class AirDragComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public AirDragComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int DRAG = 0;
    public static int DRAGMAX = 100;

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        DRAG = nbtCompound.getInt("drag");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putInt("drag", DRAG);
    }

    @Override
    public void tick() {

        if(DRAG>0) {
            DRAG--;
        }
        if(player.isInsideWaterOrBubbleColumn()||player.isOnGround()){
            DRAG=0;
        }

    }
    public static void DRAG(){
        DRAG = 100;
    }
    public static Boolean DRAGBOOLIAN() {
        return DRAG>0;
    }
}

package net.shadow.farmersmarket.item.components;

import net.minecraft.nbt.NbtCompound;

public interface IntComponent extends Component, dev.onyxstudios.cca.api.v3.component.Component {
    // Your own component interface. You still need to register it!
        int getValue();
        void increment();
    }

    class RandomIntComponent implements IntComponent {
        private int value = (int) (Math.random() * 50); // random initial value because why not
        @Override public int getValue() { return this.value; }
        @Override public void increment() { this.value++; }
        @Override public void readFromNbt(NbtCompound tag) { this.value = tag.getInt("value"); }
        @Override public void writeToNbt(NbtCompound tag) { tag.putInt("value", this.value); }
    }


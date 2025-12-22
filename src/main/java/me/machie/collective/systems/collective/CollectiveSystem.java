package me.machie.collective.systems.collective;

import meteordevelopment.meteorclient.systems.System;
import meteordevelopment.meteorclient.systems.Systems;
import net.minecraft.nbt.NbtCompound;

public class CollectiveSystem extends System<CollectiveSystem> {
    public CollectiveSystem() {
        super("collective");
    }

    public static CollectiveSystem get() {
        return Systems.get(CollectiveSystem.class);
    }

    @Override
    public NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();

        return tag;
    }

    @Override
    public CollectiveSystem fromTag(NbtCompound tag) {
        return this;
    }
}

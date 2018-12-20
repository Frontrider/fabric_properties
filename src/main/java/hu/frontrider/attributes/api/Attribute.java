package hu.frontrider.attributes.api;

import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

public interface Attribute {
    void read(CompoundTag tag);

    CompoundTag write(CompoundTag tag);
}

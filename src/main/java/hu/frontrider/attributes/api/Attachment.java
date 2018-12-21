package hu.frontrider.attributes.api;

import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

public interface Attachment {
    void read(CompoundTag tag);

    CompoundTag write(CompoundTag tag);
}

package hu.frontrider.attachments.api;

import net.minecraft.nbt.CompoundTag;

public interface Attachment {
    void read(CompoundTag tag);

    CompoundTag write(CompoundTag tag);
}

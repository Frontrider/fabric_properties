package hu.frontrider.attachments.mixin.internal;

import hu.frontrider.attachments.api.Attachment;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public interface SidedAttachmentHolder {
    void addAttribute(String key, Attachment attribute, Class type, Direction side);

    <T> Optional<T> getAttribute(String key, Class<T> type, Direction side);

    boolean removeAttribute(String key, Direction side);

}

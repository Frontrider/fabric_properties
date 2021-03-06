package hu.frontrider.attachments.mixin;


import hu.frontrider.attachments.api.Attachment;
import hu.frontrider.attachments.internal.AttachmentContainer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(value = {BlockEntity.class})
public class BlockEntityAttachmentMixin {

    private AttachmentContainer[] attachmentContainers;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstructed(CallbackInfo ci) {
        attachmentContainers = new AttachmentContainer[6];
    }
    public void addAttribute(String key, Attachment attribute, Class type, Direction side) {
        AttachmentContainer attachmentContainer = attachmentContainers[side.ordinal()];
        if(attachmentContainer == null) {
            attachmentContainer = new AttachmentContainer();
            attachmentContainers[side.ordinal()] = attachmentContainer;
        }

        attachmentContainer.addAttribute(key, attribute, type);
    }

    public <T> Optional<T> getAttribute(String key, Class<T> type, Direction side) {
        AttachmentContainer attachmentContainer = attachmentContainers[side.ordinal()];
        if(attachmentContainer == null) {
            attachmentContainer = new AttachmentContainer();
            attachmentContainers[side.ordinal()] = attachmentContainer;
            return Optional.empty();
        }

        return attachmentContainer.getAttribute(key,type);
    }

    public boolean removeAttribute(String key, Direction side) {
        AttachmentContainer attachmentContainer = attachmentContainers[side.ordinal()];
        if(attachmentContainer == null) {
            attachmentContainer = new AttachmentContainer();
            attachmentContainers[side.ordinal()] = attachmentContainer;
            return false;
        }
        return attachmentContainer.removeAttribute(key);
    }
}

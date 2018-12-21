package hu.frontrider.attributes.mixin;


import hu.frontrider.attributes.api.Attachment;
import hu.frontrider.attributes.internal.AttachmentContainer;
import net.minecraft.block.Block;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(value = {Block.class})
public class BlockAttachmentMixin{

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

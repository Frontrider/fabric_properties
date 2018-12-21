package hu.frontrider.attachments.mixin;

import hu.frontrider.attachments.api.Attachment;
import hu.frontrider.attachments.internal.AttachmentContainer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

/**
 * The attachment implementation for the entity class.
 * We don't have interfaces, because we want to keep these declarations separate, tuned for the class that we are mixing into.
 * */
@Mixin(Entity.class)
public class EntityAttachmentMixin {
    private AttachmentContainer attachmentContainer;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstructed(CallbackInfo ci) {
        attachmentContainer = new AttachmentContainer();
    }


    public void addAttribute(String key, Attachment attribute, Class type) {
        attachmentContainer.addAttribute(key, attribute, type);
    }

    public <T> Optional<T> getAttribute(String key, Class<T> type) {
        return attachmentContainer.getAttribute(key, type);
    }

    public boolean removeAttribute(String key) {
        return attachmentContainer.removeAttribute(key);
    }
}

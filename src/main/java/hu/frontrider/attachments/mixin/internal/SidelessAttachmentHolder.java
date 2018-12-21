package hu.frontrider.attachments.mixin.internal;

import hu.frontrider.attachments.api.Attachment;

import java.util.Optional;

public interface SidelessAttachmentHolder {
    void addAttribute(String key, Attachment attribute, Class type);

    <T> Optional<T> getAttribute(String key, Class<T> type);

    boolean removeAttribute(String key);

}

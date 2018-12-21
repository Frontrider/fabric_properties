package hu.frontrider.attachments.internal;

import hu.frontrider.attachments.api.Attachment;

import java.util.Optional;

/**
 * The attachment container template
 * */
public interface AttachmentContainerInterface {

    void addAttribute(String key, Attachment attribute, Class type);

    <T> Optional<T> getAttribute(String key, Class<T> type);

    boolean removeAttribute(String key);

}

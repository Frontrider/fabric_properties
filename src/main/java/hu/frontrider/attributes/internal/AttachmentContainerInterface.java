package hu.frontrider.attributes.internal;

import hu.frontrider.attributes.api.Attachment;

import java.util.Optional;

/**
 * The attachment container template
 * */
public interface AttachmentContainerInterface {

    void addAttribute(String key, Attachment attribute, Class type);

    <T> Optional<T> getAttribute(String key, Class<T> type);

    boolean removeAttribute(String key);

}

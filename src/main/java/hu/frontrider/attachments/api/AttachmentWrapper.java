package hu.frontrider.attachments.api;

public interface AttachmentWrapper extends Attachment {
    /**
     * Gives you the attribute that you're going to wrap.
     * */
    void wrap(Attachment attribute);

    /**
     * Return the attribute that you are wrapping.
     * YOU CAN NOT RETURN NULL!!
     * */
    Attachment getWrapped();
}

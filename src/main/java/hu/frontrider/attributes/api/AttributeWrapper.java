package hu.frontrider.attributes.api;

public interface AttributeWrapper extends Attribute {
    /**
     * Gives you the attribute that you're going to wrap.
     * */
    void wrap(Attribute attribute);

    /**
     * Return the attribute that you are wrapping.
     * YOU CAN NOT RETURN NULL!!
     * */
    Attribute getWrapped();
}

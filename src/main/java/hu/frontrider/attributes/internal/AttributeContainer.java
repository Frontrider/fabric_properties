package hu.frontrider.attributes.internal;


import hu.frontrider.attributes.api.Attribute;
import hu.frontrider.attributes.api.AttributeWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

public class AttributeContainer implements AttributeContainerInterface {
    private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
    private Map<String, AttributeEntry> attributes;

    public AttributeContainer() {
        attributes = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getAttribute(String key, Class<T> type) {
        if (attributes.containsKey(key)) {
            AttributeEntry attribute = attributes.get(key);
            if (type == attribute.type) {
                return Optional.of((T)attribute.getAttribute());
            } else {
                logger.warning("Invalid type " + attribute.getClass().getCanonicalName() + " for " + key);
            }
        }
        return Optional.empty();
    }

    public void addAttribute(String key, Attribute attribute,Class type) {
        if (attributes.containsKey(key)) {
            if (attribute instanceof AttributeWrapper) {
                final AttributeEntry existingAttribute = attributes.get(key);
                if(attribute.getClass().isInstance(existingAttribute)){
                    ((AttributeWrapper) attribute).wrap(existingAttribute.attribute);
                    attributes.put(key,new AttributeEntry(attribute, type));
                }else{
                    logger.warning("Attribute type mismatch "+type.getCanonicalName() + " is not matching "+existingAttribute.type.getCanonicalName()+". Changes have been discarded.");
                }
            } else {
                logger.warning(attribute.getClass().getCanonicalName() + " can not wrap other attributes, and the key is already in use. Changes have been discarded.");
            }
        } else {
            attributes.put(key, new AttributeEntry(attribute, type));
        }
    }

    public void removeAttribute(String key){

    }

    class AttributeEntry{
        private final Attribute attribute;
        private final Class type;

        /**
         * @param attribute the object we want to use
         * @param type  the type that we're going to treat our attribute. MUST BE AN INTERFACE!
         * */
        AttributeEntry(Attribute attribute,Class type){
            this.type = type;
            if(!type.isInterface())
                throw new AttributeException(type.getCanonicalName()+" is not an interface!");

            if(type.isInstance(attribute)){
                this.attribute = attribute;
            }else{
                throw new AttributeException(attribute.getClass().getCanonicalName()+" is not an instance of "+type.getCanonicalName()+"!");
            }

        }

        public Attribute getAttribute() {
            return attribute;
        }

        public Class getType() {
            return type;
        }

        @Override
        public String toString() {
            return "AttributeEntry{" +
                    "attribute=" + attribute +
                    ", type=" + type +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AttributeEntry that = (AttributeEntry) o;
            return Objects.equals(getAttribute(), that.getAttribute()) &&
                    Objects.equals(getType(), that.getType());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getAttribute(), getType());
        }
    }

}

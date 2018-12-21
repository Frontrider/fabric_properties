package hu.frontrider.attributes.api;

import hu.frontrider.attributes.internal.AttachmentContainer;
import hu.frontrider.attributes.internal.AttachmentException;

import java.util.*;
import java.util.function.Function;

public class Registry<T> {

    private Map<Class<T>, List<EntryContainer>> attributes;

    public Registry() {
        attributes = new HashMap<>();
    }

    /**
     * Adds an attribute to an entry, upon it's creation.
     *
     * @param type    the concrete class of the entry that we attach to.
     * @param creator a function tha can build the new parameter that we attach
     */
    public void delegateToEntry(Class<T> type, String name, Function<T, Attachment> creator) {
        EntryContainer entryContainer = new EntryContainer(name, creator, type);
        if (attributes.containsKey(type)) {
            attributes.get(type).add(entryContainer);
        } else {
            LinkedList<EntryContainer> entryContainers = new LinkedList<>();
            entryContainers.add(entryContainer);
            attributes.put(type, entryContainers);
        }
    }

    public T attachToEntry(T entry) {
        Class<?> entryClass = entry.getClass();
        if (!(entry instanceof AttachmentContainer)) {
            throw new AttachmentException(entryClass.getCanonicalName() + " is not an attribute container");
        }

        if (attributes.containsKey(entryClass)) {
            for (EntryContainer entryContainer : attributes.get(entryClass)) {
                Attachment attribute = entryContainer.creator.apply(entry);
                ((AttachmentContainer) entry).addAttribute(entryContainer.name, attribute, entryContainer.type);
            }
        }

        return entry;
    }

    private class EntryContainer {
        private final String name;
        private final Function<T, Attachment> creator;
        private final Class type;

        public EntryContainer(String name, Function<T, Attachment> creator, Class type) {
            this.name = name;
            this.creator = creator;
            this.type = type;
        }

    }
}

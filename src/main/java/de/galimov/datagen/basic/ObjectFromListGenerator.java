package de.galimov.datagen.basic;

import java.util.Iterator;
import java.util.List;

public class ObjectFromListGenerator<T> extends AbstractDataGenerator<T> {
    private final List<T> source;
    private Iterator<T> iterator;

    public ObjectFromListGenerator(List<T> source) {
        this.source = source;
        iterator = source.iterator();
    }

    @Override
    protected T generateInternal() {
        if(source.isEmpty()) {
            return null;
        } else {
            moveIteratorByOne();
            return iterator.next();
        }
    }

    private void moveIteratorByOne() {
        if(!iterator.hasNext()) {
            iterator = source.iterator();
        }
    }

    @Override
    public void setSeed(long seed) {
        iterator = source.listIterator((int) (seed  % source.size()));
    }

    @Override
    public int getSize() {
        return source.size();
    }
}
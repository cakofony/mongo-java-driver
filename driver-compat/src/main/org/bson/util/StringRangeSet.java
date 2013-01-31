/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bson.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// TODO: can we get rid of this class?
public class StringRangeSet implements Set<String> {

    private final int size;

    private static final int NUMSTR_LEN = 100;
    private static final String[] NUMSTRS = new String[100];

    static {
        for (int i = 0; i < NUMSTR_LEN; ++i) {
            NUMSTRS[i] = String.valueOf(i);
        }
    }

    public StringRangeSet(final int size) {
        this.size = size;
    }

    public int size() {
        return size;
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {

            int index = 0;

            public boolean hasNext() {
                return index < size;
            }

            public String next() {
                if (index < NUMSTR_LEN) {
                    return NUMSTRS[index++];
                }
                return String.valueOf(index++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean add(final String e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(final Object o) {
        final int t = Integer.parseInt(String.valueOf(o));
        return t >= 0 && t < size;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        final String[] array = new String[size()];
        for (int i = 0; i < size; ++i) {
            if (i < NUMSTR_LEN) {
                array[i] = NUMSTRS[i];
            }
            else {
                array[i] = String.valueOf(i);
            }
        }
        return array;
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        throw new UnsupportedOperationException();
    }
}

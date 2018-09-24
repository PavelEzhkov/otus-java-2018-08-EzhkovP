import java.util.*;

public class ArrayList<E>  implements List<E>{
    private int size;
    private transient Object[] elementData;
    private static final int DEFAULT_CAPACITY = 16;


    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = new Object[initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        if(elementData.length == size){
            increaseCapacity();}
        elementData[size] = e;
        size++;
        return true;
    }

    private void increaseCapacity() {
        Object[] increase = new Object[elementData.length+10];
        System.arraycopy(elementData,0,increase,0,elementData.length);
        elementData = increase;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }
    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for (int i = 0; i < size ; i++)
            elementData[i] = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index<0||index >= size){
            throw new IndexOutOfBoundsException();
        }
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        if (index<0||index >= size){
            throw new IndexOutOfBoundsException();
        }
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new NewIterator<>();
    }

    private class  NewIterator<E> implements ListIterator<E> {
        int cursor;
        ArrayList<E> list;

        NewIterator(){}

        NewIterator(ArrayList<E> list, int index){
            this.list = list;
            this.cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if (cursor>= size)
                throw new NoSuchElementException();
            return (E) elementData[cursor++];
        }

        @Override
        public boolean hasPrevious() {throw new UnsupportedOperationException();}

        @Override
        public E previous() {throw new UnsupportedOperationException();}

        @Override
        public int nextIndex() {throw new UnsupportedOperationException();}

        @Override
        public int previousIndex() {throw new UnsupportedOperationException();}

        @Override
        public void remove() {throw new UnsupportedOperationException();}

        @Override
        public void set(E e) {
            list.set(cursor-1, e);
        }

        @Override
        public void add(E e) {throw new UnsupportedOperationException();}
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        return new NewIterator<E>(this, index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}

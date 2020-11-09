import java.util.ArrayList;
import java.util.List;


public class Heap<T extends IHeapItem<T>>{

    public List<T> items;
    public int currentItemCount;

    public T get(int i) {
        return items.get(i);
    }
    public void clear() {
        items.clear();
    }
    public int size() {
        return items.size();
    }
    public void remove(T o) {
        items.remove(o);
    }

    public Heap(int maxHeapSize) {
        items = new ArrayList<T>(maxHeapSize);
    }
    
    public void add(T item) {
        item.HeapIndex = currentItemCount;
        items.set(currentItemCount, item);
        sortUp(item);
        currentItemCount++;
    }
    public T removeFirst() {
        T firstItem = items.get(0);
        currentItemCount--;
        items.set(0, items.get(currentItemCount));
        items.get(0).HeapIndex = 0;
        sortUp(items.get(0));
        return firstItem;
    }
    public void updateItem(T item) {
        sortUp(item);
    }

    public int count() {
        return currentItemCount;
    }

    public boolean contains(T item) {
        return item.equals(items.get(item.HeapIndex));
    }
    void sortDown(T item) {
        while(true) {
            int childIndexLeft = item.HeapIndex * 2 + 1;
            int childIndexRight = item.HeapIndex * 2 + 2;
            int swapIndex = 0;
            if (childIndexLeft < currentItemCount) {
                swapIndex = childIndexLeft;
                if(childIndexRight < currentItemCount) {
                    if(items.get(childIndexLeft).compareTo(items.get(childIndexRight)) < 0) {
                        swapIndex = childIndexRight;
                    }
                }
                if(item.compareTo(items.get(swapIndex)) < 0) {
                    swap(item, items.get(swapIndex));
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
    public void sortUp(T item) {
        int parentIndex = (item.HeapIndex-1)/2;
        while(true) {
            T parentItem = items.get(parentIndex);
            if(item.compareTo(parentItem) > 0) {
                swap(item, parentItem);
            } else {
                break;
            }
            parentIndex = (item.HeapIndex-1)/2;
        }
    }
    public void swap(T itemA, T itemB) {
        items.set(itemA.HeapIndex, itemB);
        items.set(itemB.HeapIndex, itemA);
        int itemAindex = itemA.HeapIndex;
        itemA.HeapIndex = itemB.HeapIndex;
        itemB.HeapIndex = itemAindex;

    }
}

class IHeapItem<T> implements Comparable<T> {
    public int HeapIndex;

    @Override
    public int compareTo(T o) {
        return 0;
    }
}

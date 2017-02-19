/**
 * Code written by Andres Cremisini.
 */

package PriorityQueues;
import java.util.Comparator;

public class ListDoubleEndedPriorityQueue<AnyType> implements DoubleEndedPriorityQueue {

    private Comparator<?super AnyType> cmp;
    private Node<AnyType> first = null;
    private Node<AnyType> last = null;
    private int size;

    public ListDoubleEndedPriorityQueue(){
        makeEmpty();
    }

    //Private Classes:
    private class myComparator implements Comparator<AnyType>{
        /**
         * Subclasses of Number compared with <, >. Any other Object is represented as a String and
         * compared using o1.compareTo(o2).
         *
         * @param o1 first object to be compared
         * @param o2 second object to be compared
         * @return 1 iff o1>o2, -1 iff o1<o2, 0 iff o1 == o2
         *
         */
        @Override
        public int compare(Object o1, Object o2) {

            if(o1 instanceof Number && o2 instanceof Number){
                Number v1 = ((Number)o1).doubleValue();
                Number v2 = ((Number)o2).doubleValue();

                if (v1.doubleValue() > v2.doubleValue())
                    return 1;
                else if (v1.doubleValue() < v2.doubleValue())
                    return -1;
                else
                    return 0;
            }
            else{
                String v1 = o1.toString();
                String v2 = o2.toString();
                return v1.compareTo(v2);
            }
        }
    }
    private static class Node<AnyType> {
        public Node(Object d, Node<AnyType> p, Node<AnyType> n){
            data = d; prev = p; next = n;
        }

        public Object data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

    }

    //Complementary methods(not from interface)

    /**
     *
     * @return current size of list
     */
    public int size(){
        return size;
    }

    //~~Private helpers for add(Object x)~~
    /**
     * Method to set either first or last node, depending on idx parameter
     * @param idx if idx = 0, then first.data is filled with x, if idx = 1 last.data is filled
     * @param x object from add(Object x)
     */
    private void setFL(int idx, Object x){
        if(idx == 0) {
            first.data = x;
            size++;
        }
        else if(idx == 1) {
            last.data = x;
            size++;
        }
    }
    /**
     * Used if parameter is less than the first node
     * @param x object from add(Object x)
     * @return (lt = less than) true if x is less than first node and has now been added as first node
     */
    private boolean ltFirst(Object x){
        if(cmp.compare((AnyType) x, (AnyType) first.data) < 0){
            if(last.data == null){
                last.data = first.data;
                first.data = x;
                size++;
            }
            else{
                Node<AnyType> n = new Node<>(x, null, first);
                first.prev = n;
                first = n;
                size++;
            }
            return true;
        }
        else
            return false;
    }
    /**
     * Used if parameter is greater than the first node
     * @param x object from add(Object x)
     * @return (gt = greater than) true if x is greater than last node and has now been added as last node
     */
    private boolean gtLast(Object x){
        if(cmp.compare((AnyType) x, (AnyType) last.data) > 0){
            Node<AnyType> n = new Node(x, last, null);
            last.next = n;
            last = n;
            size++;
            return true;
        }
        else
            return false;
    }
    /**
     * Used to insert parameter in its proper place in the list (before pointer returned by getPointer)
     * @param p add before this node in the list
     * @param x element to be added
     */
    private void addBefore(Node<AnyType> p, Object x){
        Node<AnyType> n = new Node<>(x, p.prev, p);
        n.prev.next = n;
        p.prev = n;
        size++;
    }
    /**
     * gets proper place to insert the passed in element
     * @param p the node from which to begin search for appropriate position (always first.next)
     * @param x the object to be inserted by add(Object x)
     * @return a pointer to the node that will directly follow the object to be added by add(Object x)
     *
     * Note: the passed-in node is first.next(so never null, see add method) and the value to be added
     *  will always be <= value in last node (so last node doesn't have to be replaced)
     */
    private Node<AnyType> getPointer(Node<AnyType> p, AnyType x){
        if(cmp.compare(x, (AnyType) p.data) <= 0){
            return p;
        }
        else
            return getPointer(p.next, x);
    }

    //~~Overridden Methods~~

    /**
     * Empties List
     */
    @Override
    public void makeEmpty() {
        first = new Node<>(null, null, null);
        last = new Node<>(null, first, null);
        first.next = last;
        size = 0;
    }
    /**
     * @param x object to be added
     *
     * Note: uses private helper methods above to add the object in correct place (based on increasing order)
     * and maintain first and last pointers
     */
    @Override
    public void add(Object x){
        cmp = new myComparator();
        if(size() == 0){
            setFL(0, x);
        }
        else if(size() == 1){
            if(ltFirst(x));
            else
                setFL(1, x);
        }
        else{
            if(ltFirst(x));
            else if(gtLast(x));
            else {
                addBefore(getPointer(first.next, (AnyType) x), x);
            }
        }
    }
    /**
     * Deletes object with smallest value from list
     * @return smallest value in list (which was deleted)
     */
    @Override
    public Object deleteMin() {
        Object min;
        if(isEmpty())
            return "List is empty";
        else if(size() == 1){
            min = first.data;
            first.data = null;
            size--;
        }
        else if (size() == 2){
            min = first.data;
            first.data = last.data;
            last.data = null;
            size--;
        }
        else{
            min = first.data;
            first.next.prev = null;
            first = first.next;
            size--;
        }
        return min;
    }
    /**
     * Deletes object with largest value from list
     * @return largest value in list (which was deleted)
     */
    @Override
    public Object deleteMax() {
        Object max;
        if(isEmpty())
            return "List is empty";
        if(size() == 1){
            max = first.data;
            first.data = null;
            size--;
        }
        else if (size() == 2){
            max = last.data;
            last.data = null;
            size--;
        }
        else{
            max = last.data;
            last.prev.next = null;
            last = last.prev;
            size--;
        }
        return max;
    }
    /**
     *
     * @return smallest-valued object in list
     */
    @Override
    public Object findMin() {
        if(isEmpty())
            return "List is empty";
        else
            return first.data;
    }
    /**
     *
     * @return largest-valued object in list
     */
    @Override
    public Object findMax() {
        if(isEmpty())
            return "List is empty";
        else if(size() == 1)
            return first.data;
        else
            return last.data;
    }
    /**
     *
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        if(size() == 0)
            return true;
        else
            return false;
    }
    /**
     *
     * @return objects in list in sorted order
     */
    @Override
    public String toString(){
        Node<AnyType> p = first;
        String result = "";
        while(p != null){
            result+= p.data + " ";
            p = p.next;
        }
        return result;
    }
}

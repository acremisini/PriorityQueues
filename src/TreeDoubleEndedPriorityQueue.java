/**
 * Code written by Andres Cremisini. All work is my own.
 */

package PriorityQueues;
import java.util.Comparator;

public class TreeDoubleEndedPriorityQueue<AnyType> implements DoubleEndedPriorityQueue {

    private Comparator<?super AnyType> cmp;
    private Node<AnyType> root = null;
    private StringBuffer contents = new StringBuffer();

    public TreeDoubleEndedPriorityQueue(){
        makeEmpty();
    }

    /**
     * Implementation of toString
     * @return List contents in ascending order
     */
    @Override
    public String toString(){
        contents.setLength(0);
        toString(root, contents);
        return contents.toString();
    }
    /**
     * Private helper for toString. Populates a StringBuffer with all list elements in ascending order,
     * using Inorder traversal
     * @param r root node to begin recursion
     * @param c StringBuffer to hold all list contents
     */
    private void toString(Node<AnyType> r, StringBuffer c){
        if (r == null)
            return;
        else{
            toString(r.left, c);
            c.append(r.items.dump());
            toString(r.right, c);
        }
    }

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

    /**
     * This is basically a big node with the regular left, right and items (data) variables,
     * but the items variable (class List) is essentially a LinkedList implementation of a Stack, with a couple
     * of extra methods to satisfy the problem definition (pushDub, singleVal and dump)
     * @param <AnyType> Generic type
     */
    private static class Node<AnyType> {
        private Node<AnyType> left;
        private Node<AnyType> right;
        private List<AnyType> items;

        public Node(AnyType data) {
            left = right = null;
            items = new List(data);
        }
        /**
         * The individual elements in a Node's item list
         * @param <AnyType>
         */
        private static class ListNode<AnyType> {
            private AnyType data;
            private ListNode<AnyType> next;

            public ListNode(AnyType d, ListNode<AnyType> n) {
                data = d;
                next = n;
            }
        }
        /**
         * The above-mentioned Stack hybrid
         * @param <AnyType>
         */
        public class List<AnyType> {

            private ListNode<AnyType> first;

            public List(AnyType data) {
                first = new ListNode<>(data, null);
            }
            /**
             * Used for add in case of duplicates (ie. if Comparator returns 0)
             * @param d the new element to be placed in a Node's list
             */
            public void pushDub(AnyType d){
                ListNode<AnyType> n = new ListNode(d, first);
                first = n;
            }
            /**
             * Used for deleteMin/deleteMax if singleVal returns false
             * @return the top element in a Node's stack after releasing it for garbage collection
             */
            public Object pop(){
                ListNode<AnyType> temp = first;
                first = first.next;
                return temp;
            }
            /**
             * Used for findMin/findMax
             * @return the top element in a Node's stack
             */
            public Object peek(){
                return first.data;
            }
            /**
             * Used to check if Node has no duplicates, used in deleteMin/deleteMax
             * @return true if Node's stack has only one element, false otherwise
             */
            public boolean singleVal(){
                if (first.next == null)
                    return true;
                else
                    return false;
            }
            /**
             *
             * @return value(s) (including duplicates) in a Node's item list
             */
            public String dump(){
                ListNode<AnyType> temp = first;
                String out = temp.data.toString() + " ";
                while(temp.next != null){
                    temp = temp.next;
                    out += temp.data.toString() + " ";
                }
                return out;
            }
        }
    }

    @Override
    public void makeEmpty() {
        root = null;
    }

    /**
     * Recursive add method with private helper
     * @param x Object to be added
     */
    @Override
    public void add(Object x) {
        cmp = new myComparator();
        root = add(root, x, cmp);
    }
    private Node<AnyType> add(Node<AnyType> r, Object x, Comparator c){
        if(r == null)
            return new Node<>((AnyType) x);
        else{
            if(c.compare(x, r.items.peek()) < 0)
                r.left = add(r.left, x, c);
            else if (c.compare(x, r.items.peek()) > 0)
                r.right = add(r.right, x, c);
            else
                r.items.pushDub((AnyType) x);
        }
        return r;
    }

    /**
     * Recursive deleteMin with private helper
     * @return deleted value
     */
    @Override
    public Object deleteMin() {
        if (isEmpty())
            return "List is empty";
        else{
            Object min = findMin();
            root = deleteMin(root);
            return min;
        }
    }
    private Node<AnyType> deleteMin(Node<AnyType> r){
        if (r.left == null){
            if(r.items.singleVal()) {
                return r.right;
            }
            else{
                r.items.pop();
                return r;
            }
        }
        r.left = deleteMin(r.left);
        return r;
    }

    /**
     * Recursive deleteMax with private helper
     * @return deleted value
     */
    @Override
    public Object deleteMax() {
        if (isEmpty())
            return "List is empty";
        else{
            Object max = findMax();
            root = deleteMax(root);
            return max;
        }
    }
    private Node<AnyType> deleteMax(Node<AnyType> r){
        if (r.right == null){
            if(r.items.singleVal())
                return r.left;
            else{
                r.items.pop();
                return r;
            }
        }
        r.right = deleteMax(r.right);
        return r;
    }

    /**
     * Recursive findMin method
     * @return smallest value in list
     */
    @Override
    public Object findMin() {
        if (isEmpty())
            return "List is empty";
        else
            return ((Node)findMin(root)).items.first.data;
    }
    private Node<AnyType> findMin(Node<AnyType> r){
        if(r.left == null)
            return r;
        else
            return findMin(r.left);
    }

    /**
     * Recursive findMax method
     * @return largest value in list
     */
    @Override
    public Object findMax() {
        if (isEmpty())
            return "List is empty";
        else {
            return ((Node)findMax(root)).items.first.data;
        }
    }
    private Node<AnyType> findMax(Node<AnyType> r){
        if(r.right == null)
            return r;
        else
            return findMax(r.right);
    }

    /**
     *
     * @return true if list empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if(root == null)
            return true;
        else
            return false;
    }
}
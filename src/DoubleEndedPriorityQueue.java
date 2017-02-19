/**
 * Code written by Andres Cremisini. All work is my own.
 */

package PriorityQueues;

public interface DoubleEndedPriorityQueue<AnyType> {
    void makeEmpty();
    void add(AnyType x);
    AnyType deleteMin();
    AnyType deleteMax();
    AnyType findMin();
    AnyType findMax();
    boolean isEmpty();
}

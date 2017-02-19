//Test Class provided by Prof. Giri Narasimhan 

import PriorityQueues.ListDoubleEndedPriorityQueue;
import PriorityQueues.TreeDoubleEndedPriorityQueue;

public class PriorityQueuesTester {
    public PriorityQueuesTester() {
    }

    public static void main(String[] args) {
        System.out.println("Running Tests for Double Ended Priority Queue ...");
        System.out.println("-------------------------------------------------");
        System.out.println("Testing List implementation ...");
        ListDoubleEndedPriorityQueue sampleL = new ListDoubleEndedPriorityQueue();
        sampleL.add(Integer.valueOf(5));
        sampleL.add(Integer.valueOf(12));
        sampleL.add(Integer.valueOf(5));
        sampleL.add(Integer.valueOf(1));
        if(sampleL.isEmpty()) {
            System.out.println("Priority Queue is EMPTY ...");
        } else {
            System.out.println("Priority Queue Not Empty: " + sampleL.toString());
        }

        System.out.println("Running findMin: " + sampleL.findMin());
        System.out.println("RUNNING findMax: " + sampleL.findMax());
        sampleL.makeEmpty();
        if(sampleL.isEmpty()) {
            System.out.println("Priority Queue is EMPTY ...");
        } else {
            System.out.println("Priority Queue Not Empty: " + sampleL.toString());
        }

        System.out.println("Running findMin: " + sampleL.findMin());
        System.out.println("RUNNING findMax: " + sampleL.findMax());
        sampleL.add(Integer.valueOf(-7));
        sampleL.add(Integer.valueOf(3));
        sampleL.add(Integer.valueOf(5));
        sampleL.add(Integer.valueOf(-7));
        sampleL.add(Integer.valueOf(12));
        sampleL.add(Integer.valueOf(5));
        sampleL.add(Integer.valueOf(1));
        System.out.println("Priority Queue Contents: " + sampleL.toString());
        sampleL.add(Integer.valueOf(5));
        System.out.println("Priority Queue Contents: " + sampleL.toString());
        System.out.println("Deleting min: " + sampleL.deleteMin());
        System.out.println("Deleting MAX: " + sampleL.deleteMax());
        System.out.println("Deleting min: " + sampleL.deleteMin());
        System.out.println("Deleting MAX: " + sampleL.deleteMax());
        System.out.println("Deleting min: " + sampleL.deleteMin());
        System.out.println("Deleting min: " + sampleL.deleteMin());
        System.out.println("Deleting MAX: " + sampleL.deleteMax());
        System.out.println("Deleting min: " + sampleL.deleteMin());
        System.out.println("Deleting min: " + sampleL.deleteMin());
        System.out.println("Deleting MAX: " + sampleL.deleteMax());
        System.out.println("-------------------------------------------------");
        System.out.println("Testing Binary Search Tree implementation ...");
        TreeDoubleEndedPriorityQueue sampleT = new TreeDoubleEndedPriorityQueue();
        sampleT.add(Integer.valueOf(5));
        sampleT.add(Integer.valueOf(12));
        sampleT.add(Integer.valueOf(5));
        sampleT.add(Integer.valueOf(1));
        if(sampleT.isEmpty()) {
            System.out.println("Priority Queue is EMPTY ...");
        } else {
            System.out.println("Priority Queue Not Empty: " + sampleT.toString());
        }

        System.out.println("Running findMin: " + sampleT.findMin());
        System.out.println("RUNNING findMax: " + sampleT.findMax());
        sampleT.makeEmpty();
        if(sampleT.isEmpty()) {
            System.out.println("Priority Queue is EMPTY ...");
        } else {
            System.out.println("Priority Queue Not Empty: " + sampleT.toString());
        }

        System.out.println("Running findMin: " + sampleT.findMin());
        System.out.println("RUNNING findMax: " + sampleT.findMax());
        sampleT.add(Integer.valueOf(-7));
        sampleT.add(Integer.valueOf(3));
        sampleT.add(Integer.valueOf(5));
        sampleT.add(Integer.valueOf(-7));
        sampleT.add(Integer.valueOf(12));
        sampleT.add(Integer.valueOf(5));
        sampleT.add(Integer.valueOf(1));
        System.out.println("Priority Queue Contents: " + sampleT.toString());
        sampleT.add(Integer.valueOf(5));
        System.out.println("Priority Queue Contents: " + sampleT.toString());
        System.out.println("Deleting min: " + sampleT.deleteMin());
        System.out.println("Deleting MAX: " + sampleT.deleteMax());
        System.out.println("Deleting min: " + sampleT.deleteMin());
        System.out.println("Deleting MAX: " + sampleT.deleteMax());
        System.out.println("Deleting min: " + sampleT.deleteMin());
        System.out.println("Deleting min: " + sampleT.deleteMin());
        System.out.println("Deleting MAX: " + sampleT.deleteMax());
        System.out.println("Deleting min: " + sampleT.deleteMin());
        System.out.println("Deleting min: " + sampleT.deleteMin());
        System.out.println("Deleting MAX: " + sampleT.deleteMax());
        System.out.println("-------------------------------------------------");
        System.out.println("Testing Queue of a different type ...");
        TreeDoubleEndedPriorityQueue sampleF = new TreeDoubleEndedPriorityQueue();
        sampleF.add(Double.valueOf(5.5D));
        sampleF.add(Double.valueOf(12.3D));
        System.out.println("Priority Queue Contents: " + sampleF.toString());
        sampleF.add(Double.valueOf(12.3D));
        System.out.println("Running findMax: " + sampleF.findMax());
        System.out.println("Deleting Min: " + sampleF.deleteMin());
        System.out.println("Priority Queue Contents: " + sampleF.toString());
        System.out.println("-------------------------------------------------");
        System.out.println("Testing Queue of yet another type ...");
        TreeDoubleEndedPriorityQueue sampleS = new TreeDoubleEndedPriorityQueue();
        sampleS.add("Jan");
        sampleS.add("Feb");
        sampleS.add("Mar");
        sampleS.add("Apr");
        sampleS.add("May");
        sampleS.add("Jun");
        sampleS.add("Jul");
        sampleS.add("Aug");
        sampleS.add("Aug");
        sampleS.add("Sep");
        sampleS.add("Oct");
        sampleS.add("Nov");
        sampleS.add("Dec");
        System.out.println("Priority Queue Contents: " + sampleS.toString());
        System.out.println("Running findMin: " + (String)sampleS.findMin());
        System.out.println("Deleting MAX: " + (String)sampleS.deleteMax());
        System.out.println("Priority Queue Contents: " + sampleS.toString());
        System.out.println("-------------------------------------------------");
        System.out.println("End of All Tests!");
    }
}

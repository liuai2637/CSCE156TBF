package unl.cse.queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unl.cse.stacks.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueueTests {

    Queue<String> testQueue;

    /**
     * Setup a {@link Queue} object for use in each test method
     */
    @BeforeEach
    void queueSetup() {
        testQueue = new Queue<>();
    }

    /**
     * Test that the implementation of {@link Queue}'s methods function correctly
     */
    @Test
    void queueTest() {
        // isEmpty - returning true
        assertTrue(testQueue.isEmpty(), "Queues should start off empty");

        // Push
        testQueue.enqueue("Test");
        assertEquals(1, testQueue.size(), String.format("size() returned %d for a stack with %d elements", testQueue.size(), 1));

        testQueue.enqueue("CSCE");
        assertEquals(2, testQueue.size(), String.format("size() returned %d for a stack with %d elements", testQueue.size(), 2));

        testQueue.enqueue("156");
        assertEquals(3, testQueue.size(), String.format("size() returned %d for a stack with %d elements", testQueue.size(), 3));

        testQueue.enqueue("156");
        assertEquals(4, testQueue.size(), String.format("size() returned %d for a stack with %d elements", testQueue.size(), 4));

        // isEmpty - returning false
        assertFalse(testQueue.isEmpty(), "Queue should not be empty at this point");

        // Dequeue
        assertEquals("Test", testQueue.dequeue(), "Queue returned out of order");
        assertEquals("CSCE", testQueue.dequeue(), "Queue returned out of order");
        assertEquals("156", testQueue.dequeue(), "Queue returned out of order");
        assertEquals("156", testQueue.dequeue(), "Queue returned out of order");

        try {
            // Attempt to dequeue from an empty queue
            // This permits returning null or throwing an exception
            assertNull(testQueue.dequeue(), "Queue return should be null or throw an exception");
        } catch (Exception ignored) {}

        // isEmpty - returning true
        assertTrue(testQueue.isEmpty(), "Queue should now be empty");
    }
}

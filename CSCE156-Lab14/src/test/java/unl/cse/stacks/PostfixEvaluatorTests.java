package unl.cse.stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PostfixEvaluatorTests {

    PostfixEvaluator postfixEvaluator;

    /**
     * Setup a {@link PostfixEvaluator} object for use in each test method
     */
    @BeforeEach
    void stackSetup() {
        postfixEvaluator = new PostfixEvaluator();

    }

    /**
     * Test {@link PostfixEvaluator#evaluateExpression(String)} returns the correct values
     */
    @Test
    void evaluateTest() {
        assertEquals(1, postfixEvaluator.evaluateExpression("1"), "A single number should return itself");
        assertEquals(5, postfixEvaluator.evaluateExpression("3 2 +"), "This should be interpreted as 3 + 2");
        assertEquals(1.5, postfixEvaluator.evaluateExpression("3 2 /"), "This should be interpreted as 3 / 2");
        assertEquals(1, postfixEvaluator.evaluateExpression("2 2 /"), "This should be interpreted as 2 / 2");
        assertEquals(6, postfixEvaluator.evaluateExpression("3 2 *"), "This should be interpreted as 3 * 2");
        assertEquals(1, postfixEvaluator.evaluateExpression("1 1 /"), "This should be interpreted as 1 / 1");
        assertEquals(0, postfixEvaluator.evaluateExpression("0 2 /"), "This should be interpreted as 0 / 2");

        assertEquals(1, postfixEvaluator.evaluateExpression("1.5 3 2 / /"), "(3 / 2) / 1.5");
        assertEquals(10, postfixEvaluator.evaluateExpression("2 3 2 + *"), "(3 + 2) * 2");
        assertEquals(10, postfixEvaluator.evaluateExpression("3 2 + 2 *"), "(3 + 2) * ");
        assertEquals(15, postfixEvaluator.evaluateExpression("2 5 * 3 2 + +"), "(2 * 5) + (3 + 2)");
        assertEquals(0, postfixEvaluator.evaluateExpression("3 2 * 6 -"), "(3 * 2) - 6");

        try {
            // failure to throw an exception fails the test
            postfixEvaluator.evaluateExpression("3 4 T");
            fail("'T' is an invalid operator");
        } catch (IllegalStateException|NumberFormatException ignored) {}
    }
}

package unl.cse.stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonValidatorTests {

    /**
     * Test JSON for valid
     */
    @Test
    void validatorTest() {
        //Valid tests
        assertTrue(JsonValidator.isValidJSON("{}"), "The brackets are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("[myFavoriteNumber: 42]"), "The brackets are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{[phpIsFun: false]}"), "The brackets are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{}[]{easterEggs++}[]{}"), "The brackets are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{[{[[[]]]}]}"), "The brackets are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{{{{{{{{}}}}}}}}"), "The brackets are balanced, making this a valid JSON string");

        assertFalse(JsonValidator.isValidJSON("{"), "The brackets are unbalanced, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("["), "The brackets are unbalanced, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("}"), "The brackets are unbalanced, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("]"), "The brackets are unbalanced, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("}{"), "The brackets are reversed, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("]["), "The brackets are reversed, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("{[{[}]}"), "The center bracket is unmatched, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("{[][][]"), "The first bracket is unmatched, making this an invalid JSON string");
        assertFalse(JsonValidator.isValidJSON("{}{}{}{}{}{}{}{}[][][]["), "The last bracket is unmatched, making this an invalid JSON string");
    }

    /**
     * Test the extra portion of the JSON validator
     */
    @Test
    void quotationExtraTest(){
        assertTrue(JsonValidator.isValidJSON("[\"\"]"), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("\"{\""), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{\"\"}"), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("\"{{{\""), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("\"\\\"{\""), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("\"{[{[}]}\""), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{\"{[{[}]}\"\"\"\"{{{\"}"), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{[{{{{{{{{{\"\"}}}}}}}}}]}"), "The brackets and quotes are balanced, making this a valid JSON string");
        assertTrue(JsonValidator.isValidJSON("{\"\\\"\\\"\\\"\\\"\\\"\\\"\"}"), "The brackets and quotes are balanced, making this a valid JSON string");

        assertFalse(JsonValidator.isValidJSON("\""), "Unbalanced quote mark");
        assertFalse(JsonValidator.isValidJSON("[\"]"), "Unbalanced quote mark");
        assertFalse(JsonValidator.isValidJSON("{}\""), "Unbalanced quote mark");
        assertFalse(JsonValidator.isValidJSON("\"\\\""), "Unbalanced quote mark (The second quote is escaped)");
        assertFalse(JsonValidator.isValidJSON("\"{{{\"]"), "The last array bracket is unmatched");
        assertFalse(JsonValidator.isValidJSON("\"\"{}{}\""), "Unmatched quote mark");
    }
}

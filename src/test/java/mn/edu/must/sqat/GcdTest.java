package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * all-DU-pairs coverage for gcd (line numbers = DataFlowTargets.java lines).
 * Pair notation: var(defLine,useLine).
 */
class GcdTest {

    @Test
    @DisplayName("G1 zero iterations: x(13,20) y(13,15F)")
    void gcd_yIsZero_loopSkipped_returnsX() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G2 one iteration: x(13,16) x(17,20) y(13,15T) y(13,16) y(18,15F) tmp(16,18)")
    void gcd_oneIteration_xZero_returnsY() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }

    @Test
    @DisplayName("G3 two iterations: x(17,16) y(18,15T) y(18,16) plus G2 pairs")
    void gcd_twoIterations_returnsGcd() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }
}

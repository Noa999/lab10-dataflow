package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Phase 1: ALL-DEFS tests for bonus() (line numbers = DataFlowTargets.java lines).
 * Notation: var(defLine,useLine).
 */
class BonusTest {

    @Test
    @DisplayName("D1 no positive elements, present<threshold, best<5: score(49,56) present(35,50) best(37,53)")
    void bonus_allNonPositive_scoreStaysZero() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{0, -1, 0}, 1));
    }

    @Test
    @DisplayName("D2 zero then positive: streak(46,41) score(51,56) present(40,50) best(43,53)")
    void bonus_killThenPositive_scoreFive() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{0, 1}, 1));
    }

    @Test
    @DisplayName("D3 five consecutive positives: streak(36,41) score(54,56)")
    void bonus_fiveConsecutive_scoreEight() {
        assertEquals(8, DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1}, 3));
    }
}

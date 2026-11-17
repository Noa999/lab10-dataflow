package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * bonus() tests (line numbers = DataFlowTargets.java lines).
 * Phase 1 (ALL-DEFS): D1-D3. Phase 2 (ALL-DU-PAIRS): D4-D8 added.
 * Notation: var(defLine,useLine).
 */
class BonusTest {

    // ---------- Phase 1: ALL-DEFS ----------

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
    @DisplayName("D3 five consecutive positives: streak(36,41) score(51,54) score(54,56)")
    void bonus_fiveConsecutive_scoreEight() {
        assertEquals(8, DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1}, 3));
    }

    // ---------- Phase 2: ALL-DU-PAIRS (additional tests) ----------

    @Test
    @DisplayName("D4 null attendance: attendance(31,32) true branch")
    void bonus_nullAttendance_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 3));
    }

    @Test
    @DisplayName("D5 negative threshold: threshold(31,32) true branch")
    void bonus_negativeThreshold_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[]{1}, -1));
    }

    @Test
    @DisplayName("D6 empty array: loop never entered, attendance(31,38) exit, score(51,56)")
    void bonus_emptyArray_thresholdZero_scoreFive() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{}, 0));
    }

    @Test
    @DisplayName("D7 1,1,0,1: streak(41,41) present(40,40) best(43,42)T best(43,42)F streak(41,42)F")
    void bonus_streakResetsAfterTwo_scoreFive() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{1, 1, 0, 1}, 1));
    }

    @Test
    @DisplayName("D8 streak bonus only: score(49,54) score(54,56), present<threshold and best>=5")
    void bonus_streakBonusWithoutThreshold_scoreThree() {
        assertEquals(3, DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1}, 10));
    }
}

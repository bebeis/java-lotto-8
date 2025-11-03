package lotto.domain.result;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false),
    SECOND(5, true),
    THIRD(5, false),
    FOURTH(4, false),
    FIFTH(3, false),
    NONE(0, false);

    private final int matchCount;
    private final boolean requiresBonusMatch;

    Rank(final int matchCount, final boolean requiresBonusMatch) {
        this.matchCount = matchCount;
        this.requiresBonusMatch = requiresBonusMatch;
    }

    public static Rank findRankBy(final int matchCount, final boolean bonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatchedAllCondition(matchCount, bonusMatch))
                .findFirst()
                .orElse(NONE);
    }

    public boolean isMatchedAllCondition(final int matchCount, final boolean bonusMatch) {
        if (this.matchCount != matchCount) {
            return false;
        }
        if (this.requiresBonusMatch) {
            return bonusMatch;
        }
        return true;
    }
}

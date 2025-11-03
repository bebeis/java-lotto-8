package lotto.domain.result;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean requiresBonusMatch;
    private final int prize;

    Rank(final int matchCount, final boolean requiresBonusMatch, final int prize) {
        this.matchCount = matchCount;
        this.requiresBonusMatch = requiresBonusMatch;
        this.prize = prize;
    }

    public static Rank findRankBy(final int matchCount, final boolean bonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatchedAllCondition(matchCount, bonusMatch))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isMatchedAllCondition(final int matchCount, final boolean bonusMatch) {
        if (this.matchCount != matchCount) {
            return false;
        }
        if (this.requiresBonusMatch) {
            return bonusMatch;
        }
        return true;
    }

    public int getPrize() {
        return prize;
    }
}

package lotto.stub;

import lotto.ui.InputReader;

import java.util.List;

public class StubInputReader implements InputReader {

    // 검증 대상 - 호출 여부
    public boolean requestedPurchaseAmount = false;
    public boolean requestedWinningNumbers = false;
    public boolean requestedBonusNumber = false;

    // 반환할 값
    private final int purchaseAmount;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public StubInputReader(final int purchaseAmount, final List<Integer> winningNumbers, final int bonusNumber) {
        this.purchaseAmount = purchaseAmount;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    @Override
    public int readPurchaseAmount() {
        requestedPurchaseAmount = true;
        return purchaseAmount;
    }

    @Override
    public List<Integer> readWinningNumbers() {
        requestedWinningNumbers = true;
        return winningNumbers;
    }

    @Override
    public int readBonusNumber() {
        requestedBonusNumber = true;
        return bonusNumber;
    }
}

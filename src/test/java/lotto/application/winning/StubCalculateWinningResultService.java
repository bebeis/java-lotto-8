package lotto.application.winning;

import java.util.ArrayList;
import java.util.List;

public class StubCalculateWinningResultService extends CalculateWinningResultService {

    // 검증 대상 - 메서드 호출 여부 및 인자
    boolean validateWinningNumbersCalled = false;
    List<Integer> receivedWinningNumbersForValidation = new ArrayList<>();

    boolean calculateWinningResultCalled = false;
    List<Integer> receivedWinningNumbers = new ArrayList<>();
    int receivedBonusNumber = 0;

    // 반환할 값
    private final LottoWinningResultResponse response;

    public StubCalculateWinningResultService(final LottoWinningResultResponse response) {
        super(null, null, null);
        this.response = response;
    }

    @Override
    public void validateWinningNumbers(final List<Integer> winningNumbers) {
        validateWinningNumbersCalled = true;
        receivedWinningNumbersForValidation = winningNumbers;
    }

    @Override
    public LottoWinningResultResponse calculateWinningResult(
            final List<Integer> winningNumbers,
            final int bonusNumber) {
        calculateWinningResultCalled = true;
        receivedWinningNumbers = winningNumbers;
        receivedBonusNumber = bonusNumber;
        return response;
    }
}

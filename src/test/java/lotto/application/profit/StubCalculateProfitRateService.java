package lotto.application.profit;

public class StubCalculateProfitRateService extends CalculateProfitRateService {

    // 검증 대상 - 메서드 호출 여부
    public boolean calculateProfitRateCalled = false;

    // 반환할 값
    private final ProfitRateResponse response;

    public StubCalculateProfitRateService(final ProfitRateResponse response) {
        super(null, null);
        this.response = response;
    }

    @Override
    public ProfitRateResponse calculateProfitRate() {
        calculateProfitRateCalled = true;
        return response;
    }
}

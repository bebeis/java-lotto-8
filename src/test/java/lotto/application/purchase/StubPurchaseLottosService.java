package lotto.application.purchase;

public class StubPurchaseLottosService extends PurchaseLottosService {

    // 검증 대상 - 메서드 호출 여부 및 인자
    boolean purchaseLottosCalled = false;
    int receivedAmount = 0;

    // 반환할 값
    private final PurchasedLottoResponse response;

    public StubPurchaseLottosService(final PurchasedLottoResponse response) {
        super(null, null);
        this.response = response;
    }

    @Override
    public PurchasedLottoResponse purchaseLottos(final int amount) {
        purchaseLottosCalled = true;
        receivedAmount = amount;
        return response;
    }
}

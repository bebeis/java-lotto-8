package lotto.application.purchase;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import lotto.stub.SpyOutputView;
import lotto.stub.StubInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PurchaseLottosController 테스트")
class PurchaseLottosControllerTest {

    @Nested
    @DisplayName("입력 읽기 테스트")
    class InputReadTest {
        StubInputReader inputReader;
        SpyOutputView outputView;
        StubPurchaseLottosService purchaseLottosService;
        PurchaseLottosController controller;

        @BeforeEach
        void init() {
            inputReader = new StubInputReader(10000, List.of(), 0);
            outputView = new SpyOutputView();
            purchaseLottosService = new StubPurchaseLottosService(new PurchasedLottoResponse(List.of()));
            controller = new PurchaseLottosController(purchaseLottosService, inputReader, outputView);
        }

        @Test
        @DisplayName("Controller는 InputReader를 통해 구매 금액을 읽는다")
        void shouldReadPurchaseAmount() {
            // when
            controller.run();

            // then
            assertThat(inputReader.requestedPurchaseAmount).isTrue();
        }
    }

    @Nested
    @DisplayName("PurchaseLottosService 호출 테스트")
    class ServiceCallTest {
        StubInputReader inputReader;
        SpyOutputView outputView;
        StubPurchaseLottosService purchaseLottosService;
        PurchaseLottosController controller;

        @BeforeEach
        void init() {
            inputReader = new StubInputReader(10000, List.of(), 0);
            outputView = new SpyOutputView();
            purchaseLottosService = new StubPurchaseLottosService(new PurchasedLottoResponse(List.of()));
            controller = new PurchaseLottosController(purchaseLottosService, inputReader, outputView);
        }

        @Test
        @DisplayName("Controller는 입력받은 구매 금액으로 purchaseLottos()를 호출한다")
        void shouldCallPurchaseLottos() {
            // when
            controller.run();

            // then
            assertThat(purchaseLottosService.purchaseLottosCalled).isTrue();
            assertThat(purchaseLottosService.receivedAmount).isEqualTo(10000);
        }
    }

    @Nested
    @DisplayName("OutputView 호출 테스트")
    class OutputViewCallTest {
        StubInputReader inputReader;
        SpyOutputView outputView;

        @BeforeEach
        void init() {
            inputReader = new StubInputReader(10000, List.of(), 0);
            outputView = new SpyOutputView();
        }

        @Test
        @DisplayName("Controller는 PurchaseLottosService로부터 받은 응답을 OutputView로 전달한다")
        void shouldShowPurchasedLottos() {
            // given
            PurchasedLottoResponse expectedResponse = new PurchasedLottoResponse(
                    List.of(
                            new Lotto(List.of(
                                    new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                                    new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                            )),
                            new Lotto(List.of(
                                    new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                                    new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)
                            ))
                    )
            );
            StubPurchaseLottosService purchaseLottosService = new StubPurchaseLottosService(expectedResponse);
            PurchaseLottosController controller = new PurchaseLottosController(
                    purchaseLottosService, inputReader, outputView
            );

            // when
            controller.run();

            // then
            assertThat(outputView.purchasedLottoResponse).isEqualTo(expectedResponse);
        }
    }
}

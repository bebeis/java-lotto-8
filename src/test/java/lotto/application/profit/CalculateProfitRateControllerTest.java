package lotto.application.profit;

import lotto.stub.SpyOutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateProfitRateControllerTest {

    @Nested
    @DisplayName("CalculateProfitRateService 호출 테스트")
    class ServiceCallTest {
        SpyOutputView outputView;
        StubCalculateProfitRateService profitRateService;
        CalculateProfitRateController controller;

        @BeforeEach
        void init() {
            outputView = new SpyOutputView();
            profitRateService = new StubCalculateProfitRateService(
                    new ProfitRateResponse(62.5)
            );
            controller = new CalculateProfitRateController(profitRateService, outputView);
        }

        @Test
        @DisplayName("Controller는 calculateProfitRate()를 호출한다")
        void shouldCallCalculateProfitRate() {
            // when
            controller.run();

            // then
            assertThat(profitRateService.calculateProfitRateCalled).isTrue();
        }
    }

    @Nested
    @DisplayName("OutputView 호출 테스트")
    class OutputViewCallTest {
        SpyOutputView outputView;

        @BeforeEach
        void init() {
            outputView = new SpyOutputView();
        }

        @Test
        @DisplayName("Controller는 CalculateProfitRateService로부터 받은 응답을 OutputView로 전달한다")
        void shouldShowProfitRate() {
            // given
            ProfitRateResponse expectedResponse = new ProfitRateResponse(62.5);
            StubCalculateProfitRateService profitRateService = new StubCalculateProfitRateService(
                    expectedResponse
            );
            CalculateProfitRateController controller = new CalculateProfitRateController(
                    profitRateService, outputView
            );

            // when
            controller.run();

            // then
            assertThat(outputView.profitRateResponse).isEqualTo(expectedResponse);
        }
    }
}

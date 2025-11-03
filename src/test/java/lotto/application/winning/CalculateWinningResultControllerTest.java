package lotto.application.winning;

import lotto.domain.result.Rank;
import lotto.stub.SpyOutputView;
import lotto.stub.StubInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CalculateWinningResultController 테스트")
class CalculateWinningResultControllerTest {

    @Nested
    @DisplayName("입력 읽기 테스트")
    class InputReadTest {
        StubInputReader inputReader;
        SpyOutputView outputView;
        StubCalculateWinningResultService winningResultService;
        CalculateWinningResultController controller;

        @BeforeEach
        void init() {
            inputReader = new StubInputReader(0, List.of(1, 2, 3, 4, 5, 6), 7);
            outputView = new SpyOutputView();
            winningResultService = new StubCalculateWinningResultService(
                    new LottoWinningResultResponse(Map.of(), 0L)
            );
            controller = new CalculateWinningResultController(
                    winningResultService, inputReader, outputView
            );
        }

        @Test
        @DisplayName("Controller는 InputReader를 통해 당첨 번호를 읽는다")
        void shouldReadWinningNumbers() {
            // when
            controller.run();

            // then
            assertThat(inputReader.requestedWinningNumbers).isTrue();
        }

        @Test
        @DisplayName("Controller는 InputReader를 통해 보너스 번호를 읽는다")
        void shouldReadBonusNumber() {
            // when
            controller.run();

            // then
            assertThat(inputReader.requestedBonusNumber).isTrue();
        }
    }

    @Nested
    @DisplayName("CalculateWinningResultService 호출 테스트")
    class ServiceCallTest {
        StubInputReader inputReader;
        SpyOutputView outputView;
        StubCalculateWinningResultService winningResultService;
        CalculateWinningResultController controller;

        @BeforeEach
        void init() {
            inputReader = new StubInputReader(0, List.of(1, 2, 3, 4, 5, 6), 7);
            outputView = new SpyOutputView();
            winningResultService = new StubCalculateWinningResultService(
                    new LottoWinningResultResponse(Map.of(), 0L)
            );
            controller = new CalculateWinningResultController(
                    winningResultService, inputReader, outputView
            );
        }

        @Test
        @DisplayName("Controller는 입력받은 당첨 번호로 validateWinningNumbers()를 호출한다")
        void shouldCallValidateWinningNumbers() {
            // when
            controller.run();

            // then
            assertThat(winningResultService.validateWinningNumbersCalled).isTrue();
            assertThat(winningResultService.receivedWinningNumbersForValidation)
                    .containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("Controller는 입력받은 당첨 번호와 보너스 번호로 calculateWinningResult()를 호출한다")
        void shouldCallCalculateWinningResult() {
            // when
            controller.run();

            // then
            assertThat(winningResultService.calculateWinningResultCalled).isTrue();
            assertThat(winningResultService.receivedWinningNumbers)
                    .containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(winningResultService.receivedBonusNumber).isEqualTo(7);
        }
    }

    @Nested
    @DisplayName("OutputView 호출 테스트")
    class OutputViewCallTest {
        StubInputReader inputReader;
        SpyOutputView outputView;

        @BeforeEach
        void init() {
            inputReader = new StubInputReader(0, List.of(1, 2, 3, 4, 5, 6), 7);
            outputView = new SpyOutputView();
        }

        @Test
        @DisplayName("Controller는 CalculateWinningResultService로부터 받은 응답을 OutputView로 전달한다")
        void shouldShowWinningResult() {
            // given
            LottoWinningResultResponse expectedResponse = new LottoWinningResultResponse(
                    Map.of(
                            Rank.FIRST, 1L,
                            Rank.SECOND, 2L,
                            Rank.THIRD, 3L
                    ),
                    10000000L
            );
            StubCalculateWinningResultService winningResultService = new StubCalculateWinningResultService(
                    expectedResponse
            );
            CalculateWinningResultController controller = new CalculateWinningResultController(
                    winningResultService, inputReader, outputView
            );

            // when
            controller.run();

            // then
            assertThat(outputView.lottoWinningResultResponse).isEqualTo(expectedResponse);
        }
    }
}

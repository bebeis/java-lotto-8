package lotto.infrastructure;

import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryPurchasedLottosRepositoryTest {

    private InMemoryPurchasedLottosRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPurchasedLottosRepository();
    }
    
    @Test
    @DisplayName("구매한 로또를 저장하고 조회할 수 있다")
    void saveAndFind() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );
        PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
        PurchasedLottos purchasedLottos = new PurchasedLottos(lottos, purchaseAmount);

        // when
        repository.save(purchasedLottos);
        PurchasedLottos found = repository.find();

        // then
        assertThat(found).isEqualTo(purchasedLottos);
        assertThat(found.lottos()).hasSize(1);
        assertThat(found.amount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("저장된 로또가 없을 때 조회하면 예외가 발생한다")
    void findWithoutSave() {
        // when & then
        assertThatThrownBy(() -> repository.find())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("구매한 로또가 없습니다");
    }

    @Test
    @DisplayName("여러 개의 로또를 포함한 PurchasedLottos를 저장하고 조회할 수 있다")
    void saveAndFindMultipleLottos() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                )),
                new Lotto(List.of(
                        new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)
                )),
                new Lotto(List.of(
                        new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                        new LottoNumber(16), new LottoNumber(17), new LottoNumber(18)
                ))
        );
        PurchaseAmount purchaseAmount = new PurchaseAmount(3000);
        PurchasedLottos purchasedLottos = new PurchasedLottos(lottos, purchaseAmount);

        // when
        repository.save(purchasedLottos);
        PurchasedLottos found = repository.find();

        // then
        assertThat(found.lottos()).hasSize(3);
        assertThat(found.amount()).isEqualTo(3000);
    }
}

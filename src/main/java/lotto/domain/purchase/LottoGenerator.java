package lotto.domain.purchase;

import lotto.domain.shared.Lotto;

import java.util.List;
import java.util.stream.Stream;

public class LottoGenerator {

    private final LottoNumberGenerator numberGenerator;

    public LottoGenerator(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> generate(PurchaseAmount amount) {
        return Stream.generate(numberGenerator::generate)
                .limit(amount.lottoCount())
                .map(Lotto::new)
                .toList();
    }
}

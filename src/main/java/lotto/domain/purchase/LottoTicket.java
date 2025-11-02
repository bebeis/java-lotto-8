package lotto.domain.purchase;

import lotto.domain.shared.Lotto;

import java.util.List;
import java.util.stream.Stream;

public class LottoTicket {
    private final List<Lotto> lottos;

    private LottoTicket(final List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public static LottoTicket issueFrom(final PurchaseAmount amount, LottoNumberGenerator lottoNumberGenerator) {
        List<Lotto> lottos = Stream.generate(lottoNumberGenerator::generate)
                .limit(amount.lottoCount())
                .map(Lotto::new)
                .toList();

        return new LottoTicket(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}

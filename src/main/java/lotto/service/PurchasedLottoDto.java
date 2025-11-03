package lotto.service;

import lotto.domain.shared.Lotto;

import java.util.List;

public record PurchasedLottoDto(List<Lotto> lottos) {

    public PurchasedLottoDto(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }
}

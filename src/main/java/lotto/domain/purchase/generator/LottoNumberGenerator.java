package lotto.domain.purchase.generator;

import lotto.domain.shared.LottoNumber;

import java.util.List;

public interface LottoNumberGenerator {

    List<LottoNumber> generate();
}

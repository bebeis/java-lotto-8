package lotto.domain.purchase;

import lotto.domain.shared.LottoNumber;

import java.util.List;

public interface LottoNumberGenerator {

    List<LottoNumber> generate();
}

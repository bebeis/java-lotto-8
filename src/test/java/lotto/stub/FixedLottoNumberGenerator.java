package lotto.stub;

import lotto.domain.purchase.LottoNumberGenerator;
import lotto.domain.shared.LottoNumber;

import java.util.List;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<LottoNumber> generate() {
        return List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
    }
}

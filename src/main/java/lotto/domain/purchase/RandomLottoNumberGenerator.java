package lotto.domain.purchase;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.shared.LottoNumber;

import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<LottoNumber> generate() {
        return Randoms.pickUniqueNumbersInRange(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER, Lotto.LOTTO_NUMBER_COUNT)
                .stream()
                .map(LottoNumber::new)
                .toList();
    }
}

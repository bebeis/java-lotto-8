package lotto.domain.purchase;

import lotto.domain.shared.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        validate(lottoNumbers);
        this.numbers = List.copyOf(lottoNumbers);
    }

    private void validate(final List<LottoNumber> lottoNumbers) {
        validateLottoNumberCount(lottoNumbers);
        validateNoDuplicateLottoNumbers(lottoNumbers);
    }

    private void validateLottoNumberCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(PurchaseErrorMessage.INVALID_LOTTO_NUMBER_COUNT
                    .message()
                    .formatted(LOTTO_NUMBER_COUNT));
        }
    }

    private void validateNoDuplicateLottoNumbers(final List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> uniqueLottoNumbers = new HashSet<>(lottoNumbers);
        if (uniqueLottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(PurchaseErrorMessage.DUPLICATED_LOTTO_NUMBER
                    .message());
        }
    }
}

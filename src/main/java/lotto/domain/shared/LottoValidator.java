package lotto.domain.shared;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoValidator {
    private static final int LOTTO_NUMBER_COUNT = 6;

    public void validateNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        validate(lottoNumbers);
    }

    public void validate(List<LottoNumber> lottoNumbers) {
        validateLottoNumberCount(lottoNumbers);
        validateNoDuplicateLottoNumbers(lottoNumbers);
    }

    private void validateLottoNumberCount(List<?> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(SharedErrorMessage.INVALID_LOTTO_NUMBER_COUNT
                    .message()
                    .formatted(LOTTO_NUMBER_COUNT));
        }
    }

    private void validateNoDuplicateLottoNumbers(List<?> numbers) {
        Set<?> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(SharedErrorMessage.DUPLICATED_LOTTO_NUMBER
                    .message());
        }
    }
}

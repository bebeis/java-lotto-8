package lotto.domain.shared;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(final List<LottoNumber> lottoNumbers) {
        validateLottoNumberCount(lottoNumbers);
        validateNoDuplicateLottoNumbers(lottoNumbers);
    }

    private void validateLottoNumberCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(SharedErrorMessage.INVALID_LOTTO_NUMBER_COUNT
                    .message()
                    .formatted(LOTTO_NUMBER_COUNT));
        }
    }

    private void validateNoDuplicateLottoNumbers(final List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> uniqueLottoNumbers = new HashSet<>(lottoNumbers);
        if (uniqueLottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(SharedErrorMessage.DUPLICATED_LOTTO_NUMBER
                    .message());
        }
    }

    public boolean contains(final LottoNumber input) {
        return numbers.contains(input);
    }

    public int matchCountWith(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }
}

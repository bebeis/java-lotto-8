package lotto.domain.shared;

import java.util.List;

public class Lotto {
    public static final int LOTTO_NUMBER_COUNT = 6;
    private static final LottoValidator validator = new LottoValidator();

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validator.validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public boolean contains(final LottoNumber input) {
        return numbers.contains(input);
    }

    public int matchCountWith(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::number)
                .toList();
    }
}

package lotto.domain.result;

public interface LottoWinningResultRepository {

    void save(LottoWinningResult winningResult);

    LottoWinningResult find();
}

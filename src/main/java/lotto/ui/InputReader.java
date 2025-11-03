package lotto.ui;

import java.util.List;

public interface InputReader {
    
    int readPurchaseAmount();

    List<Integer> readWinningNumbers();

    int readBonusNumber();
}

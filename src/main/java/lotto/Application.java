package lotto;

import lotto.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        
        appConfig.purchaseLottosController().run();
        appConfig.calculateWinningResultController().run();
        appConfig.calculateProfitRateController().run();
    }
}

package by.mrj.besu.gold;

import by.mrj.besu.gold.contract.SwissGoldToken;
import by.mrj.besu.service.ContractLoader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SwissGoldTokenService {

    public final static String SWISS_GOLD_TOKEN = "SwissGoldToken";

    @Getter
    // fixme: no direct access. All needed methods wrapped here with proper loging.
    private final SwissGoldToken swissGoldToken;
    @Getter
    private final String address;

    public SwissGoldTokenService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) throws IOException {

        log.info("Loading SwissGoldToken contract...");

        address = networkBaseContractService.getSwissGoldTokenAddress();
        swissGoldToken = contractLoader.load(address, SwissGoldToken.class);

        if (!this.swissGoldToken.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }
    }
}

package by.mrj.besu.gold;

import by.mrj.besu.gold.contract.SGoldToken;
import by.mrj.besu.service.ContractLoader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SGoldTokenService {

    public final static String S_GOLD_TOKEN = "SGoldToken";

    @Getter
    // fixme: no direct access. All needed methods wrapped here with proper loging.
    private final SGoldToken sGoldToken;
    @Getter
    private final String address;

    public SGoldTokenService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) throws IOException {

        log.info("Loading SGoldToken contract...");

        address = networkBaseContractService.getSGoldTokenAddress();
        sGoldToken = contractLoader.load(address, SGoldToken.class);

        if (!this.sGoldToken.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }
    }
}

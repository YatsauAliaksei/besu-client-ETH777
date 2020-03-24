package by.mrj.besu.gold;

import by.mrj.besu.gold.contract.SGoldExecutor;
import by.mrj.besu.gold.contract.SGoldToken;
import by.mrj.besu.service.ContractLoader;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SGoldExecutorService {

    public final static String S_GOLD_EXECUTOR = "SGoldExecutor";

    @Getter
    private final SGoldExecutor sGoldExecutor;
    @Getter
    private final String address;

    @SneakyThrows
    public SGoldExecutorService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) {

        log.info("Loading SGoldExecutor contract...");

        address = networkBaseContractService.getSGoldExecutorAddress();
        sGoldExecutor = contractLoader.load(address, SGoldExecutor.class);

        if (!this.sGoldExecutor.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }

        var sGoldToken = contractLoader.load(networkBaseContractService.getSGoldTokenAddress(), SGoldToken.class);
        sGoldToken.addAllowed(address).send();
    }
}

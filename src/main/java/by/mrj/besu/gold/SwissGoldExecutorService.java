package by.mrj.besu.gold;

import by.mrj.besu.gold.contract.SwissGoldExecutor;
import by.mrj.besu.gold.contract.SwissGoldToken;
import by.mrj.besu.service.ContractLoader;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@DependsOn()
public class SwissGoldExecutorService {

    public final static String SWISS_GOLD_EXECUTOR = "SwissGoldExecutor";

    @Getter
    private final SwissGoldExecutor swissGoldExecutor;
    @Getter
    private final String address;

    @SneakyThrows
    public SwissGoldExecutorService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) {

        log.info("Loading SwissGoldExecutor contract...");

        address = networkBaseContractService.getSwissGoldExecutorAddress();
        swissGoldExecutor = contractLoader.load(address, SwissGoldExecutor.class);

        if (!this.swissGoldExecutor.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }

        var swissGoldToken = contractLoader.load(networkBaseContractService.getSwissGoldTokenAddress(), SwissGoldToken.class);
        swissGoldToken.addAllowed(address).send();
    }
}

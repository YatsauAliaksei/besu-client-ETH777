package by.mrj.besu.gold;

import by.mrj.besu.config.ApplicationProperties;
import by.mrj.besu.contract.ERC1820Registry;
import by.mrj.besu.gold.contract.SGoldExecutor;
import by.mrj.besu.gold.contract.SGoldToken;
import by.mrj.besu.service.ContractLoader;
import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static by.mrj.besu.gold.SGoldExecutorService.S_GOLD_EXECUTOR;
import static by.mrj.besu.gold.SGoldTokenService.S_GOLD_TOKEN;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class NetworkBaseContractService {

    public final static String ERC1820_REGISTRY = "ERC1820Registry";

    private final TransactionManager transactionManager;

    @Getter
    private final String sGoldTokenAddress;
    @Getter
    private final String sGoldExecutorAddress;

    public NetworkBaseContractService(ApplicationProperties applicationProperties, Web3jClient web3jClient,
                                      GodCredentials credentials, TransactionManager transactionManager,
                                      ContractLoader contractLoader) throws IOException {

        this.transactionManager = transactionManager;

        Map<String, String> nameToAddress = applicationProperties.getNetContracts().stream()
            .collect(toMap(ApplicationProperties.NetContract::getName, ApplicationProperties.NetContract::getAddress));

        var erc1820Address = deployErc1820(contractLoader, nameToAddress.get(ERC1820_REGISTRY));

        this.sGoldTokenAddress = deploySGoldTokenContract(web3jClient, credentials,
            nameToAddress.get(S_GOLD_TOKEN), erc1820Address);

        this.sGoldExecutorAddress = deploySGoldExecutorContract(web3jClient,
            nameToAddress.get(S_GOLD_EXECUTOR), this.sGoldTokenAddress, applicationProperties.getContract().getLimit());
    }

    /**
     * Creates {@link SGoldToken} contract if doesn't exist
     */
    private String deploySGoldExecutorContract(Web3jClient web3jClient,
                                                   String sGoldExecutorAddress, String tokenAddress, long limit) throws IOException {
        if (StringUtils.hasText(sGoldExecutorAddress)) {
            return sGoldExecutorAddress;
        }

        log.info("SGoldExecutor contract address was not found. Creating...");

        var sGoldExecutor = SGoldExecutor.deploy(web3jClient.getWeb3j(), transactionManager,
            new DefaultGasProvider(), tokenAddress, BigInteger.valueOf(limit)).sendAsync().join();

        sGoldExecutorAddress = sGoldExecutor.getContractAddress();
        log.info("SGoldExecutor contract created with address [{}]", sGoldExecutorAddress);

        if (!sGoldExecutor.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + sGoldExecutorAddress + "]");
        }

        return sGoldExecutorAddress;
    }

    /**
     * Creates {@link SGoldToken} contract if doesn't exist
     */
    private String deploySGoldTokenContract(Web3jClient web3jClient, GodCredentials credentials, String goldTokenAddress, String erc1820Address) throws IOException {
        if (StringUtils.hasText(goldTokenAddress)) {
            return goldTokenAddress;
        }

        log.info("SGoldToken contract address was not found. Creating...");

      var sGoldToken = SGoldToken.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            List.of(credentials.getCredentials().getAddress()), erc1820Address).sendAsync().join();

        goldTokenAddress = sGoldToken.getContractAddress();
        log.info("SGoldToken contract created with address [{}]", goldTokenAddress);

        if (!sGoldToken.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + goldTokenAddress + "]");
        }

        return goldTokenAddress;
    }

    /**
     * Creates ERC1820Registry in case doesn't exist yet.
     * Not needed so far for our needs
     */
    private String deployErc1820(ContractLoader contractLoader, String erc1820Address) {

        if (!StringUtils.hasText(erc1820Address)) {
            log.info("ERC1820 address not found. Creating new...");

            erc1820Address = contractLoader.deploy(ERC1820Registry.BINARY, ERC1820Registry.class).getContractAddress();

            log.info("ERC1820 contract created. Address [{}]", erc1820Address);
        }

        return erc1820Address;
    }
}

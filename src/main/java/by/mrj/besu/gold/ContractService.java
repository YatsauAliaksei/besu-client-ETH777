package by.mrj.besu.gold;


import by.mrj.besu.gold.contract.SGoldBuy;
import by.mrj.besu.gold.contract.SGoldSell;
import by.mrj.besu.gold.contract.SGoldTransfer;
import by.mrj.besu.web3j.Web3jClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class ContractService {

    private final Web3jClient web3jClient;
    private final TransactionManager transactionManager;
    private final String goldExecutorServiceAddress;

    public ContractService(Web3jClient web3jClient, TransactionManager transactionManager, SGoldExecutorService sGoldExecutorService) {
        this.web3jClient = web3jClient;
        this.transactionManager = transactionManager;
        this.goldExecutorServiceAddress = sGoldExecutorService.getAddress();
    }

    /**
     * Sells to {@param buyer} {@param amount} of gold by {@param price}
     * @param amount - 0.1g
     * @param price  - 1 cent
     */
    public CompletableFuture<SGoldBuy> createBuy(String buyer, long amount, BigInteger price) {
        log.info("Creating BUY contract for [{}], size: [{}] price: [{}]", buyer, amount, price);
        log.info("Executor: {}", goldExecutorServiceAddress);


        return SGoldBuy.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            goldExecutorServiceAddress, buyer, BigInteger.valueOf(amount), price).sendAsync();
    }

    public CompletableFuture<SGoldSell> createSell(String seller, long amount, BigInteger price) {
        log.info("Creating SELL contract for [{}], size: [{}] price: [{}]", seller, amount, price);

        return SGoldSell.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            goldExecutorServiceAddress, seller, BigInteger.valueOf(amount), price).sendAsync();
    }

    public CompletableFuture<SGoldTransfer> createTransfer(String from, String to, long amount) {
        log.info("Creating TRANSFER contract from [{}] to [{}], size: [{}]", from, to, amount);

        return SGoldTransfer.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            goldExecutorServiceAddress, from, to, BigInteger.valueOf(amount)).sendAsync();

    }
}

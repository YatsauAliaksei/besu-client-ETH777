package by.mrj.besu.gold;


import by.mrj.besu.gold.contract.SwissGoldBuy;
import by.mrj.besu.gold.contract.SwissGoldSell;
import by.mrj.besu.gold.contract.SwissGoldTransfer;
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

    public ContractService(Web3jClient web3jClient, TransactionManager transactionManager, SwissGoldExecutorService swissGoldExecutorService) {
        this.web3jClient = web3jClient;
        this.transactionManager = transactionManager;
        this.goldExecutorServiceAddress = swissGoldExecutorService.getAddress();
    }

    /**
     * Sells to {@param buyer} {@param amount} of gold by {@param price}
     * @param amount - 0.1g
     * @param price  - 1 cent
     */
    public CompletableFuture<SwissGoldBuy> createBuy(String buyer, long amount, BigInteger price) {
        log.info("Creating BUY contract for [{}], size: [{}] price: [{}]", buyer, amount, price);
        log.info("Executor: {}", goldExecutorServiceAddress);


        return SwissGoldBuy.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            goldExecutorServiceAddress, buyer, BigInteger.valueOf(amount), price).sendAsync();
    }

    public CompletableFuture<SwissGoldSell> createSell(String seller, long amount, BigInteger price) {
        log.info("Creating SELL contract for [{}], size: [{}] price: [{}]", seller, amount, price);

        return SwissGoldSell.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            goldExecutorServiceAddress, seller, BigInteger.valueOf(amount), price).sendAsync();
    }

    public CompletableFuture<SwissGoldTransfer> createTransfer(String from, String to, long amount) {
        log.info("Creating TRANSFER contract from [{}] to [{}], size: [{}]", from, to, amount);

        return SwissGoldTransfer.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            goldExecutorServiceAddress, from, to, BigInteger.valueOf(amount)).sendAsync();

    }
}

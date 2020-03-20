package by.mrj.besu.service;

import by.mrj.besu.gold.ContractService;
import by.mrj.besu.gold.SwissGoldExecutorService;
import by.mrj.besu.gold.SwissGoldTokenService;
import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class Checker {

    private final GodCredentials credentials;
    private final SwissGoldTokenService swissGoldTokenService;
    private final SwissGoldExecutorService swissGoldExecutorService;
    private final AccountService accountService;
    private final Web3jClient web3jClient;
    private final ContractService contractService;

    @SneakyThrows
    public void check() {

        long amount = 10L;

        log.info("Starting check with amount - {}", amount);

        mintPlusCheck(amount);

        // INITIAL
        String newClientAddress = createNewClient();
        logBalance(newClientAddress);

        log.info("Direct token call");
        swissGoldTokenService.getSwissGoldToken().send(newClientAddress, BigInteger.valueOf(amount), "".getBytes()).send();
        logBalance(newClientAddress);

        String tokenAddress = swissGoldExecutorService.getSwissGoldExecutor().getTokenAddress().send();
        log.info("Token address: {}", tokenAddress);

        String tokenOwner = swissGoldExecutorService.getSwissGoldExecutor().tokenOwner().send();
        log.info("Token Owner address: {}", tokenOwner);

        log.info("Direct executor call");
//        swissGoldExecutorService.getSwissGoldExecutor().buy(newClientAddress, BigInteger.valueOf(amount)).send();
        swissGoldExecutorService.getSwissGoldExecutor().buy(newClientAddress).send();
        mintPlusCheck(amount);
        logBalance(newClientAddress);

        // BUY
        // fixme: should be done in other way. Contract should be created initially and address saved.
        // todo: Create mechanism which destroys contracts if they were not executed after specified time limit.
        contractService.createBuy(newClientAddress, amount, BigInteger.ONE).join().buy().send();
        logBalance(newClientAddress);

        // SELL
        contractService.createSell(newClientAddress, amount, BigInteger.ONE).join().sell().send();
        logBalance(newClientAddress);

        mintPlusCheck(amount);

        // 2nd client
        String newClientAddress1 = createNewClient();
        logBalance(newClientAddress1);

        // BUY
        contractService.createBuy(newClientAddress, amount, BigInteger.ONE).join().buy().send();
        logBalance(newClientAddress);

        // TRANSFER
        contractService.createTransfer(newClientAddress, newClientAddress1, amount).join().transfer().send();
        logBalance(newClientAddress);
        logBalance(newClientAddress1);
    }

    private void logBalance(String address) throws Exception {
        BigInteger ethBalance = getEthBalance(address);
        BigInteger goldBalance = getGoldBalance(address);
        log.info("\nAddress: {}\nBalance ETH: [{}], GoldToken: [{}]", address, ethBalance, goldBalance);
    }

    private void mintPlusCheck(long amount) throws Exception {
        String address = credentials.getCredentials().getAddress();
        BigInteger balance = getGoldBalance(address);

        log.info("Balance before [{}] - {}", address, balance);

        swissGoldTokenService.getSwissGoldToken().mint(BigInteger.valueOf(amount)).sendAsync().join();

        address = credentials.getCredentials().getAddress();
        balance = getGoldBalance(address);

        log.info("Balance after [{}] - {}", address, balance);
    }

    private String createNewClient() {
        Credentials newUserCredentials = accountService.createCredentials();
        String newClientAddress = newUserCredentials.getAddress();

        BigInteger privateKey = credentials.getCredentials().getEcKeyPair().getPrivateKey();
        log.info("New client address [{}] private [{}]", newClientAddress, Numeric.toHexStringNoPrefix(privateKey));
        return newClientAddress;
    }

    private BigInteger getGoldBalance(String address) throws Exception {
        return swissGoldTokenService.getSwissGoldToken().balanceOf(address).send();
    }

    private BigInteger getEthBalance(String a) throws java.io.IOException {
        Request<?, EthGetBalance> request = web3jClient.getWeb3j().ethGetBalance(a, DefaultBlockParameter.valueOf("latest"));
        return request.send().getBalance();
    }
}

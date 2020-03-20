package by.mrj.besu.gold.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class SwissGoldExecutor extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161057c38038061057c8339818101604052604081101561003357600080fd5b508051602090910151600080546001600160a01b039093166001600160a01b0319938416811790915560028054841690911790556001805490921633179091556003556104f7806100856000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c806310fe9ae8146100675780636c197ff51461008b578063a3e67610146100b9578063a4d66daf146100c1578063beabacc8146100db578063f088d54714610111575b600080fd5b61006f610137565b604080516001600160a01b039092168252519081900360200190f35b6100b7600480360360408110156100a157600080fd5b506001600160a01b038135169060200135610146565b005b61006f610264565b6100c96102e4565b60408051918252519081900360200190f35b6100b7600480360360608110156100f157600080fd5b506001600160a01b038135811691602081013590911690604001356102ea565b6100b76004803603602081101561012757600080fd5b50356001600160a01b0316610411565b6002546001600160a01b031690565b80600354811061018f576040805162461bcd60e51b815260206004820152600f60248201526e2634b6b4ba103b34b7b630ba34b7b760891b604482015290519081900360640190fd5b6001546001600160a01b031633146101d85760405162461bcd60e51b81526004018080602001828103825260288152602001806104756028913960400191505060405180910390fd5b600080546040805163fc673c4f60e01b81526001600160a01b03878116600483015260248201879052608060448301526084820185905260c0606483015260c482018590529151919092169263fc673c4f92610104808201939182900301818387803b15801561024757600080fd5b505af115801561025b573d6000803e3d6000fd5b50505050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316638da5cb5b6040518163ffffffff1660e01b815260040160206040518083038186803b1580156102b357600080fd5b505afa1580156102c7573d6000803e3d6000fd5b505050506040513d60208110156102dd57600080fd5b5051905090565b60035481565b806003548110610333576040805162461bcd60e51b815260206004820152600f60248201526e2634b6b4ba103b34b7b630ba34b7b760891b604482015290519081900360640190fd5b6001546001600160a01b0316331461037c5760405162461bcd60e51b81526004018080602001828103825260288152602001806104756028913960400191505060405180910390fd5b60008054604080516362ad1b8360e01b81526001600160a01b03888116600483015287811660248301526044820187905260a0606483015260a4820185905260e0608483015260e48201859052915191909216926362ad1b8392610124808201939182900301818387803b1580156103f357600080fd5b505af1158015610407573d6000803e3d6000fd5b5050505050505050565b600080546040805163140e25ad60e31b8152600a600482015290516001600160a01b039092169263a0712d689260248084019382900301818387803b15801561045957600080fd5b505af115801561046d573d6000803e3d6000fd5b505050505056fe496e73756666696369656e742070726976696c6567657320746f207472616e736665722066726f6da2646970667358221220a273d04dba64cd3aef08f8137bdc40a528d1b78fe388672a834453a059e8757964736f6c637827302e362e352d646576656c6f702e323032302e332e31392b636f6d6d69742e38383334623161630058";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_LIMIT = "limit";

    public static final String FUNC_SELL = "sell";

    public static final String FUNC_TOKENOWNER = "tokenOwner";

    public static final String FUNC_TRANSFER = "transfer";

    @Deprecated
    protected SwissGoldExecutor(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SwissGoldExecutor(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SwissGoldExecutor(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SwissGoldExecutor(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> buy(String buyer) {
        final Function function = new Function(
                FUNC_BUY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, buyer)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getTokenAddress() {
        final Function function = new Function(FUNC_GETTOKENADDRESS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> limit() {
        final Function function = new Function(FUNC_LIMIT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sell(String seller, BigInteger amount) {
        final Function function = new Function(
                FUNC_SELL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, seller),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> tokenOwner() {
        final Function function = new Function(FUNC_TOKENOWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String seller, String buyer, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, seller),
                new org.web3j.abi.datatypes.Address(160, buyer),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SwissGoldExecutor load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SwissGoldExecutor(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SwissGoldExecutor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SwissGoldExecutor(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SwissGoldExecutor load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SwissGoldExecutor(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SwissGoldExecutor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SwissGoldExecutor(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SwissGoldExecutor> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(SwissGoldExecutor.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<SwissGoldExecutor> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(SwissGoldExecutor.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SwissGoldExecutor> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(SwissGoldExecutor.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SwissGoldExecutor> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(SwissGoldExecutor.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
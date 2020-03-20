package by.mrj.besu.gold.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class SwissGoldToken extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200257b3803806200257b833981810160405260408110156200003757600080fd5b81019080805160405193929190846401000000008211156200005857600080fd5b9083019060208201858111156200006e57600080fd5b82518660208202830111640100000000821117156200008c57600080fd5b82525081516020918201928201910280838360005b83811015620000bb578181015183820152602001620000a1565b505050509190910160408181526020938401518183018252600983526814ddda5cdcd1dbdb1960ba1b8584019081528251808401909352600583526414ddd1db1960da1b9583019590955282519096509194509286925085916200012291600391620002ff565b50825162000138906004906020860190620002ff565b5081516200014e90600590602085019062000384565b5060005b600554811015620001ae57600160066000600584815481106200017157fe5b6000918252602080832091909101546001600160a01b031683528201929092526040019020805460ff191691151591909117905560010162000152565b50600080546001600160a01b038084166001600160a01b031990921691909117808355604080516a22a9219b9b9baa37b5b2b760a91b8152815190819003600b0181206329965a1d60e01b8252306004830181905260248301919091526044820152905191909216926329965a1d926064808201939182900301818387803b1580156200023a57600080fd5b505af11580156200024f573d6000803e3d6000fd5b505060008054604080516922a92199182a37b5b2b760b11b8152815190819003600a0181206329965a1d60e01b825230600483018190526024830191909152604482015290516001600160a01b0390921694506329965a1d9350606480820193929182900301818387803b158015620002c757600080fd5b505af1158015620002dc573d6000803e3d6000fd5b5050600a80546001600160a01b0319163317905550620004319650505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200034257805160ff191683800117855562000372565b8280016001018555821562000372579182015b828111156200037257825182559160200191906001019062000355565b5062000380929150620003ea565b5090565b828054828255906000526020600020908101928215620003dc579160200282015b82811115620003dc57825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190620003a5565b50620003809291506200040a565b6200040791905b80821115620003805760008155600101620003f1565b90565b6200040791905b80821115620003805780546001600160a01b031916815560010162000411565b61213a80620004416000396000f3fe608060405234801561001057600080fd5b506004361061012c5760003560e01c8063959b8c3f116100ad578063d95b637111610071578063d95b637114610581578063dd62ed3e146105af578063fad8b32a146105dd578063fc673c4f14610603578063fe9d9303146107415761012c565b8063959b8c3f1461045157806395d89b41146104775780639bd9bbc61461047f578063a0712d6814610538578063a9059cbb146105555761012c565b8063313ce567116100f4578063313ce56714610296578063556f0dc7146102b457806362ad1b83146102bc57806370a08231146104075780638da5cb5b1461042d5761012c565b806306e485381461013157806306fdde0314610189578063095ea7b31461020657806318160ddd1461024657806323b872dd14610260575b600080fd5b6101396107ec565b60408051602080825283518183015283519192839290830191858101910280838360005b8381101561017557818101518382015260200161015d565b505050509050019250505060405180910390f35b61019161084e565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101cb5781810151838201526020016101b3565b50505050905090810190601f1680156101f85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6102326004803603604081101561021c57600080fd5b506001600160a01b0381351690602001356108db565b604080519115158252519081900360200190f35b61024e6108fd565b60408051918252519081900360200190f35b6102326004803603606081101561027657600080fd5b506001600160a01b03813581169160208101359091169060400135610903565b61029e610a86565b6040805160ff9092168252519081900360200190f35b61024e610a8b565b610405600480360360a08110156102d257600080fd5b6001600160a01b03823581169260208101359091169160408201359190810190608081016060820135600160201b81111561030c57600080fd5b82018360208201111561031e57600080fd5b803590602001918460018302840111600160201b8311171561033f57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561039157600080fd5b8201836020820111156103a357600080fd5b803590602001918460018302840111600160201b831117156103c457600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610a90945050505050565b005b61024e6004803603602081101561041d57600080fd5b50356001600160a01b0316610afa565b610435610b15565b604080516001600160a01b039092168252519081900360200190f35b6104056004803603602081101561046757600080fd5b50356001600160a01b0316610b24565b610191610c70565b6104056004803603606081101561049557600080fd5b6001600160a01b0382351691602081013591810190606081016040820135600160201b8111156104c457600080fd5b8201836020820111156104d657600080fd5b803590602001918460018302840111600160201b831117156104f757600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610cd1945050505050565b6104056004803603602081101561054e57600080fd5b5035610d03565b6102326004803603604081101561056b57600080fd5b506001600160a01b038135169060200135610d3f565b6102326004803603604081101561059757600080fd5b506001600160a01b0381358116916020013516610e18565b61024e600480360360408110156105c557600080fd5b506001600160a01b0381358116916020013516610eba565b610405600480360360208110156105f357600080fd5b50356001600160a01b0316610ee5565b6104056004803603608081101561061957600080fd5b6001600160a01b0382351691602081013591810190606081016040820135600160201b81111561064857600080fd5b82018360208201111561065a57600080fd5b803590602001918460018302840111600160201b8311171561067b57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b8111156106cd57600080fd5b8201836020820111156106df57600080fd5b803590602001918460018302840111600160201b8311171561070057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550611031945050505050565b6104056004803603604081101561075757600080fd5b81359190810190604081016020820135600160201b81111561077857600080fd5b82018360208201111561078a57600080fd5b803590602001918460018302840111600160201b831117156107ab57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550611097945050505050565b6060600580548060200260200160405190810160405280929190818152602001828054801561084457602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610826575b5050505050905090565b60038054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156108445780601f106108af57610100808354040283529160200191610844565b820191906000526020600020905b8154815290600101906020018083116108bd57509395945050505050565b6000806108e66110c5565b90506108f38185856110c9565b5060019392505050565b60025490565b60006001600160a01b03831661094a5760405162461bcd60e51b8152600401808060200182810382526024815260200180611ffb6024913960400191505060405180910390fd5b6001600160a01b03841661098f5760405162461bcd60e51b81526004018080602001828103825260268152602001806120746026913960400191505060405180910390fd5b60006109996110c5565b90506109c7818686866040518060200160405280600081525060405180602001604052806000815250611170565b6109f38186868660405180602001604052806000815250604051806020016040528060008152506113a8565b610a4d8582610a488660405180606001604052806029815260200161204b602991396001600160a01b03808c166000908152600960209081526040808320938b1683529290522054919063ffffffff6115ce16565b6110c9565b610a7b8186868660405180602001604052806000815250604051806020016040528060008152506000611665565b506001949350505050565b601290565b600190565b610aa1610a9b6110c5565b86610e18565b610adc5760405162461bcd60e51b815260040180806020018281038252602c81526020018061201f602c913960400191505060405180910390fd5b610af3610ae76110c5565b868686868660016118f5565b5050505050565b6001600160a01b031660009081526001602052604090205490565b600a546001600160a01b031681565b806001600160a01b0316610b366110c5565b6001600160a01b03161415610b7c5760405162461bcd60e51b8152600401808060200182810382526024815260200180611f696024913960400191505060405180910390fd5b6001600160a01b03811660009081526006602052604090205460ff1615610bdf5760086000610ba96110c5565b6001600160a01b03908116825260208083019390935260409182016000908120918516815292529020805460ff19169055610c26565b600160076000610bed6110c5565b6001600160a01b03908116825260208083019390935260409182016000908120918616815292529020805460ff19169115159190911790555b610c2e6110c5565b6001600160a01b0316816001600160a01b03167ff4caeb2d6ca8932a215a353d0703c326ec2d81fc68170f320eb2ab49e9df61f960405160405180910390a350565b60048054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156108445780601f106108af57610100808354040283529160200191610844565b610cfe610cdc6110c5565b610ce46110c5565b8585856040518060200160405280600081525060016118f5565b505050565b610d3c610d0e6110c5565b610d166110c5565b8360405180602001604052806000815250604051806020016040528060008152506119c0565b50565b60006001600160a01b038316610d865760405162461bcd60e51b8152600401808060200182810382526024815260200180611ffb6024913960400191505060405180910390fd5b6000610d906110c5565b9050610dbe818286866040518060200160405280600081525060405180602001604052806000815250611170565b610dea8182868660405180602001604052806000815250604051806020016040528060008152506113a8565b6108f38182868660405180602001604052806000815250604051806020016040528060008152506000611665565b6000816001600160a01b0316836001600160a01b03161480610e8357506001600160a01b03831660009081526006602052604090205460ff168015610e8357506001600160a01b0380831660009081526008602090815260408083209387168352929052205460ff16155b80610eb357506001600160a01b0380831660009081526007602090815260408083209387168352929052205460ff165b9392505050565b6001600160a01b03918216600090815260096020908152604080832093909416825291909152205490565b610eed6110c5565b6001600160a01b0316816001600160a01b03161415610f3d5760405162461bcd60e51b8152600401808060200182810382526021815260200180611f8d6021913960400191505060405180910390fd5b6001600160a01b03811660009081526006602052604090205460ff1615610fa957600160086000610f6c6110c5565b6001600160a01b03908116825260208083019390935260409182016000908120918616815292529020805460ff1916911515919091179055610fe7565b60076000610fb56110c5565b6001600160a01b03908116825260208083019390935260409182016000908120918516815292529020805460ff191690555b610fef6110c5565b6001600160a01b0316816001600160a01b03167f50546e66e5f44d728365dc3908c63bc5cfeeab470722c1677e3073a6ac294aa160405160405180910390a350565b61104261103c6110c5565b85610e18565b61107d5760405162461bcd60e51b815260040180806020018281038252602c81526020018061201f602c913960400191505060405180910390fd5b6110916110886110c5565b85858585611bf8565b50505050565b6110c16110a26110c5565b6110aa6110c5565b848460405180602001604052806000815250611bf8565b5050565b3390565b6001600160a01b03821661110e5760405162461bcd60e51b81526004018080602001828103825260238152602001806120bd6023913960400191505060405180910390fd5b6001600160a01b03808416600081815260096020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b600080546040805163555ddc6560e11b81526001600160a01b0389811660048301527f29ddb589b1fb5fc7cf394961c1adf5f8c6454761adf795e67fe149f658abe89560248301529151919092169163aabbb8ca916044808301926020929190829003018186803b1580156111e457600080fd5b505afa1580156111f8573d6000803e3d6000fd5b505050506040513d602081101561120e57600080fd5b505190506001600160a01b0381161561139f57806001600160a01b03166375ab97828888888888886040518763ffffffff1660e01b815260040180876001600160a01b03166001600160a01b03168152602001866001600160a01b03166001600160a01b03168152602001856001600160a01b03166001600160a01b031681526020018481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156112d45781810151838201526020016112bc565b50505050905090810190601f1680156113015780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b8381101561133457818101518382015260200161131c565b50505050905090810190601f1680156113615780820380516001836020036101000a031916815260200191505b5098505050505050505050600060405180830381600087803b15801561138657600080fd5b505af115801561139a573d6000803e3d6000fd5b505050505b50505050505050565b6113b486868686611091565b6113f783604051806060016040528060278152602001611f20602791396001600160a01b038816600090815260016020526040902054919063ffffffff6115ce16565b6001600160a01b03808716600090815260016020526040808220939093559086168152205461142c908463ffffffff611e2516565b60016000866001600160a01b03166001600160a01b0316815260200190815260200160002081905550836001600160a01b0316856001600160a01b0316876001600160a01b03167f06b541ddaa720db2b10a4d0cdac39b8d360425fc073085fac19bc82614677987868686604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156114de5781810151838201526020016114c6565b50505050905090810190601f16801561150b5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b8381101561153e578181015183820152602001611526565b50505050905090810190601f16801561156b5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a4836001600160a01b0316856001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef856040518082815260200191505060405180910390a3505050505050565b6000818484111561165d5760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561162257818101518382015260200161160a565b50505050905090810190601f16801561164f5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b505050900390565b600080546040805163555ddc6560e11b81526001600160a01b0389811660048301527fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b60248301529151919092169163aabbb8ca916044808301926020929190829003018186803b1580156116d957600080fd5b505afa1580156116ed573d6000803e3d6000fd5b505050506040513d602081101561170357600080fd5b505190506001600160a01b0381161561189757806001600160a01b03166223de298989898989896040518763ffffffff1660e01b815260040180876001600160a01b03166001600160a01b03168152602001866001600160a01b03166001600160a01b03168152602001856001600160a01b03166001600160a01b031681526020018481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156117c85781810151838201526020016117b0565b50505050905090810190601f1680156117f55780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b83811015611828578181015183820152602001611810565b50505050905090810190601f1680156118555780820380516001836020036101000a031916815260200191505b5098505050505050505050600060405180830381600087803b15801561187a57600080fd5b505af115801561188e573d6000803e3d6000fd5b505050506118eb565b81156118eb576118af866001600160a01b0316611e7f565b156118eb5760405162461bcd60e51b815260040180806020018281038252604d815260200180611fae604d913960600191505060405180910390fd5b5050505050505050565b6001600160a01b03861661193a5760405162461bcd60e51b8152600401808060200182810382526022815260200180611efe6022913960400191505060405180910390fd5b6001600160a01b038516611995576040805162461bcd60e51b815260206004820181905260248201527f4552433737373a2073656e6420746f20746865207a65726f2061646472657373604482015290519081900360640190fd5b6119a3878787878787611170565b6119b18787878787876113a8565b61139f87878787878787611665565b6001600160a01b038416611a1b576040805162461bcd60e51b815260206004820181905260248201527f4552433737373a206d696e7420746f20746865207a65726f2061646472657373604482015290519081900360640190fd5b600254611a2e908463ffffffff611e2516565b6002556001600160a01b038416600090815260016020526040902054611a5a908463ffffffff611e2516565b60016000866001600160a01b03166001600160a01b0316815260200190815260200160002081905550611a94856000868686866001611665565b836001600160a01b0316856001600160a01b03167f2fe5be0146f74c5bce36c0b80911af6c7d86ff27e89d5cfa61fc681327954e5d858585604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b83811015611b13578181015183820152602001611afb565b50505050905090810190601f168015611b405780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b83811015611b73578181015183820152602001611b5b565b50505050905090810190601f168015611ba05780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a36040805184815290516001600160a01b038616916000917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9181900360200190a35050505050565b6001600160a01b038416611c3d5760405162461bcd60e51b8152600401808060200182810382526022815260200180611f476022913960400191505060405180910390fd5b611c4c85856000868686611170565b611c8f8360405180606001604052806023815260200161209a602391396001600160a01b038716600090815260016020526040902054919063ffffffff6115ce16565b6001600160a01b038516600090815260016020526040902055600254611cbb908463ffffffff611ebb16565b600281905550836001600160a01b0316856001600160a01b03167fa78a9be3a7b862d26933ad85fb11d80ef66b8f972d7cbba06621d583943a4098858585604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b83811015611d40578181015183820152602001611d28565b50505050905090810190601f168015611d6d5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b83811015611da0578181015183820152602001611d88565b50505050905090810190601f168015611dcd5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a36040805184815290516000916001600160a01b038716917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9181900360200190a35050505050565b600082820183811015610eb3576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b6000813f7fc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470818114801590611eb357508115155b949350505050565b6000610eb383836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f7700008152506115ce56fe4552433737373a2073656e642066726f6d20746865207a65726f20616464726573734552433737373a207472616e7366657220616d6f756e7420657863656564732062616c616e63654552433737373a206275726e2066726f6d20746865207a65726f20616464726573734552433737373a20617574686f72697a696e672073656c66206173206f70657261746f724552433737373a207265766f6b696e672073656c66206173206f70657261746f724552433737373a20746f6b656e20726563697069656e7420636f6e747261637420686173206e6f20696d706c656d656e74657220666f7220455243373737546f6b656e73526563697069656e744552433737373a207472616e7366657220746f20746865207a65726f20616464726573734552433737373a2063616c6c6572206973206e6f7420616e206f70657261746f7220666f7220686f6c6465724552433737373a207472616e7366657220616d6f756e74206578636565647320616c6c6f77616e63654552433737373a207472616e736665722066726f6d20746865207a65726f20616464726573734552433737373a206275726e20616d6f756e7420657863656564732062616c616e63654552433737373a20617070726f766520746f20746865207a65726f2061646472657373a26469706673582212204ae99e0d28af25b50d3e26aeb52eca92c671c7d9c0f63abeed61131c62c002e264736f6c637827302e362e352d646576656c6f702e323032302e332e31392b636f6d6d69742e38383334623161630058";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_AUTHORIZEOPERATOR = "authorizeOperator";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DEFAULTOPERATORS = "defaultOperators";

    public static final String FUNC_GRANULARITY = "granularity";

    public static final String FUNC_ISOPERATORFOR = "isOperatorFor";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OPERATORBURN = "operatorBurn";

    public static final String FUNC_OPERATORSEND = "operatorSend";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REVOKEOPERATOR = "revokeOperator";

    public static final String FUNC_SEND = "send";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event AUTHORIZEDOPERATOR_EVENT = new Event("AuthorizedOperator",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event BURNED_EVENT = new Event("Burned",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event MINTED_EVENT = new Event("Minted",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event REVOKEDOPERATOR_EVENT = new Event("RevokedOperator",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SENT_EVENT = new Event("Sent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected SwissGoldToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SwissGoldToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SwissGoldToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SwissGoldToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<AuthorizedOperatorEventResponse> getAuthorizedOperatorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(AUTHORIZEDOPERATOR_EVENT, transactionReceipt);
        ArrayList<AuthorizedOperatorEventResponse> responses = new ArrayList<AuthorizedOperatorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AuthorizedOperatorEventResponse typedResponse = new AuthorizedOperatorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AuthorizedOperatorEventResponse> authorizedOperatorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AuthorizedOperatorEventResponse>() {
            @Override
            public AuthorizedOperatorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AUTHORIZEDOPERATOR_EVENT, log);
                AuthorizedOperatorEventResponse typedResponse = new AuthorizedOperatorEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AuthorizedOperatorEventResponse> authorizedOperatorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AUTHORIZEDOPERATOR_EVENT));
        return authorizedOperatorEventFlowable(filter);
    }

    public List<BurnedEventResponse> getBurnedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BURNED_EVENT, transactionReceipt);
        ArrayList<BurnedEventResponse> responses = new ArrayList<BurnedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BurnedEventResponse typedResponse = new BurnedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BurnedEventResponse> burnedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BurnedEventResponse>() {
            @Override
            public BurnedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BURNED_EVENT, log);
                BurnedEventResponse typedResponse = new BurnedEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BurnedEventResponse> burnedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURNED_EVENT));
        return burnedEventFlowable(filter);
    }

    public List<MintedEventResponse> getMintedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MINTED_EVENT, transactionReceipt);
        ArrayList<MintedEventResponse> responses = new ArrayList<MintedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintedEventResponse typedResponse = new MintedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MintedEventResponse>() {
            @Override
            public MintedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MINTED_EVENT, log);
                MintedEventResponse typedResponse = new MintedEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTED_EVENT));
        return mintedEventFlowable(filter);
    }

    public List<RevokedOperatorEventResponse> getRevokedOperatorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REVOKEDOPERATOR_EVENT, transactionReceipt);
        ArrayList<RevokedOperatorEventResponse> responses = new ArrayList<RevokedOperatorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RevokedOperatorEventResponse typedResponse = new RevokedOperatorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RevokedOperatorEventResponse> revokedOperatorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RevokedOperatorEventResponse>() {
            @Override
            public RevokedOperatorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REVOKEDOPERATOR_EVENT, log);
                RevokedOperatorEventResponse typedResponse = new RevokedOperatorEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RevokedOperatorEventResponse> revokedOperatorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REVOKEDOPERATOR_EVENT));
        return revokedOperatorEventFlowable(filter);
    }

    public List<SentEventResponse> getSentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SENT_EVENT, transactionReceipt);
        ArrayList<SentEventResponse> responses = new ArrayList<SentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SentEventResponse typedResponse = new SentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SentEventResponse> sentEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SentEventResponse>() {
            @Override
            public SentEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SENT_EVENT, log);
                SentEventResponse typedResponse = new SentEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SentEventResponse> sentEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SENT_EVENT));
        return sentEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String holder, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder),
                new org.web3j.abi.datatypes.Address(160, spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
                new org.web3j.abi.datatypes.generated.Uint256(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeOperator(String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_AUTHORIZEOPERATOR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String tokenHolder) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, tokenHolder)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount),
                new org.web3j.abi.datatypes.DynamicBytes(data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> defaultOperators() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEFAULTOPERATORS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> granularity() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GRANULARITY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isOperatorFor(String operator, String tokenHolder) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOPERATORFOR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator),
                new org.web3j.abi.datatypes.Address(160, tokenHolder)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> operatorBurn(String account, BigInteger amount, byte[] data, byte[] operatorData) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_OPERATORBURN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account),
                new org.web3j.abi.datatypes.generated.Uint256(amount),
                new org.web3j.abi.datatypes.DynamicBytes(data),
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> operatorSend(String sender, String recipient, BigInteger amount, byte[] data, byte[] operatorData) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_OPERATORSEND,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender),
                new org.web3j.abi.datatypes.Address(160, recipient),
                new org.web3j.abi.datatypes.generated.Uint256(amount),
                new org.web3j.abi.datatypes.DynamicBytes(data),
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeOperator(String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEOPERATOR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> send(String recipient, BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SEND,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient),
                new org.web3j.abi.datatypes.generated.Uint256(amount),
                new org.web3j.abi.datatypes.DynamicBytes(data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String holder, String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder),
                new org.web3j.abi.datatypes.Address(160, recipient),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SwissGoldToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SwissGoldToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SwissGoldToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SwissGoldToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SwissGoldToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SwissGoldToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SwissGoldToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SwissGoldToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SwissGoldToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, List<String> defaultOperators, String erc1820Address) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)),
                new org.web3j.abi.datatypes.Address(160, erc1820Address)));
        return deployRemoteCall(SwissGoldToken.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<SwissGoldToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, List<String> defaultOperators, String erc1820Address) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)),
                new org.web3j.abi.datatypes.Address(160, erc1820Address)));
        return deployRemoteCall(SwissGoldToken.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SwissGoldToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<String> defaultOperators, String erc1820Address) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)),
                new org.web3j.abi.datatypes.Address(160, erc1820Address)));
        return deployRemoteCall(SwissGoldToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SwissGoldToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<String> defaultOperators, String erc1820Address) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)),
                new org.web3j.abi.datatypes.Address(160, erc1820Address)));
        return deployRemoteCall(SwissGoldToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class AuthorizedOperatorEventResponse extends BaseEventResponse {
        public String operator;

        public String tokenHolder;
    }

    public static class BurnedEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class MintedEventResponse extends BaseEventResponse {
        public String operator;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class RevokedOperatorEventResponse extends BaseEventResponse {
        public String operator;

        public String tokenHolder;
    }

    public static class SentEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
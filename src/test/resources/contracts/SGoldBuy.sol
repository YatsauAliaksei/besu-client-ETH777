pragma solidity ^0.6.0;

import "./SGoldExecutor.sol";

contract SGoldBuy {

    address private issuer;
    SGoldExecutor private sGoldExecutor;

    address private buyer;
    bool private burned = false;
    uint private amount;
    uint private price;

    constructor (address executorAddress, address _buyer, uint _amount, uint _price) public {
        sGoldExecutor = SGoldExecutor(executorAddress);

        issuer = msg.sender;
        buyer = _buyer;
        amount = _amount;
        price = _price;
    }

    modifier onlyOwner() {
        require(msg.sender == issuer, "Insufficient privileges");
        _;
    }

    function buy() public onlyOwner {
        require(!burned, "Contract could be processed only once");

        sGoldExecutor.buy(msg.sender, buyer, amount);

        burned = true;
    }

    // Retrieves contract info
    function getInfo() public view returns (address, uint, uint, bool) {
        return (buyer, amount, price, burned);
    }

}

pragma solidity ^0.6.0;

import "./SwissGoldExecutor.sol";

contract SwissGoldSell {

    address private issuer;
    SwissGoldExecutor private swissGoldExecutor;

    address private seller;
    bool private burned = false;
    uint private amount;
    uint private price;

    constructor (address executorAddress, address _seller, uint _amount, uint _price) public {
        swissGoldExecutor = SwissGoldExecutor(executorAddress);

        issuer = msg.sender;
        seller = _seller;
        amount = _amount;
        price = _price;
    }

    modifier onlyOwner() {
        require(msg.sender == issuer, "Insufficient privileges");
        _;
    }

    function sell() public onlyOwner {
        require(!burned, "Contract could be processed only once");

        swissGoldExecutor.sell(msg.sender, seller, amount);

        burned = true;
    }

    // Retrieves contract info
    function getInfo() public view returns (address, uint, uint, bool) {
        return (seller, amount, price, burned);
    }

}

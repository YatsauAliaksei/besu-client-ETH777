pragma solidity ^0.6.0;

import "./SwissGoldExecutor.sol";

contract SwissGoldTransfer {

    address private issuer;
    SwissGoldExecutor private swissGoldExecutor;

    address private from;
    address private to;
    uint private amount;
    bool private burned = false;

    constructor (address executorAddress, address _from, address _to, uint _amount) public {
        swissGoldExecutor = SwissGoldExecutor(executorAddress);

        issuer = msg.sender;
        from = _from;
        to = _to;
        amount = _amount;
    }

    modifier onlyOwner() {
        require(msg.sender == issuer, "Insufficient privileges");
        _;
    }

    function transfer() public onlyOwner {
        require(!burned, "Contract could be processed only once");

        swissGoldExecutor.transfer(from, to, amount);

        burned = true;
    }

    // Retrieves contract info
    function getInfo() public view returns (address, address, uint, bool) {
        return (from, to, amount, burned);
    }

}

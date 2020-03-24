pragma solidity ^0.6.0;

import "./SwissGoldToken.sol";

contract SwissGoldExecutor {

    SwissGoldToken private swissGoldToken;
    address private owner;
    uint public limit;

    constructor (address _tokenAddress, uint _limit) public {
        swissGoldToken = SwissGoldToken(_tokenAddress);

        owner = msg.sender;
        limit = _limit;
    }

    function buy(address god, address buyer, uint amount) public onlyOwnerOrGodProvided(god) checkLimit(amount) {
        address adr = address(this);

        swissGoldToken.mint(god, amount);
        swissGoldToken.approveFor(god, adr, amount);

        if (!swissGoldToken.transferFrom(god, buyer, amount)) {
            revert("Transfer failed");
        }
    }

    function sell(address god, address seller, uint amount) public onlyOwnerOrGodProvided(god) checkLimit(amount) {
        swissGoldToken.approveFor(seller, address(this), amount);

        if (!swissGoldToken.transferFrom(seller, address(this), amount)) {
            revert("Transfer failed");
        }

        swissGoldToken.burn(amount, "");
    }

    function transfer(address god, address seller, address buyer, uint amount) public onlyOwnerOrGodProvided(god) checkLimit(amount) {
        swissGoldToken.approveFor(seller, address(this), amount);

        if (!swissGoldToken.transferFrom(seller, buyer, amount)) {
            revert("Transfer failed");
        }
    }

    modifier checkLimit(uint amount) {
        require(amount < limit, "Limit violation");
        _;
    }

    modifier onlyOwnerOrGodProvided(address god) {
        require(msg.sender == owner || god == owner, "Insufficient privileges to transfer from");
        _;
    }

    event ErrorMsg(string msg, bytes error);
}

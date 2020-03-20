pragma solidity ^0.6.0;

import "./SwissGoldToken.sol";

contract SwissGoldExecutor {

    SwissGoldToken private swissGoldToken;
    address private owner;
    address private tokenAddress;
    uint public limit;

    constructor (address _tokenAddress, uint _limit) public {
        swissGoldToken = SwissGoldToken(_tokenAddress);
        tokenAddress = _tokenAddress;

//        require(swissGoldToken.owner() == msg.sender, "SwissGoldToken was not found ");

        owner = msg.sender;
        limit = _limit;
    }

    function buy(address buyer) public {
//        SwissGoldToken(tokenAddress).mint(10);
        swissGoldToken.mint(10);
//        swissGoldToken.send(buyer, amount, "");
    }

    function sell(address seller, uint256 amount) public checkLimit(amount) onlyOwner {
        swissGoldToken.operatorBurn(seller, amount, "", "");
    }

    function transfer(address seller, address buyer, uint amount) public checkLimit(amount) onlyOwner {
        swissGoldToken.operatorSend(seller, buyer, amount, "", "");
    }

    function getTokenAddress() public view returns (address) {
        return address(SwissGoldToken(tokenAddress));
    }

    modifier checkLimit(uint amount) {
        require(amount < limit, "Limit violation");
        _;
    }

    function tokenOwner() public view returns (address) {
        return swissGoldToken.owner();
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "Insufficient privileges to transfer from");
        _;
    }
}

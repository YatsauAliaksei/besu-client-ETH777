pragma solidity ^0.6.0;

import "./openzeppelin/token/ERC777/ERC777.sol";

contract SwissGoldToken is ERC777 {

    address public owner;

    constructor(address[] memory defaultOperators , address erc1820Address)
        ERC777("SwissGold", "SwGld", defaultOperators, erc1820Address ) public {

        owner = msg.sender;
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "Insufficient privileges");
        _;
    }

    function mint(uint amount) public {
        _mint(_msgSender(), _msgSender(), amount, "", "");
    }
}

#!/usr/bin/env bash

rm contracts/compiled/*

solc --bin --abi --optimize --overwrite -o contracts/compiled/ contracts/*.sol

if [ -z "$(ls -A contracts/compiled)" ]; then
   echo "Compilation errors found"
   exit
fi

GOLD_CONTRACTS=(
          SwissGoldToken SwissGoldSell
          SwissGoldBuy SwissGoldTransfer SwissGoldExecutor
   )

COMMON_CONTRACTS=( ERC1820Registry )

for item in ${GOLD_CONTRACTS[*]}
    do
        web3j solidity generate -a=contracts/compiled/$item.abi -b=contracts/compiled/$item.bin -o=../../main/java/ -p=by.mrj.gold.contract
    done

for item in ${COMMON_CONTRACTS[*]}
    do
        web3j solidity generate -a=contracts/compiled/$item.abi -b=contracts/compiled/$item.bin -o=../../main/java/ -p=by.mrj.contract
    done


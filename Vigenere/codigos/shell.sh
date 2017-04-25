#!/bin/sh


for i in `seq 1 400`
do
	echo "$i"
    java Vigenere att albertini2.txt key.txt $i a > key.txt 
    java Vigenere dec albertini2.txt key.txt > decifrados/$i.txt 
done

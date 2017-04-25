# Security Information (SI)

## Cifra de Vigenere


Os codigos se encontram dentro da pasta "codigos".

Como utilizar:

bash
> javac Vigenere.java



- Para encriptar:
bash
> java Vigenere enc ../textos-em-claro/albertini2.txt key.txt

(sendo albertini2.txt, o texto em claro, e key.txt, o arquivo que contem a chave).


- Para decriptar: 
bash
> java Vigenere dec ../textos-cifrados/albertini2.txt key.txt

(sendo albertini2.txt, o texto cifrado, e key.txt, o arquivo que contem a chave).


- Para atacar:
bash
> java Vigenere att ../textos-cifrados/albertini2.txt key.txt 18

(sendo albertini2.txt, o texto cifrado; key.txt, o arquivo que contem a chave; e 18, o tamanho da mesma).


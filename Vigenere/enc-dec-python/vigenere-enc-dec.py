# Encriptacao e Decriptacao atraves da cifra de Vigenere

import re
####################
def encriptaVigenere(arq, key):
  textoc = arq.read()

  arquivoSaida = open('arquivoEncriptado', 'w')
  i = 0
  
  for caracterlido in textoc:
    blido = bytearray(caracterlido)
    bk = bytearray(key)


    arquivoSaida.write(hex(blido[0]^bk[i%len(bk)]))
    
    if (i != len(textoc)-1):
      arquivoSaida.write(' ')
    i = i+1

  arquivoSaida.close()
  return 1


####################
def decriptaVigenere(arquivoEncript, key):
  arquivoSaida = open('arquivoDecriptado', 'w')
  textoc = arquivoEncript.read()


  Hexas = re.split(' ', textoc)

  i = 0
  for hexaLido in Hexas:

    bhexaLido = bytearray(hexaLido)
    bk = bytearray(key)

    hex_int = int(hexaLido, 16)

    arquivoSaida.write(chr(hex_int^bk[i%len(bk)]))
    i = i+1

  arquivoSaida.close()
  return 1

####################

#   M A I N

arq = open('arquivoOriginal', 'r')
encriptaVigenere(arq, "abcdef")


arqDec = open('arquivoEncriptado', 'r')
decriptaVigenere(arqDec, "abcdef")

print("Encriptacao e Decriptacao feitas. Arquivos: arquivoEncriptado, e arquivoDecriptado")

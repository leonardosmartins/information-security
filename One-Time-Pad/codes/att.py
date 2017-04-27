import string
arqkey = open("../input/msg/keys/key.txt" , "r")
key = arqkey.read()
dec = ""
ler = range(7)
escrever = range(7)

for i in range(7):
  ler[i] = open("../input/msg/cifradas-bin-spaces/c" +str(i+1) + ".txt" , "r")
  escrever[i] = open("../input/msg/decifradas-bin-spaces/c" +str(i+1) + ".txt" , "w")
  lido = ler[i].read()
  dec=""
  for j in range(len(key)):
    if(key[j] == ' ' or lido[j]==' '):
      dec = dec[0:]+' '
    elif(key[j] == lido[j]):
      dec = dec[0:]+'0'
    else:
      dec = dec[0:]+'1'
  teste=""
  tokens = dec.split(" ")
  for l in range(len(tokens)):
    ascii = int(tokens[l],2)
    if ascii < 127 and ascii > 31:
      teste = teste[0:]+str(unichr(ascii))
    else:
      teste = teste[0:]+'#'
  #escrever[i].write(dec)       
  print (teste)
  ler[i].close()
  escrever[i].close()

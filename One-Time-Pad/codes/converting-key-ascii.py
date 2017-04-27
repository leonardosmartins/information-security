arqkey = open("../input/msg/keys/key2.txt" , "r")
key = arqkey.read()
arqkey.close()
teste=""
tokens = key.split(" ")
print tokens
for l in range(len(tokens)):
  ascii = int(tokens[l],2)
  print ascii
  if ascii < 127 and ascii > 31:
    teste = teste[0:]+str(unichr(ascii))
  else:
    teste = teste[0:]+'#'


print teste

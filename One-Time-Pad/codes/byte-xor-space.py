import sys


entrada = sys.argv[2]
pontuacao = sys.argv[1]

space = ""

#esta gambi aqui
if pontuacao == " ":
  space = "00100000"
elif pontuacao == ",":
  space = "00101100"
elif pontuacao == ".":
  space = "00101110"
elif pontuacao == ":":
  space = "01011000"
else:
  space = pontuacao

xor = ""


for i in range(8):
  if (space[i] == entrada[i]):
    xor = xor[0:] + '0'
  else:
    xor = xor[0:] + '1'

print xor
#print "'" + str(unichr(int(xor,2))) + "'"


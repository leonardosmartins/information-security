v1 = range(0,7)
v2 = range(0,7)

for i in range(0,7):
  print i
  leitura_mod = ""
  v1[i] = open("../input/msg/cifradas-bin/c"+ str(i+1)+ ".txt", "r")
  v2[i] = open("../input/msg/cifradas-bin-spaces/c"+ str(i+1)+ ".txt", "w")
  leitura = v1[i].read()
  k=0
  for j in range(len(leitura)):
    if j%8 ==0 and j>0:
      leitura_mod = leitura_mod[0:] + ' '
      k = k +1
    leitura_mod = leitura_mod[0:] + leitura[j]
    k=k+1
  v2[i].write(leitura_mod)
  v1[i].close()
  v2[i].close()




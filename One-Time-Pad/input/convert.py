v1 = range(0,7)
#leitura = range(1,7)
v2 = range(0,7)


for i in range(0,7):
	print (i)
	v1[i] = open("./msg/cifradas/c"+ str(i+1)+ ".txt", "r")
	v2[i] = open("./msg/cifradas-bin/c"+ str(i+1)+ ".txt", "w")
	leitura = v1[i].read()
	v2[i].write((bin(int(leitura, 16))[2:]))
	v1[i].close()
	v2[i].close()

#for i in range(0,6):
 #       v1[i] = open("./msg/cifradas/c"+ str(i)+ " .txt", "w")
  #      leitura[i] = v1[i].read()
   #     v1[i].close()


#leitura = c1.read()
#print(leitura)



#binario = (bin(int(leitura, 16))[2:])

#c1.write("oi")

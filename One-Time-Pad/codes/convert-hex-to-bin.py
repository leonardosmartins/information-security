v1 = range(0,7)
v2 = range(0,7)

for i in range(0,7):
	print (i)
	v1[i] = open("../input/msg/cifradas-hex/c"+ str(i+1)+ ".txt", "r")
	v2[i] = open("../input/msg/cifradas-bin/c"+ str(i+1)+ ".txt", "w")
	leitura = v1[i].read()
	v2[i].write((bin(int(leitura, 16))[2:]))
	v1[i].close()
	v2[i].close()


import re
#############################################################
# Funcao que faz XOR de dois arquivos
def xorArq(arquivo1, arquivo2, nome1, nome2):

	leitura_arq1 = arquivo1.read()
	leitura_arq2 = arquivo2.read()

	arq_saida = open("Xors/" + nome1 + nome2 + ".txt", "w")

	i=0
	while i < len(leitura_arq1)-1:
		meio_byte1 = leitura_arq1[i]
		meio_byte2 = leitura_arq2[i]
		byte1 = meio_byte1 + leitura_arq1[i+1]
		byte2 = meio_byte2 + leitura_arq2[i+1]
		byte1 = "0x" + byte1
		byte2 = "0x" + byte2
		byteInt1 = int(byte1, 16)
		byteInt2 = int(byte2, 16)


		byte_binario_string = str(bin(byteInt1^byteInt2))

		aux = re.split("0b", byte_binario_string)
		byte_binario_string = aux[1]

		# Colocando o binario no formato de 8 bits
		j=len(byte_binario_string)
		while j < 8:
			byte_binario_string = "0" + byte_binario_string
			j = j+1

		arq_saida.write(byte_binario_string + " ")

		i = i + 2
	
	arquivo1.seek(0, 0)
	arquivo2.seek(0, 0)
	arq_saida.close()


##############################################################


#  M A I N 
vetArq = range(0,7)


# Fazendo XOR com todos os 7 arquivos, sem repeticoes
for i in range(0,7):
	vetArq[i] = open("Mensagens_Cifradas/"+str(i+1)+".txt", "r")

for i in range(0,7):
	for j in range(i,7):
		if (i != j):
			xorArq(vetArq[i],vetArq[j], str(i+1), str(j+1))

for i in range(0,7):
	vetArq[i].close()

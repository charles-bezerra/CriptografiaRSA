import sys
from random import randint
from random import choice
class RSA(object):
	__p = 11
	__q = 17
	__n = 187
	__fi = 160
	__e = 37
	__d = 173

	def __init__(self):
		encrypt = []
		print("Valor de fi: ", self.__fi)
		print("Valor de n: ", self.__n)
		print("Chave pública: ", self.__e)
		print("Chave privada: ", self.__d)
		line = input("Digite texto: ")

		for letra in line:		
			encrypt.append( self.encode(ord(letra)) )
			encrypt.append(";")
		
		msg_c = ""

		for i in encrypt:
			msg_c = msg_c + str(i)
			
		print(msg_c)

	def public_create_key(self):
		self.__n = self.__p * self.__q
		self.__fi = (self.__p-1)*(self.__q-1)

		mdcs = []
		cont = self.__fi

		while (cont != 1):
			if(self.mdc(self.__fi, cont)==1):
				mdcs.append(cont)
			cont -= 1
		print(mdcs)
		

		while(not(self.isprime(self.__e)) or self.__e == 0 or self.__e == 1):
			self.__e = mdcs[randint(0,len(mdcs)-1)]

	def private_create_key(self):
		aux=1
		while(True):
			if((aux*self.__e)%(self.__fi)==1 and aux>self.__e):
				break
			aux+=1
		self.__d = aux

	def decode(self,x):
		return (x**self.__d)%self.__n

	def encode(self,x):
		return (x**self.__e)%self.__n

	def isprime(self,num):
		if(num%2 == 0 or num == 2):
			return False
		else:
			cont = 0;
			for i in range(2,num):
				if(num%i == 0):
					print(cont)
					cont+=1
			if(cont==0):
				return True
				print("True")
			else:
				print("False")
				return False


	def mdc(self, a, b):
		resto = 1
		result = 0
		if(a == 0 or b == 0):
			return 0
		
		elif(a>b):
			while(resto != 0):
				aux1 = a
				aux2 = b
				b = a % b
				a = aux2
				resto = aux1%aux2
				result = aux2
		elif(a<b):
			while (resto != 0):
				aux1 = b
				aux2 = a
				a = b % a
				b = aux2
				resto = aux1%aux2
				result = aux2
		else:
			return a
		return result

if __name__ == "__main__":
	RSA()
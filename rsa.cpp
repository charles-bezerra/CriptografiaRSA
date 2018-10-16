#include<iostream>
#include<math.h>
#include<vector>
#include <ctime>
#include <stdlib.h> 

using namespace std;

class RSA
{
	protected:
		int e = 0;
		int n = 0;
		int fi_n = 0;
		int d = 0;
		bool isprime(int num);
		int mdc(int a, int b);
		int mod(int a, int b);
	
	public:
		void public_create_key(int p, int q);
		void private_create_key();
		int encode(int x);
		int decode(int x);	
};
int RSA::mdc(int a, int b)
{
	int aux1, aux2, resto = 1, resultado = 0;

	if(a == 0 || b == 0)
	{
		return 0;
	}
	else if(a>b)
	{
	    while (resto != 0)
	    {
	    	aux1 = a;
	        aux2 = b;
	        b = a % b;
	        a = aux2;
	        resto = aux1%aux2;
	        resultado = aux2;
		}
	}
	else if(b>a)
	{
	    while (resto != 0)
	    {
	    	aux1 = b;
	        aux2 = a;
	        a = b % a;
	        b = aux2;
	        resto = aux1%aux2;
	        resultado = aux2;
	    }
	}
	else
	{
		return a;
	}
	return resultado;	
}
int RSA::mod(int a, int b)
{
	if(a < b){
        return a;
    }
    return a%b;
}
bool RSA::isprime(int num)
{
	if(num%2 == 0 || num == 2)
	{
		return false;
	}
	else
	{
		int cont = 0;
		for(int i = 2; i < num; i++)
		{
			if(num%i == 0)
			{
				cont++;
			}
		}
		if(cont==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
void RSA::public_create_key(int p, int q)
{
	this->n = p*q;
	vector<int> mdcs;

	this->fi_n = (p-1)*(q-1);

	cout<<"Valor fi: "<< this->fi_n << endl;
	int cont = this->fi_n;
	
	while(cont != 1)
	{
		if(this->mdc(this->fi_n, cont) == 1){
			mdcs.push_back(cont);
		}
		cont -= 1;
	}
	
	for(int i = 0; i < mdcs.size(); i++) cout<< mdcs[i] << ", ";
	cout<<endl;
	
	while( not(this->isprime(this->e)) || this->e == 0 || this->e == 1)
	{
		this->e = mdcs[ (rand()%mdcs.size()) ];
	}
	cout<<"Chave publica: "<<this->e<<endl;
}
void RSA::private_create_key()
{
	int d = 0;
    while ( ((this->e*d) % this->fi_n) != 1 && d != this->e)
	{
    	d = d + 1;
    }
	this->d = d;
	cout<<"Chave privada: "<< this->d<<endl;
}
int RSA::encode(int x)
{
	int pot = pow(x, this->e);
	int aux = this->mod(pot,this->n);
	return aux;
}
int RSA::decode(int x)
{
	int pot = pow(x, this->d);
	int aux = this->mod(pot, this->n);	
	return aux;
}	


int main(){
	int p, q, x;
	
	cout<<"P: ";
	cin>>p;
	
	cout<<"Q: ";
	cin>>q;
	
	RSA rsa;
	
	rsa.public_create_key(p,q);
	rsa.private_create_key();
	
	cout<<"Digite um numero: ";
	cin>>x;
	
	int c = rsa.encode(x);
	
	cout<<c<<endl;
	int result = rsa.decode(c);
	
	cout<< result<<endl;
	
}

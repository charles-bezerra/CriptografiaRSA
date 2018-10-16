import java.lang.System;
import java.lang.Math;
import java.util.Scanner;
import java.util.Random;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class Criptografador{
	protected int e = 0;
	protected int n = 0;
	protected int fi = 0;
	protected int d = 0;

	protected String[][] table = 
	{ 
		{"A", "111"}, {"B", "112"}, {"C", "113"}, 
		{"D", "114"}, {"E", "115"}, {"F", "116"}, 
		{"G", "117"}, {"H", "118"}, {"I", "119"}, 
		{"J", "121"}, {"K", "122"}, {"L", "123"},
		{"M", "124"}, {"N", "125"}, {"O", "126"}, 
		{"P", "127"}, {"Q", "128"}, {"R", "129"}, 
		{"S", "131"}, {"T", "132"}, {"W", "133"}, 
		{"X", "134"}, {"Y", "135"}, {"Z", "136"}
	};
	protected BigDecimal encodado;
	protected BigDecimal decodado;
	
	public static void main(String [] args)
	{ 
		Scanner sc = new Scanner(System.in);
		Criptografador c = new Criptografador();

		System.out.print("P: ");
		int p = sc.nextInt();

		System.out.print("Q: ");
		int q = sc.nextInt();
		
		c.public_create_key(p, q);
		c.private_create_key();

		System.out.print("Chave pública: ");
		System.out.println(c.e);
		System.out.print("Chave privada: ");
		System.out.println(c.d);
		System.out.print("N: ");
		System.out.println(c.n);


		System.out.print("Digite um número para criptografar: ");
		int x = sc.nextInt();

		c.encode(x);


		c.decode(c.encodado.intValue());

		System.out.println(c.encodado);
		System.out.println(c.decodado);

	}
	public void public_create_key(int p, int q)
	{
		this.n = p*q;

		ArrayList<Integer> mdcs = new ArrayList<Integer>();


		this.fi = (p-1)*(q-1);

		System.out.print("valor fi: ");
		System.out.println(this.fi);

		int cont = this.fi;

		while(cont != 1)
		{
			if(mdc(this.fi,cont)==1){
				mdcs.add(cont);
			}
			cont -= 1;
		}

		System.out.println(mdcs);
		Random g = new Random();
		
		while(!isprime(this.e) || this.e == 0 || this.e == 1)
		{
			this.e = mdcs.get( g.nextInt( mdcs.size() ) );
		}
		
	}
	
	public void private_create_key()
	{		
		int aux = 1;
    	while(true)
    	{
    		if((aux*this.e)%(this.fi) == 1 && aux > this.e)
    		{
    			break;
    		}
        	aux++;
    	}
		this.d = aux;
	}
	public void encode(int x)
	{	
		BigDecimal aux = new BigDecimal("1");
		BigDecimal aux1 =  new BigDecimal( this.n );
		
		BigDecimal aux2 = new BigDecimal( x );

		for(int i = 0; i < this.e; i++)
		{
			aux = aux.multiply(aux2);
		}
		System.out.println(aux);
		this.encodado = aux.remainder(aux1);
	}
	public void decode(int x)
	{
		BigDecimal aux = new BigDecimal("1");
		BigDecimal aux1 =  new BigDecimal( this.n );
		
		BigDecimal aux2 = new BigDecimal( x );

		for(int i = 0; i < this.d; i++)
		{
			aux = aux.multiply(aux2);
		}
		System.out.println(aux);
		this.decodado = aux.remainder(aux1);
	}
	public boolean isprime(int num)
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
			else{
				return false;
			}
		}
	}

	public int mdc(int a, int b)
	{
		  int aux1, aux2, resto = 1, resultado = 0;

	      if(a == 0 || b == 0)
	      {
	        return 0;
	      }
	      else if(a>b){
	        while (resto != 0)
	        {
	            aux1 = a;
	            aux2 = b;
	            b = a % b;
	            a = aux2;
	            resto = aux1%aux2;
	            resultado = aux2;
	        }
	      }else if(b>a){
	        while (resto != 0)
	        {
	            aux1 = b;
	            aux2 = a;
	            a = b % a;
	            b = aux2;
	            resto = aux1%aux2;
	            resultado = aux2;
	        }
	      }else{
	        return a;
	      }
	      return resultado;
	}
}
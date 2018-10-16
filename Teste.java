import java.util.Scanner;
import java.lang.Math;
import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Teste{
	private int n = 187;
    private int d = 173;
	public static void main(String[] args) {
		/*System.out.print("N: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		if(num%2 == 0 || num == 1 || num == 2)
		{
			System.out.println("False");
		}
		else
		{
			int cont = 0;
			for(int i = 2; i < num; i++)
			{
				if(num%i == 0)
				{
					System.out.println(cont);
					cont++;
				}
			}
			if(cont==0)
			{
				System.out.println("True");
		//		return true;
			}
			else{
				System.out.println("False");
		//		return false;
			}
		}*/
		Teste t = new Teste();
		t.descrypt();
	}
	protected void descrypt()
    {
        StringBuilder msg_d = new StringBuilder("");
        Scanner sc = new Scanner(System.in);

        System.out.print("Mensagem: ");

        String msg_c = sc.nextLine();

        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(String num: msg_c.split(";"))
        {

            nums.add(Integer.parseInt(num));
        }
        for(int i = 0; i < nums.size(); i++)
        {
			char aux = (char)decode(nums.get(i));
            System.out.println(aux);

        }
        //System.out.println(msg_d.toString());
    }
   	public int decode(int x)
    {
        BigDecimal aux = new BigDecimal("1");
        BigDecimal aux1 =  new BigDecimal( n );
        BigDecimal aux2 = new BigDecimal( x );
        for(int i = 0; i < d; i++)
        {
            aux = aux.multiply(aux2);
        }
        BigDecimal decodado = aux.remainder(aux1);
        return decodado.intValue();
    }
}
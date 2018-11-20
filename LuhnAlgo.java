import java.util.Scanner;
public class LuhnAlgo 
{
	long N;
	public void getno()
	{
		Scanner rr=new Scanner(System.in);
		N=rr.nextLong();
	}
	public void isValid()
	{
		long s=0;
		long e=0;
		int oddcheck=0;
		while(N>=1)
		{
			long b=N%10;
			if(oddcheck%2==0)
				{s=s+b;}
			else
			{
				long c=b*2;
				if(c>=10)
				{
					long d;
					while(c>=1)
					{
						d=c%10;
						e=e+d;
						c=c/10;
					}
					s=s+e;
					e=0;						
				}
				else
					{s=s+c;}
			}
			oddcheck+=1;
			N=N/10;
			System.out.println(s);
		}
		if(s%10==0)
			{System.out.println("VALID");}
		else
			{System.out.println("INVALID");}
	
	}
	public static void main(String args[])
	{
		int T;
		LuhnAlgo obj= new LuhnAlgo();
		Scanner sc=new Scanner(System.in);	
		T= sc.nextInt();
		for(int f=0;f<T;f++)
		{
			obj.getno();
			obj.isValid();
		}
		sc.close();
	}
}

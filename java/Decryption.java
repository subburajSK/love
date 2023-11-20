import java.util.Scanner; 
 
public class Decryption{ 
 	 
 	static void decrypt(String encpt, String key)
 { 
 	 	 
 	 	char[] encpt_arr=encpt.toCharArray(); 
 	 	char[] key_arr=key.toCharArray(); 
 	 	 
 	 	char[] 
alpha={'A','B','C','D','E','F','G','H','I','K','L', 'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};  
 	 	char[][] table=new char[5][5]; 
 	 	 
 	 	int ctr=-1; 
 	 	for(int i=0;i<key_arr.length;i++)
  	 	 	for(int j=0;j<25;j++) 
 	 	 	 	if(key_arr[i]==alpha[j]) 
 	 	 	 	{ 
 	 	 	 	 	ctr++; 
 	 	 	 	 	int round = ctr/5; 
 	 	 	 	 	table[round][ctr%5]=alpha[j];
  	 	 	 	 	alpha[j]='0';
  	 	 	 	 	break; 
 	 	 	 	} 
 	 	for(int i=0;i<alpha.length;i++) 
 	 	{ 
 	 	 	 	if(alpha[i]!='0') 
 	 	 	 	{ 
 	 	 	 	 	ctr++; 
 	 	 	 	 	int round = ctr/5; 
 	 	 	 	 	table[round][ctr%5]=alpha[i]; 
 	 	 	 	} 
 	 	} 
 	 	 
  System.out.println("\nThe Reference Table is:"); 
 	 	for(int i=0;i<5;i++) 
 	 	{ 
	 	for(int j=0;j<5;j++) 
	 	 	System.out.print(table[i][j]+" "); 
	 	System.out.println(); 
}  
char[] any=new char[encpt_arr.length]; 
int any_ctr=0; 
 	 
 	 	for(int i=0;i<encpt_arr.length;i=i+2) 
 	 	{ 
 	 	 	int row1=0,row2=0,col1=0,col2=0; 
 	 	 	for(int j=0;j<5;j++) 
 	 	 	{ 
 	 	 	 	for(int k=0;k<5;k++) 
 	 	 	 	{ 
 	 	 	 	 	if(encpt_arr[i]==table[j][k]) 
 	 	 	 	 	{ 
 	 	 	 	 	 	row1=j;
  	 	 	 	 	 	col1=k; 
 	 	 	 	 	 	break; 
 	 	 	 	 	} 
 	 	 	 	} 
 	 	 	} 
 	 	 	for(int j=0;j<5;j++) 
 	 	 	{ 
 	 	 	 	for(int k=0;k<5;k++) 
 	 	 	 	{ 
 	 	 	 
 	if(encpt_arr[i+1]==table[j][k]) 
 	 	 	 	 	{ 
 	 	 	 	 	 	row2=j;
  	 	 	 	 	 	col2=k; 
 	 	 	 	 	 	break; 
 	 	 	 	 	} 
 	 	 	 	} 
 	 	 	} 
 	 	 	if(row1==row2) 
	 	{ 
 	 	col1=(col1-1+5)%5;
  	 	col2=(col2-1+5)%5;
  	 	any[any_ctr++]=table[row1][col1];
  	        any[any_ctr++]=table[row2][col2];
 } 
else if(col1==col2) 
	 	 	{ 
 	 	 	 	row1=(row1-1+5)%5;
  	 	 	 	row2=(row2-1+5)%5;
  	 	 	 	any[any_ctr++]=table[row1][col1];
  	 	 	 	any[any_ctr++]=table[row2][col2]; 
 	 	 	} 
 	 	 	else if(row1!=row2 && col1!=col2) 
 	 	 	{ 
 	 	 	 	int row=0,col=0;
  	 	 	 	row=row1;
  	 	 	 	col=col2; 
 	 	 	 	any[any_ctr++]=table[row][col]; 
 	 	 	 	row=row2;
  	 	 	 	col=col1; 
 	 	 	 	any[any_ctr++]=table[row][col]; 
 	 	 	} 
 	 	 	else 
 	 	 	{ 
 	 	 	} 
 	 	} 
 	 	 
  System.out.print("\nThe Intermediate Text is: "); 
 	 	for(int i=0;i<any_ctr;i++)
  	 	System.out.print(any[i]); 
 	 	 
 	 	char[] decpt_arr=new char[100];
  	 	int decpt_arr_ctr=0; 
 	 	 
 	 	for(int i=0;i<any_ctr;i++) 
{ 
	 	if(i==0) 
	 	{ 
 	 	decpt_arr[decpt_arr_ctr++]=any[i];
         	continue; 
} 
if(i==1 && any[i-1]==any[i+1] && any[i]=='X') 
 	 	 	{ 
 	 	 	 	continue; 
 	 	 	} 
   if(i==1 && any[i-1]!=any[i+1] && any[i]!='X') 
 	 	 	{ 
 	 	 	 	decpt_arr[decpt_arr_ctr++]=any[i]; 
 	 	 	 	continue; 
 	 	 	} 
   if(i==2 && any[i-1]==any[i+1] && any[i]=='X') 
 	 	 	{ 
 	 	 	 	continue; 
 	 	 	} 
   if(i==2 && any[i-1]!=any[i+1] && any[i]!='X') 
 	 	 	{ 
 	 	 	 	decpt_arr[decpt_arr_ctr++]=any[i]; 
 	 	 	 	continue; 
 	 	 	} 
 	 	 	if(i!=any_ctr-2 && i!=any_ctr-1 && any[i-1]==any[i+1] && any[i]=='X') 
 	 	 	{ 
 	 	 	 	continue; 
 	 	 	} 
 	 	 	if(i!=any_ctr-2 && i!=any_ctr-1 && any[i-1]==any[i+1] && any[i]!='X') 
 	 	 	{ 
 	 	 	 	decpt_arr[decpt_arr_ctr++]=any[i]; 
 	 	 	 	continue; 
 	 	 	} 
 	 	 	if(i==any_ctr-2 && any[i-1]==any[i+1] && any[i]=='X') 
{ 
	 	continue; 
} 
 	 	 	if(i==any_ctr-2 && any[i-1]==any[i+1] && any[i]!='X') 
 	 	 	{ 
 	 	 	 	decpt_arr[decpt_arr_ctr++]=any[i]; 
 	 	 	 	continue; 
 	 	 	} 
 	 	 	if(i==any_ctr-1 && i%2!=0 && any[i]=='X') 
 	 	 	{ 
 	 	 	 	continue; 
 	 	 	} 
 	 	 	if(i==any_ctr-1 && any[i]!='X') 
 	 	 	{ 
 	 	 	 	decpt_arr[decpt_arr_ctr++]=any[i]; 
 	 	 	 	continue; 
 	 	 	} 
 	 	 	if(i==any_ctr-1 && i%2==0 && any[i]=='X') 
 	 	 	{ 
 	 	 	 	continue; 
 	 	 	} 
 	 	 	if(i>=0) 
 	 	 	{ 
 	 	 	 	decpt_arr[decpt_arr_ctr++]=any[i]; 
 	 	 	 	continue; 
 	 	 	} 
 	 	} 
 	 	 
  System.out.print("\n\nThe Decrypted Text is: "); 
 	 	for(int i=0;i<decpt_arr_ctr;i++)
  	 	System.out.print(decpt_arr[i]); 
 	 	 
 	} 
 
public static void main(String[] args)
 { 
 	 	String encpt; 
 	 	System.out.print("Enter Ciphertext: ");
  	 	Scanner scanner = new Scanner(System.in); 
 	 	encpt = scanner.nextLine(); 
 	 	String key; 
 	 	System.out.print("Enter Key: "); 
 	 	key = scanner.nextLine(); 
 	 	 
 	 	decrypt(encpt,key); 
 	} 
}

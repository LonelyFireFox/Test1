#include <iostream>
#include <string>
using namespace std;

//#include <cstring>//必须引用
//memset(sum,0,sizeof(sum));

bool isReverNum(string s){
	//判断字符串的相同的字符是否为偶数个，可带有一个单数的字符，则能成为回文数
	//否则imposiable
	int eddCount = 0;
	int a[26] = {0};
	bool isAllEvent;
	for(int i=0;i<s.length();i++){
		a[s[i]-'a'] ++;
	}
	
	
	for(int i=0;i<26;i++){
		cout<<a[i];
		if(a[i] % 2 !=0){
			cout<<a[i]<<endl;
			eddCount++;//如果有 
		}
	}
	cout<<"edd==>>"<<eddCount;
	//超过一个，不能构成回文 
	if(eddCount >1)
		return false;
	
	return true;
}

int main(){
	//先解决如何判断字符串是否能通过交换成为回文数
	 string str;
	 cin>>str;
	 cout<<isReverNum(str);
	

	return 0;
}


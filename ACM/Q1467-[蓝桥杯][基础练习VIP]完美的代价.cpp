#include <iostream>
#include <string>
using namespace std;

//#include <cstring>//��������
//memset(sum,0,sizeof(sum));

bool isReverNum(string s){
	//�ж��ַ�������ͬ���ַ��Ƿ�Ϊż�������ɴ���һ���������ַ������ܳ�Ϊ������
	//����imposiable
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
			eddCount++;//����� 
		}
	}
	cout<<"edd==>>"<<eddCount;
	//����һ�������ܹ��ɻ��� 
	if(eddCount >1)
		return false;
	
	return true;
}

int main(){
	//�Ƚ������ж��ַ����Ƿ���ͨ��������Ϊ������
	 string str;
	 cin>>str;
	 cout<<isReverNum(str);
	

	return 0;
}


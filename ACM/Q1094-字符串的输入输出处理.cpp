#include <iostream>
#include <string>
using namespace std;

int main(){
	int n;
	string str,str2;
	cin>>n;
	cin.get();
	while(n--){
		getline(cin,str);
		cout<<str.append("\n")<<endl;;//����ֱ�������һ��<<endl���� 
	}
	
	//����֮��İ��ո�ͻس��ָ�� 
	while(getline(cin,str2)){
	
		//C++û���Դ��ַ����ָ���ñ����������ո�ͻس������
		int index = 0;
		for(int i=0;i<=str2.length();i++){
//			cout<<i<<"==>"<<str2[i];
			if(str2[i] == ' '|| i == str2.length()){
//				cout<<index<<".."<<i<<endl;
				cout<<str2.substr(index,i-index).append("\n")<<endl;
				index = i+1;//����index 
			}
		} 
		
	}
	return 0;
}



//#include<iostream>
//#include<vector>
//#include<string>
//using namespace std;
//int main(){
//    int n;cin>>n;
//    cin.get();
//    vector<string> str;
//string s;
//    for(int i=0;i<n;++i){
//        getline(cin,s);
//        str.push_back(s);
//    }
//    while(cin>>s)
//       str.push_back(s);
//    vector<string>::iterator ite;
//    for(ite=str.begin();ite!=str.end();ite++)
//    cout<<*ite<<endl<<endl;
//return 0;
//}


//scanf() �������سɹ��������Ŀ�ĸ����������û�ж�ȡ�κ���Ŀ(��������������һ�����ֶ���ȴ�����һ���������ַ�ʱ�ͻᷢ���������),scanf()����0�� 
//������⵽���ļ�ĩβ��(end of file)ʱ��������EOF(EOF�����ļ�stdio.h�еĶ���õ�һ������ֵ��һ�㣬#defineָ�EOF��ֵ����Ϊ-1)������ѧ��ѭ��֮��
//���ڲμ�ACM�����ı�����ʹ����������ϵͳ��ʱ��ᾭ���õ�����д����

//c������� 
//#include<stdio.h>
//#include<string.h>
//int main()
//{
//	char a[1001];
//	int n;
//	 
//	scanf("%d",&n);
//	getchar();
//	for(int i=0;i<n;i++)
//	{gets(a);puts(a);printf("\n");}
//	 
//	while(scanf("%s",a)!=EOF)
//	{ //%s,�����Ե�һ���ǿհ׷������յ���һ���հ�Ϊֹ���ַ���
		//����������պþ��ǽ��յ��ʹ�ӡ��perfect 
//	  printf("%s\n",a);
//	  printf("\n");
//	}
//	return 0;
//}

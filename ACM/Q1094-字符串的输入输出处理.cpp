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
		cout<<str.append("\n")<<endl;;//这里直接再输出一个<<endl即可 
	}
	
	//大于之后的按空格和回车分割换行 
	while(getline(cin,str2)){
	
		//C++没有自带字符串分割，采用遍历，遇到空格和回车就输出
		int index = 0;
		for(int i=0;i<=str2.length();i++){
//			cout<<i<<"==>"<<str2[i];
			if(str2[i] == ' '|| i == str2.length()){
//				cout<<index<<".."<<i<<endl;
				cout<<str2.substr(index,i-index).append("\n")<<endl;
				index = i+1;//更新index 
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


//scanf() 函数返回成功读入的项目的个数。如果它没有读取任何项目(比如它期望接收一个数字而您却输入的一个非数字字符时就会发生这种情况),scanf()返回0。 
//当它检测到“文件末尾”(end of file)时，它返回EOF(EOF在是文件stdio.h中的定义好的一个特殊值，一般，#define指令将EOF的值定义为-1)。当您学过循环之后，
//或在参加ACM这样的比赛及使用在线评测系统的时候会经常用到这种写法。

//c解决方法 
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
//	{ //%s,接收以第一个非空白符到接收到下一个空白为止的字符串
		//所以在这里刚好就是接收到就打印，perfect 
//	  printf("%s\n",a);
//	  printf("\n");
//	}
//	return 0;
//}

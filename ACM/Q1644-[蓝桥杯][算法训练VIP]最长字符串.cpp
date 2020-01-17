#include <iostream>
#include <string>
#include <vector>
#include<algorithm> 
using namespace std;

bool comp(string str1,string str2)
{
    return str1.length()>str2.length();
}
int main(){
	
	//用vector做一道水题，为了熟悉下vector而已...
	 
	 
//	vector<string> vec;
//	string s;
//	while(cin>>s){
//		vec.push_back(s);
//		
//	}
	
	
	
//	vector<string>::iterator ite;
//	for(ite = vec.begin();ite != vec.end(); ite++){
//		cout<<*ite<<endl;
//	}
//	
	//直接排序输出第一个
//	sort(vec.begin(),vec.end(),comp);//#include<algorithm> d
//	cout<<vec[0]; 

	return 0;
}



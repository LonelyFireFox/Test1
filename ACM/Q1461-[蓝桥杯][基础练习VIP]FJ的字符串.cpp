#include <iostream>
#include <string>
using namespace std;
int main(){
	int n;
	cin>>n;
	string res,temp;
	for(int i=65;i<n+65;i++){
		res = temp + (char)i + temp;
		temp = res;
	}
	cout<<res;
	return 0;
}

#include <iostream>
#include <string>
using namespace std;

string exchange(string s,int i,int j) {
//	cout<<'i'<<i<<'j'<<j<<endl;
	//将s字符串中i位置的字符从左往右一步一步移到j位置
	for(int a = i; a<j; a++) {
		char ch = s[a];
		s[a] = s[a+1];
		s[a+1] = ch;
//		cout<<"s=>>"<<s<<endl;
	}
	return s;
}

int countChar(string s){
	int count = 0;
	int a[26] = {0};
	for(int i=0;i<s.length();i++){
		a[s[i]-'a']++;
	} 
	for(int i=0;i<26;i++){
		if(a[i]%2 !=0)
			count++;
	}
	return count;
}
int main() {
	int n;
	cin>>n;
	string str;
	cin>>str;

	//countChar是计算字符串中出现奇数次的字符有多少个
	if((n%2 ==0 && countChar(str)>0)
	        || (n% 2 != 0 && countChar(str)>1)
	  ) {
		cout<<"Impossible";
	} else {
		int res = 0;
		int len = str.length();
		for(int i=0; i<len/2; i++) {
			for(int j=len-1; j>=i; j--) {
				if(j == i){
					//相当于找不到相同的，该字符只有单个
					res += n/2 -i;//到中间的位置 
					cout<<"ress"<<res<<endl;
					cout<<str<<"..."<<endl;
					break;
				}
				if(str[i] == str[j] && i+j-1 != len) {
//				cout<<"xx"<<i<<j;
//				cout<<"=="<<str[i]<<str[j];
//				cout<<"before==>>"<<str<<endl;
					//找到相同且最适合的（贪心思想）
					int count = n-i-1-j;
					str = exchange(str,j,n-i-1);
				cout<<"after=>>"<<str<<'_'<<count<<endl;
					res += count;
					break;
				}
			}
		}
		cout<<res;

	}


	return 0;
}



//#include <iostream>
//using namespace std;
//int main() {
//    int n;
//    cin >> n;
//    string s;
//    cin >> s;
//    int j = n - 1;
//    int cnt = 0;//cnt用来统计交换的次数
//    int flag = 0;//flag判断是否已经有一个单独的奇个数的字符了
//    for(int i = 0; i < j; i++) {//i指针从头遍历到倒数第二个字符
//        for(int k = j; k >= i; k--) {//k指针从后面往前一直到i寻找和s[i]相同的s[k]
//            if(k == i) {//如果找不到相同的
//                if(n % 2 == 0 || flag == 1) {//impossible的两种情况
//                    cout << "Impossible";
//                    return 0;
//                }
//                flag = 1;
//                cnt += n / 2 - i;
//            } else if(s[k] == s[i]) {
//                for(int l = k; l < j; l++) {
//                    swap(s[l], s[l+1]);//把s[k]换到s[j]处
//                    cnt++;//统计交换次数
//                }
//                j--;
//                break;
//            }
//        }
//    }
//    cout << cnt;
//    return 0;
//}



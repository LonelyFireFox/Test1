#include <iostream>
#include <string>
using namespace std;

string exchange(string s,int i,int j) {
//	cout<<'i'<<i<<'j'<<j<<endl;
	//��s�ַ�����iλ�õ��ַ���������һ��һ���Ƶ�jλ��
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

	//countChar�Ǽ����ַ����г��������ε��ַ��ж��ٸ�
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
					//�൱���Ҳ�����ͬ�ģ����ַ�ֻ�е���
					res += n/2 -i;//���м��λ�� 
					cout<<"ress"<<res<<endl;
					cout<<str<<"..."<<endl;
					break;
				}
				if(str[i] == str[j] && i+j-1 != len) {
//				cout<<"xx"<<i<<j;
//				cout<<"=="<<str[i]<<str[j];
//				cout<<"before==>>"<<str<<endl;
					//�ҵ���ͬ�����ʺϵģ�̰��˼�룩
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
//    int cnt = 0;//cnt����ͳ�ƽ����Ĵ���
//    int flag = 0;//flag�ж��Ƿ��Ѿ���һ����������������ַ���
//    for(int i = 0; i < j; i++) {//iָ���ͷ�����������ڶ����ַ�
//        for(int k = j; k >= i; k--) {//kָ��Ӻ�����ǰһֱ��iѰ�Һ�s[i]��ͬ��s[k]
//            if(k == i) {//����Ҳ�����ͬ��
//                if(n % 2 == 0 || flag == 1) {//impossible���������
//                    cout << "Impossible";
//                    return 0;
//                }
//                flag = 1;
//                cnt += n / 2 - i;
//            } else if(s[k] == s[i]) {
//                for(int l = k; l < j; l++) {
//                    swap(s[l], s[l+1]);//��s[k]����s[j]��
//                    cnt++;//ͳ�ƽ�������
//                }
//                j--;
//                break;
//            }
//        }
//    }
//    cout << cnt;
//    return 0;
//}



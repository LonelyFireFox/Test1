#include <iostream>
using namespace std;

/*
*其它思路：将字符串可以直接==比较是否相同，可以先判断是否相同，不同的情况下，
再把两个字符串的每个字符加32，都变成大写的，（判断原先如果是大写就不用转），然后
再比对转换后的字符串，此时相同就属于不区分大小写时相等了
*/

bool isSameIgnore(string s1,string s2) {
	for(int i=0; i<s1.length(); i++) {
		if(s1[i]-s2[i] !=0 && s1[i] - s2[i] != 32 &&s1[i] - s2[i] != -32 ) {
			return false;
		}
	}
	return true;
}
bool isSame(string s1,string s2) {
	for(int i=0; i<s1.length(); i++) {
		if(s1[i] != s2[i]) {
			return false;
		}
	}
	return true;
}
int main() {
	string s1,s2;
	cin>>s1;
	cin>>s2;
	if(s1.length() == s2.length()) {
		//长度相同
		if(isSameIgnore(s1,s2)) {
			//不区分大小，能一样
			if(isSame(s1,s2)) {
				//完全一样
				cout<<2;
			} else {
				//只能是不区分大小的情况下一样，bengjing 和 BeiJING
				cout<<3;
			}
		} else {
			//完全不一样
			cout<<4;
		}
	} else {
		//长度不同
		cout<<1;
	}


	return 0;
}


//C解决
//串长相等，先设置类别为2，
//假设两串相等，在随后搜寻串内字符过程中，若发现对应字符不等，
//再假设当大小写无关时，两串相等，设置类别为3，但是随后判断两串中对应字符的距离：若不等于32，两串显然不等，设置类别为4，并停止搜寻随后的字符。

//#include<stdio.h>
//#include<string.h>
//#include<stdlib.h>
//int main(){
//    int la,lb,i,f;
//    char a[11],b[11];
//    scanf("%s%s",a,b);
//    la=strlen(a);
//    lb=strlen(b);
//    if(la!=lb) printf("1");//串长不等
//    else{//假设两串相等
//        for(f=2,i=0;i<la;i++){
//            if(a[i]!=b[i]){//若对应字符不等
//                f=3;//假设当大小写无关时两串相等
//                if(32!=abs(a[i]-b[i])){//若对应两字符距离不等于32
//                    f=4;//两串一定不等
//                    break;
//                }
//            }
//        }
//        printf("%d",f);
//    }
//    return 0;
//}



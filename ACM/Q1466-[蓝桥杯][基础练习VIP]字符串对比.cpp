#include <iostream>
using namespace std;

/*
*����˼·�����ַ�������ֱ��==�Ƚ��Ƿ���ͬ���������ж��Ƿ���ͬ����ͬ������£�
�ٰ������ַ�����ÿ���ַ���32������ɴ�д�ģ����ж�ԭ������Ǵ�д�Ͳ���ת����Ȼ��
�ٱȶ�ת������ַ�������ʱ��ͬ�����ڲ����ִ�Сдʱ�����
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
		//������ͬ
		if(isSameIgnore(s1,s2)) {
			//�����ִ�С����һ��
			if(isSame(s1,s2)) {
				//��ȫһ��
				cout<<2;
			} else {
				//ֻ���ǲ����ִ�С�������һ����bengjing �� BeiJING
				cout<<3;
			}
		} else {
			//��ȫ��һ��
			cout<<4;
		}
	} else {
		//���Ȳ�ͬ
		cout<<1;
	}


	return 0;
}


//C���
//������ȣ����������Ϊ2��
//����������ȣ��������Ѱ�����ַ������У������ֶ�Ӧ�ַ����ȣ�
//�ټ��赱��Сд�޹�ʱ��������ȣ��������Ϊ3����������ж������ж�Ӧ�ַ��ľ��룺��������32��������Ȼ���ȣ��������Ϊ4����ֹͣ��Ѱ�����ַ���

//#include<stdio.h>
//#include<string.h>
//#include<stdlib.h>
//int main(){
//    int la,lb,i,f;
//    char a[11],b[11];
//    scanf("%s%s",a,b);
//    la=strlen(a);
//    lb=strlen(b);
//    if(la!=lb) printf("1");//��������
//    else{//�����������
//        for(f=2,i=0;i<la;i++){
//            if(a[i]!=b[i]){//����Ӧ�ַ�����
//                f=3;//���赱��Сд�޹�ʱ�������
//                if(32!=abs(a[i]-b[i])){//����Ӧ���ַ����벻����32
//                    f=4;//����һ������
//                    break;
//                }
//            }
//        }
//        printf("%d",f);
//    }
//    return 0;
//}



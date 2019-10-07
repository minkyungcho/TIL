#include <iostream>
#include <stack>
#include <string>

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
	int number;
	cin >> number;
	cin.ignore();
	while(number--){
		string inputStr;
		getline(cin, inputStr);
		inputStr += '\n';
		stack<char> word;
		for(char ch : inputStr){
			if(ch == ' ' || ch == '\n'){
				while(!word.empty()){
					cout << word.top();
					word.pop();	
				}
				cout << ch;
			}else{
				word.push(ch);	
			}
		}
	}
	return 0;
}
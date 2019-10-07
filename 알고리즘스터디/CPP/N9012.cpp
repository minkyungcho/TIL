#include <iostream>
// #include <stack>

using namespace std;

string valid(string s){
	int cnt = 0;
	for(int i=0; i<s.size(); i++){
		if(s[i] == '('){
			cnt += 1;
		} else{
			cnt -= 1;
		}
		if(cnt < 0){
			return "NO";
		}
	}
	
	if(cnt == 0){
		return "YES";
	} else {
		return "NO";
	}
}

int main(){
	// ios_base::sync_with_stdio(false);
    // cin.tie(nullptr);
	int number;
	cin >> number;
	// cin.ignore();
	while(number--){
		string s;
		cin >> s;
		cout << valid(s) << '\n';
	}
	return 0;
}
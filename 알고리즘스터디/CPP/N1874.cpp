#include <iostream>
#include <stack>

using namespace std;

int main(){
	int t;
	cin >> t;
	string ans;
	stack<int> s;
	int m = 0;
	
	while(t--){
		int x;
		cin >> x;
		if(x > m){
			while(x > m){
				m++;
				s.push(m);
				ans += '+';
			}
			s.pop();
			ans += '-';
		} else{
			bool found = false;
			if(!s.empty()){
				int top = s.top();
				s.pop();
				ans += '-';
				if( x == top){
					found = true;
				}
			}
			if(!found){
				cout << "NO" << '\n';
				return 0;
			}
		}
	}
	for(auto x : ans){
		cout << x << '\n';
	}
	return 0;
}
#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

vector<vector<int>> map(10001, vector<int>(10001,0));
vector<vector<int>> finalmap(10001, vector<int>(10001,0));
vector<int> check(10001,0);

int number, nummax;

void DFS(int v){
	for(int i=1; i<=number; i++){
		if(map[v][i]==1 && check[i]==0){
			check[i] = 1;
			DFS(i);
		}
	}
}

int main(){
	int line;
	cin >> number;
	cin >> line;
	vector<int> max(10001,0);
	
	for(int i=1; i<=line; i++){
		int a, b;
		cin >> a >> b;
		map[b][a] = 1;
	}
	
	for(int i=1; i<=number; i++){
		DFS(i);
		
		for(int k=1; k<=number; k++){
			finalmap[i][k] = check[k];
		}
		
		for(int j=1; j<=number; j++){
			check[j] = 0;
		}
	}
	
// 	cout << endl;
	
	// for(int i=1; i<=number; i++){
	// 	for(int j=1; j<=number; j++){
	// 		cout << map[i][j] << " ";
	// 	}
	// 	cout << endl;
	// }
	
	// cout << endl;
	
	// for(int i=1; i<=number; i++){
	// 	for(int j=1; j<=number; j++){
	// 		cout << finalmap[i][j] << " ";
	// 	}
	// 	cout << endl;
	// }
	
	for(int i=1; i<=number; i++){
		for(int j=1; j<=number; j++){
			if(finalmap[i][j]==1){
				max[i]++;	
			}
		}
	}
	
	// cout << endl;
	
	// for(int i=1; i<=number; i++){
	// 	cout << max[i] << " ";
	// }
	// cout << endl;
	// cout << endl;
	
	nummax = 0;
	for(int i=1; i<=number; i++){
		if(max[i] > nummax){
			nummax = max[i];
		}
	}
	
	for(int i=1; i<=number; i++){
		if(max[i] == nummax){
			cout << i << " ";
		}
	}
	
	// cout << endl;
	
	return 0;
}
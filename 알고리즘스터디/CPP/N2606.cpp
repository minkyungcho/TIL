#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> map(101, vector<int>(101,0));
vector<vector<int>> finalmap(101, vector<int>(101,0));
vector<int> check(101,0);
int num;

void DFS(int v){
	for(int i=1; i<=num; i++){
		if(check[i]==0 && map[v][i]==1){
			check[i] = 1;
			DFS(i);
		}
	}
}

int main(){
	num;
	int netnum;
	cin >> num >> netnum;
	for(int i=0; i<netnum; i++){
		int a, b;
		cin >> a >> b;
		map[a][b] = 1;
		map[b][a] = 1;
	}
	
	// map 출력
	// cout << endl;
	// for(int i=1; i<=num; i++){
	// 	for(int j=1; j<=num; j++){
	// 		cout << map[i][j] << " ";
	// 	}
	// 	cout << endl;
	// }
	// cout << endl;
	
	for(int i=1; i<=num; i++){
		DFS(i);
		for(int j=1; j<=num; j++){
			finalmap[i][j] = check[j];
		}
		for(int k=1; k<=num; k++){
			check[k] = 0;
		}
	}
	
	// finalmap 출력
	// for(int i=1; i<=num; i++){
	// 	for(int j=1; j<=num; j++){
	// 		cout << finalmap[i][j] << " ";
	// 	}
	// 	cout << endl;
	// }
	int cnt = 0;
	for(int i=1; i<=num; i++){
		if(finalmap[1][i]==1){
			cnt++;
		}
	}
	int result;
	result = cnt -1;
	
	cout << result;
}
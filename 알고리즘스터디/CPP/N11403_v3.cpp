#include <iostream>
#include <cstring>
#include <vector>
// #include <>
using namespace std;

// int map[101][101];
// int finalmap[101][101];
vector<vector<int>> map(101, vector<int>(101,0));
vector<vector<int>> finalmap(101, vector<int>(101,0));
vector<int> check(101,0);

int number, tmp;


void DFS(int v){
	for(int i=1; i<=number; i++){
		if(map[v][i]==1 && check[i]==0){
			check[i] = 1;
			DFS(i);
		}
	}
}

int main(){
	cin >> number;
	
	
	
	for(int i=1; i<=number; i++){
		for(int j=1; j<=number; j++){
			cin >> map[i][j];
		}
	}
	
	for(int i=1; i<=number; i++){
		
		// tmp = i;
		DFS(i);
		
		for(int j=1; j<=number; j++){
			finalmap[i][j] = check[j];
		}
		// memset(check, 0, sizeof(101)); 포인터 써야해
		
		// for문 0으로 check vector 초기화 시키기
		for(int k=1; k<=number; k++){
			check[k] = 0;
		}
		
		
	}
	
	
	for(int i=1; i<=number;i++){
		for(int j=1; j<=number; j++){
			cout << finalmap[i][j] << " ";
		}
		cout << "\n";
	}

	return 0;
}


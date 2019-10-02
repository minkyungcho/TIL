#include <iostream>
#include <cstring>
// #include <>
using namespace std;

int map[101][101];
int finalmap[101][101];
int number;

void DFS(int n[101][101], int* c, int v){
	for(int i=1; i<=number; i++){
		if(n[v][i]==1 && c[i]==0){
			c[i] = 1;
			DFS(n, c, i);
		}
	}
}

int main(){
	cin >> number;
	int* check = new int[number + 1];
	// int형 vector 크기 지정
	// vector<vector<int>> map(number+1, vector<int>(number+1,0));
	// vector<vector<int>> finalmap(number+1, vector<int>(number+1,0));
	for(int i=1; i<=number;i++){
		check[i] = 0;
		
		for(int j=1; j<=number; j++){
			cin >> map[i][j];
		}
	} 
	
	for(int i=1; i<=number;i++){
		DFS(map, check, i);
		
		for(int j=1; j<=number; j++){
			finalmap[i][j] = check[j];
		}
		
		memset(check, 0, sizeof(int) * (number + 1));
	}	
	
	for(int i=1; i<=number;i++){
		for(int j=1; j<=number; j++){
			cout << finalmap[i][j] << " ";
		}
		cout << "\n";
	}

	return 0;
}


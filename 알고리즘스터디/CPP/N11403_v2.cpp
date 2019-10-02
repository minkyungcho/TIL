#include <iostream>
#include <cstring>
// #include <>
using namespace std;

int map[101][101];
int finalmap[101][101];
int number;

void DFS(int* check, int v){
	for(int i=1; i<=number; i++){
		if(map[v][i]==1 && check[i]==0){
			check[i] = 1;
			DFS(check, i);
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
		DFS(check, i);
		
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


#include<iostream>
#include<queue>
using namespace std;
 
#define MAX_VALUE 1001 // 정점 1~1000
int N, M, V;                  
int m[MAX_VALUE][MAX_VALUE]; // 인접행렬
int visit[MAX_VALUE]; // 방문한 정점 체크해주는 배열 
 
//void dfs(int v) {}
//void bfs(int v) {}
 
void dfs(int v) {   
    cout << v << ' ' ;
    visit[v] = 1; // 방문한 정점 배열 0 -> 1
    for(int i=1; i<=N; i++) {
        if(visit[i] == 1 || m[v][i] == 0)     
            continue;
        dfs(i);  // 재귀
    }
}
 
void bfs(int v) {
    queue <int> q; 
    q.push(v);
    visit[v] = 0; // 방문한 정점 배열 1 -> 0
    while(!q.empty()) {
        v = q.front();
        cout << q.front() << ' ';
        q.pop();
        for(int i=1; i<=N; i++) {
            if(visit[i] == 0 || m[v][i] == 0)
                continue;
            q.push(i);
            visit[i] = 0;
        }
    }
}
 
int main() {
    int x, y;
    cin >> N >> M >> V; // 정점개수, 간선개수, 시작정점
    for(int i=0; i<M; i++) {   
        cin >> x >> y; 
        m[x][y] = m[y][x] = 1; // 인접행렬에 표시
    }
    dfs(V);            
    cout << '\n';
    bfs(V);
    return 0;
}

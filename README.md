# Dijkstra
> 자바 awt 모듈을 이용해 최적경로를 표시하는 프로그램입니다.
## 사용법
1. 화면을 클릭해 노드를 만듭니다.
2. 상단의 Edge 버튼을 클릭하고 노드와 노드를 드래그하여 선을 그린 다음 경로 값을 입력합니다.
3. 상단의 Select 버튼을 클릭하고 노드를 우클릭해 start와 end를 정합니다.
4. 아무 노드나 우클릭 하고 ShortPath를 누르면 최적 경로가 표시됩니다.

## 다익스트라 알고리즘
```
public void shortest() {
  // 모든 노드의 값을 0으로 설정합니다.
  start.setDist(0);
  Node ncur = start;
  
  while (true) {
  //현재 노드를 방문 처리합니다.
    ncur.setVisit();
    if (end.getVisit()) {
      break;
    }
  //현재 노드의 값을 불러옵니다.
    int d = ncur.getDist();
    int min = 999;
  
    for (int i=0; i<nlist.size(); i++) {
      Node n = nlist.get(i);
      if (n.getVisit()) {
        continue;
      }
      int ndist = n.getDist();
  // 현재 노드와 연결된 노드를 찾고 노드의 값이 최소인 노드를 다음 노드로 정합니다.
      if (getEdge(ncur,n)!=null) {
        dist = Integer.parseInt(getEdge(ncur,n).getDist());
        if (d+dist<ndist) {
  // 현재 노드와 다음 노드의 값을 더해 다음 노드에 할당한 후 현재 노드를 다음 노드의 부모 노드로 정합니다.
          ndist = d+dist;
          n.setDist(ndist);
          n.setPre(ncur);
        }
      }

      if (ndist<min) {
        min = ndist;
        cur0 = n;
      }
    }
    ncur = cur0;
  }
  // end지점부터 start까지 부모 노드를 찾으면서 최적 경로를 찾습니다.
  ncur = end;
  while (ncur.getPre()!=null) {
    cur0 = ncur.getPre();
    Edge e = getEdge(cur0,ncur);
    e.setSP(true);
    ncur = cur0;
  }
  l.setText("Shortest Path is       "+end.getDist());
}
```

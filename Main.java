package dijkstra;

public class Main {

	public static void main(String[] args) {
		MyFrame f = new MyFrame();
		f.setLocation(1200, 300);
		f.setSize(700,600);
		f.setVisible(true);
	}

}
// 1. 상단의 Node를 클릭하고 원하는 위치에 노드를 만드세요.
// 2. 상단의 Edge를 클릭하고 노드와 노드를 드래그하여 선을 그리고 값을 입력해 ok를 누르세요.
// 3. 상단의 Select를 클릭하고 노드를 우클릭해 start와 end를 누르세요.
//    (Select모드에서 노드를 드래그하여 위치를 조정할 수 있습니다.)
// 4. 아무노드나 우클릭해 ShortPath를 누르면 최적의 경로가 표시됩니다. 
// 5. (이때 reset을 누르면 최적 경로값이 리셋됩니다.)

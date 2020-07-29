package errorAndfalse;

//RecordNotFoundException.java

public class RecordNotFoundException extends Exception{



// 5.0부터 필요한 변수, 속도 및 보안 문제 해결 목적

/*Serializable class를 사용하게 되면

static final = serialVersionUID 필드(클래스의 버전넘버) 가 생성되는데

보통은 컴파일을 다시해도 유지가 되지만,

가끔의 경우 유지가 안될 때도 있단다.

유지가 안된상태에서 빌드를 하면,

디스크에서 다시 읽을 때, 내가 갖고 있는 클래스의 시그니쳐와

파일에 기록된 클래스 시그니처를 비교하며

serialVersionUID가 달라 deserialize 실패가 된다.

이전의 버전 넘버를 강제로 되돌릴 수 있지만,

몇 개의 serializable에서 동일한 버전넘버로 디스크에

새겨져 있다면 큰 문제가 될 수 있다.

라는데 아직 모르겠다.

아래 구문을 추가하여 해결할 수 있다.*/

private static final long serialVersionUID = 1L;



public RecordNotFoundException(String mesg){

super( mesg );

}

}
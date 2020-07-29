package errorAndfalse;

//RecordNotFoundException.java

public class RecordNotFoundException extends Exception{



// 5.0���� �ʿ��� ����, �ӵ� �� ���� ���� �ذ� ����

/*Serializable class�� ����ϰ� �Ǹ�

static final = serialVersionUID �ʵ�(Ŭ������ �����ѹ�) �� �����Ǵµ�

������ �������� �ٽ��ص� ������ ������,

������ ��� ������ �ȵ� ���� �ִܴ�.

������ �ȵȻ��¿��� ���带 �ϸ�,

��ũ���� �ٽ� ���� ��, ���� ���� �ִ� Ŭ������ �ñ״��Ŀ�

���Ͽ� ��ϵ� Ŭ���� �ñ״�ó�� ���ϸ�

serialVersionUID�� �޶� deserialize ���а� �ȴ�.

������ ���� �ѹ��� ������ �ǵ��� �� ������,

�� ���� serializable���� ������ �����ѹ��� ��ũ��

������ �ִٸ� ū ������ �� �� �ִ�.

��µ� ���� �𸣰ڴ�.

�Ʒ� ������ �߰��Ͽ� �ذ��� �� �ִ�.*/

private static final long serialVersionUID = 1L;



public RecordNotFoundException(String mesg){

super( mesg );

}

}
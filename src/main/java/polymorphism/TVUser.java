package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		//1.스프링 컨테이너를 구동한다
	AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
	//2.Sptring 컨테이너로부터 필요한 객체 르르 요청한다
	
	
	TV tv=(TV)factory.getBean("tv");
	tv.powerOn();
	tv.SoundUP();
	tv.SoundDown();
	tv.powerOff();


	//3.Spring 컨테이너를 종료한다
	factory.close();
	
	}
	
}

package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component("tv")
public class LgTV implements TV {
	
	@Autowired
	private Speaker speaker;
	
	public LgTV() {
		System.out.println("===>LgTV객체 생성");
	}
	
	public void powerOn() {
		System.out.println("LGTV----전원 켠다");
	}
	public void powerOff() {
		System.out.println("LGTV----전원 끈다");
	}
	public void  SoundUP() {
		speaker.volumeUp();
	}
	public void  SoundDown() {
		speaker.volumeDown();
	}

}

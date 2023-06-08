package polymorphism;

import org.springframework.stereotype.Component;


public class SonySpeaker implements Speaker {
	
	public SonySpeaker() {
		System.out.println("===> Sony Speaker 객체생성");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker ---- 소리올린다");
	}
	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker ---- 소리내린다");
	}

}

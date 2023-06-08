package polymorphism;

import org.springframework.stereotype.Component;

public class AppleSpeaker implements Speaker {
	
	public AppleSpeaker() {
		System.out.println("===> Apple Speaker객체생성 ");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker--볼륨올립니다");
	}
	@Override
	public void volumeUp() {

		System.out.println("AppleSpeaker--볼륨내립니다");
	}

}

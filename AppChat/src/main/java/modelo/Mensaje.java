package modelo;

import java.time.LocalDateTime;

public class Mensaje {
	private String text;
	private LocalDateTime time;
	private int emoticon;

	public Mensaje(String text, int emoticon) {
		this.text = text;
		this.time = LocalDateTime.now();
		this.emoticon = emoticon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getEmoticon() {
		return emoticon;
	}

	public void setEmoticon(int emoticon) {
		this.emoticon = emoticon;
	}
}

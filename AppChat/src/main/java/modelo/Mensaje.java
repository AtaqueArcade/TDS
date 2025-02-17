package modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Mensaje {
	private String text;
	private LocalDateTime time;
	private int emoticon;
	private int speaker;

	public Mensaje(String text, int emoticon, int speaker) {
		this.text = text;
		this.time = LocalDateTime.now();
		this.emoticon = emoticon;
		this.speaker = speaker;
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

	public void setSpeaker(int speaker) {
		this.speaker = speaker;
	}

	public LocalDateTime getDate() {
		return time;
	}

	public int getSpeaker() {
		return speaker;
	}

	public boolean isSentByUser(int id) {
		return speaker == id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Mensaje mensaje = (Mensaje) o;
		return Objects.equals(text, mensaje.text) && Objects.equals(time, mensaje.time)
				&& Objects.equals(emoticon, mensaje.emoticon) && Objects.equals(speaker, mensaje.speaker);
	}
}

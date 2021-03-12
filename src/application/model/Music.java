package application.model;

import java.io.File;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Music {

	private StringProperty name = new SimpleStringProperty();
	private ObjectProperty<File> f = new SimpleObjectProperty<File>();
	private ObjectProperty<Media> music = new SimpleObjectProperty<Media>();
	private ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<Genre> genre = new SimpleObjectProperty<Genre>();
	private ObjectProperty<Image> img = new SimpleObjectProperty<Image>();

	public Music(String name, File file, Media music, LocalDate date, Genre genre, Image img) {
		if (file != null) {
			setName(name);
			setF(file);
			setMusic(music);
			setDate(date);
			setGenre(genre);
			setImg(img);
		} else {

		}

	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Bitte geben sie einen Namen ein!");
		}
		this.name.set(name);

	}

	public File getF() {
		return f.get();
	}

	public ObjectProperty<File> fProperty() {
		return f;
	}

	public void setF(File f) {
		if (f == null) {
			throw new IllegalArgumentException("Kein Pfad ausgesucht!");
		}
		this.f.set(f);
	}

	public Media getMusic() {
		return music.get();
	}

	public ObjectProperty<Media> musicProperty() {
		return music;
	}

	public void setMusic(Media music) {
		if (music == null) {
			throw new IllegalArgumentException("Es wurde keine Musik ausgesucht!");
		}
		this.music.set(music);
		;
	}

	public LocalDate getDate() {
		return date.get();
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}

	public void setDate(LocalDate date) {
		if (date == null || date.isAfter(LocalDate.now().plusDays(1))) {
			throw new IllegalArgumentException("Das Datum kann nicht in der Zukunft liegen!");
		}
		this.date.set(date);
		;
	}

	public Genre getGenre() {
		return genre.get();
	}

	public ObjectProperty<Genre> genreProperty() {
		return genre;
	}

	public void setGenre(Genre genre) {
		if (genre == null) {
			throw new IllegalArgumentException("Keine Genre ausgesucht!!");
		}
		this.genre.set(genre);

	}

	public Image getImg() {
		return img.get();
	}

	public ObjectProperty<Image> imgProperty() {
		return img;
	}

	public void setImg(Image img) {
		if (img == null) {
			throw new IllegalArgumentException("Kein Bild ausgesucht");
		}
		this.img.set(img);

	}

	@Override
	public String toString() {
		return "Music [Name =" + getName() + ", File =" + getF() + ", Music =" + getMusic() + ", Date =" + getDate()
				+ ", Genre =" + getGenre() + ", Img =" + getImg() + "]";
	}

}

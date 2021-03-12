package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import application.view.MusicPane;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.util.Callback;

public class MusicAdministration {
	MusicPane mp;
	List<Music> musicList = new ArrayList<Music>();
	public ObservableList<Music> musicObservableList;

	public MusicAdministration(MusicPane mp) {
		this.mp = mp;
		musicObservableList = FXCollections.observableList(new ArrayList<Music>(), new Callback<Music, Observable[]>() {

			@Override
			public Observable[] call(Music m) {
				// TODO Auto-generated method stub
				return new Observable[] { m.nameProperty(), m.fProperty(), m.musicProperty(), m.dateProperty(),
						m.genreProperty(), m.imgProperty() };
			}
		});
		musicObservableList.add(new Music("Falling by Trevor ", new File("Trevor Daniel - Falling (Lyrics).mp3"),
				new Media(new File("Image//Trevor Daniel - Falling (Lyrics).mp3").toURI().toString()),
				LocalDate.of(2002, 3, 20), Genre.Rap, new Image("falling.png")));
		musicObservableList.add(new Music("Sel ", new File("Data Luv - fiji.mp3"),
				new Media(new File("Image//Data Luv - fiji.mp3").toURI().toString()), LocalDate.of(2002, 3, 20),
				Genre.Rap, new Image("falling.png")));
	}

	public ObservableList<Music> getMusicObservableList() {
		return musicObservableList;
	}

	public void setMusicObservableList(ObservableList<Music> musicObservableList) {
		this.musicObservableList = musicObservableList;
	}

	public void add(Music m) {
		musicObservableList.add(m);
	}

	public void del(ObservableList<Music> o) {
		musicObservableList.removeAll(o);
	}

	public ObservableList<Music> getMusic(Genre g) {

		ObservableList<Music> mList = FXCollections.observableArrayList();

		for (Music m : musicObservableList) {
			if (m.getGenre().equals(g)) {
				mList.add(m);
			}
		}
		return mList;
	}

	public void sortName() {
		FXCollections.sort(musicObservableList, new Comparator<Music>() {

			@Override
			public int compare(Music o1, Music o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	public void sortDate() {
		FXCollections.sort(musicObservableList, new Comparator<Music>() {

			@Override
			public int compare(Music o1, Music o2) {
				// TODO Auto-generated method stub
				return o1.getDate().compareTo(o2.getDate());
			}
		});
	}

	public void speichern(File f) {
		try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(f))) {
			ArrayList<Music> list = new ArrayList<Music>(musicObservableList);
			for (Music m : list) {
				ois.writeUTF(m.getName());
				ois.writeObject(m.getF());
				ois.writeObject(m.getMusic().getSource());
				ois.writeObject(m.getDate());
				ois.writeObject(m.getGenre());
				ois.writeObject(m.getImg().impl_getUrl());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void lesen(File f) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			for (int i = 0; i < musicObservableList.size(); i++) {
				add(new Music((String) ois.readUTF(), (File) ois.readObject(), (Media) ois.readObject(),
						(LocalDate) ois.readObject(), (Genre) ois.readObject(), (Image) ois.readObject()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

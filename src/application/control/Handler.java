package application.control;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import application.model.Genre;
import application.model.Music;
import application.model.MusicAdministration;

import application.view.MusicPane;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class Handler implements EventHandler<ActionEvent> {
	MusicPane mp;

	double volume;
	Music m;
	MediaPlayer mPlayer;
	ImageView img = new ImageView(new Image("pause.png"));
	ImageView im1 = new ImageView(new Image("playbutton.png"));
	MusicAdministration vw;
	MultipleSelectionModel<Music> selectionModel;

	Iterator<Music> it;

	public Handler(MusicPane mp, MusicAdministration vw) {
		this.mp = mp;
		this.vw = vw;
//		selectionModel = mp.list.getSelectionModel();
//		it = vw.musicObservableList.listIterator(selectionModel.getSelectedIndex() + 1);

		init();

	}

	public void init() {
		img.setFitWidth(70);
		img.setFitHeight(70);
		im1.setFitWidth(70);
		im1.setFitHeight(70);

	}

	@Override
	public void handle(ActionEvent event) {

		String data = "";
		if (event.getSource() instanceof ComboBox) {

			ComboBox<Genre> cb = (ComboBox<Genre>) event.getSource();
			switch (cb.getValue()) {
			case All:
				mp.list.getItems().clear();
				mp.list.getItems().addAll(vw.getMusicObservableList());
				break;

			case AfrikanischeMusik:

				mp.list.getItems().clear();
				mp.list.getItems().addAll(vw.getMusic(Genre.AfrikanischeMusik));
				break;
			case AsiatischeMusik:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.AsiatischeMusik));
				break;
			case Balladen:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Balladen));
				break;
			case Blues:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Blues));
				break;
			case Country:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Country));
				break;
			case Disco:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Disco));
				break;
			case DrumNBass:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.DrumNBass));
				break;
			case Electro:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Electro));
				break;
			case FilmandMusical:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.FilmandMusical));
				break;
			case Funk:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Funk));
				break;
			case Gospel:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Gospel));
				break;
			case HeavyRock:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.HeavyRock));
				break;
			case HipHop:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.HipHop));
				break;
			case Instrumental:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Instrumental));
				break;
			case Jazz:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Jazz));
				break;
			case Klassik:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Klassik));
				break;
			case Oriental:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Oriental));
				break;
			case Pop:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Pop));
				break;
			case Punk:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Punk));
				break;
			case Rap:

				mp.list.getItems().clear();
				mp.list.getItems().addAll(vw.getMusic(Genre.Rap));
				break;
			case Reggae:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Reggae));
				break;
			case Rock:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Rock));
				break;
			case RocknRoll:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.RocknRoll));
				break;
			case Salsa:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Salsa));
				break;
			case Schlager:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Schlager));
				break;
			case Techno:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Techno));
				break;
			case Volksmusik:
				mp.list.getItems().removeAll(vw.getMusicObservableList());
				mp.list.getItems().addAll(vw.getMusic(Genre.Volksmusik));
				break;

			}
		}

		if (event.getSource() instanceof MenuItem)

		{
			MenuItem m1 = (MenuItem) event.getSource();
			data = (String) m1.getUserData();
		} else if (event.getSource() instanceof Button) {
			Button b = (Button) event.getSource();
			data = (String) b.getUserData();
		}
		switch (data) {
		case "Add":

			mp.Dialog();
			mp.tfName.setText("");
			mp.dp.setValue(null);
			mp.cbGenre.setValue(null);
			mp.lImage.setImage(null);
			mp.lFile.setText("");
			mp.label.setText("");
			if (m != null) {
				vw.add(m);
				mp.musicObservableList = vw.getMusicObservableList();
				System.out.println(vw.getMusicObservableList());
			}

			break;
		case "Image":
			mp.fileImageChooser();
			break;
		case "File":
			mp.fileChooser();
			break;
		case "Play":
			System.out.println("Moigaaa");
			System.out.println(mp.getMusicListe().getMusic());
			System.out.println(mp.getMusicListe().getName());
			mPlayer = new MediaPlayer(mp.getMusicListe().getMusic());
			mp.musicImage.setImage(mp.getMusicListe().getImg());
			mp.musicTitle.setText(mp.getMusicListe().getName());
			mPlayer.play();
			mp.volumeSlider.setValue(mPlayer.getVolume() * 100);
			mp.zahl.setText(String.valueOf(Math.round(mPlayer.getVolume() * 100)));
			System.out.println("Sel");
			Status status = mPlayer.getStatus();

			System.out.println(status.toString());
			if (status == status.PLAYING) {
				if (mPlayer.getCurrentTime().greaterThanOrEqualTo(mPlayer.getTotalDuration())) {
					mPlayer.seek(mPlayer.getStartTime());
					mPlayer.play();
				}

			}
			mPlayer.currentTimeProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable observable) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							mp.musicSlider.setValue(
									mPlayer.getCurrentTime().toMillis() / mPlayer.getTotalDuration().toMillis() * 100);

						}

					});

				}
			});
			mp.musicSlider.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable observable) {
					// TODO Auto-generated method stub
					if (mp.musicSlider.isPressed()) {
						mPlayer.seek(mPlayer.getMedia().getDuration().multiply(mp.musicSlider.getValue() / 100));
					}
				}
			});

			mp.volumeSlider.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable observable) {
					mPlayer.setVolume(mp.volumeSlider.getValue() / 100);
					mp.setVolume(mPlayer.getVolume());
					mp.zahl.setText(String.valueOf(Math.round(mp.getVolume() * 100)));

				}
			});

			mp.play.setGraphic(img);
			mp.play.setUserData("Pause");
			break;
		case "Pause":
			mPlayer.pause();
			mp.play.setGraphic(im1);
			mp.play.setUserData("Play");

			break;
		case "Del":
			vw.del(mp.getMusicListen());
			mp.list.getItems().clear();
			mp.list.getItems().addAll(vw.getMusicObservableList());
			System.out.println(vw.getMusicObservableList());
			break;
		case "Name":
			vw.sortName();
			mp.list.getItems().clear();
			mp.list.getItems().addAll(vw.getMusicObservableList());
			System.out.println(vw.getMusicObservableList());
			break;
		case "Date":
			vw.sortDate();
			mp.list.getItems().clear();
			mp.list.getItems().addAll(vw.getMusicObservableList());
			break;
		case "Next":

//			if (it.hasNext()) {
//				mPlayer.pause();
//				Music m = it.next();
//				mPlayer = new MediaPlayer(m.getMusic());
//				mp.musicImage.setImage(m.getImg());
//				mp.musicTitle.setText(m.getName());
//				mPlayer.play();
//				mp.volumeSlider.setValue(mPlayer.getVolume() * 100);
//				mp.zahl.setText(String.valueOf(Math.round(mPlayer.getVolume() * 100)));
//				mPlayer.currentTimeProperty().addListener(new InvalidationListener() {
//
//					@Override
//					public void invalidated(Observable observable) {
//						Platform.runLater(new Runnable() {
//
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								mp.musicSlider.setValue(mPlayer.getCurrentTime().toMillis()
//										/ mPlayer.getTotalDuration().toMillis() * 100);
//
//							}
//
//						});
//
//					}
//				});
//				mp.musicSlider.valueProperty().addListener(new InvalidationListener() {
//
//					@Override
//					public void invalidated(Observable observable) {
//						// TODO Auto-generated method stub
//						if (mp.musicSlider.isPressed()) {
//							mPlayer.seek(mPlayer.getMedia().getDuration().multiply(mp.musicSlider.getValue() / 100));
//						}
//					}
//				});
//
//				mp.volumeSlider.valueProperty().addListener(new InvalidationListener() {
//
//					@Override
//					public void invalidated(Observable observable) {
//						mPlayer.setVolume(mp.volumeSlider.getValue() / 100);
//						mp.setVolume(mPlayer.getVolume());
//						mp.zahl.setText(String.valueOf(Math.round(mp.getVolume() * 100)));
//
//					}
//				});
//
//				mp.play.setGraphic(img);
//				mp.play.setUserData("Pause");
//			}
			break;
		case "Save":
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(null);
			if (f != null) {
				vw.speichern(f);
			}

			break;
		case "Load":
			FileChooser fc1 = new FileChooser();
			File f1 = fc1.showOpenDialog(null);
			if (f1 != null) {
				vw.lesen(f1);
			}

			break;
		}

	}

}

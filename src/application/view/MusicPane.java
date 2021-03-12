package application.view;

import java.io.File;
import java.util.ArrayList;

import application.control.Handler;
import application.model.Genre;
import application.model.Music;
import application.model.MusicAdministration;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.scene.control.Dialog;

public class MusicPane extends BorderPane {
	public ListView<Music> list;
	public Label zahl = new Label();
	DoubleProperty volume = new SimpleDoubleProperty();
	public ObservableList<Music> musicObservableList;
	FlowPane fp = new FlowPane();
	VBox vb = new VBox();
	public ImageView musicImage = new ImageView();
	public Label musicTitle = new Label();
	Button back = new Button();
	public Button play = new Button();
	Button next = new Button();
	public Slider musicSlider = new Slider();
	public Slider volumeSlider = new Slider();

	public ComboBox<Genre> cb = new ComboBox<Genre>();
	VBox vb1 = new VBox();
	Dialog<Music> add;
	public TextField tfName = new TextField();
	public ComboBox<Genre> cbGenre = new ComboBox<Genre>();
	public DatePicker dp = new DatePicker();
	public Label lFile = new Label();
	public ImageView lImage = new ImageView();
	public Button fileChooser = new Button("Select");
	public Button imageChooser = new Button("Select");
	public Label label = new Label();

	MusicAdministration ma = new MusicAdministration(this);
	Handler handler = new Handler(this, ma);
	ObservableList<Music> music = FXCollections.observableArrayList(new ArrayList<Music>());

	public MusicPane() {
		list = new ListView<Music>();
		init();
	}

	public void init() {
//		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Music>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Music> observable, Music oldValue, Music newValue) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		centerSet();
		bottomSet();
		leftSet();
		rightSet();
		topSet();
		musicImage.setFitHeight(150);
		musicImage.setFitWidth(300);

		list.getItems().addAll(ma.getMusicObservableList());
	}

	public void topSet() {
		MenuBar bar = new MenuBar();
		Menu music = new Menu("Music");
		Menu sort = new Menu("Sort");
		Menu newP = new Menu("New");
		Menu options = new Menu("Options");
		MenuItem sortDate = new MenuItem("Date");
		sortDate.setOnAction(handler);
		sortDate.setUserData("Date");
		MenuItem sortName = new MenuItem("Name");
		sortName.setOnAction(handler);
		sortName.setUserData("Name");
		MenuItem add = new MenuItem("Add");
		add.setOnAction(handler);
		add.setUserData("Add");
		MenuItem del = new MenuItem("Del");
		del.setOnAction(handler);
		del.setUserData("Del");
		MenuItem save = new MenuItem("Save");
		save.setOnAction(handler);
		save.setUserData("Save");
		MenuItem load = new MenuItem("Load");
		load.setOnAction(handler);
		load.setUserData("Load");
		bar.getMenus().addAll(music, sort, newP, options);
		options.getItems().addAll(save, load);
		music.getItems().addAll(add, del);
		sort.getItems().addAll(sortName, sortDate);
		setTop(bar);
	}

	public void centerSet() {
		list.setPrefSize(500, 470);
		FlowPane fp = new FlowPane();
		fp.setPrefSize(500, 20);
		fp.setAlignment(Pos.CENTER);
		cb.setValue(Genre.All);
		cb.getItems().addAll(Genre.values());
		cb.setOnAction(handler);
		fp.getChildren().addAll(cb);
		vb1.getChildren().addAll(fp, list);
		setCenter(vb1);

	}

	public void bottomSet() {

	}

	public void leftSet() {

	}

	public void rightSet() {
		VBox volumeBox = new VBox();
		ImageView img = new ImageView(new Image("skip2.png"));
		ImageView img1 = new ImageView(new Image("playbutton.png"));
		ImageView img2 = new ImageView(new Image("skip1.png"));
		FlowPane fp1 = new FlowPane();
		FlowPane fp2 = new FlowPane();

		vb.prefWidth(500);
		vb.setSpacing(20);
		img.setFitWidth(70);
		img.setFitHeight(70);
		img1.setFitHeight(76);
		img1.setFitWidth(76);
		img2.setFitWidth(70);
		img2.setFitHeight(70);

		volumeSlider.setOrientation(Orientation.VERTICAL);

		back.setUserData("Back");
		play.setUserData("Play");
		play.setOnAction(handler);
		next.setUserData("Next");
		back.setOnAction(handler);
		play.setOnAction(handler);
		next.setOnAction(handler);

		vb.setAlignment(Pos.CENTER);
		fp.setAlignment(Pos.CENTER);

		back.setGraphic(img);
		play.setGraphic(img1);
		next.setGraphic(img2);

		fp1.setAlignment(Pos.CENTER);
		fp1.getChildren().addAll(musicSlider);
		musicSlider.maxWidth(400);
		musicSlider.setMinWidth(100);
		musicSlider.setPrefWidth(350);
		musicSlider.setMaxWidth(400);
		fp2.setHgap(30);
		zahl.setAlignment(Pos.CENTER_RIGHT);
		zahl.setText(" " + String.valueOf(Math.round(getVolume())));
		volumeBox.getChildren().addAll(zahl, volumeSlider);

		fp2.getChildren().addAll(new Label(), musicImage, volumeBox);
		fp.getChildren().addAll(play);
		vb.getChildren().addAll(fp2, musicTitle, fp, fp1);
		setRight(vb);

	}

	public Music getMusicListe() {
		return list.getSelectionModel().getSelectedItem();
	}

	public ObservableList<Music> getMusicListen() {
		return list.getSelectionModel().getSelectedItems();
	}

	public double getVolume() {
		return volume.get();
	}

	public DoubleProperty volumeProperty() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume.set(volume);
	}

	public void Dialog() {
		add = new Dialog<Music>();
		add.setHeight(500);
		add.setWidth(500);
		GridPane gp = new GridPane();
		lImage.setFitHeight(10);
		lImage.setFitWidth(10);
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		gp.add(new Label("Name"), 0, 0);
		gp.add(tfName, 1, 0);
		gp.add(new Label("Datum"), 0, 1);
		gp.add(dp, 1, 1);
		gp.add(new Label("Genre"), 0, 2);
		gp.add(cbGenre, 1, 2);
		gp.add(new Label("Image"), 0, 3);
		gp.add(lImage, 1, 3);
		gp.add(imageChooser, 2, 3);
		gp.add(new Label("File"), 0, 4);
		gp.add(lFile, 1, 4);
		gp.add(fileChooser, 2, 4);
		gp.add(label, 0, 5);
		add.getDialogPane().setContent(gp);
		cbGenre.getItems().addAll(Genre.AfrikanischeMusik, Genre.AsiatischeMusik, Genre.Balladen, Genre.Blues,
				Genre.Country, Genre.Disco, Genre.DrumNBass, Genre.Electro, Genre.FilmandMusical, Genre.Funk,
				Genre.Gospel, Genre.HeavyRock, Genre.HipHop, Genre.Instrumental, Genre.Jazz, Genre.Klassik,
				Genre.Oriental, Genre.Pop, Genre.Punk, Genre.Rap, Genre.Reggae, Genre.Rock, Genre.RocknRoll,
				Genre.Salsa, Genre.Schlager, Genre.Techno, Genre.Volksmusik);
		imageChooser.setUserData("Image");
		imageChooser.setOnAction(handler);
		fileChooser.setUserData("File");
		fileChooser.setOnAction(handler);
		ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
//		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		add.getDialogPane().getButtonTypes().addAll(buttonTypeOk, ButtonType.CANCEL);

		final Button btOk = (Button) add.getDialogPane().lookupButton(buttonTypeOk);

		btOk.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					System.out.println(" moin");
					System.out.println(tfName.getText() + " " + lFile.getText() + " " + dp.getValue() + " "
							+ cbGenre.getValue() + " " + lImage.getImage());
					Music m = new Music(tfName.getText(), new File(lFile.getText()), new Media(lFile.getText()),
							dp.getValue(), cbGenre.getValue(), lImage.getImage());

				} catch (Exception e) {
					label.setText(e.getMessage());
					event.consume();
				}

			}
		});
		add.setResultConverter(new Callback<ButtonType, Music>() {

			@Override
			public Music call(ButtonType param) {
				// TODO Auto-generated method stub
				if (param == buttonTypeOk) {
					ma.add(new Music(tfName.getText(), new File(lFile.getText()),
							new Media(new File(lFile.getText()).toURI().toString()), dp.getValue(), cbGenre.getValue(),
							lImage.getImage()));
//					System.out.println(m);
					System.out.println(ma.getMusicObservableList());
				}
				return null;
			}
		});
		add.showAndWait();

	}

	public void fileChooser() {
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(null);
		if (f != null)
			lFile.setText(String.valueOf(f));
	}

	public void fileImageChooser() {
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(null);
		if (f != null)
			lImage.setImage(new Image("File:\\" + f.getAbsolutePath()));
	}

	public Music getM() {
		return new Music(tfName.getText(), new File(lFile.getText()),
				new Media(new File(lFile.getText()).toURI().toString()), dp.getValue(), cbGenre.getValue(),
				lImage.getImage());
	}

	public void setM(Music m) {
		tfName.setText(m.getName());
		lFile.setText(String.valueOf(m.getF()));
		dp.setValue(m.getDate());
		lImage.setImage(m.getImg());
		cbGenre.setValue(m.getGenre());

	}

}

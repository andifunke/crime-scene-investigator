package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Indizien;
import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control_Indizien extends MainController {

	public static Control_Indizien controlMe;

	@FXML VBox imgBox;
	@FXML VBox imgSuperBox;
	Image img;
	ImageView imgView;
	String imgFileName;
	final String imgFileNameDefault = "kein-bild.gif";
	Button addImg;
	final String imgPath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+System.getProperty("file.separator");

	FileChooser fileChooser;
	File outputfile;
	BufferedImage bi;

	public Control_Indizien() {
		//System.out.println(imgPath);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Indizien.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		controlMe = this;

		table = Indizien.table;

		attr = new String[Indizien.attr.length];
		System.arraycopy(Indizien.attr, 0, attr, 0, attr.length);

		attrName = new String[attr.length];
		attrName[0] = "ID";
		attrName[1] = "angelegt am";
		attrName[2] = attr[2];
		attrName[3] = attr[3];
		attrName[4] = "Polizist ID";
		attrName[5] = "Fall ID";

		listTable = new String[4];
		listTable[0] = "Polizisten";
		listTable[1] = "Faelle";
		listTable[2] = "";
		listTable[3] = "";

		textAttr = new TextField[7];
		textAttr[0] = textAttr0;
		textAttr[1] = textAttr1;
		textAttr[2] = textAttr2;
		textAttr[3] = textAttr3;
		textAttr[4] = textAttr4;
		textAttr[5] = textAttr5;
		textAttr[6] = textAttr6;

		textAttr[0].setPromptText("* Pflichtfeld - wird automatisch generiert");
		textAttr[1].setPromptText("* Pflichtfeld");
		textAttr[4].setPromptText("* Pflichtfeld");
		textAttr[5].setPromptText("* Pflichtfeld");

		filterAttr = new TextField[7];
		filterAttr[0] = filterAttr0;
		filterAttr[1] = filterAttr1;
		filterAttr[2] = filterAttr2;
		filterAttr[3] = filterAttr3;
		filterAttr[4] = filterAttr4;
		filterAttr[5] = filterAttr5;
		filterAttr[6] = filterAttr6;

		for (int i = 0; i < attr.length; i++)
			if (filterAttr[i] != null)
				filterAttr[i].setPromptText("nach '" + attrName[i] + "' filtern");

		labelAttr = new Label[7];
		labelAttr[0] = labelAttr0;
		labelAttr[1] = labelAttr1;
		labelAttr[2] = labelAttr2;
		labelAttr[3] = labelAttr3;
		labelAttr[4] = labelAttr4;
		labelAttr[5] = labelAttr5;
		labelAttr[6] = labelAttr6;

		for (int i = 0; i < attr.length; i++)
			labelAttr[i].setText(attrName[i]);

		columnAttr = new TableColumn[7];
		columnAttr[0] = columnAttr0;
		columnAttr[1] = columnAttr1;
		columnAttr[2] = columnAttr2;
		columnAttr[3] = columnAttr3;
		columnAttr[4] = columnAttr4;
		columnAttr[5] = columnAttr5;
		columnAttr[6] = columnAttr6;

		for (int i = 0; i < attr.length; i++)
			columnAttr[i].setText(attrName[i]);

		labelList = new Label[4];
		labelList[0] = labelList0;
		labelList[1] = labelList1;
		labelList[2] = labelList2;
		labelList[3] = labelList3;

		for (int i = 0; i < listTable.length; i++)
			if (labelList[i] != null)
				labelList[i].setText(listTable[i]);
		labelList[0].setText("Polizist");
		labelList[1].setText("Fall");

		val = new String[attr.length];
		for (int i = 0; i < attr.length; i++)
			val[i] = "";

		imgFileName = imgFileNameDefault;
		loadImageView();

		fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		addImg = new Button("hinzufügen");
		addImg.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				File file = fileChooser.showOpenDialog(CrimeSceneInvestigator.openStage);
				if (file != null) {
					openFile(file);
				}
			}
		});
		imgSuperBox.getChildren().add(addImg);


		olTable = SQLController.selectFromTable(table);
		tableView.setItems(olTable);
		tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				isNew = false;
				for (int i = 0; i < attr.length; i++)
					val[i] = newValue.getValue(i);
				setUpFields();
				setUpLists();
				setUpPicture();
			}
			else {
				reset();
			}
		});
		tableView.getSelectionModel().selectFirst();
	}

	private static void configureFileChooser(
			final FileChooser fileChooser) {
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(
				new File(System.getProperty("user.home"))
		);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
				new FileChooser.ExtensionFilter("All Files", "*.*")
		);
	}

	void loadImageView() {
		imgBox.getChildren().clear();
		imgBox.setPrefHeight(400.0);
		imgView = new ImageView();
		imgView.setFitHeight(400);
		imgView.setFitWidth(400);
		imgView.setPreserveRatio(true);
		imgView.setSmooth(true);
		imgBox.getChildren().add(imgView);
	}

	private void openFile(File inputfile) {
		try {
			bi = ImageIO.read(inputfile);
			textAttr[2].setText(inputfile.getName());
			val[2] = inputfile.getName();
			img = SwingFXUtils.toFXImage(bi, null);
			loadImageView();
			imgView.setImage(img);
			outputfile = new File(imgPath + inputfile.getName());
		} catch (IOException ex) {
			Logger.getLogger(
					Control_Indizien.class.getName()).log(
					Level.SEVERE, null, ex
			);
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	void setUpPicture() {
		loadImageView();
		try {
			if (textAttr[2].getText().equals("")) {
				imgFileName = imgFileNameDefault;
				//System.out.println("kein Bild");
			}
			else {
				val[2] = textAttr[2].getText();
				imgFileName = val[2];
			}
			String path = imgPath + imgFileName;
			//System.out.println(path);
			bi = ImageIO.read(new File(path));
			img = SwingFXUtils.toFXImage(bi, null);
			imgView.setImage(img);
		} catch (Exception e) {
			System.out.println("kann Bild nicht laden");
			try {
				String path = imgPath + imgFileNameDefault;
				bi = ImageIO.read(new File(path));
				img = SwingFXUtils.toFXImage(bi, null);
				imgView.setImage(img);
			} catch (Exception e2) {
				System.out.println("kann Default-Bild nicht laden");
				loadImageView();
				imgBox.setPrefHeight(10.0);
			}
		}
	}

	void setUpLists() {
		String query0 =
				"SELECT Personen.PersonID,Personen.Name,Geschlecht,Nationalitaet,Geburtsdatum,Todesdatum,Dienstgrad\n" +
						" FROM Personen,Polizisten\n" +
						" WHERE Polizisten.PersonID = Personen.PersonID\n" +
						" AND Personen.PersonID = '" + val[4] + "';";
		ol0 = SQLController.selectFromQuery(listTable[0], query0);
		list0.setItems(ol0);

		String query1 =
				"SELECT * FROM Faelle WHERE FallID = '" + val[5] + "';";
		ol1 = SQLController.selectFromQuery(listTable[1], query1);
		list1.setItems(ol1);
	}

	@FXML
	void neu(ActionEvent actionEvent) {
		neuDefault();
		val[1] = formatter.format(new Date());
		textAttr1.setText(val[1]);
	}

	@FXML
	void save(ActionEvent actionEvent) {
		String[] keys = new String[10];
		keys[0] = val[0];
		for (int i = 0; i < attr.length; i++)
			val[i] = textAttr[i].getText();
		try {
			int x = Integer.parseInt(val[0]);
		} catch (NumberFormatException e) {
			System.out.println(attr[0] + " ist kein Integer");
			try {
				int x = Integer.parseInt(keys[0]);
				val[0] = keys[0];
				textAttr0.setText(val[0]);
			} catch (NumberFormatException e2) {
				val[0] = "";
				textAttr0.setText(val[0]);
			}
		}
		if (val[1].equals("")) {
			System.out.println("kein " + attr[1] + " eingetragen");
			return;
		}
		if (val[4].equals("")) {
			System.out.println("kein " + attr[4] + " eingetragen");
			return;
		}
		if (val[5].equals("")) {
			System.out.println("kein " + attr[5] + " eingetragen");
			return;
		}
		try {
			if (outputfile != null) {
				ImageIO.write(bi, "png", outputfile);
			}
		} catch (Exception e) {
			System.out.println("kann Bild nicht speichern");
		}
		if (isNew) {
			Tuplet tuplet = new Indizien(val);
			SQLController.insert(tuplet);
			resetFilter(new ActionEvent());
			tableView.getSelectionModel().clearSelection();
			tableView.getSelectionModel().selectLast();
			int last = tableView.getSelectionModel().getSelectedIndex();
			tableView.scrollTo(last);
			isNew = false;
		}
		else {
			Tuplet tuplet = tableView.getSelectionModel().getSelectedItem();
			int index = tableView.getSelectionModel().getSelectedIndex();
			for (int i = 0; i < attr.length; i++)
				tuplet.setValue(i, val[i]);
			SQLController.update(tuplet, keys);
			filter(new ActionEvent());
			tableView.getSelectionModel().clearAndSelect(index);
		}
		Control_Faelle.controlMe.filter(new ActionEvent());
		Control_Polizisten.controlMe.filter(new ActionEvent());
	}

}
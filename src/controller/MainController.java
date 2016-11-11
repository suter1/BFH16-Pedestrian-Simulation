package controller;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import manager.SimulationManager;
import manager.SpawnManager;
import model.ConfigModel;
import model.Room;
import model.persons.Person;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by suter1 on 28.10.2016.
 */
public class MainController implements Initializable {

	@FXML
	private Room simulationRoom;

	@FXML
	private TextField totalPersons;

	@FXML
	private CheckBox isWeighted;

	@FXML
	private Slider sliderYoung;

	@FXML
	private Slider sliderMidage;

	@FXML
	private Slider sliderOld;

	@FXML
	private Slider sliderHandicap;

	@FXML
	private TextField weightYoung;

	@FXML
	private TextField weightMidage;

	@FXML
	private TextField weightOld;

	@FXML
	private TextField weightHandicap;

	@FXML
	private Button spawnButton;

	@FXML
	private Button startButton;

	@FXML
	private Button resetButton;

	@FXML
	private AnchorPane basePane;

	@FXML
	private Label time;

	@FXML
	private Slider simulationSpeed;

	private SimulationManager simulationManager = SimulationManager.getInstance();
	private SpawnManager spMgr = SpawnManager.getInstance();
	private ConfigModel cfg = ConfigModel.getInstance();

	private List<Slider> sliders = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		basePane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 40);
		basePane.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() - 20);
		startButton.setDisable(true);
		resetButton.setDisable(true);

		weightYoung.textProperty().bindBidirectional(sliderYoung.valueProperty(), NumberFormat.getNumberInstance());
		weightMidage.textProperty().bindBidirectional(sliderMidage.valueProperty(), NumberFormat.getNumberInstance());
		weightOld.textProperty().bindBidirectional(sliderOld.valueProperty(), NumberFormat.getNumberInstance());
		weightHandicap.textProperty().bindBidirectional(sliderHandicap.valueProperty(), NumberFormat.getNumberInstance());

		Slider[] slid = {sliderYoung, sliderMidage, sliderOld, sliderHandicap};
		sliders.addAll(Arrays.asList(slid));
		for (Slider slider : sliders) {
			slider.valueProperty().addListener((observable, oldValue, newValue) -> {
				slider.setValue(Math.round(newValue.doubleValue()));
				calculateSlider();
			});
		}

		/**
		 * Numberlistener from user "javasuns"
		 * on: http://stackoverflow.com/a/37360657
		 */
		ChangeListener<String> forceNumberListener = (observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*"))
				((StringProperty) observable).set(oldValue);
		};

//		Make sure, that only numbers (int) are entered in Textfields.
		weightYoung.textProperty().addListener(forceNumberListener);
		weightMidage.textProperty().addListener(forceNumberListener);
		weightOld.textProperty().addListener(forceNumberListener);
		weightHandicap.textProperty().addListener(forceNumberListener);
		simulationManager.speedProperty.bind(simulationSpeed.valueProperty());

		spawnButton.setOnAction((event) -> {
			/**
			 * Save data form the config window for usage in simulation.
			 */
			cfg.setTotalPersons(getTotalPersons());
			cfg.setWeighted(getIsWeighted());
			cfg.setWeightedYoungPersons(getWeightYoung());
			cfg.setWeightedMidAgePersons(getWeightMidage());
			cfg.setWeightedOldPersons(getWeightOld());
			cfg.setWeightedHandicappedPersons(getWeightHandicap());

			spMgr.createPersons();
			this.simulationRoom.getChildren().addAll(spMgr.getPersons());

			spawnButton.setDisable(true);
			startButton.setDisable(false);
			resetButton.setDisable(false);

			/**
			 * Merge meter from the config to pixel for the view.
			 */
//			cfg.calculateRoomSize();
		});

		startButton.setOnAction((event) -> {
			if (startButton.getText().equals("Start")) {
				startButton.setText("Pause");
				System.out.println("Persons: " + spMgr.getPersons());
				System.out.println("simulationRoom: " + simulationRoom);
				simulationManager.start(time);
			} else {
				startButton.setText("Start");
				simulationManager.getSimulationThread().interrupt();
			}
			startButton.setDisable(false);
		});

		resetButton.setOnAction((event -> {
//	        implement a reset.
			simulationRoom.getChildren().removeIf(item -> (item instanceof Person));
			spawnButton.setDisable(false);
			startButton.setText("Start");
			startButton.setDisable(true);
		}));
	}

	private int sliderSum() {
		int sum = 0;
		for (Slider slider : sliders) {
			sum += Math.floor(slider.getValue() + 0.5f);
		}
		return sum;
	}

	private void calculateSlider() {
		int sum = sliderSum();
		int operator;
		operator = (sum > 100) ? -1 : 1;
		while (sum != 100)
			for (Slider slider : sliders) {
				slider.setValue(slider.getValue() + operator);
				sum += operator;
				if (sum == 100) break;
			}
	}

	public Boolean getIsWeighted() {
		return this.isWeighted.isSelected();
	}

	private TextField doubleToTextfield(Double value) {
		return new TextField("" + value);
	}

	private double textFieldToDouble(TextField text) {
		return new Double(text.getText());
	}

	private int textFieldToInt(TextField text) {
		return new Integer(text.getText());
	}

	private TextField intToTextfield(int value) {
		return new TextField("" + value);
	}

	public double getTotalPersons() {
		return textFieldToDouble(this.totalPersons);
	}

	public void setTotalPersons(double totalPersons) {
		this.totalPersons = doubleToTextfield(totalPersons);
	}

	public double getSliderYoungValue() {
		return this.sliderYoung.getValue();
	}

	public void setSliderYoungValue(double sliderYoung) {
		this.sliderYoung.setValue(sliderYoung);
	}

	public double getSliderMidageValue() {
		return this.sliderMidage.getValue();
	}

	public void setSliderMidageValue(double sliderMidage) {
		this.sliderMidage.setValue(sliderMidage);
	}

	public double getSliderOldValue() {
		return this.sliderOld.getValue();
	}

	public void setSliderOldValue(double sliderOld) {
		this.sliderOld.setValue(sliderOld);
	}

	public double getSliderHandicapValue() {
		return this.sliderHandicap.getValue();
	}

	public void setSliderHandicapValue(double sliderHandicap) {
		this.sliderHandicap.setValue(sliderHandicap);
	}

	public double getWeightYoung() {
		return textFieldToInt(this.weightYoung);
	}

	public void setWeightYoung(int weightYoung) {
		this.weightYoung = intToTextfield(weightYoung);
	}

	public int getWeightMidage() {
		return textFieldToInt(this.weightMidage);
	}

	public void setWeightMidage(int weightMidage) {
		this.weightMidage = intToTextfield(weightMidage);
	}

	public int getWeightOld() {
		return textFieldToInt(this.weightOld);
	}

	public void setWeightOld(int weightOld) {
		this.weightOld = intToTextfield(weightOld);
	}

	public int getWeightHandicap() {
		return textFieldToInt(this.weightHandicap);
	}

	public void setWeightHandicap(int weightHandicap) {
		this.weightHandicap = intToTextfield(weightHandicap);
	}

	public Button getSpawnButton() {
		return this.spawnButton;
	}

}
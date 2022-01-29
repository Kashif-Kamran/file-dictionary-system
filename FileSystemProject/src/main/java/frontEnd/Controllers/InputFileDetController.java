package frontEnd.Controllers;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import BusinessLayer.FileHandle;
import BusinessLayer.FileSystem;
import BusinessLayer.FileSystemFactory;
import BusinessLayer.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InputFileDetController extends SuperPage implements Initializable
{
	FileSystem system;

	public class TableData
	{
		Integer serial;
		String word;
		Integer freq;

		public TableData(Integer serial, String word, Integer freq)
		{
			super();
			this.serial = serial;
			this.word = word;
			this.freq = freq;
		}

		public Integer getSerial()
		{
			return serial;
		}

		public void setSerial(Integer serial)
		{
			this.serial = serial;
		}

		public void setWord(String word)
		{
			this.word = word;
		}

		public void setFreq(Integer freq)
		{
			this.freq = freq;
		}

		public String getWord()
		{
			return word;
		}

		public Integer getFreq()
		{
			return freq;
		}
	}

	@FXML
	private Label error;
	@FXML
	ComboBox inFilesMenu;

	@FXML
	private TableView<TableData> table;

	@FXML
	private TableColumn<TableData, Integer> frequencyCol;

	@FXML
	private TableColumn<TableData, Integer> srNoCol;

	@FXML
	private TableColumn<TableData, String> wordCol;

	@FXML
	private ListView<String> wordsList;

	ObservableList<TableData> tableDataList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		// Mapping Columns to TableData Class entities
		srNoCol.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("serial"));
		wordCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("word"));
		frequencyCol.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("freq"));

		try
		{
			system = FileSystemFactory.getInstance(null, null).getFileSystem();
		} catch (Exception exc)
		{
			System.out.println("Exception");
			error.setText(exc.getMessage());
		}
		InitilizeFileListView();
	}

	public void loadWordList(String str) throws Exception
	{
		wordsList.getItems().setAll(system.getInputFileVector(str));
	}

	public void loadTable(String fileName) throws FileNotFoundException, Exception
	{
		ObservableList<TableData> list = FXCollections.observableArrayList();
		Iterator<Word> itr = system.getFileMatchWords(fileName).iterator();
		Integer i=1;
		while(itr.hasNext())
		{
			Word tempWrd=itr.next();
			list.add(new TableData(i, tempWrd.getWord(), tempWrd.getFreq()));
			i++;
		}
		
		this.table.setItems(list);
	}

	public void loadButton(ActionEvent event)
	{
		try
		{
			String fileName = getFileName();
			loadWordList(fileName);
			loadTable(fileName);

		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}
	}
	public void InitilizeFileListView()
	{
		Iterator<FileHandle> itr = system.getInputFiles().iterator();
		ObservableList<String> observList = FXCollections.observableArrayList();
		while(itr.hasNext())
		{
			observList.add(itr.next().getFileName());
		}
		this.inFilesMenu.setItems(observList);
	}

	public String getFileName() throws Exception
	{
		String value = (String) inFilesMenu.getValue();
		if ( value== null)
		{
			throw new NullPointerException("Please Select File");
		}
		return value;
	}
	@FXML
	void backButton(ActionEvent event)
	{
		this.changeScene(new PagesLocations().manuPage);
	}


}

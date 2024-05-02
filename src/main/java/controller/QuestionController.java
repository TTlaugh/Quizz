package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import business.model.Question;
import business.model.Subject;
import business.services.QuestionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import utils.DisplayDialog_Notification;
import utils.OpenFileExplorer;


public class QuestionController  implements Initializable{
		private Scene scene = null;
		
		private AnchorPane anchor;
		
	    private QuestionManager quesManager=new QuestionManager();
	    
	    private static Question  Question_Current;
	    
	    private ToggleGroup group_Button_Difficulty_QuestionView = new ToggleGroup();
	    
	    private ToggleGroup group_Button_Difficulty_QuestionAdd = new ToggleGroup();
	    
	    private List<Question> question_ListOfSubject;
	    
	    private ObservableList<Question> observableList_Question ;
	    
	    private List<RadioButton> listRadioButton=new ArrayList<RadioButton>();
	    
	    private List<TextArea> listTextArea=new ArrayList<TextArea>();
	    
	    private List<CheckBox> listCheckBox=new ArrayList<CheckBox>();
	    
	    public static Subject subject_Current;
	    
	    private static File file_Current; 

	    @FXML
	    private Button Export_Question_Disable;

	    @FXML
	    private Button Import_Question_Disable;
	    
	    @FXML
	    private AnchorPane anchor_CreateSubject_Question=new AnchorPane();
	    
		@FXML
	    private ComboBox<Subject> choose_Subject_Question=new ComboBox<Subject>();

	    @FXML
	    private Button delete_Subject_Question=new Button();

	    @FXML
	    private Button create_Subject_Question=new Button();

	    @FXML
	    private TextField subject_ID_Question=new TextField();

	    @FXML
	    private TextField subject_Name_Question =new TextField();
	    
	    @FXML
	    private TableView<Question> tableView_Question=new TableView<Question>();
	    
	    @FXML
	    private TableColumn<Question, Integer> Question_Chapter_Column;

	    @FXML
	    private TableColumn<Question,  Integer> Question_Difficulty_Column;
	    
	    @FXML
	    private TableColumn<Question, String>  Question_Content_Column;

	    @FXML
	    private TableColumn<Question, String>  Question_ID_Column;
	    @FXML
	    private Button button_Add_Question=new Button();

	    @FXML
	    private Button button_Delete_Question=new Button();

	    @FXML
	    private Button button_View_Question=new Button();
	    
	    @FXML
	    private TextArea answerA_QuestionView=new TextArea();

	    @FXML
	    private TextArea answerB_QuestionView=new TextArea();

	    @FXML
	    private TextArea answerC_QuestionView=new TextArea();

	    @FXML
	    private TextArea answerD_QuestionView=new TextArea();
	    
	    @FXML
	    private CheckBox checkBoxA_QuestionView=new CheckBox();

	    @FXML
	    private CheckBox checkBoxB_QuestionView=new CheckBox();

	    @FXML
	    private CheckBox checkBoxC_QuestionView=new CheckBox();

	    @FXML
	    private CheckBox checkBoxD_QuestionView=new CheckBox();
	    
	    @FXML
	    private Button buttonEdit_QuestionView=new Button();

	    @FXML
	    private Button buttonSave_QuestionView=new Button();

	    @FXML
	    private TextField chapter_QuestionView=new TextField();

	    @FXML
	    private TextArea content_QuestionView=new TextArea();

	    @FXML
	    private Label questionID_QuestionView=new Label();

	    @FXML
	    private RadioButton radioEasy_QuestionView=new RadioButton();

	    @FXML
	    private RadioButton radioHard_QuestionView=new RadioButton();

	    @FXML
	    private RadioButton radioMedium_QuestionView=new RadioButton();
	    
	    @FXML
	    private TextArea answerA_QuestionAdd=new TextArea();

	    @FXML
	    private TextArea answerB_QuestionAdd=new TextArea();

	    @FXML
	    private TextArea answerC_QuestionAdd=new TextArea();

	    @FXML
	    private TextArea answerD_QuestionAdd=new TextArea();

	    @FXML
	    private TextField chapter_QuestionAdd=new TextField();

	    @FXML
	    private CheckBox checkBoxA_QuestionAdd=new CheckBox();

	    @FXML
	    private CheckBox checkBoxB_QuestionAdd =new CheckBox();

	    @FXML
	    private CheckBox checkBoxC_QuestionAdd = new CheckBox();

	    @FXML
	    private CheckBox checkBoxD_QuestionAdd = new CheckBox();

	    @FXML
	    private TextArea content_QuestionAdd =new TextArea();

	    @FXML
	    private RadioButton radioEasy_QuestionAdd =new RadioButton();

	    @FXML
	    private RadioButton radioHard_QuestionAdd =new RadioButton();

	    @FXML
	    private RadioButton radioMedium_QuestionAdd =new RadioButton();

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			buttonRadioGroup_Add();
			
			List<Subject> subjects=quesManager.getSubjects(new LoginController().teacher_Current);
			
			for(Subject subj : subjects) {
				choose_Subject_Question.getItems().add(subj);
			}
			
			 choose_Subject_Question.setConverter(new StringConverter<Subject>() {
		            @Override
		            public String toString(Subject subj) {
		                return subj == null  ? "" : "ID_Subject : " + subj.getSubjectID() +" -- "+ subj.getSubjectName();
		            }
	
		            @Override
		            public Subject fromString(String s) {
		                return null;
		            }
				});
			 

		 button_Add_Question.setVisible(false);
		 setVisibleButton_View_Del_Question(false);
		 
		 choose_Subject_Question.setOnAction(envent -> { 
			 setDisableEX_IM(false);
			 delete_Subject_Question.setDisable(false);
			 question_ListOfSubject=quesManager.getQuestionsForSubject(choose_Subject_Question.getValue());
			 //Save subject current
			 new QuestionController().subject_Current=choose_Subject_Question.getValue();
			 
			 tableView_Question.setItems(loadQuestion_tableView(question_ListOfSubject));
			 
			 button_Add_Question.setVisible(true);
		   });
		 
		   try {
		    	SelectionModel<Question> selectionModel = tableView_Question.getSelectionModel();
		    	selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    		if (newValue != null) {
		    			new QuestionController().Question_Current=newValue;
		    			setVisibleButton_View_Del_Question(true);
		    		}
		    	});	    	
		    }catch (Exception e) {
			    e.printStackTrace();
		    }
	}
	private void buttonRadioGroup_View() {
		radioEasy_QuestionView.setToggleGroup(group_Button_Difficulty_QuestionView);
		radioHard_QuestionView.setToggleGroup(group_Button_Difficulty_QuestionView);
		radioMedium_QuestionView.setToggleGroup(group_Button_Difficulty_QuestionView);
	}
	private void buttonRadioGroup_Add() {
		radioEasy_QuestionAdd.setToggleGroup(group_Button_Difficulty_QuestionAdd);
		radioHard_QuestionAdd.setToggleGroup(group_Button_Difficulty_QuestionAdd);
		radioMedium_QuestionAdd.setToggleGroup(group_Button_Difficulty_QuestionAdd);
	}
	private void setVisibleButton_View_Del_Question(boolean b) {
		button_Delete_Question.setVisible(b);
		button_View_Question.setVisible(b);
	}
	private ObservableList loadQuestion_tableView(List<Question> list) {
		observableList_Question = FXCollections.observableArrayList(list);	    

		Question_ID_Column.setCellValueFactory(new PropertyValueFactory<Question,String>("questionID")); 
		Question_Chapter_Column.setCellValueFactory(new PropertyValueFactory<Question,Integer>("chapter"));
		Question_Difficulty_Column.setCellValueFactory(new PropertyValueFactory<Question,Integer>("difficulty"));
		Question_Content_Column.setCellValueFactory(new PropertyValueFactory<Question,String>("content"));
	    
	    return observableList_Question;
	}
	
	public void Question_Tranfer_QuestionView_Quizz(ActionEvent event) throws IOException {
		if(Question_Current != null){
			AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question_view.fxml")).load();
			setAnchor(insidePane);
			scene = (Scene) ((Node) event.getSource()).getScene();
			anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
			anchor.getChildren().clear();
			anchor.getChildren().add(insidePane);
		}
	}
	
	private void loadQuestionDetail(Question q) {
		// display questionID
		questionID_QuestionView.setText(q.getQuestionID());
		// display chapter
		chapter_QuestionView.setText(String.valueOf(q.getChapter()));
		// add radio button to list
		listRadioButton.add(radioEasy_QuestionView);
		listRadioButton.add(radioMedium_QuestionView);
		listRadioButton.add(radioHard_QuestionView);

		// display difficulty
		for(int i =0; i<listRadioButton.size();i++) {
			if(i+1==q.getDifficulty()) {
				listRadioButton.get(i).setSelected(true);
				break;
			}
		}
		// displayContent 
		content_QuestionView.setText(q.getContent());
		// add TextArea to list
		listTextArea.add(answerA_QuestionView);
		listTextArea.add(answerB_QuestionView);
		listTextArea.add(answerC_QuestionView);
		listTextArea.add(answerD_QuestionView);
		
		// display answer
		for(int i=0;i<q.getAnswers().size();i++) {
			listTextArea.get(i).setText(q.getAnswers().get(i));
		}	
		
		
		// add checkBox to list
		listCheckBox.add(checkBoxA_QuestionView);
		listCheckBox.add(checkBoxB_QuestionView);
		listCheckBox.add(checkBoxC_QuestionView);
		listCheckBox.add(checkBoxD_QuestionView);
		
		//display correct Answer
		for(Integer i:q.getCorrectAnswers()) {
			for(int j=0;j<listCheckBox.size();j++) {
				if(i==j+1) {
					listCheckBox.get(j).setSelected(true);
					break;
				}
			}
		}
	}
	
	public void Question_Tranfer_QuestionAdd_Quizz(ActionEvent event) throws IOException {
		
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question_add.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);

	}

	public void Back_Question(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}
	private void setAnchor(AnchorPane insidePane) {
		AnchorPane.setTopAnchor(insidePane, 0.0);
		AnchorPane.setBottomAnchor(insidePane, 0.0);
		AnchorPane.setRightAnchor(insidePane, 0.0);
		AnchorPane.setLeftAnchor(insidePane, 0.0);
	}

    @FXML
    void Delete_QuestionFromQuestion(ActionEvent event) {

    	 if(!quesManager.deleteQuestion(Question_Current.getQuestionID())) {
    			tableView_Question.getSelectionModel().clearSelection();
    		  DisplayDialog_Notification.Dialog_Error("Notification failed", "\r\n"
    		  		+ "Questions cannot be deleted", "Unsuccessfully");
    	 }
    	 else{
    		 DisplayDialog_Notification.Dialog_Error("Notification successfully", "\r\n"
     		  		+ "Question has been deleted", "Successfully");
    		 tableView_Question.getItems().remove(Question_Current);
    	 }
    }

    @FXML
    public void buttonEdit_QuestionView(ActionEvent event) {
    	boolean edit_Status= false;
    	loadQuestionDetail(Question_Current);
    	setDisable_display_QuestionDetail(edit_Status);
    	buttonRadioGroup_View();
    }
    
    private void setDisable_display_QuestionDetail(boolean b) {
    	buttonSave_QuestionView.setVisible(true);
    	chapter_QuestionView.setDisable(b);
    	radioEasy_QuestionView.setDisable(b);
    	radioMedium_QuestionView.setDisable(b);
    	radioHard_QuestionView.setDisable(b);
    	content_QuestionView.setDisable(b);
    	answerA_QuestionView.setDisable(b);
    	answerB_QuestionView.setDisable(b);
    	answerC_QuestionView.setDisable(b);
    	answerD_QuestionView.setDisable(b);
    	checkBoxA_QuestionView.setDisable(b);
    	checkBoxB_QuestionView.setDisable(b);
    	checkBoxC_QuestionView.setDisable(b);
    	checkBoxD_QuestionView.setDisable(b);
    }
    

    @FXML
    private void buttonSave_DataChange_QuestionView(ActionEvent event){
    	try {
    		if(!quesManager.editQuestion(new Question(
    				questionID_QuestionView.getText(),
    				subject_Current,
    				Integer.parseInt(chapter_QuestionView.getText()),
    				radioSelect_Question(listRadioButton),
    				content_QuestionView.getText(),
    				listAnswer_Question(listTextArea),
    				listCorrectAnswer_Question(listCheckBox)))) {
    			DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!","Question editing failed", "Unsuccessful");
    		}
    		else {
	    		DisplayDialog_Notification.Dialog_Infomation("Successful notification!","Question editing successfully", "Successful");
    		}
    	}
    	catch(NumberFormatException e) {
    		DisplayDialog_Notification.Dialog_Error("Error Notification", "\r\n"
    				+ "Chapter can only enter numbers", "Error");
    	}
    	catch(Exception e) {
			DisplayDialog_Notification.Dialog_Error("Error Notification", "\r\n"
					+ "Unsucessful", "Error");
		}
    }

    private int radioSelect_Question(List<RadioButton> listRadioButton) {
    	for(int i =0 ; i<listRadioButton.size();i++) {
			if(listRadioButton.get(i).isSelected()) {
				return i+1;
			}
		}
    	return 1;
    }
    
    private List<String> listAnswer_Question(List<TextArea> listTextArea) {
    	List<String> listAnswer = new ArrayList<String>();
    	for(TextArea i:listTextArea) {
    		listAnswer.add(i.getText());
    	}
	    return listAnswer;
    }
    
   private List<Integer> listCorrectAnswer_Question(List<CheckBox> listCheckBox) {
	  List listCorrectAnswer=new ArrayList<Integer>();
	  for(int i=0 ;i<listCheckBox.size();i++) {
		  if(listCheckBox.get(i).isSelected()) {
			  listCorrectAnswer.add(i+1);
		  }
	  }
	   return listCorrectAnswer;
   } 
   

   @FXML
   void save_DataAdd_QuestionAdd(ActionEvent event) {
	   List<RadioButton> listRadioButton = new ArrayList<RadioButton>();
	   listRadioButton.add(radioEasy_QuestionAdd);
	   listRadioButton.add(radioMedium_QuestionAdd);
	   listRadioButton.add(radioHard_QuestionAdd);
	   
	   List<TextArea> listTextArea = new ArrayList<TextArea>();
	   listTextArea.add(answerA_QuestionAdd);
	   listTextArea.add(answerB_QuestionAdd);
	   listTextArea.add(answerC_QuestionAdd);
	   listTextArea.add(answerD_QuestionAdd);
	   
	   List<CheckBox> listCheckBox = new ArrayList<CheckBox>();
	   listCheckBox.add(checkBoxA_QuestionAdd);
	   listCheckBox.add(checkBoxB_QuestionAdd);
	   listCheckBox.add(checkBoxC_QuestionAdd);
	   listCheckBox.add(checkBoxD_QuestionAdd);
	   
	   try {
		   if(!quesManager.addQuestion(
				   new Question(null,
						   subject_Current,
						   Integer.parseInt(chapter_QuestionAdd.getText()),
						   radioSelect_Question(listRadioButton),
						   content_QuestionAdd.getText(),
						   listAnswer_Question(listTextArea),
						   listCorrectAnswer_Question(listCheckBox)
						   ))) {
				DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!","Question add failed", "Unsuccessful");
		   }		
		   else {
			   DisplayDialog_Notification.Dialog_Infomation("Successful notification!","Question editing successfully", "Successful");
		   }
	   }
	   catch(NumberFormatException e) {
		   DisplayDialog_Notification.Dialog_Error("Error Notification", "\r\n"
   				+ "Chapter can only enter numbers", "Error");
	   }
	   catch(Exception e) {
			DisplayDialog_Notification.Dialog_Error("Error Notification", "\r\n"
					+ "Unsucessful", "Error");
		}
   }
    
   @FXML
   void button_Create_Subject_Question(ActionEvent event) {

		  try {
			if(!quesManager.addSubject(new Subject(
					  subject_ID_Question.getText(),
					  new LoginController().teacher_Current,
					  subject_Name_Question.getText()))) {
				DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!","The subject added failed!", "Unsuccessful");
			}
			else {
				DisplayDialog_Notification.Dialog_Infomation("Successful notification!","The subject added successfully!", "Successful");
				choose_Subject_Question.getItems().add(new Subject(
						  subject_ID_Question.getText(),
						  new LoginController().teacher_Current,
						  subject_Name_Question.getText()));
			}
		} catch (SQLException e) {
			DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!","The subject added failed because it already exists!", "Unsuccessful");
			
		}
	  
   }
   
   @FXML
   void button_Delete_Subject_Question(ActionEvent event) {
		if(!quesManager.deleteSubject(subject_Current.getSubjectID())) {
			DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!","The subject added failed!", "Unsuccessful");
		}
		else {
			DisplayDialog_Notification.Dialog_Infomation("Successful notification!","The subject added successfully!", "Successful");
			choose_Subject_Question.getItems().remove(subject_Current);			
		}
   }
   @FXML
   private boolean Import_Question(ActionEvent event)  {
   	file_Current = OpenFileExplorer.Open(event);
   	if(file_Current!=null) {
   		String check_xlsx = file_Current.getPath().substring(file_Current.getPath().lastIndexOf(".")+1);
   		try {
   			
				if( (check_xlsx.equalsIgnoreCase("xlsx") || check_xlsx.equalsIgnoreCase("xls") ) && quesManager.importQuestions(subject_Current, file_Current.getPath())) {	
					tableView_Question.getItems().clear();
			    	
			    	DisplayDialog_Notification.Dialog_Infomation("Successful notification", "Import Successful", "Successful");
				}
				else {
					file_Current = null;	
					DisplayDialog_Notification.Dialog_Error("Notification Error", "You have not selected a file exel", "Error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	} 	
   	
   	question_ListOfSubject = quesManager.getQuestionsForSubject(subject_Current);
   	
   	tableView_Question.setItems(loadQuestion_tableView(question_ListOfSubject));
   	
   	return file_Current==null ? false : true ;
   }
   
   @FXML
  private void Export_Question(ActionEvent event) {
   	String fileNameExel=String.valueOf(subject_Current.getSubjectName())+"_Exel.xlsx";
   	if(!quesManager.exportQuestions(subject_Current, fileNameExel)) {
			DisplayDialog_Notification.Dialog_Infomation("Unsuccessful notification", "Creat file exel failed", "Error");
		}
   	else {
   		if(DisplayDialog_Notification.Dialog_Comfrim("Successful notification",
   				"Creat file exel successfully and open file?", 
   				"Successful").getResult()==ButtonType.YES) {
   			 OpenFileExeml_Export(new File(fileNameExel));
   		}		
   	}
   }    
   
   private void OpenFileExeml_Export(File fileOpen) {
   	if(fileOpen!=null) {
   		try {
   			Desktop.getDesktop().open(fileOpen);
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}    		
   	}
   	else {
   		DisplayDialog_Notification.Dialog_Error("Notification Error", "\r\n"
   				+ "You have not imported the file", "Error");
   	}
   }
   
   private void setDisableEX_IM(boolean b) {
	   Import_Question_Disable.setDisable(b);
	   Export_Question_Disable.setDisable(b);
   }
}

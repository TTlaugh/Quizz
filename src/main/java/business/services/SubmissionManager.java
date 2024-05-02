package business.services;

import java.sql.SQLException;
import java.util.List;

import business.model.SelectedQuestion;
import business.model.Submission;
import business.model.Teacher;
import data.SubmissionAccess;
import utils.SQLUtils;

public class SubmissionManager {
	
	public List<Submission> getSubmissions(Teacher teacher) {
		try {
			return new SubmissionAccess().getAll(teacher.getTeacherID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean deleteSubmission(String submissionID) {
		try {
			return new SubmissionAccess().delete(submissionID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public List<SelectedQuestion> getSelectedQuestions(Submission submission) {
		try {
			return new SubmissionAccess().getSelectedQuestions(submission);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

}
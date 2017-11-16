package com.dpu.bean;

public class QuesAnsBean {

	private int questionId;
	private String question;
	private String answer;
	@SuppressWarnings("unused")
	private int status;
	@SuppressWarnings("unused")
	private int categoryId;

	public QuesAnsBean(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}

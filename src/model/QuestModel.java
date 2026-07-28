package model;

public class QuestModel {
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;
	public QuestModel(int id, String title, String description, boolean isCompleted) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.isCompleted = isCompleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
package model;

//Denis
public class NPCModel {

	// attributes
	private int id;
	private String name;
	private String dialog;
	private int positionX;
	private int positionY;
	
	// constructor
	public NPCModel(int id, String name, String dialog, int positionX, int positionY, String spritePath) {
		super();
		this.id = id;
		this.name = name;
		this.dialog = dialog;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public NPCModel(int id2, String name2, String dialog2, int positionX2, int positionY2, int i) {
		// TODO Auto-generated constructor stub
	}

	//getter & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	
}

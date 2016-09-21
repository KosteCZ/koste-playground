package cz.koscak.jan.mytasks2;

import java.sql.Timestamp;

public class Note {
	
	public static final String TABLE_NAME				= "note";
	public static final String ATTR_ID 					= "id";
	public static final String ATTR_CREATION_DATE 		= "creation_date";
	public static final String ATTR_CREATION_DATE_J		= "creationDate";
	public static final String ATTR_CREATOR 			= "creator";
	public static final String ATTR_TEXT 				= "text";
	public static final String ATTR_STATE 				= "state";
	public static final String ATTR_TAG 				= "tag";
	public static final String ATTR_DEVICE 				= "device";
	public static final String ATTR_MODIFICATION_DATE 	= "modification_date";
	public static final String ATTR_MODIFICATION_DATE_J	= "modificationDate";
	public static final String ATTR_MODIFIER 			= "modifier";

	private Integer id;
	private Timestamp creationDate;
	private String creator;
	private String text;
	private int state;
	private String tag;
	private String device;
	private Timestamp modificationDate;
	private String modifier;

	public Note(Timestamp creationDate, String creator, String text, int state, String tag, String device) {
		this(null, creationDate, creator, text, state, tag, device);
	}

	public Note(Integer id, Timestamp creationDate, String creator, String text, int state, String tag, String device) {
		this(id, creationDate, creator, text, state, tag, device, creationDate, creator);
	}

	public Note(Integer id, Timestamp creationDate, String creator, String text, int state, String tag, String device, Timestamp modificationDate, String modifier) {
		this.id = id;
		this.creationDate = creationDate;
		this.creator = creator;
		this.text = text;
		this.state = state;
		this.tag = tag;
		this.device = device;
		this.modificationDate = modificationDate;
		this.modifier = modifier;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Timestamp getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public boolean isSelected() {
		return this.state > 0;
	}

	public void setSelected(boolean newState) {
		if (newState) {
			this.state = 1;
		} else {
			this.state = 0;
		}
	}

	@Override
	public String toString() {
		return "Note [id=\"" + id + "\" | creationDate=\"" + creationDate + "\" | creator=\"" + creator + "\" | text=\"" + text
				+ "\" | state=\"" + state + "\" | tag=\"" + tag + "\" | device=\"" + device + "\" | modificationDate=\"" + modificationDate + "\" | modifier=\"" + modifier + "\"" + "]";
	}

}
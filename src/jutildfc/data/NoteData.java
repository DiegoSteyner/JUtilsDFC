package jutildfc.data;

import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfTime;

public class NoteData {
	
	private IDfId noteId = null;
	private IDfTime noteCreationDate = null;
	private int noteFlag = -1;
	private String noteText = null;
	private String noteWriter = null;
	
	public IDfId getNoteId() {
		return noteId;
	}

	public void setNoteId(IDfId noteId) {
		this.noteId = noteId;
	}

	public IDfTime getNoteCreationDate() {
		return noteCreationDate;
	}
	
	public void setNoteCreationDate(IDfTime noteCreationDate) {
		this.noteCreationDate = noteCreationDate;
	}
	
	public int getNoteFlag() {
		return noteFlag;
	}
	
	public void setNoteFlag(int noteFlag) {
		this.noteFlag = noteFlag;
	}
	
	public String getNoteText() {
		return noteText;
	}
	
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	
	public String getNoteWriter() {
		return noteWriter;
	}
	
	public void setNoteWriter(String noteWriter) {
		this.noteWriter = noteWriter;
	}
}
package cz.koscak.jan.mytasks2;

import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jkoscak on 12. 9. 2016.
 */
public class NoteParser {

    final String TEXT_NOTE_START    = "Note [";
    final String TEXT_NOTE_END      = "]";
    final String TEXT_PARAM_START    = "=\"";
    final String TEXT_PARAM_END     = "\" | ";

    List<Note> parseAnswer(String text) {

        List<Note> notes = new ArrayList<Note>();

        while (text.contains(TEXT_NOTE_START)) {

            int indexOfStartBracket = text.indexOf(TEXT_NOTE_START);
            int indexOfEndBracket = text.indexOf(TEXT_NOTE_END);

            String noteText = text.substring(indexOfStartBracket + TEXT_NOTE_START.length(), indexOfEndBracket);

            Note note = parseOneNote(noteText);

            notes.add(note);

            text = text.substring(indexOfEndBracket + 1);

        }

        return notes;
    }

    private Note parseOneNote(String text){

        String valueId = parseOneParameter(text, Note.ATTR_ID);
        String valueCreationDate = parseOneParameter(text, Note.ATTR_CREATION_DATE_J);
        String valueCreator = parseOneParameter(text, Note.ATTR_CREATOR);
        String valueText = parseOneParameter(text, Note.ATTR_TEXT);
        String valueState = parseOneParameter(text, Note.ATTR_STATE);
        String valueTag = parseOneParameter(text, Note.ATTR_TAG);
        String valueDevice = parseOneParameter(text, Note.ATTR_DEVICE);

        String noteString = valueId + "\n" + valueCreationDate + "\n" + valueCreator
                + "\n" + valueText + "\n" + valueState + "\n" + valueTag + "\n"
                + valueDevice;

        Log.i("parseOneNote", noteString);

        Note note = new Note(Integer.valueOf(valueId), convertStringToTimestamp(valueCreationDate), valueCreator, valueText, Integer.valueOf(valueState), valueTag, valueDevice);

        //String note = text;

        return note;

    }

    private String parseOneParameter(String text, String param) {

        param = param + TEXT_PARAM_START;

        //Log.i("parseOneParameter", "INPUT " + param + ": " + "\n" + text + "\n");

        if (text.contains(param)) {
            int indexOfParamStart = text.indexOf(param) + param.length();
            //Log.i("parseOneParameter", "INDEX of " + param + " START: " + indexOfParamStart);
            String paramText = text.substring(indexOfParamStart);
            int indexOfParamEnd = -1;
            if (paramText.contains(TEXT_PARAM_END)) {
                indexOfParamEnd = paramText.indexOf(TEXT_PARAM_END);
            } else {
                indexOfParamEnd = paramText.length() - 1; // deletion of last: "
            }
            //Log.i("parseOneParameter", "INDEX of " + param + " END: " + indexOfParamEnd);
            String paramValue = paramText.substring(0, indexOfParamEnd);
            //Log.i("parseOneParameter", "VALUE of " + param + ": " + paramValue);
            if ("null".equals(paramValue)) {
                paramValue = null;
            }
            return paramValue;
        } else {
            return null;
        }

    }

    public static Timestamp convertStringToTimestamp(String str_date) {
        try {
            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
            Date date = (Date) formatter.parse(str_date);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (java.text.ParseException e) {
            Log.w("convertStrToTimestamp", "Exception :" + e);
            return null;
        }
    }

}

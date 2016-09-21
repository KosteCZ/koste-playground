package cz.koscak.jan.mytasks2;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by jkoscak on 9. 9. 2016.
 */
public class SOAPUpdateStateOfNote extends AsyncTask<String, Void, String> {

    public static String NAMESPACE = "http://ws.jan.koscak.cz/";
    public static String methodName = "updateNoteState";
    public static String URL = "http://jws-xkoscak.rhcloud.com/greeting";

    /**
     * Update note (determined by id) state.
     *
     * @param args id, state
     * @return information about processing
     */
    protected String doInBackground(String... args) {

        //Initialize soap request + add parameters
        SoapObject request = new SoapObject(NAMESPACE, methodName);

        //Use this to add parameters
        int id = Integer.valueOf(args[0]);
        int state = Integer.valueOf(args[1]);

        UserAccount userAccount = UserAccount.newInstance();

        String modifier = userAccount.getUsername();

        String device = UserAndDeviceInfo.getDeviceNameAndAndroidVersion();

        Log.i("WS-UPDATE(state)", "id=" + id + ", state=" + state + ", device='" + device + "'" + ", modifier='" + modifier + "'");

        request.addProperty(Note.ATTR_ID, id);
        request.addProperty(Note.ATTR_STATE, state);
        request.addProperty(Note.ATTR_DEVICE, device);
        request.addProperty(Note.ATTR_MODIFIER, modifier);

        //Declare the version of the SOAP request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        System.setProperty("http.keepAlive", "false");

        //Needed to make the internet call
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

        //Passing Parameters in request
        //PropertyInfo stringArrayPropertyInfo = new PropertyInfo();
        //stringArrayPropertyInfo.setName("attributes");
        //stringArrayPropertyInfo.setValue(param);
        //stringArrayPropertyInfo.setType(param.getClass());
        //request.addProperty(stringArrayPropertyInfo);

        try {
            //this is the actual part that will call the webservice
            androidHttpTransport.call(NAMESPACE, envelope);

            //saving data received from response
            String response = envelope.getResponse().toString();
            return response;
        }  catch (Exception e) {
            Log.e("MainActivity 1", "" + e.toString());
            Log.e("MainActivity 2", "" + e.getMessage());
            return ":-( " + e;
        }

    }

}
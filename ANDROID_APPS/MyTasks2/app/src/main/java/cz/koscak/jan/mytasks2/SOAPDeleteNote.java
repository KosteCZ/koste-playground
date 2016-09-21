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
public class SOAPDeleteNote extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... args) {

        String NAMESPACE = "http://ws.jan.koscak.cz/";
        String methodName = "deleteNote";
        String URL = "http://jws-xkoscak.rhcloud.com/greeting"; //?wsdl";

        //Initialize soap request + add parameters
        SoapObject request = new SoapObject(NAMESPACE, methodName);

        //Use this to add parameters
        int id = Integer.valueOf(args[0]);

        UserAccount userAccount = UserAccount.newInstance();
        String modifier = userAccount.getUsername();
        //String device = UserAndDeviceInfo.getDeviceNameAndAndroidVersion();

        request.addProperty(Note.ATTR_ID, id);
        request.addProperty(Note.ATTR_MODIFIER, modifier);

        //Declare the version of the SOAP request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        System.setProperty("http.keepAlive", "false");

        //Needed to make the internet call
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

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

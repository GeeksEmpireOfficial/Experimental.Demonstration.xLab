package net.geekstest.parserdata.MoneyMultiplier;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.geekstest.parserdata.JSON.JSONObject;
import net.geekstest.parserdata.JSON.JSONParser;

public class PayPalPaymentCreate {
	public static void main(String[] args) {
		try {
			//curl -v -X POST https://api.sandbox.paypal.com/v1/payments/payment/PAY-34629814WL663112AKEE3AWQ/execute 
			//-H "Content-Type: application/json" 
			//-H "Authorization: Bearer A21AAG74SUMvCKhsjFu_2KTrTG1YAXVX1Xakl5gw7jurwqZz64YyJaKRKN5mKBMLKIhIOzPW5sZXE_RSCCRGyEhuLB668fRdg" 
			//-d '{"payer_id": "RRCYJUTFJGZTA"}'
			
			JSONParser jsonParser = new JSONParser();
			String PayerID = "UZ3EJS4VDFVPC";
			JSONObject jsonArray = (JSONObject) jsonParser.parse("{\"payer_id\": \"" + PayerID + "\"}");
			
			String paymentId = "PAYID-LWIYRFA5G633575FM046562J";
			String url = "https://api.sandbox.paypal.com/v1/payments/payment/" + paymentId + "/execute";
			URL urlObject = new URL(url);
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlObject.openConnection();
			httpsURLConnection.setRequestMethod("POST");
			httpsURLConnection.setRequestProperty("accept", "application/json");
			httpsURLConnection.setRequestProperty("authorization", "Bearer A21AAG74SUMvCKhsjFu_2KTrTG1YAXVX1Xakl5gw7jurwqZz64YyJaKRKN5mKBMLKIhIOzPW5sZXE_RSCCRGyEhuLB668fRdg");
			httpsURLConnection.setRequestProperty("content-type", "application/json");
			
			// Send request
			httpsURLConnection.setDoOutput(true);
			DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
			dataOutputStream.writeBytes(jsonArray.toString());
			dataOutputStream.flush();
			dataOutputStream.close();

			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(httpsURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = bufferReader.readLine()) != null) {
				response.append(inputLine);
			}
			bufferReader.close();

			// Print the response
			System.out.println(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

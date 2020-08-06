package ar.edu.unju.fi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ar.edu.unju.fi.dominio.Venta;

public class ValorDolarStub extends ValorDolar {

	@Override
	public  Double convertirAPesos(Double precio) {

		return 60d * precio;
	}


}

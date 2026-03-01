package com.walvar.securitylogmonitor.parser;

public class AnalizadorLogs {

	private String logs;
	
	public AnalizadorLogs(String logs) {
		// formato de log:   Log_IP:192.168.1.25_X:12.50_Y:4.20_OK
		if (logs != null && logs.startsWith("Log_IP") && (logs.endsWith("_OK"))) {
			this.logs = logs;
		} else {
			var mensaje = "El texto no cumple con la estructura para lectura, Texto: %s".formatted(logs);
			throw new IllegalArgumentException(mensaje);
		}
	}
	
	public static AnalizadorLogs procesarLog(String logs) {
		return new AnalizadorLogs(logs);
	}
	
	private String extraerIP() {
		var IP = this.logs.substring(this.logs.indexOf("Log_IP:") + 7, this.logs.indexOf("_X"));
		return IP;
	}
	
	private double extraerX() {
		var X = this.logs.substring(this.logs.indexOf("_X") + 3, this.logs.indexOf("_Y"));
		return Double.parseDouble(X);
	}
	
	private double extraerY() {
		var Y = this.logs.substring(this.logs.indexOf("_Y") + 3, this.logs.indexOf("_OK"));
		return Double.parseDouble(Y);
	}
	
	public String getIP() {return extraerIP(); }
	public double getX() {return extraerX(); }
	public double getY() {return extraerY(); }
}

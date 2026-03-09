package com.walvar.securitylogmonitor.parser;

public class AnalizadorLogs {

	private String logs;
	
	public AnalizadorLogs(String logs) {
		// formato de log:   [LOGIN_OK] IP:192.168.1.33_PORT:8080_USER:dev_team
		if (logs.startsWith("[LOGIN_OK]")) {
			this.logs = logs;
		} else {
			var mensaje = "registro sospecho: %s".formatted(logs);
			throw new IllegalArgumentException(mensaje);
		}
	}
	
	public static AnalizadorLogs procesarLog(String logs) {
		return new AnalizadorLogs(logs);
	}
	
	private String getData(int start, int stop) {
		var DATA = this.logs.substring(start, stop);
		return DATA;
	}
	
	private String getData(int start) {
		var DATA = this.logs.substring(start);
		return DATA;
	}
	
	public String getIP() { return getData((this.logs.indexOf("IP:") + 3), (this.logs.indexOf("_PORT:"))); }
	
	public String getPort() { return getData((this.logs.indexOf("_PORT:") + 6), (this.logs.indexOf("_USER:"))); }
	
	public String getUser() { return getData(this.logs.indexOf("_USER:") + 6); }
	
}

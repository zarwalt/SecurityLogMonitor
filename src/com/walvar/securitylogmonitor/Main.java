package com.walvar.securitylogmonitor;

import com.walvar.securitylogmonitor.parser.AnalizadorLogs;

public class Main {

	public static void main(String[] args) {
		
		var log = AnalizadorLogs.procesarLog("Log_IP:192.168.1.25_X:12.50_Y:4.20_OK");
		System.out.println("IP en texto: %s, Valor X:%.2f  y valor Y:%.2f  ".formatted(log.getIP(), log.getX(), log.getY()));
	}

}

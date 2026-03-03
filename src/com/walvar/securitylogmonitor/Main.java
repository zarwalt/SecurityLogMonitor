package com.walvar.securitylogmonitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.walvar.securitylogmonitor.parser.AnalizadorLogs;

public class Main {

	public static void main(String[] args) {
		
//		var log = AnalizadorLogs.procesarLog("Log_IP:192.168.1.25_X:12.50_Y:4.20_OK");
//		System.out.println("IP en texto: %s, Valor X:%.2f  y valor Y:%.2f  ".formatted(log.getIP(), log.getX(), log.getY()));
		
		Path fileName = Paths.get("server_logs.csv");
		System.out.println("Path correcto, archivo: %s".formatted(fileName.getFileName()));
		System.out.println("------------------------------------------------------------------------------------");
		try {
			List<String> registroLogs = Files.readAllLines(fileName);
			for (String linea : registroLogs) {
				try {
					var log = AnalizadorLogs.procesarLog(linea);
					System.out.println("Direccion IP: %s | Coordenadas ->  X: %.2f Y: %.2f".formatted(log.getIP(), log.getX(), log.getY()));
				} catch (IllegalArgumentException e) {
					System.out.println("Linea ignorada, no cumple la condicion formato: %s".formatted(e.getMessage()));
				}
			}
		} catch (IOException exception) {
			System.out.println("NO se puede acceder al archivo");
			System.out.println("Detalle: %s".formatted(exception.getMessage()));
		}
		
		
	}

}
	
package com.walvar.securitylogmonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.walvar.securitylogmonitor.parser.AnalizadorLogs;

public class Main {

	public static void main(String[] args) {
		
		// formato de log:   [LOGIN_OK] IP:192.168.1.33_PORT:8080_USER:dev_team
		
		Path reader = Paths.get("server_logs2.csv");
		Path writer_ok = Paths.get("data_log_OK");
		Path writer_warning = Paths.get("amenazas.log");
		
		System.out.println("Procesando archivo: %s".formatted(reader.getFileName()));
		System.out.println("------------------------------------------------------------------------------------");
		
		try (BufferedReader lectura = Files.newBufferedReader(reader)) {
			var line = "";
			while ((line = lectura.readLine()) != null) {
				try {
					var registro = AnalizadorLogs.procesarLog(line);
					var data_line = "IP: %s, Puerto: %s, Usuario: %s".formatted(registro.getIP(), registro.getPort(), registro.getUser());  
					String line_ok = data_line + System.lineSeparator();
					Files.write(writer_ok, line_ok.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (IllegalArgumentException exception) {
					System.out.println("Linea sospechosa, enviada a cuarentena: " + line);
					String line_warning = line + System.lineSeparator();
					Files.write(writer_warning, line_warning.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				}
			}
		} catch (IOException exception) {
			System.out.println("Error al abrir archivo");
			System.out.println("Detalle %s".formatted(exception.getMessage()));
		}		
		
	}

}
	
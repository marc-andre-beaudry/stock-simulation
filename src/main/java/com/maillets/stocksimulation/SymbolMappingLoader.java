package com.maillets.stocksimulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SymbolMappingLoader {

	public static List<String> load(String source) {
		List<String> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(SymbolMappingLoader.class.getResourceAsStream(source)))) {
			data = br.lines().collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data;
	}
}

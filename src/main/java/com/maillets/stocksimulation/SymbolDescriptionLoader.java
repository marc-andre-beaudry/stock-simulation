package com.maillets.stocksimulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SymbolDescriptionLoader {

	public static List<SymbolDescription> load(String source) {
		List<SymbolDescription> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(SymbolMappingLoader.class.getResourceAsStream(source)))) {
			data = br.lines().map(mapToSymbolDescription).collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data;
	}

	private static Function<String, SymbolDescription> mapToSymbolDescription = (line) -> {
		SymbolDescription data = new SymbolDescription();
		String[] splittedLine = line.split("|");
		data.setSymbol(splittedLine[0]);
		data.setDescription(splittedLine[1]);
		return data;
	};
}

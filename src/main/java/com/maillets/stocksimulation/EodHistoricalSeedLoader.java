package com.maillets.stocksimulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.maillets.stocksimulation.entities.EodHistoricalData;

public class EodHistoricalSeedLoader {

	public static List<EodHistoricalData> load(String source) {

		List<EodHistoricalData> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(EodHistoricalSeedLoader.class.getResourceAsStream(source)))) {
			data = br.lines().skip(1).map(mapToEodHistoricalDataFunction).filter(x -> x != null)
					.collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data;
	}

	private static Function<String, EodHistoricalData> mapToEodHistoricalDataFunction = (line) -> {
		EodHistoricalData data = new EodHistoricalData();
		try {
			String[] splittedLine = line.split(",");
			data.setDate(LocalDate.parse(splittedLine[0]));
			data.setOpen(Double.parseDouble(splittedLine[1]));
			data.setHigh(Double.parseDouble(splittedLine[2]));
			data.setLow(Double.parseDouble(splittedLine[3]));
			data.setClose(Double.parseDouble(splittedLine[4]));
			data.setVolume(Long.parseLong(splittedLine[5]));
			data.setAdjClose(Double.parseDouble(splittedLine[6]));
		} catch (Throwable ex) {
			data = null;
		}
		return data;
	};
}

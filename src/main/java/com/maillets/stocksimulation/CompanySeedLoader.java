package com.maillets.stocksimulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompanySeedLoader {

	public static List<Company> load(String source) {

		List<Company> companies = new ArrayList<Company>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(CompanySeedLoader.class.getResourceAsStream(source)))) {
			companies = br.lines().skip(1).map(mapToCompanyFunction).filter(x -> x != null)
					.collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return companies;
	}

	private static String regex = "\\$[1-9][0-9]*(\\.[0-9]*)?[BM]";

	private static Function<String, Company> mapToCompanyFunction = (line) -> {
		Company company = new Company();
		try {
			String[] splittedLine = line.split("\\|");
			company.setSymbol(splittedLine[0]);
			company.setName(splittedLine[1]);
			company.setLastSale(Double.parseDouble(splittedLine[2]));
			String marketCapStr = splittedLine[3];
			Double marketCap = 0d;
			
			if (marketCapStr.matches(regex)) {
				if (marketCapStr.charAt(marketCapStr.length() - 1) == 'M') {
					marketCapStr = marketCapStr.replaceAll("\\$", "").replaceAll("M", "");
					marketCap = Double.parseDouble(marketCapStr) * 1000000;
				} else if (marketCapStr.charAt(marketCapStr.length() - 1) == 'B') {
					marketCapStr = marketCapStr.replaceAll("\\$", "").replaceAll("B", "");
					marketCap = Double.parseDouble(marketCapStr) * 1000000000;
				}
			} else {
				// no market cap
			}
			company.setMarketCap(marketCap);
			company.setIpoYear(splittedLine[4]);
			company.setSector(splittedLine[5]);
			company.setIndustry(splittedLine[6]);
		} catch (Throwable ex) {
			company = null;
		}
		return company;
	};
}

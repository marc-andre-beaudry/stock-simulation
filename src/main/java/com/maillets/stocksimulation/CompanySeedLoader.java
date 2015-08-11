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
		try (BufferedReader br = new BufferedReader(new InputStreamReader(CompanySeedLoader.class.getResourceAsStream(source)))) {
			companies = br
					.lines()
					.skip(1)
					.map(mapToCompanyFunction)
					.filter(x -> x != null)
					.collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return companies;
	}

	private static Function<String, Company> mapToCompanyFunction = (line) -> {
		Company company = new Company();
		try {
			String[] splittedLine = line.split("\\|");
			company.setSymbol(splittedLine[0]);
			company.setName(splittedLine[1]);
			company.setLastSale(Double.parseDouble(splittedLine[2]));
			company.setMarketCap(splittedLine[3]);
			company.setIpoYear(splittedLine[4]);
			company.setSector(splittedLine[5]);
			company.setIndustry(splittedLine[6]);
		} catch (Throwable ex) {
			company = null;
		}
		return company;
	};
}

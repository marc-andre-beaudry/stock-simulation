package com.maillets.stocksimulation.identity;

public class AuthorizationModelImpl implements AuthorizationModel {

	private boolean isAuthenticated = false;

	public String getUserId() {
		if (!isAuthenticated) {
			isAuthenticated = true;
			return null;
		}

		return "beaudmar";
	}
}

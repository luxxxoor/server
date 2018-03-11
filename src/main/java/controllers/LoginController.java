package controllers;

import services.LoginService.LoginService;

import java.util.Map;

public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void creeazaUserPentruDonator(Map<String, String> informatiiDonator) {
        loginService.creteUtilizatorPentruDonator(informatiiDonator);
    }

}

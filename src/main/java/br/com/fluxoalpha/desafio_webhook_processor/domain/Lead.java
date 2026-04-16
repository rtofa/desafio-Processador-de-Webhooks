package br.com.fluxoalpha.desafio_webhook_processor.domain;

public class Lead {

    private String name;
    private String phoneNumber;
    private String email;
    private String campaign;
    private String status;

    public Lead(String name, String phoneNumber, String email, String campaign, String status) {
        this.name = nameValidation(name);
        this.phoneNumber = phoneNumberValidation(phoneNumber);
        this.email = emailValidation(email);
        this.campaign = campaign;
        this.status = status;
    }

    // validations
    private String emailValidation(String email){
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("O email não pode ser vazio!");
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
        return email;
    }

    private String phoneNumberValidation(String phoneNumber){
        if (phoneNumber == null || phoneNumber.length() < 10){
            throw new IllegalArgumentException("Telefone deve ter DDD e número.");
        }
        return phoneNumber;
    }

    private String nameValidation(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser vazio!");
        }
        return name;
    }
    // #############################################################

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumberValidation(phoneNumber);
    }

    public void setEmail(String email) {
        this.email = emailValidation(email);
    }

    public void setName(String name) {
        this.name = nameValidation(name);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getName() {
        return name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCampaign() {
        return campaign;
    }

    public String getStatus() {
        return status;
    }

}

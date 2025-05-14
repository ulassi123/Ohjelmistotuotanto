package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.controllers.LoginController;
import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static com.example.ohjelmistotuotanto.utils.Utilities.APP_KORKEUS;
import static com.example.ohjelmistotuotanto.utils.Utilities.APP_LEVEYS;


public class KirjautumisIkkuna {
    private Label userLabel = new Label("Käyttäjätunnus:");
    TextField userField = new TextField();
    Label passLabel = new Label("Salasana:");
    PasswordField passField = new PasswordField();
    Button loginButton = new Button("Kirjaudu");
    Label messageLabel = new Label("Väärä käyttäjätunnus\ntai salasana.");
    Button guestLogin = new Button("Kirjaudu vierailijana");
    ImageView naytalogo = new ImageView(new Image("parhainlogoikina.png"));
    Background taustakuva;
    Image taustakuvaImage = new Image("/mokki.jpg");

    public void show(){
        Scene kehys = luoKehys();
        kehys.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        new LoginController(this);
        IkkunanVaihto.vaihdaKehys(kehys);
    }

    private Scene luoKehys(){
        BorderPane kirjautumisIkkuna = new BorderPane();
        kirjautumisIkkuna.setPadding(new Insets(20,20,20,20));
        BackgroundSize taustakuvaKoko = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
         taustakuva = new Background(new BackgroundImage(taustakuvaImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, taustakuvaKoko));
        kirjautumisIkkuna.setBackground(taustakuva);

        GridPane mainContainerBox = new GridPane();

        mainContainerBox.add(userLabel, 0, 0);
        mainContainerBox.add(userField, 1, 0);
        mainContainerBox.add(passLabel, 0, 1);
        mainContainerBox.add(passField, 1, 1);
        mainContainerBox.add(loginButton, 0, 2);
        mainContainerBox.add(messageLabel, 1, 2);

        mainContainerBox.setId("login-grid");
        passLabel.setId("login-label");
        userLabel.setId("login-label");
        messageLabel.setId("login-error-label");
        userField.setId("custom-textfield");
        passField.setId("custom-textfield");
        loginButton.setId("custom-button");
        guestLogin.setId("custom-button");
        mainContainerBox.setHgap(20);
        mainContainerBox.setVgap(20);
        mainContainerBox.setMaxHeight(220);
        mainContainerBox.setMaxWidth(400);
        mainContainerBox.setAlignment(Pos.CENTER);
        userLabel.setPrefWidth(100);
        passLabel.setPrefWidth(100);
        messageLabel.setVisible(false);
        messageLabel.setPrefSize(200, 60);
        messageLabel.setAlignment(Pos.CENTER);

        naytalogo.setFitHeight(300);
        naytalogo.setFitWidth(300);

        kirjautumisIkkuna.setLeft(naytalogo);
        kirjautumisIkkuna.setBottom(guestLogin);
        kirjautumisIkkuna.setCenter(mainContainerBox);

        BorderPane.setAlignment(mainContainerBox, Pos.CENTER);
        BorderPane.setAlignment(naytalogo, Pos.CENTER);
        BorderPane.setAlignment(guestLogin, Pos.CENTER_RIGHT);

        return new Scene(kirjautumisIkkuna, APP_LEVEYS, APP_KORKEUS);
    }

    public Background getTaustakuva() {
        return taustakuva;
    }

    public void setTaustakuva(Background taustakuva) {
        this.taustakuva = taustakuva;
    }

    public ImageView getNaytalogo() {
        return naytalogo;
    }

    public void setNaytalogo(ImageView naytalogo) {
        this.naytalogo = naytalogo;
    }

    public Button getGuestLogin() {
        return guestLogin;
    }

    public void setGuestLogin(Button guestLogin) {
        this.guestLogin = guestLogin;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(Label messageLabel) {
        this.messageLabel = messageLabel;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public PasswordField getPassField() {
        return passField;
    }

    public void setPassField(PasswordField passField) {
        this.passField = passField;
    }

    public Label getPassLabel() {
        return passLabel;
    }

    public void setPassLabel(Label passLabel) {
        this.passLabel = passLabel;
    }

    public TextField getUserField() {
        return userField;
    }

    public void setUserField(TextField userField) {
        this.userField = userField;
    }

    public Label getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(Label userLabel) {
        this.userLabel = userLabel;
    }
}

package sistema.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Sessao {

    private static Sessao instancia;

    private Usuario usuarioLogado;
    private final ObjectProperty<Image> logoSistema = new SimpleObjectProperty<>();

    // Construtor privado impede que outras classes deem "new Sessao()"
    private Sessao() {}

    // Ponto global de acesso
    public static Sessao getInstancia() {
        if (instancia == null) {
            instancia = new Sessao();
        }
        return instancia;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Image getLogoSistema() {
        return logoSistema.get();
    }

    public void setLogoSistema(Image logo) {
        this.logoSistema.set(logo);
    }


    public ObjectProperty<Image> logoSistemaProperty() {
        return logoSistema;
    }
}